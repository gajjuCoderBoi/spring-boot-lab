package com.ga.musiclabboot.controller;

import com.ga.musiclabboot.Controller.UserController;
import com.ga.musiclabboot.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void signup() throws Exception {
        signup_User_Success();
        signup_User_Fail();
    }


    @Test
    public void login() throws Exception {
        login_User_Success();
        login_User_Fail();
    }


    @Test
    public void updateUser() throws Exception {
        update_User_Success();
        update_User_Fail();
    }


    @Test
    public void deleteUser() throws Exception {
        delete_User_Success();
        delete_User_Fail();
    }


    public void login_User_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("joe", "abc"));

        when(userService.login(any())).thenReturn("123456789");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"123456789\"}"))
                .andReturn();
    }
    public void login_User_Fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
    public void signup_User_Success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("joe","abc"));

        when(userService.signup(any())).thenReturn("123456");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"123456\"}"))
                .andReturn();
    }
    public void signup_User_Fail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
    public void update_User_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("joe", "abc"));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
    public void update_User_Fail() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
    public void delete_User_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
    public void delete_User_Fail() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/users/asd")
                .contentType(MediaType.APPLICATION_JSON)
                .content("");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
    private static String createUserInJson(String username, String password) {
        return "{ \"username\": \"" + username + "\", " +
                "\"password\":\"" + password + "\"}";
    }
}