package com.philosophy.lesson4.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDTO {

    private Integer Id;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("Price")
    private Integer Price;

    private Customer customer;

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public Integer getPrice() {
        return Price;
    }

    public Customer getCustomer() {
          return customer;
     }

    public void setId(Integer id) { this.Id = id; }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPrice(Integer Price) {
        this.Price = Price;
    }

     public void setCustomer(Customer customer) {
       this.customer = customer;
     }
}
