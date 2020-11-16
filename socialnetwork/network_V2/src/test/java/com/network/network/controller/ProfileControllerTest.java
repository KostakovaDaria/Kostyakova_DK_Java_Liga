package com.network.network.controller;

import com.network.network.convert.Convert;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Convert convert;

    /**
     * Тесты на метод GetDataProfile
     * @throws Exception
     */

    @Test
    @Transactional
    public void whenInputIsValidIdReturnsProfileStatus200() throws Exception
    {
        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestProfileNew1");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("TestRestFirst");
        profileRegistrationDTO.setLastname("TestRestLast");
        profileRegistrationDTO.setEmail("yandex@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        MvcResult result = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        String content = "/users/" +
                result.getResponse().getContentAsString().substring(1, 37);

        this.mockMvc.perform(get(content))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void whenInputIsInValidIdReturnsStatus400() throws Exception
    {
        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestProfileNew2");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("TestRestFirst");
        profileRegistrationDTO.setLastname("TestRestLast");
        profileRegistrationDTO.setEmail("yandex@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        MvcResult result = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        String content = "/profile/" +
                result.getResponse().getContentAsString().substring(1, 37)
                + "notexists";

        this.mockMvc.perform(get(content))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Тесты на метод UpdateProfile
     * @throws Exception
     */

    @Test
    @Transactional
    public void whenInputIsValidProfileReturnsStatus204() throws Exception
    {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestProfileNew3");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("TestRestFirst");
        profileRegistrationDTO.setLastname("TestRestLast");
        profileRegistrationDTO.setEmail("yandex@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        MvcResult result = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        profileRegistrationDTO.setFirstname("NewTestFirstName");
        profileRegistrationDTO.setLastname("NewTestLastName");
        profileRegistrationDTO.setEmail("yandex@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("F");

        reqestJson = convert.JsonObject(profileRegistrationDTO);

        String content = "/users/" +
                result.getResponse().getContentAsString().substring(1, 37);

        this.mockMvc.perform(put(content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Transactional
    public void whenInputIsInvalidProfileReturnsStatus400() throws Exception
    {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestProfileNew4");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("TestRestFirst");
        profileRegistrationDTO.setLastname("TestRestLast");
        profileRegistrationDTO.setEmail("yandex@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        MvcResult result = this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        profileRegistrationDTO.setFirstname("");
        profileRegistrationDTO.setLastname("");
        profileRegistrationDTO.setEmail("yandex@yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");

        reqestJson = convert.JsonObject(profileRegistrationDTO);

        String content = "/profile/" +
                result.getResponse().getContentAsString().substring(1, 37);

        this.mockMvc.perform(put(content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
