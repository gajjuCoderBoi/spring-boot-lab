package com.ga.musiclabboot.service;


import com.ga.musiclabboot.config.JwtUtil;
import com.ga.musiclabboot.model.User;
import com.ga.musiclabboot.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    PasswordEncoder bCryptPasswordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @Spy
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private User user;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void initializeDummyUser() {

        user.setUserId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
    }

    @Test
    public void signup() {
        signup_ReturnsJwt_Success();
    }


    @Test
    public void login() {
        login_ReturnsJwt_Success();
    }

    @Test
    public void updateUser() {
        User user =  new User();
        user.setPassword("123");
        when(userRepository.findById(this.user.getUserId())).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);

        assertEquals(this.user.getPassword(), userService.updateUser(this.user, 1L).getPassword());
    }

    @Test
    public void deleteUser() {
        doNothing().when(userRepository).delete(any());

    }


    public void signup_ReturnsJwt_Success() {
        String expectedToken = "12345";

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");

        String actualToken = userService.signup(user);

        assertEquals(actualToken, expectedToken);
    }


    public void login_ReturnsJwt_Success() {
        String expectedToken = "12345";

        when(userRepository.login(any(),any())).thenReturn(user);
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("robin");

        String actualToken = userService.login(user);
        assertEquals(actualToken, expectedToken);
    }


}