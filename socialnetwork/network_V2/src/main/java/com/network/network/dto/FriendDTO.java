package com.network.network.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.UUID;

@ApiModel(description = "Хранит UUID пользователя")
public class FriendDTO implements Serializable {

    @ApiModelProperty(notes = "Уникальный идентификатор пользователя")
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
