package com.globalL.userServices.services;

import com.globalL.userServices.requests.LoginUsuarioDto;
import com.globalL.userServices.requests.RegistroUsuarioDto;
import com.globalL.userServices.requests.TelefonoDto;
import com.globalL.userServices.entities.Telefono;
import com.globalL.userServices.entities.User;
import com.globalL.userServices.repositories.TelefonoRepository;
import com.globalL.userServices.repositories.UserRepository;
import com.globalL.userServices.responses.LoginResponse;
import com.globalL.userServices.responses.RegistroResponse;
import com.globalL.userServices.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutService  {
    private final UserRepository userRepository;

    private final TelefonoRepository telefonoRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtService;

    public AutService(
            UserRepository userRepository,
            TelefonoRepository telefonoRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.telefonoRepository = telefonoRepository;
        this.jwtService = jwtService;
    }

    public RegistroResponse signup(RegistroUsuarioDto input) {

        Optional<User> uDetails = userRepository.findByEmail(input.getEmail());
        if(!uDetails.isEmpty())
            throw new UsernameNotFoundException("User with email = " + input.getEmail()+" already exists");

        User user = new User();
            user.setName(input.getName());
            user.setEmail (input.getEmail());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setCreated(new Date());
            user.setLastLogin(new Date());
            user.setActiveU(true);

        userRepository.save(user);

        List<TelefonoDto> TelResponse = convTelDtoEntity(input.getPhones(), user);

        String jwtToken = jwtService.generateToken(input.getEmail());

        RegistroResponse registroResponse = new RegistroResponse();
        registroResponse.setToken(jwtToken);
        registroResponse.setId(user.getId());
        registroResponse.setName(user.getName());
        registroResponse.setEmail(user.getEmail());
        registroResponse.setPhones(TelResponse);
        registroResponse.setActive(user.isActiveU());
        registroResponse.setLastLogin(user.getLastLogin());
        registroResponse.setCreated(user.getCreated());

        return registroResponse;
    }

   public List<TelefonoDto> convTelDtoEntity(List<TelefonoDto> TelDto, User user){

        TelDto.forEach(TelDt ->{
            Telefono tel = new Telefono();
            tel.setUser(user.getId());
            tel.setNumber(TelDt.getNumber());
            tel.setCityCode(TelDt.getCityCode());
            tel.setCountryCode(TelDt.getCountryCode());

            telefonoRepository.save(tel);
        });

        return TelDto;
    }

    public List<TelefonoDto> convertEntityToDto(User user){
        List<Telefono> tel = telefonoRepository.findByUser(user.getId());
        List<TelefonoDto> telDto = new ArrayList<>();
        tel.forEach(T ->{
            TelefonoDto TDto = new TelefonoDto();
            TDto.setNumber(T.getNumber());
            TDto.setCityCode(T.getCityCode());
            TDto.setCountryCode(T.getCountryCode());

            telDto.add(TDto);
        });

        return telDto;
    }
    public LoginResponse authenticate(LoginUsuarioDto loginUsuarioDto) {

        Optional<User> uDetails = userRepository.findByEmail(loginUsuarioDto.getEmail());
        if(uDetails.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + loginUsuarioDto.getEmail());
        User user = uDetails.get();

        List<TelefonoDto> TelDto = convertEntityToDto(user);

        LoginResponse logResponse = new LoginResponse();
        logResponse.setId(user.getId());
        logResponse.setActive(user.isActiveU());
        logResponse.setName(user.getName());
        logResponse.setPassword(user.getPassword());
        logResponse.setCreated(user.getCreated());
        logResponse.setLastLogin(user.getLastLogin());
        logResponse.setPhones(TelDto);

        String jwtToken = jwtService.generateToken(user.getEmail());

        logResponse.setToken(jwtToken);

        return logResponse;
    }
}
