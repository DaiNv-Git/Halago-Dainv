package com.example.halagodainv.dto.page;

import com.example.halagodainv.service.impl.InfluencerServiceImpl;
import com.example.halagodainv.until.ConvertString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

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
    private List<Integer> industryId;

    public PageDto(long id, String pageName, String phone, String link, String follower, String expense, String industry, Date created, String industryId) {
        this.id = id;
        this.pageName = pageName;
        this.phone = phone;
        this.link = link;
        this.follower = follower;
        this.expense = expense;
        this.industryId = ConvertString.parseStringToListOfIntegers(industryId);
        this.industry = industry;
        this.created = DateFormatUtils.format(created, "yyyy-MM-dd");
    }
}
