package com.example.halagodainv.dto.kol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KolAndRepresentDetailDto {
    List<RepresentativeMapEntity> representative = new ArrayList<>();
    List<KolMapEntity> kol = new ArrayList<>();
}
