package com.philosophy.lesson4.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    private Integer Id;

    @JsonProperty("Name_Customer")
    private String Name;

    @JsonProperty("Email_Address")
    private String Email_Address;

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail_Address() { return Email_Address; }

    public void setId(Integer id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail_Address(String email_Address) {
        Email_Address = email_Address;
    }
}
