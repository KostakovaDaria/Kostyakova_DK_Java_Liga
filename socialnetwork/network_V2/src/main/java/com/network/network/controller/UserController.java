package com.network.network.controller;

import com.network.network.dto.*;
import com.network.network.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j

    @Api(description = "Контроллер для работы с пользователями")

public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Логинирование
     */

    org.slf4j.Logger LogUser = org.slf4j.LoggerFactory.getLogger(UserController.class);

    /**
     * Регистрация в соц.сети
     *
     * @param registrationDomain
     * {UserDTO - логин и пароль пользователя
     * ProfileDTO - персональные сведения пользователя}
     * @return Id пользователя
     */

    @ApiOperation("Регистрация нового пользователя и создание профиля. " +
            "В случае если пользователь с указанным логином существует " +
            " вернется UUID = null и код 204 / данные не прошли валидацию" +
            "возвращается код 400. В успешном варианте возвращается код 201")
    @PostMapping("/registration")
    public ResponseEntity<UUID> Registration(@ApiParam("Должны быть указаны следующие данные: логин, пароль, персональная информация пользователя")
            @Valid @RequestBody RegistrationDomain registrationDomain) {
        LogUser.info("Регистрация нового пользователя login = {}", registrationDomain.getUserDTO().getUsername());
        UUID res = userService.Registration(registrationDomain);
        if ( res == null)
            return new ResponseEntity<>(null, HttpStatus.valueOf(204));
        else
            return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Вход в профиль
     * @param userDTO - логин и пароль пользователя
     * @return Id
     */

    @ApiOperation("Аутентификация существующего пользователя. В случае если указан несуществующий логин" +
            " или неверный пароль / данные не прошли валидацию возвращает код 400." +
            "В успешном варианте возвращается код 200")
    @PostMapping("/enter")
    public ResponseEntity<UUID> Enter(@ApiParam("Должны быть указаны логин и пароль")
            @Valid @RequestBody UserDTO userDTO) {
        LogUser.info("Вход в систему login = {}", userDTO.getUsername());
        UUID res = userService.Enter(userDTO);
        if ( res == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Изменить логин и пароль
     * @param userDTO  - логин и пароль пользователя
     * @return HttpStatus 204
     */

    @ApiOperation("Изменить логин и пароль. В случае если указан несуществующий Id " +
            "/ данные не прошли валиацию возвращается код 400. " +
            "В случае успеха возвращается 204")
    @PutMapping("/users/{Id}/loginAndPassword")
    public HttpStatus UpdateLoginAndPassword(@ApiParam("Id - уникальный идентификатор поьзователя." +
            "Должен быть указан.") @PathVariable UUID Id,
             @ApiParam("UserDTO - логин и пароль. Должны быть указаны новые логин и пароль")
             @Valid @RequestBody UserDTO userDTO) {
        LogUser.info("Смена логина и пароля Id = {}", Id);
        userService.UpdateLoginAndPassword(Id, userDTO);
        return HttpStatus.valueOf(204);
    }

    /**
     * Удалить пользователя
     * @param Id пользователя
     * @return HttpStatus 200
     */

    @ApiOperation("Удаление пользователя и его профиля. В случае указания несуществующего " +
            "или некорректного Id будет возвращен код 400. В успешном случае будет возвращен код 200")
    @DeleteMapping("/users/{Id}")
    public void Delete(@ApiParam("Id - уникальный идентификатор пользователя. " +
            "Должен быть указан")
            @PathVariable UUID Id) {
        LogUser.info("Пользователь и его профиль был удален Id={}", Id);
        userService.Delete(Id);
    }

}



