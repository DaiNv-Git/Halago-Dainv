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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@SuppressWarnings("deprecation")
@Slf4j
@RequiredArgsConstructor
public class InfluencerExcel {

    private static final int SHEET_ZERO = 0;
    private static final String FONT_NAME = "Calibri";
    private static final Short FONT_SIZE = 200;
    private static final int ROW_COUNT = 1;
    private Workbook workbook;
    private List<InfluencerExportExcelDto> datas;
    private String sourceFile;
    private final ResourceLoader resourceLoader;

    public void initializeData(List<InfluencerExportExcelDto> datas, String sourceFile) {
        this.datas = datas;
        this.sourceFile = sourceFile;
    }

    private void writeDataLines() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + sourceFile);
        InputStream inp = resource.getInputStream();
        workbook = WorkbookFactory.create(inp);
        Sheet sheet = workbook.getSheetAt(SHEET_ZERO);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeight(FONT_SIZE);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        int count = ROW_COUNT;
        for (InfluencerExportExcelDto item : datas) {
            Row row = sheet.createRow(count++);
            int columnCount = 0;
            createCell(row, columnCount++, count - 1, style);
            createCell(row, columnCount++, item.getName(), style);
            createCell(row, columnCount++, item.getSex(), style);
            createCell(row, columnCount++, item.getLink(), style);
            createCell(row, columnCount++, item.getFollower(), style);
            createCell(row, columnCount++, item.getExpense(), style);
            createCell(row, columnCount++, item.getAddress(), style);
            createCell(row, columnCount++, item.getIndustry(), style);
            createCell(row, columnCount, item.getClassify(), style);
        }
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
        org.apache.commons.io.output.ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
            workbook.close();
        }
        return bos.toByteArray();
    }
}
