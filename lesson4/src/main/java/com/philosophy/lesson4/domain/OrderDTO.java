package com.philosophy.lesson4.domain;

import java.io.Serializable;

/**
 * Клиенту в качесте ответа будет представлен Id
 */

public class OrderDTO implements Serializable {

    private Integer Id;

    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) { this.Id = id; }
}
