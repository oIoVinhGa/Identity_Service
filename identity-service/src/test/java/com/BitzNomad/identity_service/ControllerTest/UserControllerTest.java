package com.BitzNomad.identity_service.ControllerTest;

import com.BitzNomad.identity_service.DtoReponese.UserReponese;
import com.BitzNomad.identity_service.DtoRequest.UserCreateRequest;
import com.BitzNomad.identity_service.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    private UserCreateRequest request;
    private UserReponese userReponese;

    @BeforeEach
    void initData(){
    }

    @Test
    void CreateUser(){
        log.info("Create User");
    }
}
