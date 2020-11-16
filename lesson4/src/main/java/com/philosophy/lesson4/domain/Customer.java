package com.philosophy.lesson4.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.philosophy.lesson4.dao.Identified;

@JsonAutoDetect
public class Customer implements Identified<Integer> {

    @JsonProperty("Id_Customer")
    private Integer Id;

    private String Name;

    private String Email_Address;

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail_Address() { return Email_Address; }

    public void setId(Integer id) { Id = id; }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }
}
