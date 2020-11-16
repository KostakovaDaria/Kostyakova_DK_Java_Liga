package com.network.network.service;

import com.network.network.domain.Profile;
import com.network.network.dto.RegistrationDomain;
import com.network.network.domain.User;
import com.network.network.dto.UserDTO;
import com.network.network.repository.UserRepository;
import com.network.network.service.convert.DomainToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.*;
import java.util.UUID;

    @Service
    public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DomainToDto domainToDto;

    /**
     * Регистрация в соц.сети
     *
     * @param registrationDomain
     * {UserDTO - логин и пароль пользователя
     * ProfileDTO - персональные данные пользователя}
     * @return UUID пользователя
     */

    public UUID Registration(RegistrationDomain registrationDomain) {

        User user = userRepository.findByUsername(registrationDomain.getUserDTO().getUsername());

        if (user == null){

            user = userRepository.save(CreateUser(registrationDomain));
            return user.getId();
        }
        else
            return null;

    }

    /**
     * Вход в профиль
     *
     * @param userDTO - логин и пароль пользователя
     * @return UUID пользователя
     */

    @Transactional
    public UUID Enter(UserDTO userDTO) {

        DomainToDto domainToDto = new DomainToDto();

        User user_exist = userRepository.findByUsername(userDTO.getUsername());

        if (user_exist == null)
            return null;
        else if (domainToDto.encrypt(userDTO.getPassword()).equals(user_exist.getPassword()) != true)
            return null;
        else
            return user_exist.getId();
        }

    /**
     * Изменение логина и пароля
     *
     * @param Id - идентификатор пользователя
     * @param userDTO - логин и пароль пользователя
     * @return UUID пользователя
     */

    @Transactional
    public UUID UpdateLoginAndPassword(UUID Id, UserDTO userDTO) {

        User user = userRepository.getOne(Id);

            user.setUsername(userDTO.getUsername());
            user.setPassword(domainToDto.encrypt(userDTO.getPassword()));
            userRepository.save(user);
            return user.getId();

    }

    /**
    * Удалить пользователя и его профиль
    *
    * @param Id - идентификатор пользователя
    */

    @Transactional
    public void Delete(UUID Id) {

        userRepository.deleteById(Id);
    }

    /**
         * Создание пользователя
         * @param registrationDomain
         * {UserDTO - логин и пароль пользователя
         * ProfileDTO - персональные данные пользователя}
         * @return User
         */

    private User CreateUser(RegistrationDomain registrationDomain) {

         User user = domainToDto.fromUserDtoToUser(registrationDomain.getUserDTO());
         Profile profile = domainToDto.fromProfileDtoToProfile(registrationDomain.getProfileDTO());
         user.setProfile(profile);
         profile.setUser(user);

         return user;
    }

}
