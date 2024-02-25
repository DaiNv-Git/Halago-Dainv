package com.example.halagodainv.excel;

import com.example.halagodainv.dto.influcer.InfluencerExportExcelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InfluencerExcel {

    private static final int SHEET_ZERO = 0;
    private static final String FONT_NAME = "Calibri";
    private static final Short FONT_SIZE = 220;
    private static final int ROW_COUNT = 1;
    private Workbook workbook;
    private List<InfluencerExportExcelDto> exportAll;
    private String sourceFile;
    private final ResourceLoader resourceLoader;

    public void initializeData(List<InfluencerExportExcelDto> exportAll, String sourceFile) {
        this.exportAll = exportAll;
        this.sourceFile = sourceFile;
    }

    private void writeDataLines() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + sourceFile);
        InputStream inp = resource.getInputStream();
        workbook = WorkbookFactory.create(inp);
        Sheet sheetInfluencers = workbook.getSheetAt(SHEET_ZERO);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeight(FONT_SIZE);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        getData(exportAll, ROW_COUNT, sheetInfluencers, style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Byte) {
            cell.setCellValue((Byte) value);
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public byte[] export() throws IOException {
        writeDataLines();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (bos) {
            workbook.write(bos);
        } finally {
            workbook.close();
        }
        return bos.toByteArray();
    }

    public void getData(List<InfluencerExportExcelDto> data, int count, Sheet sheet, CellStyle style) {
        for (InfluencerExportExcelDto item : data) {
            Row row = sheet.createRow(count++);
            int columnCount = 0;
            createCell(row, columnCount++, count - 1, style);
            createCell(row, columnCount++, item.getId(), style);
            createCell(row, columnCount++, valueEmpty(item.getName()), style);
            createCell(row, columnCount++, valueEmpty(item.getDateOfBirth()), style);
            createCell(row, columnCount++, valueEmpty(item.getSex()), style);
            createCell(row, columnCount++, valueEmpty(item.getAddress()), style);
            createCell(row, columnCount++, valueEmpty(item.getIndustry()), style);
            createCell(row, columnCount++, valueEmpty(item.getClassify()), style);
            createCell(row, columnCount++, valueEmpty(item.getPhone()), style);
            createCell(row, columnCount++, valueEmpty(item.getLinkFacebook()), style);
            createCell(row, columnCount++, valueEmpty(item.getFollowerFacebook()), style);
            createCell(row, columnCount++, valueEmpty(item.getExpenseFacebook()), style);
            createCell(row, columnCount++, valueEmpty(item.getLinkInstagram()), style);
            createCell(row, columnCount++, valueEmpty(item.getFollowerInstagram()), style);
            createCell(row, columnCount++, valueEmpty(item.getExpenseInstagram()), style);
            createCell(row, columnCount++, valueEmpty(item.getLinkTiktok()), style);
            createCell(row, columnCount++, valueEmpty(item.getFollowerTiktok()), style);
            createCell(row, columnCount++, valueEmpty(item.getExpenseTiktok()), style);
            createCell(row, columnCount++, valueEmpty(item.getLinkYoutube()), style);
            createCell(row, columnCount++, valueEmpty(item.getFollowerYoutube()), style);
            createCell(row, columnCount, valueEmpty(item.getExpenseYoutube()), style);
        }
    }

    private static String valueEmpty(String value) {
        return value == null || value.equalsIgnoreCase("null") ? "" : value;
    }
}
