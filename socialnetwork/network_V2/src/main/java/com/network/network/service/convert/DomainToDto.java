package com.network.network.service.convert;

import com.network.network.domain.*;
import com.network.network.dto.*;
import com.network.network.service.CityService;
import com.network.network.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


    @Component
    public class DomainToDto {

    @Autowired
    private GenderService genderService;

    @Autowired
    private CityService cityService;

    /**
     * Преобразование ProfileDTO в Profile
     *
     * @param
     * @param profileDTO
     * @return
     */


    public Profile fromProfileDtoToProfile(ProfileDTO profileDTO)
    {
        Profile profile = new Profile();

        profile.setFirstname(profileDTO.getFirstname());
        profile.setLastname(profileDTO.getLastname());
        profile.setEmail(profileDTO.getEmail());

        profile.setCity(cityService.FindCity(
                profileDTO.getCountry(),
                profileDTO.getRegion(),
                profileDTO.getCity()));

        profile.setGender(genderService.findByName(profileDTO.getGender()));
        profile.setEmail(profileDTO.getEmail());
        profile.setBirthday(profileDTO.getBirthday());
        profile.setActive(true);

        return profile;

    }

    /**
     * Преобразование Profile в ProfileDTO
     * @param profile
     * @param profileDTO
     * @return
     */

    public ProfileDTO fromProfileToProfileDto(Profile profile, ProfileDTO profileDTO) {

        profileDTO.setFirstname(profile.getFirstname());
        profileDTO.setLastname(profile.getLastname());
        profileDTO.setBirthday(profile.getBirthday());
        profileDTO.setInterests(profile.getInterests());

        profileDTO.setCity(profile.getCity().getName());
        profileDTO.setRegion(profile.getCity().getRegion().getName());
        profileDTO.setCountry(profile.getCity().getRegion().getCountry().getName());

        profileDTO.setGender(profile.getGender().getGender());
        profileDTO.setEmail(profile.getEmail());

        return profileDTO;
    }

    /**
     * Преобразование UserDTO в User
     *
     * @param userDTO
     * @return
     */

    public User fromUserDtoToUser(UserDTO userDTO) {

       User user = new User(userDTO.getUsername(), encrypt(userDTO.getPassword()));
       return user;
    }

    /**
     * Шифрование пароля
     *
     * @param password
     * @return
     */

    public String encrypt(String password) {
        try {
            return new String(MessageDigest.getInstance("MD5").digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot encrypt password!");
        }
    }

    public ProfileByListDTO convertToProfileByListDTO(Profile profile) {
        ProfileByListDTO profileByListDTO = new ProfileByListDTO();
        profileByListDTO.setId(profile.getId());
        profileByListDTO.setFirstname(profile.getFirstname());
        profileByListDTO.setLastname(profile.getLastname());
        profileByListDTO.setActive(profile.getActive());

        return profileByListDTO;
    }

}
