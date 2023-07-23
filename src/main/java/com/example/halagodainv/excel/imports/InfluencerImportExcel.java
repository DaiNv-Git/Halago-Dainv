package com.example.halagodainv.excel.imports;

import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.*;
import com.example.halagodainv.repository.CityRepository;
import com.example.halagodainv.repository.ClassifyRepository;
import com.example.halagodainv.repository.IndustryRepository;
import com.example.halagodainv.repository.SexRepostory;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Component
public class InfluencerImportExcel {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SexRepostory sexRepostory;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private ClassifyRepository classifyRepository;

    @Autowired
    private CityRepository cityRepository;

    public void getSheetFileExcel(MultipartFile file) throws IOException, GeneralException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        List<InfluencerEntity> influencerEntities = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            influencerEntities.addAll(importExcel(file, workbook.getSheetIndex(workbook.getSheetAt(i))));
        }
    }

    public List<InfluencerEntity> importExcel(MultipartFile file, int sheetPage) throws GeneralException {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            List<InfluencerEntity> calendarEntities = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(sheetPage);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                InfluencerEntity calendar = new InfluencerEntity();
                Row row = sheet.getRow(i);
                if (row != null) {
                    InfluencerEntity influencer = new InfluencerEntity();
                    List<InfluencerDetailEntity> influencerDetailEntities = new ArrayList<>();
                    influencer.setInflucerName(row.getCell(i).getStringCellValue());
                    influencer.setEmail(row.getCell(i).getStringCellValue());
                    influencer.setPhone(row.getCell(i).getStringCellValue());
                    String sex = row.getCell(i).getStringCellValue();
                    if (Strings.isBlank(sex)) {
                        SexEntity entity = sexRepostory.findByName(sex);
                        if (entity != null) {
                            influencer.setSex(entity.getId());
                        }
                    }
                    influencer.setYearOld(row.getCell(i).getStringCellValue());
                    List<String> industry = parseStringToListStrings(row.getCell(i).getStringCellValue());
                    if (industry.size() > 0) {
                        List<IndustryEntity> industryEntities = industryRepository.findByIndustryNameIn(industry);
                        StringJoiner stringJoinerIndustry = new StringJoiner(",");
                        industryEntities.forEach(industryEntity -> {
                            stringJoinerIndustry.add(String.valueOf(industryEntity.getId()));
                        });
                        influencer.setIndustry(stringJoinerIndustry.toString());
                    }
                    List<String> classify = parseStringToListStrings(row.getCell(i).getStringCellValue());
                    if (classify.size() > 0) {
                        List<ClassifyEntity> classifyEntities = classifyRepository.findByNameIn(classify);
                        StringJoiner stringJoinerClassify = new StringJoiner(",");
                        classifyEntities.forEach(classifyEntity -> {
                            stringJoinerClassify.add(String.valueOf(classifyEntity.getId()));
                        });
                        influencer.setClassifyId(stringJoinerClassify.toString());
                    }
                    influencer.setBankId(row.getCell(i).getStringCellValue());
                    influencer.setAccountNumber(row.getCell(i).getStringCellValue());
                    String valueProvice = row.getCell(i).getStringCellValue();
                    Optional<CityEntity> cityEntity = cityRepository.findByCityName(valueProvice);
                    cityEntity.ifPresent(entity -> influencer.setProvinceId(entity.getCityId()));
                    influencer.setAddress(row.getCell(i).getStringCellValue());
                    influencer.setCreated(new Date());
//                    if (Boolean.TRUE.equals(request.getIsFacebook())) {
//                        InfluencerDetailEntity detailEntityFacebook = new InfluencerDetailEntity();
//                        detailEntityFacebook.setChannel("FACEBOOK".toUpperCase());
//                        detailEntityFacebook.setFollower(request.getFollowerFb());
//                        detailEntityFacebook.setExpense(request.getExpenseFb());
//                        detailEntityFacebook.setUrl(request.getLinkFb());
//                        detailEntityFacebook.setInfluId(influencer.getId());
//                        influencerDetailEntities.add(detailEntityFacebook);
//                    }
//                    if (Boolean.TRUE.equals(request.getIsTikTok())) {
//                        InfluencerDetailEntity detailEntityTikTok = new InfluencerDetailEntity();
//                        detailEntityTikTok.setChannel("TIKTOK".toUpperCase());
//                        detailEntityTikTok.setFollower(request.getFollowerTT());
//                        detailEntityTikTok.setExpense(request.getExpenseTT());
//                        detailEntityTikTok.setUrl(request.getLinkTT());
//                        detailEntityTikTok.setInfluId(influencer.getId());
//                        influencerDetailEntities.add(detailEntityTikTok);
//                    }
//                    if (Boolean.TRUE.equals(request.getIsYoutube())) {
//                        InfluencerDetailEntity detailEntityYoutube = new InfluencerDetailEntity();
//                        detailEntityYoutube.setChannel("YOUTUBE".toUpperCase());
//                        detailEntityYoutube.setFollower(request.getFollowerYT());
//                        detailEntityYoutube.setExpense(request.getExpenseYT());
//                        detailEntityYoutube.setUrl(String.valueOf(request.getLinkYT()));
//                        detailEntityYoutube.setInfluId(influencer.getId());
//                        influencerDetailEntities.add(detailEntityYoutube);
//                    }
//                    if (Boolean.TRUE.equals(request.getIsInstagram())) {
//                        InfluencerDetailEntity detailEntityInstagram = new InfluencerDetailEntity();
//                        detailEntityInstagram.setChannel("INSTAGRAM".toUpperCase());
//                        detailEntityInstagram.setFollower(request.getFollowerIns());
//                        detailEntityInstagram.setExpense(request.getExpenseIns());
//                        detailEntityInstagram.setUrl(String.valueOf(request.getLinkIns()));
//                        detailEntityInstagram.setInfluId(influencer.getId());
//                        influencerDetailEntities.add(detailEntityInstagram);
//                    }
                    influencer.setHistoryCreated(new Date());

                }
            }
            return calendarEntities;
        } catch (Exception ex) {
            throw new GeneralException(ex);
        }
    }


    //    public void batchInsertOrUpdateCalendars(List<InfluencerEntity> calendars) {
//        String sql = "IF EXISTS (SELECT * FROM Cladia_Demo.dbo.Calendars WHERE WorkDate = ?) " +
//                "UPDATE Cladia_Demo.dbo.Calendars SET IsWork = ? WHERE WorkDate = ? " +
//                "ELSE " +
//                "INSERT INTO Cladia_Demo.dbo.Calendars (WorkDate, IsWork) VALUES (?, ?)";
//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                CalendarEntity calendar = calendars.get(i);
//                ps.setDate(1, new java.sql.Date(calendar.getWorkDate().getTime()));
//                ps.setBoolean(2, calendar.getIsWork());
//                ps.setDate(3, new java.sql.Date(calendar.getWorkDate().getTime()));
//                ps.setDate(4, new java.sql.Date(calendar.getWorkDate().getTime()));
//                ps.setBoolean(5, calendar.getIsWork());
//            }
//            @Override
//            public int getBatchSize() {
//                return calendars.size();
//            }
//        });
//    }

    public static List<String> parseStringToListStrings(String input) {
        List<String> integerList = new ArrayList<>();
        String[] numberStrings = input.split(",");
        for (String numberString : numberStrings) {
            String number = numberString.trim();
            integerList.add(number);
        }
        return integerList;
    }
}
