package com.example.halagodainv.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@Table(name = "log_authen")
@AllArgsConstructor
@NoArgsConstructor
public class LogAuthenEntity {

    @Id
    private Long id;
    private int userId;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
