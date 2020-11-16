package com.network.network.repository;

import com.network.network.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Short>
{
    Gender findByGender(String gender);
}
