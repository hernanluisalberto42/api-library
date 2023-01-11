package com.collage.library.dto;

import com.collage.library.model.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long idUser;
    private String ci;
    private String email;
    private String password;
    private String area;

    public static UserDto from(User user){
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}
