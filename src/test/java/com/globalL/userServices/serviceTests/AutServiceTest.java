package com.globalL.userServices.serviceTests;

import com.globalL.userServices.requests.LoginUsuarioDto;
import com.globalL.userServices.requests.RegistroUsuarioDto;
import com.globalL.userServices.requests.TelefonoDto;
import com.globalL.userServices.entities.Telefono;
import com.globalL.userServices.entities.User;
import com.globalL.userServices.repositories.TelefonoRepository;
import com.globalL.userServices.repositories.UserRepository;
import com.globalL.userServices.services.AutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutServiceTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        private TelefonoRepository telefonoRepository;

        @Mock
        private PasswordEncoder passwordEncoder;

        @Mock
        private AuthenticationManager authenticationManager;

        @InjectMocks
        private AutService autService;

        @Test
        void signup_ShouldReturnSavedUser_WhenInputIsValid() {
            // Arrange
            RegistroUsuarioDto registroUsuarioDto = new RegistroUsuarioDto(
                    "Test User",
                    "test@example.com",
                    "password",
                    List.of(new TelefonoDto(123456789, 1, 34))
            );

            User savedUser = User.builder()
                    .id(UUID.randomUUID())
                    .name("Test User")
                    .email("test@example.com")
                    .password("encodedPassword")
                    .created(new Date())
                    .lastLogin(new Date())
                    .isActiveU(true)
                    .build();

            when(passwordEncoder.encode(registroUsuarioDto.getPassword())).thenReturn("encodedPassword");
            when(userRepository.save(any(User.class))).thenReturn(savedUser);

            // Act
            User result = autService.signup(registroUsuarioDto);

            // Assert
            assertNotNull(result);
            assertEquals(savedUser.getName(), result.getName());
            assertEquals(savedUser.getEmail(), result.getEmail());
            assertEquals(savedUser.getPassword(), result.getPassword());
            verify(userRepository).save(any(User.class));
        }

        @Test
        void convTelDtoEntity_ShouldReturnDtoList_WhenPhonesAreSaved() {
            // Arrange
            User user = User.builder()
                    .id(UUID.randomUUID())
                    .name("Test User")
                    .build();

            List<TelefonoDto> telefonoDtoList = List.of(
                    new TelefonoDto(123456789, 1, 34)
            );

            Telefono savedTelefono = new Telefono();
            savedTelefono.setNumber(123456789);
            savedTelefono.setCityCode(1);
            savedTelefono.setCountryCode(34);
            savedTelefono.setUser(user.getId());

            // Act
            List<TelefonoDto> result = autService.convTelDtoEntity(telefonoDtoList, user);

            // Assert
            assertNotNull(result);
            assertEquals(telefonoDtoList.size(), result.size());
            verify(telefonoRepository).save(any(Telefono.class));
        }

        @Test
        void convertEntityToDto_ShouldReturnTelefonoDtoList_WhenUserHasPhones() {
            // Arrange
            UUID userId = UUID.randomUUID();
            User user = User.builder().id(userId).build();

            List<Telefono> telefonoList = List.of(
                    new Telefono(1, userId, 123456789,88, 34)
            );

            when(telefonoRepository.findByUser(userId)).thenReturn(telefonoList);

            // Act
            List<TelefonoDto> result = autService.convertEntityToDto(user);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(telefonoList.get(0).getNumber(), result.get(0).getNumber());
            verify(telefonoRepository).findByUser(userId);
        }

        @Test
        void authenticate_ShouldReturnUser_WhenCredentialsAreValid() {
            // Arrange
            LoginUsuarioDto loginUsuarioDto = new LoginUsuarioDto(
                    "test@example.com",
                    "password"
            );

            User user = User.builder()
                    .id(UUID.randomUUID())
                    .name("Test User")
                    .email("test@example.com")
                    .build();

            when(userRepository.findByEmail(loginUsuarioDto.getEmail())).thenReturn(Optional.of(user));

            User result = (User) autService.loadUserByUsername(loginUsuarioDto.getEmail());

            assertNotNull(result);
            assertEquals(user.getEmail(), result.getEmail());
            assertEquals(user.getName(), result.getName());
            verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
            verify(userRepository).findByEmail(loginUsuarioDto.getEmail());
        }
}
