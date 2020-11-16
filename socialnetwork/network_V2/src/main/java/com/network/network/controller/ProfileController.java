package com.network.network.controller;

import com.network.network.dto.ProfileDTO;
import com.network.network.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j

@Api(description = "Контроллер для работы с профилем пользователя")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /**
     * Логгирование
     */

    org.slf4j.Logger LogProfile = org.slf4j.LoggerFactory.getLogger(ProfileController.class);

    /**
     * Получить данные о профиле
     * @param Id пользователя
     * @return DTO профиля пользователя
     */


    @ApiOperation("Получить профиль пользователя. В случае указания несуществующего или некорректного Id" +
            "будет возвращен код 400. В успешном случае вернется код 200")
    @GetMapping("/{Id}")
    public ProfileDTO GetDataProfile(@ApiParam("Id - уникальный идентификатор пользователя. " +
            "Должен быть указан")
            @PathVariable UUID Id) {
        LogProfile.info("Поиск профиля пользователя по идентификатору Id={}", Id);
        return profileService.GetDataProfile(Id);
    }

    /**
     * Изменить профиль
     * @param profileDTO - новые персональные данные
     * @return Id
     */

    @ApiOperation("Обновить профиль пользователя. В случае если указан несуществуйщий или" +
            "некорректный Id / данные не прошли валидацию будет возвращен код 400." +
            "В случае успеха будет возращен код 204")
    @PutMapping("/{Id}")
    public HttpStatus UpdateProfile(@ApiParam("Id - уникальный идентификатор пользователя." +
            "Должен быть указан.") @PathVariable UUID Id,
            @ApiParam("ProfileDTO - персональные данные пользователя. Должны быть указаны")
            @Valid @RequestBody ProfileDTO profileDTO) {
        profileService.UpdateProfile(Id, profileDTO);
        return HttpStatus.valueOf(204);
    }
}

