package com.ga.musiclabboot.service;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.entity.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    PasswordEncoder bCryptPasswordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private User user;

    @InjectMocks
    private UserRole userRole;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void initializeDummyUser() {
        userRole.setName("ROLE_ADMIN");

        user.setUserId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
        user.setUserRole(userRole);
    }

    @Test
    public void signup() {
        signup_ReturnsJwt_Success();
        signup_UserNotFound_Error();
    }


    @Test
    public void login() {
        login_ReturnsJwt_Success();
        login_UserNotFound_Error();
    }

    @Test
    public void updateUser() {
        User user =  new User();
        user.setPassword("123");
        when(userDao.updateUser(any(), anyLong())).thenReturn(user);

        assertEquals(user.getPassword(), userService.updateUser(this.user, 1L).getPassword());
    }

    @Test
    public void deleteUser() {
        when(userDao.deleteUser(anyLong()).getUserId()).thenReturn(1L);

        assertEquals(java.util.Optional.of(1L), java.util.Optional.of(userService.deleteUser(1L)));
    }


    public void signup_ReturnsJwt_Success() {
        String expectedToken = "12345";

        when(userDao.signup(any())).thenReturn(user);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");

        String actualToken = userService.signup(user);

        assertEquals(actualToken, expectedToken);
    }


    public void signup_UserNotFound_Error() {
        user.setUserId(null);
        user.setUsername("batman");
        user.setPassword("robin");

        when(userDao.signup(any())).thenReturn(user);

        String token = userService.signup(user);

        assertNull(token);
    }


    public void login_ReturnsJwt_Success() {
        String expectedToken = "12345";

        when(userDao.login(any())).thenReturn(user);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("robin");

        String actualToken = userService.login(user);
        assertEquals(actualToken, expectedToken);
    }


    public void login_UserNotFound_Error(){
        user.setUserId(null);
        user.setUsername("batman");
        user.setPassword("robin");

        when(userDao.login(any())).thenReturn(user);

        String token = userService.login(user);

        assertNull(token);
    }
}