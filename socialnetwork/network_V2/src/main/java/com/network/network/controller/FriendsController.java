package com.network.network.controller;

import com.network.network.dto.FriendDTO;
import com.network.network.dto.ProfileByListDTO;
import com.network.network.service.FriendsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@Slf4j

@RequestMapping("/users")

@Api(description = "Контроллер для работы с друзьями пользователя")
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    org.slf4j.Logger LogFriend = org.slf4j.LoggerFactory.getLogger(FriendsController.class);

    /**
     * Отправить запрос на дружбу
     * @param Id пользователя которому отправляем запрос
     * @param friendDTO хранит Id пользователя отправившего запрос
     * @return HttpStatus 200
     */

    @ApiOperation("Отправить запрос на дружбу. В случае если были указаны несуществущие или " +
            " неккоректные Id будет возвращен код 400. В успешном случае вернется код 201")
    @PostMapping("/{Id}/friend")
    public HttpStatus AddFriends(@ApiParam("Id - уникальный идентификатор пользователя которому был отправлен" +
            " запрос на дружбу.") @PathVariable UUID Id,
            @ApiParam("friendDTO - хранит Id пользователя отправившего запрос")
            @RequestBody FriendDTO friendDTO) {
        LogFriend.info("Отправить запрос на дружбу", Id, friendDTO);
        friendsService.AddFriend(Id, friendDTO);
        return HttpStatus.CREATED;
    }

    /**
     * Принять запрос на дружбу
     * @param Id пользователя которому был отправлен запрос
     * @param friendDTO хранит Id пользователя отправившего запрос
     * @return HttpStatus 200
     */

    @ApiOperation("Подтвердить запрос на дружбу. В случае если были указаны несуществущие или " +
            " неккоректные Id будет возвращен код 400. В успешном случае вернется код 201")
    @PostMapping("/{Id}/friends/accept")
    public HttpStatus AcceptFriend(@ApiParam("Id - уникальный идентификатор пользователя которому был отправлен" +
            " запрос на дружбу. friendDTO - хранит Id пользователя отправившего запрос")
            @PathVariable UUID Id,
            @ApiParam("friendDTO - хранит Id пользователя отправившего запрос")
            @RequestBody FriendDTO friendDTO) {
        LogFriend.info("Потвердить запрос на дружбу", Id, friendDTO);
        friendsService.AcceptFriend(Id, friendDTO);
        return HttpStatus.CREATED;
    }

    /**
     * Удалить из друзей
     * @param User_Id - содержит идентификатор лица отправившего запрос на удаление
     * @param Friend_Id - идентификатор того, кого собираемся удалить
     * @return HttpStatus 200
     */

    @ApiOperation("Удалить из друзей. В случае если были указаны несуществущие или" +
            " неккоректные Id будет возвращен код 400. В успешном случае вернется код 200")
    @DeleteMapping("/{User_Id}/friends/{Friend_Id}/delete")
    public void DeleteFriend(@ApiParam("User_Id - уникальный идентификатор пользователя " +
            "инициировавшего удаление ") @PathVariable UUID User_Id,
            @ApiParam("Friend_Id - уникальный идентификатор пользователя (кого удаляем)")
            @PathVariable UUID Friend_Id) {
        LogFriend.info("Удалить из друзей", User_Id, Friend_Id);
        friendsService.DeleteFriend(User_Id, Friend_Id);
    }

    /**
     * Отклонить запрос на дружбу
     * @param User_Id пользователя которому был отправлен запрос
     * @param Friend_Id хранит Id пользователя отправившего запрос
     * @return HttpStatus 200
     */

    @ApiOperation("Отклонить запрос на дружбу. В случае если были указаны несуществущие или" +
            " неккоректные Id будет возвращен код 400. В успешном случае вернется код 200")
    @DeleteMapping("/{User_Id}/friends/{Friend_Id}/refuse")
    public void RefuseFriend(@ApiParam("User_Id - уникальный идентификатор пользователя " +
            "отправившего запрос ") @PathVariable UUID User_Id,
            @ApiParam("Friend_Id - уникальный идентификатор пользователя которому был отправлен " +
                    "запрос на дружбу")
            @PathVariable UUID Friend_Id) {
        LogFriend.info("Отклонить запрос на дружбу", User_Id, Friend_Id);
        friendsService.RefuseFriend(User_Id, Friend_Id);
    }

    /**
     * Получить всех друзей
     * @param Id идентификатор поьзователя
     * @return страница, содержащая список пользователей со статусом 'друг'
     */

    @ApiOperation("Получить список друзей пользователя. В случае если были указаны несуществущие " +
            "или неккоректные Id будет возвращен код 400. В успешном случае вернется код 200")
    @GetMapping("/{Id}/friends")
    public Page<ProfileByListDTO> findFriends(@ApiParam("Id - уникальный идентификатор пользователя." +
            " Должен быть указан")
            @PathVariable UUID Id) {
        return friendsService.findFriends(Id,true);
    }

    /**
     * Получить всех пользователей с запросом дружбы
     * @param Id идентификатор пользователя
     * @return страница, содержащая список пользователей со статусом 'запрос дружбы'
     */

    @ApiOperation("Получить список пользователей с запросом дружбы. В случае если были указаны " +
            "несуществущие или неккоректные Id будет возвращен код 400. " +
            "В успешном случае вернется код 200")
    @GetMapping("/{Id}/friends/request")
    public Page<ProfileByListDTO> findFriendsRequest(@ApiParam("Id - уникальный идентификатор пользователя." +
            " Должен быть указан")
            @PathVariable UUID Id) {
        return friendsService.findFriends(Id,false);
    }
}
