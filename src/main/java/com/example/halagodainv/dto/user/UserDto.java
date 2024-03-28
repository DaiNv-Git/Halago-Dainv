package com.example.halagodainv.dto.user;

import com.example.halagodainv.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String userName;
    private int roleId;
    private String role;
    private String phone;

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.userName = StringUtils.isEmpty(userEntity.getUserName()) ? "" : userEntity.getUserName();
        this.roleId = userEntity.getRoleId();
        this.role = role(userEntity.getRoleId());
        this.phone = StringUtils.isEmpty(userEntity.getPhone()) ? "" : userEntity.getPhone();
    }

    private static String role(int roleId) {
        switch (roleId) {
            case 1:
                return "Admin";
            case 2:
                return "Marketing";
            case 3:
                return "Project";
            case 4:
                return "InfuCampaign";
        }
        return "";
    }
}
