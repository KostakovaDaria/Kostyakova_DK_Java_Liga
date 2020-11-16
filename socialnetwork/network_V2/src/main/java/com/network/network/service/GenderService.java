package com.network.network.service;

import com.network.network.domain.Gender;
import com.network.network.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

        /**
         * Возвращает Gender (id) по указанным параметрам
         * @param gender - наименование пола
         * @return Gender (id)
         */

    public Gender findByName(String gender)
        {
            return genderRepository.findByGender(gender);
        }

}
