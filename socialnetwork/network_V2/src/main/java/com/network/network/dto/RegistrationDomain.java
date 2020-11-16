package com.network.network.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@ApiModel(description = "Хранит логин, пароль и персональные данные пользователя")
public class RegistrationDomain {

    @Valid
    @NotNull
    @ApiModelProperty(notes = "Хранит логин и пароль пользователя")
    UserDTO userDTO;

    @Valid
    @NotNull
    @ApiModelProperty(notes = "Хранит персональные данные пользователя")
    ProfileDTO profileDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ProfileDTO getProfileDTO() {
        return profileDTO;
    }

    public void setProfileDTO(ProfileDTO profileDTO) {
        this.profileDTO = profileDTO;
    }
}
