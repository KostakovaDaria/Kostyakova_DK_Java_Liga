package com.network.network.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

@ApiModel(description = "Содержит список пользователей")
public class ProfileByListDTO {

        /**
         * Идентификатор пользователя
         */
        @ApiModelProperty(notes = "Уникальный идентификатор пользователя")
        private UUID id;

        /**
         * Имя пользователя
         */
        @ApiModelProperty(notes = "Имя пользователя")
        private String firstname;

        /**
         * Фамилия пользователя
         */
        @ApiModelProperty(notes = "Фамилия пользователя")
        private String lastname;

        /**
         * Признак активности пользователя
         */
        @ApiModelProperty(notes = "Статус активности пользователя")
        private Boolean active;

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

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

        public Boolean getActive() {
                return active;
        }

        public void setActive(Boolean active) {
                this.active = active;
        }
}
