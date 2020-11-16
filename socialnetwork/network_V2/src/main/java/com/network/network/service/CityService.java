package com.network.network.service;

import com.network.network.domain.City;
import com.network.network.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service

public class CityService {

        @Autowired
        private CityRepository cityRepository;

        /**
         * Возращает City (id) по указанным параметрам
         * @param contry - название страны
         * @param region - название региона
         * @param city - название города
         * @return City (id)
         */

        public City FindCity(String contry, String region, String city)
        {
            return cityRepository.findByName(contry, region, city);
        }
}
