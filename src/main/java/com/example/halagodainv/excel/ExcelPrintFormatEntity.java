package com.example.halagodainv.excel;

import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXCEL_PRINT_FORMAT")
public class ExcelPrintFormatEntity {
    @Id
    @Column(name = "ModuleName", nullable = false)
    private String moduleName;

    @Column(name = "FileName")
    private String fileName;

    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "Data")
    @Lob
    private byte[] data;

    @Column(name = "CreAt")
    private Date editDate;

    @Column(name = "EditUser")
    private int editUser;

    public ExcelPrintFormatEntity(String moduleName, MultipartFile file, int userLoginId) throws IOException {
        this.moduleName = moduleName;
        this.fileName = file.getName();
        this.type = Files.getFileExtension(file.getOriginalFilename());
        this.data = file.getBytes();
        this.editDate = new Date();
        this.editUser = userLoginId;
    }
}
