package com.example.halagodainv.excel.imports;

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
public class DownFileImportExcel {
    private List<InfluencerExportExcelDto> datas;
    private String sourceFile;

    private Workbook workbook;
    private final ResourceLoader resourceLoader;

    public void initializeData(String sourceFile) {
        this.sourceFile = sourceFile;
    }
    private void writeDataLines() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + sourceFile);
        InputStream inp = resource.getInputStream();
        workbook = WorkbookFactory.create(inp);
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
