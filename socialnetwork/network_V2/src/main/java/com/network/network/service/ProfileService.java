package com.network.network.service;

import com.network.network.domain.*;
import com.network.network.dto.*;
import com.network.network.repository.*;
import com.network.network.service.convert.DomainToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.UUID;

@Service

public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    DomainToDto domainToDto;

    /**
     * Получить данные о профиле
     *
     * @param Id - идентификатор пользователя
     * @return ProfileDTO - персональные данные пользователя
     */

    @Transactional
    public ProfileDTO GetDataProfile(UUID Id) {

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO = domainToDto.fromProfileToProfileDto(profileRepository.getOne(Id), profileDTO);
        return profileDTO;
    }

    /**
     * Обновить профиль
     *
     * @param Id - идентификатор пользователя
     * @param profileDTO - персональные данные пользователя
     * @return UUID пользователя
     */

    @Transactional
    public void UpdateProfile(UUID Id, ProfileDTO profileDTO) {

        Profile profile = domainToDto.fromProfileDtoToProfile(profileDTO);
        profile.setId(Id);
        profileRepository.save(profile).getId();
    }

}

