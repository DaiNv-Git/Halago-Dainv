package com.example.halagodainv.request.homepage;

import com.example.halagodainv.model.AdvantageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvantageRequest {
    private String titleVN;
    private String contentVN;
    private String titleEN;
    private String contentEN;
}
