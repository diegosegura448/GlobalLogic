package com.globalL.userServices.controllerTests;

import com.globalL.userServices.controllers.AutController;
import com.globalL.userServices.requests.LoginUsuarioDto;
import com.globalL.userServices.requests.RegistroUsuarioDto;
import com.globalL.userServices.entities.User;
import com.globalL.userServices.security.JwtUtil;
import com.globalL.userServices.security.SecurityConfiguration;
import com.globalL.userServices.services.AutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutController.class)
@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(classes={SecurityConfiguration.class,AutController.class})
public class AutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutService authenticationService;

    @MockBean
    private JwtUtil jwtService;

    @Test
    void testRegisterSuccess() throws Exception {

        String jsonInput = "{ " +
                "\"username\": \"newuser\", " +
                "\"password\": \"a2asfGfdfdf4\", " +
                "\"email\": \"email@example.com\", " +
                "\"phones\": [" +
                "   {\"number\": \"123456789\", \"cityCode\": \"1\", \"countryCode\": \"34\"}" +
                "]" +
                "}";


        when(authenticationService.signup(any(RegistroUsuarioDto.class)))
                .thenReturn(new User());

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterWithEmptyPhones() throws Exception {

        String jsonInput = "{ " +
                "\"username\": \"newuser\", " +
                "\"password\": \"a2asfGfdfdf4\", " +
                "\"email\": \"email@example.com\", " +
                "\"phones\": []" +
                "}";


        when(authenticationService.signup(any(RegistroUsuarioDto.class)))
                .thenReturn(new User());

        // Probar la petición de registro con un 'phones' vacío
        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterWithInvalidData() throws Exception {

        String jsonInput = "{ " +
                "\"username\": \"\", " +
                "\"password\": \"\", " +
                "\"email\": \"invalid-email\", " +
                "\"phones\": [" +
                "   {\"number\": \"invalid\", \"cityCode\": \"\", \"countryCode\": \"\"}" +
                "]" +
                "}";

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andExpect(status().isBadRequest());
    }
}
