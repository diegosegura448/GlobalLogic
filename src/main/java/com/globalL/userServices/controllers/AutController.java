package com.globalL.userServices.controllers;

import com.globalL.userServices.requests.LoginUsuarioDto;
import com.globalL.userServices.requests.RegistroUsuarioDto;
import com.globalL.userServices.requests.TelefonoDto;
import com.globalL.userServices.entities.User;
import com.globalL.userServices.responses.LoginResponse;
import com.globalL.userServices.responses.RegistroResponse;
import com.globalL.userServices.security.JwtUtil;
import com.globalL.userServices.services.AutService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/auth")
@RestController
public class AutController {



    private final AutService authenticationService;

    public AutController( AutService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<RegistroResponse> register(@Valid @RequestBody RegistroUsuarioDto registerUsuarioDto) {

        RegistroResponse registeredUser = authenticationService.signup(registerUsuarioDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUsuarioDto loginUsuarioDto) {

        LoginResponse authenticatedUser = authenticationService.authenticate(loginUsuarioDto);

        return ResponseEntity.ok(authenticatedUser);
    }
}
