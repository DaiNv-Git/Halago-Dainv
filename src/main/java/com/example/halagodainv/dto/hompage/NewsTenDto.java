package com.example.halagodainv.dto.hompage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsTenDto {
    private String title;
    private String image;
    private String created;

    public NewsTenDto(String title, String image, Date created) {
        this.title = title;
        this.image = image;
        this.created = DateFormatUtils.format(created, "yyyy-MM-dd HH:ss aa");
    }
}