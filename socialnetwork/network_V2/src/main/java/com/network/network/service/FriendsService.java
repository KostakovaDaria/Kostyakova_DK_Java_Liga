package com.network.network.service;

import com.network.network.domain.CustomKey;
import com.network.network.domain.FriendsShip;
import com.network.network.domain.Profile;
import com.network.network.dto.FriendDTO;
import com.network.network.dto.ProfileByListDTO;
import com.network.network.repository.ProfileRepository;
import com.network.network.service.convert.DomainToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.UUID;

    @Service

    public class FriendsService {

    @Autowired
    private DomainToDto domainToDto;

    @Autowired
    private ProfileRepository profileRepository;

    /**
     * Отправить запрос на дружбу
     *
     * @param Id - идентификатор пользователя (получатель)
     * @param friendDTO - идентификатор пользователя (отправитель)
     */

    @Transactional
    public void AddFriend(UUID Id, FriendDTO friendDTO) {

       Profile user = profileRepository.getOne(Id);
       Profile friend = profileRepository.getOne(friendDTO.getUserId());

       FriendsShip friends = CreateFriend(friend, user);
       friends.setStatus(false);

       user.getUsers().add(friends);
       profileRepository.save(user);
    }

    /**
     * Подтвердить запрос на дружбу
     *
     * @param Id - идентификатор пользователя (получатель)
     * @param friendDTO - идентификатор пользователя (отправитель)
     */

    @Transactional
    public void AcceptFriend(UUID Id, FriendDTO friendDTO) {

        Profile user = profileRepository.getOne(Id);
        Profile friend = profileRepository.getOne(friendDTO.getUserId());

        FriendsShip friends = CreateFriend(friend, user);
        friends.setStatus(true);

        user.getUsers().add(friends);
        profileRepository.save(user);

    }

    /**
     * Отклонить запрос на дружбу
     *
     * @param Id - идентификатор пользователя (получатель)
     * @param Friend_Id - идентификатор пользователя (отправитель)
     */

    @Transactional
    public void RefuseFriend(UUID Id, UUID Friend_Id)
    {
        Profile user = profileRepository.getOne(Id);
        Profile friend = profileRepository.getOne(Friend_Id);

        FriendsShip friends = CreateFriend(user, friend);
        friends.setStatus(false);

        user.getUsers().remove(friends);
        profileRepository.save(user);

        friends = CreateFriend(friend, user);
        friends.setStatus(false);

        user.getUsers().remove(friends);
        profileRepository.save(user);
    }

    /**
     * Удалить из друзей
     *
     * @param User_Id - идентификатор пользователя (получатель / отправитель)
     * @param Friend_Id - идентификатор пользователя (получить / отправитель)
     */

    @Transactional
    public void DeleteFriend(UUID User_Id, UUID Friend_Id) {

        Profile user = profileRepository.getOne(User_Id);
        Profile friend = profileRepository.getOne(Friend_Id);

        FriendsShip friends = CreateFriend(user, friend);
        friends.setStatus(true);

        user.getUsers().remove(friends);
        friend.getUsers().remove(friends);

        friends = CreateFriend(friend, user);
        friends.setStatus(true);

        user.getUsers().remove(friends);
        friend.getUsers().remove(friends);

        profileRepository.save(user);
        profileRepository.save(friend);
    }

    /** Получить список пользователей
     *
     * @param Id - идентификатор пользователя
     * @param status - статус отношений ('запрос на дружбу' / 'друг')
     * @return список пользователей
     */

    @Transactional
    public Page<ProfileByListDTO> findFriends(UUID Id, Boolean status) {

        Pageable pageable = PageRequest.of(0, 5);

        return profileRepository
                .findAllByFriends(Id, status, pageable)
                .map(domainToDto::convertToProfileByListDTO);
    }

    /**
    * Создать дружеские отношения
    * @param User - профиль пользователя
    * @param Friend - профиль пользователя
    * @return FriendsShip
    */

    private FriendsShip CreateFriend(Profile User, Profile Friend) {

        CustomKey customKey = new CustomKey(User.getId(), Friend.getId());
        FriendsShip friendsShip = new FriendsShip(customKey, User, Friend);

        return friendsShip;
    }

}
