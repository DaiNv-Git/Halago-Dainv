package com.example.halagodainv.dto.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private long id;
    private String pageName;
    private String phone;
    private String link;
    private String follower;
    private String expense;
    private String industry;
    private String created;

    public PageDto(long id, String pageName, String phone, String link, String follower, String expense, String industry, Date created) {
        this.id = id;
        this.pageName = pageName;
        this.phone = phone;
        this.link = link;
        this.follower = follower;
        this.expense = expense;
        this.industry = industry;
        this.created = DateFormatUtils.format(created, "yyyy-MM-dd");
    }
}
