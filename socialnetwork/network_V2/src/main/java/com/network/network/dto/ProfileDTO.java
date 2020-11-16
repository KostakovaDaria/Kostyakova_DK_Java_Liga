package com.network.network.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApiModel(description = "Хранит персональные данные пользователя")
public class ProfileDTO implements Serializable {

    @NotBlank(message = "Необходимо указать Имя")
    @Size(min = 3, max = 50)
    @ApiModelProperty(notes = "Имя пользователя")
    private String firstname;

    @NotBlank(message = "Необходимо указать Фамилию")
    @Size(min = 3, max = 50)
    @ApiModelProperty(notes = "Фамилия пользователя")
    private String lastname;

    @NotBlank(message = "Необходимо указать Страну")
    @ApiModelProperty(notes = "Страна проживания")
    private String country;

    @NotBlank(message = "Необходимо указать Регион")
    @ApiModelProperty(notes = "Регион проживания")
    private String region;

    @NotBlank(message = "Необходимо указать Город")
    @ApiModelProperty(notes = "Город проживания")
    private String city;

    @NotBlank(message = "Необходимо указать Пол")
    @ApiModelProperty(notes = "Пол пользователя")
    private String gender;

    @NotBlank(message = "Необходимо указать Почту")
    @Email
    @ApiModelProperty(notes = "Email пользователя")
    private String email;

    @Past
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-M-d")
    @ApiModelProperty(notes = "Дата рождения пользователя")
    private LocalDate birthday;

    @Size(max = 2048)
    @ApiModelProperty(notes = "Интересы пользователя")
    private String interests;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {

       this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {

        if (birthday != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            this.birthday = LocalDate.parse(birthday.toString(), formatter);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
