package com.example.halagodainv.request.kolCeleb;

import com.example.halagodainv.dto.kol.KolMapEntity;
import com.example.halagodainv.dto.kol.RepresentativeMapEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KolCelebRequest {
    private List<RepresentativeMapEntity> representative;
    private List<KolMapEntity> kol;
}
