package com.network.network.controller;

import com.network.network.convert.Convert;
import com.network.network.dto.FriendDTO;
import com.network.network.dto.ProfileDTO;
import com.network.network.dto.RegistrationDomain;
import com.network.network.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import javax.transaction.Transactional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class FriendsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Convert convert;

    /**
     * Тесты на метод AddFriend
     * @throws Exception
     */

    @Test
    @Transactional
    public void whenInputValidIdAddFriendReturnStatus200() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestFriendNew1");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("Test");
        profileRegistrationDTO.setLastname("Test");
        profileRegistrationDTO.setEmail("ya@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        MvcResult friend1 = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        userDTO.setUsername("TestFriendNew2");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("Test");
        profileRegistrationDTO.setLastname("Test");
        profileRegistrationDTO.setEmail("ya@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        reqestJson = convert.JsonObject(registrationDomain);

        MvcResult friend2 = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        String friend1_content = "/users/" +
                friend1.getResponse().getContentAsString().substring(1, 37)
                + "/friend";

        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setUserId(UUID.fromString(friend2.getResponse().getContentAsString().substring(1, 37)));

        reqestJson = convert.JsonObject(friendDTO);

        this.mockMvc.perform(post(friend1_content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Тесты на метод AcceptFriend
     * @throws Exception
     */

    @Test
   // @Transactional
    public void whenInputValidIdAcceptFriendReturnStatus201() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestFriendNew3");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("Test");
        profileRegistrationDTO.setLastname("Test");
        profileRegistrationDTO.setEmail("ya@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        MvcResult friend1 = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        userDTO.setUsername("TestFriendNew4");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("Test");
        profileRegistrationDTO.setLastname("Test");
        profileRegistrationDTO.setEmail("ya@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        reqestJson = convert.JsonObject(registrationDomain);

        MvcResult friend2 = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        String friend1_content = "/users/" +
                friend1.getResponse().getContentAsString().substring(1, 37)
                + "/friend";

        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setUserId(UUID.fromString(friend2.getResponse().getContentAsString().substring(1, 37)));

        reqestJson = convert.JsonObject(friendDTO);

        this.mockMvc.perform(post(friend1_content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        friend1_content = "/users/" +
                friend1.getResponse().getContentAsString().substring(1, 37)
                + "/friends/accept";

        this.mockMvc.perform(post(friend1_content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Тесты на метод RefuseFriend
     * @throws Exception
     */

    @Test
    @Transactional
    public void whenInputValidIdRefuseFriendReturnStatus201() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestFriendNew5");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("Test");
        profileRegistrationDTO.setLastname("Test");
        profileRegistrationDTO.setEmail("ya@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        MvcResult friend1 = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        userDTO.setUsername("TestFriendNew6");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("Test");
        profileRegistrationDTO.setLastname("Test");
        profileRegistrationDTO.setEmail("ya@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        reqestJson = convert.JsonObject(registrationDomain);

        MvcResult friend2 = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        String friend1_content = "/users/" +
                friend1.getResponse().getContentAsString().substring(1, 37)
                + "/friend";

        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setUserId(UUID.fromString(friend2.getResponse().getContentAsString().substring(1, 37)));

        reqestJson = convert.JsonObject(friendDTO);

        this.mockMvc.perform(post(friend1_content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        String friend2_content = "/users/" +
                friend2.getResponse().getContentAsString().substring(1, 37)
                + "/friends/" + friend1.getResponse().getContentAsString().substring(1, 37)
                +"/delete";

        this.mockMvc.perform(delete(friend2_content))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Тесты на метод Delete
     * @throws Exception
     */

    @Test
   // @Transactional
    public void whenInputValidIdDeleteFriendReturnStatus200() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestFriendNew7");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("Test");
        profileRegistrationDTO.setLastname("Test");
        profileRegistrationDTO.setEmail("ya@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        MvcResult friend1 = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        userDTO.setUsername("TestFriendNew8");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("Test");
        profileRegistrationDTO.setLastname("Test");
        profileRegistrationDTO.setEmail("ya@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        reqestJson = convert.JsonObject(registrationDomain);

        MvcResult friend2 = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        String friend1_content = "/users/" +
                friend1.getResponse().getContentAsString().substring(1, 37)
                + "/friend";

        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setUserId(UUID.fromString(friend2.getResponse().getContentAsString().substring(1, 37)));

        reqestJson = convert.JsonObject(friendDTO);

        this.mockMvc.perform(post(friend1_content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        friend1_content = "/users/" +
                friend1.getResponse().getContentAsString().substring(1, 37)
                + "/friends/accept";

        this.mockMvc.perform(post(friend1_content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        String friend2_content = "/users/" +
                friend2.getResponse().getContentAsString().substring(1, 37)
                + "/friends/" + friend1.getResponse().getContentAsString().substring(1, 37)
                +"/delete";

        this.mockMvc.perform(delete(friend2_content))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
