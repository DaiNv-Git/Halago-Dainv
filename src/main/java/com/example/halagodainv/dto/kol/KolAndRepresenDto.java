package com.example.halagodainv.dto.kol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KolAndRepresenDto {
    private List<KolDetailDto> kol = new ArrayList<>();
    private List<RepresentativeDtos> representative = new ArrayList<>();
}
