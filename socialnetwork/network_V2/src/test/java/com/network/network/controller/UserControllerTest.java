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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Convert convert;

    /**
     * Тесты на метод Registration
     * @throws Exception
     */

    @Test
    @Transactional
    public void whenInputIsValidTheReturnsStatus201() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew1");
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

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
        .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty());

    }

    @Test
    @Transactional
    public void whenInputIsInvalidUserTheReturnsStatus400() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("T");
        userDTO.setPassword("P");

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

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void whenInputIsInvalidProfileTheReturnsStatus400() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRest");
        userDTO.setPassword("PasswordRest");

        profileRegistrationDTO.setFirstname("T");
        profileRegistrationDTO.setLastname("T");
        profileRegistrationDTO.setEmail("yandex.ru");
        profileRegistrationDTO.setCountry("Россия");
        profileRegistrationDTO.setRegion("Москва и Московская обл.");
        profileRegistrationDTO.setCity("Москва");
        profileRegistrationDTO.setGender("M");

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void whenInputIsEmptyUserAndProfileTheReturnsStatus400() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();

        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        registrationDomain.setUserDTO(userDTO);
        registrationDomain.setProfileDTO(profileRegistrationDTO);

        String reqestJson = convert.JsonObject(registrationDomain);

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void whenInputIsExistingLoginTheReturns204AndBodyNull() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew2");
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

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty());

       this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").doesNotExist());
    }

    /**
     * Тесты на метод Enter
     * @throws Exception
     */

    @Test
    @Transactional
    public void whenInputIsValidUserReturnsStatus200() throws Exception
    {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew3");
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

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty());

        userDTO.setUsername("TestRestNew3");
        userDTO.setPassword("PasswordRest");

        reqestJson = convert.JsonObject(userDTO);

        this.mockMvc.perform(post("/enter").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @Transactional
    public void whenInputIsInvalidUserReturnsStatus400() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew4");
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

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty());

        userDTO.setUsername("T");
        userDTO.setPassword("P");

        reqestJson = convert.JsonObject(userDTO);

        this.mockMvc.perform(post("/enter").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void whenInputIsNotExistsLoginReturnNull() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew5");
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

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty());

        userDTO.setUsername("UserNotCreated");
        userDTO.setPassword("PasswordRest");

        reqestJson = convert.JsonObject(userDTO);

        this.mockMvc.perform(post("/enter").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @Transactional
    public void whenInputIsNotCorrectPasswordReturnNull() throws Exception {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew6");
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

        this.mockMvc.perform(post("/registration").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$").isNotEmpty());

        userDTO.setUsername("TestRestNe6");
        userDTO.setPassword("PasswordNotCorrect");

        reqestJson = convert.JsonObject(userDTO);

        this.mockMvc.perform(post("/enter").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError()).andExpect(jsonPath("$").doesNotExist());
    }

    /**
     * Тесты на метод UpdateLoginAndPassword
     * @throws Exception
     */

    @Test
    @Transactional
    public void whenIdIsInvalidReturn404() throws Exception
    {
        UserDTO userDTO = new UserDTO();

        String reqestJson = convert.JsonObject(userDTO);

        this.mockMvc.perform(put("/users/e631b436-31df-41a1-not-exists/loginAndPassword").contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void whenInputIsInvalidLoginAndPasswordReturnsStatus400() throws Exception
    {

        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew7");
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
                .andExpect(status().is2xxSuccessful()).andReturn();

        String content = "/users/" +
                result.getResponse().getContentAsString().substring(1, 37)
                + "/loginAndPassword";

        userDTO.setUsername("T");
        userDTO.setPassword("P");

        reqestJson = convert.JsonObject(userDTO);

        this.mockMvc.perform(put(content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void whenInputIsValidValuesReturnsStatus204() throws Exception
    {
        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew8");
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
                .andExpect(status().is2xxSuccessful()).andReturn();

        String content = "/users/" +
                result.getResponse().getContentAsString().substring(1, 37)
                + "/loginAndPassword";

        userDTO.setUsername("TestRestNew9");
        userDTO.setPassword("PasswordRest");

        reqestJson = convert.JsonObject(userDTO);

        this.mockMvc.perform(put(content).contentType(convert.APPLICATION_JSON_UTF8)
                .content(reqestJson))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    /**
     * Тесты на метод Delete
     */

    @Test
   @Transactional
    public void whenInputInvalidIdReturnsStatus400() throws Exception
    {
        String content = "/users/Id_notexist";

        this.mockMvc.perform(delete(content))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Transactional
    public void whenInputIsValidIdReturnsStatus200() throws Exception
    {
        RegistrationDomain registrationDomain = new RegistrationDomain();
        UserDTO userDTO = new UserDTO();
        ProfileDTO profileRegistrationDTO = new ProfileDTO();

        userDTO.setUsername("TestRestNew10");
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
                .andExpect(status().is2xxSuccessful()).andReturn();

        String content = "/users/" +
                result.getResponse().getContentAsString().substring(1, 37);

        this.mockMvc.perform(delete(content))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
