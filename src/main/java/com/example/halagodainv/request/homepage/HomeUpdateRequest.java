package com.example.halagodainv.request.homepage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeUpdateRequest {
    private Long id;
    private String follow;
}
