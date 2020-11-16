package com.network.network.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel(description = "Хранит логи и пароль")
public class UserDTO implements Serializable {

    @NotBlank(message = "Необходимо указать логин")
    @Size(min = 6, max = 255)
    @ApiModelProperty(notes = "Логин пользователя")
    private String username;

    @NotBlank(message = "Необходимо указать пароль")
    @Size(min = 6, max = 255)
    @ApiModelProperty(notes = "Пароль пользователя")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
