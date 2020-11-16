package com.network.network.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(columnDefinition = "BIGINT")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Region> region = new HashSet<Region>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Region> getRegion() {
        return region;
    }

    public void setRegion(Set<Region> region) {
        this.region = region;
    }

}
