package com.network.network.repository;

import com.network.network.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CityRepository extends JpaRepository<City, Long>
{
    @Query(value = "SELECT city.id, city.name, city.region_id FROM city " +
            "INNER JOIN region r on city.region_id = r.id " +
            "INNER JOIN country c on r.country_id = c.id " +
            "WHERE (c.name = ?1 AND r.name = ?2 AND city.name = ?3)",
            nativeQuery = true)
    City findByName(String country, String region, String city);

}
