package com.network.network.service.filters;

import com.network.network.domain.Profile;
import com.network.network.service.specification.BaseSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static org.springframework.data.jpa.domain.Specification.where;

public class ProfileFilter implements Filter<Profile> {

    /**
     * Имя пользователя (like / equal)
     */
    private String firstname;

    /**
     * Фамилия пользователя (like / equal)
     */
    private String lastname;

    /**
     * Email пользователя (like / equal)
     */

    private String email;

    /**
     * Признак активности
     */
    private Boolean active;


    @Override
    public Specification<Profile> toSpecification() {
        return where(BaseSpecification.<Profile>like("fistname", firstname))
                .and(BaseSpecification.like("email", email))
                .and(BaseSpecification.equal("active", active));
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
