package com.example.halagodainv.converter;

import com.example.halagodainv.model.LogAuthenEntity;
import com.example.halagodainv.model.UserEntity;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class LogConverter {

    public static LogAuthenEntity save(UserEntity userEntity, String accessToken, String refreshToken) {
        return LogAuthenEntity.builder()
                .userId(userEntity.getId())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
