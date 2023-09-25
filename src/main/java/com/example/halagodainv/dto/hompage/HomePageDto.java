package com.example.halagodainv.dto.hompage;

import com.example.halagodainv.model.viewdisplayentity.HomepageEntitty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageDto {
    List<HomePageDetail> follows;
    List<NewsTenDto> halagoProject;

}
