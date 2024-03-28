package com.example.halagodainv.dto.group;

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
public class GroupDto {
    private long id;
    private String groupName;
    private String phone;
    private String link;
    private String memberTotal;
    private String expense;
    private String created;
    private String industry;
    private List<Integer> industryId;

    public GroupDto(long id, String groupName, String phone, String link, String memberTotal, String expense, Date created,String industry, String industryId) {
        this.id = id;
        this.groupName = groupName;
        this.phone = phone;
        this.link = link;
        this.memberTotal = memberTotal;
        this.expense = expense;
        this.created = DateFormatUtils.format(created, "dd-MM-yyyy");
        this.industryId = ConvertString.parseStringToListOfIntegers(industryId);
        this.industry = industry;
    }
}
