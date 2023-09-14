package com.example.halagodainv.excel.imports;

import com.example.halagodainv.exception.GeneralException;
import com.example.halagodainv.model.*;
import com.example.halagodainv.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Component
public class InfluencerImportExcel {
    @Autowired
    private SexRepostory sexRepostory;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private ClassifyRepository classifyRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private InfluencerEntityRepository influencerEntityRepository;
    @Autowired
    private InfluencerDetailRepository influencerDetailRepository;

    public void ImportFileExcel(MultipartFile file) throws IOException, GeneralException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        List<InfluencerDetailEntity> influencerDetailEntities = new ArrayList<>();
        influencerDetailRepository.saveAll(importExcel(file, influencerDetailEntities, workbook.getSheetIndex(workbook.getSheetAt(0))));
    }

    public List<InfluencerDetailEntity> importExcel(MultipartFile file, List<InfluencerDetailEntity> influencerDetailEntities, int sheetPage) throws GeneralException {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(sheetPage);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    InfluencerEntity influencer = new InfluencerEntity();
                    influencer.setInflucerName(isString(row.getCell(0)));
                    influencer.setEmail(isString(row.getCell(1)));
                    influencer.setPhone(isString(row.getCell(2)));
                    String sex = isString(row.getCell(3));
                    if (!StringUtils.isBlank(sex)) {
                        SexEntity entity = sexRepostory.findByName(sex);
                        if (entity != null) {
                            influencer.setSex(entity.getId());
                        }
                    }
                    influencer.setYearOld(isString(row.getCell(4)));
                    List<String> industry = parseStringToListStrings(isString(row.getCell(5)));
                    if (industry.size() > 0) {
                        List<IndustryEntity> industryEntities = industryRepository.findByIndustryNameIn(industry);
                        StringJoiner stringJoinerIndustry = new StringJoiner(",");
                        if (!CollectionUtils.isEmpty(industryEntities)) {
                            industryEntities.forEach(industryEntity -> {
                                stringJoinerIndustry.add(String.valueOf(industryEntity.getId()));
                            });
                            influencer.setIndustry(stringJoinerIndustry.toString());
                            influencer.setIndustryName(isString(row.getCell(5)));
                        }
                    }
                    List<String> classify = parseStringToListStrings(isString(row.getCell(6)));
                    if (classify.size() > 0) {
                        List<ClassifyEntity> classifyEntities = classifyRepository.findByNameIn(classify);
                        if (!CollectionUtils.isEmpty(classifyEntities)) {
                            StringJoiner stringJoinerClassify = new StringJoiner(",");
                            classifyEntities.forEach(classifyEntity -> {
                                stringJoinerClassify.add(String.valueOf(classifyEntity.getId()));
                            });
                            influencer.setClassifyId(stringJoinerClassify.toString());
                            influencer.setClassifyName(isString(row.getCell(6)));
                        }
                    }
                    influencer.setBankId(isString(row.getCell(7)));
                    influencer.setAccountNumber(isString(row.getCell(8)));
                    String valueProvice = isString(row.getCell(9));
                    Optional<CityEntity> cityEntity = cityRepository.findByCityName(valueProvice);
                    cityEntity.ifPresent(entity -> influencer.setProvinceId(entity.getCityId()));
                    influencer.setAddress(isString(row.getCell(10)));
                    influencer.setCreated(new Date());
                    influencer.setHistoryCreated(new Date());
                    InfluencerDetailEntity influencerDetailFB = new InfluencerDetailEntity();
                    InfluencerDetailEntity influencerDetailTT = new InfluencerDetailEntity();
                    InfluencerDetailEntity influencerDetailYT = new InfluencerDetailEntity();
                    InfluencerDetailEntity influencerDetailIns = new InfluencerDetailEntity();
                    if (row.getCell(11) != null || (row.getCell(12) != null || row.getCell(13) != null)) {
                        influencer.setFacebook(true);
                        influencerDetailFB.setUrl(isString(row.getCell(11)));
                        influencerDetailFB.setFollower(isString(row.getCell(12)));
                        influencerDetailFB.setExpense(isString(row.getCell(13)));
                        influencerDetailFB.setChannel("facebook".toUpperCase());
                    }
                    if (row.getCell(14) != null || row.getCell(15) != null || row.getCell(16) != null) {
                        influencer.setTiktok(true);
                        influencerDetailTT.setUrl(isString(row.getCell(14)));
                        influencerDetailTT.setFollower(isString(row.getCell(15)));
                        influencerDetailTT.setExpense(isString(row.getCell(16)));
                        influencerDetailTT.setChannel("tiktok".toUpperCase());
                    }
                    if (row.getCell(17) != null || row.getCell(18) != null || row.getCell(19) != null) {
                        influencer.setYoutube(true);
                        influencerDetailYT.setUrl(isString(row.getCell(17)));
                        influencerDetailYT.setFollower(isString(row.getCell(18)));
                        influencerDetailYT.setExpense(isString(row.getCell(19)));
                        influencerDetailYT.setChannel("youtube".toUpperCase());
                    }
                    if (row.getCell(20) != null || row.getCell(21) != null || row.getCell(22) != null) {
                        influencer.setInstagram(true);
                        influencerDetailIns.setUrl(isString(row.getCell(20)));
                        influencerDetailIns.setFollower(isString(row.getCell(21)));
                        influencerDetailIns.setExpense(isString(row.getCell(12)));
                        influencerDetailIns.setChannel("instagram".toUpperCase());
                    }
                    influencerEntityRepository.save(influencer);

                    influencerDetailFB.setInfluId(influencer.getId());
                    influencerDetailEntities.add(influencerDetailFB);

                    influencerDetailTT.setInfluId(influencer.getId());
                    influencerDetailEntities.add(influencerDetailTT);

                    influencerDetailYT.setInfluId(influencer.getId());
                    influencerDetailEntities.add(influencerDetailYT);

                    influencerDetailIns.setInfluId(influencer.getId());
                    influencerDetailEntities.add(influencerDetailIns);

                }
            }
            return influencerDetailEntities;
        } catch (Exception ex) {
            throw new GeneralException(ex);
        }
    }

    private static String isString(Cell cell) {
        String value = "";
        if (cell != null) {
            value = cell.getStringCellValue();
        }
        return value;
    }

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
