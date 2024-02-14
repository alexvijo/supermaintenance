package com.w2m.supermaintenance.controller;

import com.w2m.supermaintenance.models.User;
import com.w2m.supermaintenance.payload.request.LoginRequest;
import com.w2m.supermaintenance.payload.request.SignupRequest;
import com.w2m.supermaintenance.repository.UserRepository;
import com.w2m.supermaintenance.security.jwt.JwtUtils;
import com.w2m.supermaintenance.security.services.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    @MockBean
    private JwtUtils jwtUtils;

    private LoginRequest loginRequest;
    private SignupRequest signupRequest;

    @BeforeEach
    public void setUp() {
        loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPassword");

        signupRequest = new SignupRequest();
        signupRequest.setUsername("testUser");
        signupRequest.setPassword("testPassword");
    }

    @Test
    public void testAuthenticateUser() throws Exception {
        User user = new User();
        user.setUsername("Superman");
        when(userRepository.findByUsername("Superman")).thenReturn(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(
            UserDetailsImpl.build(user),
            null
        );
        
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(jwtUtils.generateJwtToken(any())).thenReturn("testToken");

        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testUser\",\"password\":\"testPassword\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterUser() throws Exception {
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(encoder.encode(any())).thenReturn("encodedPassword");

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testUser\",\"password\":\"testPassword\"}"))
                .andExpect(status().isOk());
    }
}
