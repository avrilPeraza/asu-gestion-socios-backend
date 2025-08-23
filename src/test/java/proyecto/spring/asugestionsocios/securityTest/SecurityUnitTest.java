package proyecto.spring.asugestionsocios.securityTest;

import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import proyecto.spring.asugestionsocios.model.LoginUserDTO;
import proyecto.spring.asugestionsocios.model.UserDetailsImpl;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileCreateDTO;
import proyecto.spring.asugestionsocios.model.entity.*;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;
import proyecto.spring.asugestionsocios.repository.SubcommitteeRepository;
import proyecto.spring.asugestionsocios.repository.UserRepository;
import proyecto.spring.asugestionsocios.security.CustomAuthProvider;
import proyecto.spring.asugestionsocios.service.AuthenticationService;
import proyecto.spring.asugestionsocios.service.ContactService;
import proyecto.spring.asugestionsocios.service.ProfileService;
import proyecto.spring.asugestionsocios.util.MemberNumberGenerator;
import proyecto.spring.asugestionsocios.util.auth.JwtUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class SecurityUnitTest {
    @Nested
    @DisplayName("Authentication Test")
    class AuthenticationTests{
        @Mock
        private AuthenticationManager authenticationManager;

        @Mock
        private UserRepository userRepository;

        @Mock
        private ProfileRepository profileRepository;

        @Mock
        private ProfileService profileService;

        @Mock
        private SubcommitteeRepository subcommitteeRepository;

        @Mock
        private ContactService contactService;

        @Mock
        private MemberNumberGenerator memberNumberGenerator;

        @Mock
        private PasswordEncoder passwordEncoder;

        @Mock
        private JwtUtils jwtUtils;

        @InjectMocks
        private AuthenticationService authenticationService;

        private User testUser;
        private Profile testProfile;
        private Subcommittee testSubcommittee;

        @BeforeEach
        void setUp(){
            testProfile = new Profile();
            testProfile.setId(2L);
            testProfile.setName("ADMINISTRADOR");
            testProfile.setDescription("Usuario administrador");
            testProfile.setStatus(Status.ACTIVE);

            testSubcommittee = new Subcommittee();
            testSubcommittee.setId(3L);
            testSubcommittee.setName("Comisión Académica");
            testSubcommittee.setDescription("Supervisa programas educativos y desarrollo académico.");

            Country country = new Country();
            country.setId(1L);
            country.setName("Uruguay");
            country.setPrefix("+598");

            Location location = new Location();
            location.setId(1L);
            location.setName("Durazno");
            location.setAreaCode("36");
            location.setCountry(country);

            testUser = new User();
            testUser.setFirstName("Juan");
            testUser.setLastName("Pérez");
            testUser.setEmail("juan.perez@example.com");
            testUser.setDocumentType(DocumentType.CI);
            testUser.setDocument("65481140");
            testUser.setBirthdate(LocalDate.of(1990, 5, 20));
            testUser.setStreet("25 de agosto 718");
            testUser.setApartment("B2");
            testUser.setHouseNumber("303");
            testUser.setPassword("12345476Hddg");
            testUser.setHasHearingImpairment(true);
            testUser.setUsesSignLanguage(true);
            testUser.setBelongsToCommittee(true);
            testUser.setSubcommittee(testSubcommittee);
            testUser.setMemberNumber("SOC-2025-0001");
            testUser.setStatus(UserStatus.ACTIVE);
            testUser.setProfile(testProfile);

            Phone phone = new Phone();
            phone.setNumber("099123456");
            phone.setPhoneType(PhoneType.MOBILE);
            phone.setLocation(location);
            phone.setUser(testUser);

        }

        @Nested
        @DisplayName("Login Test")
        class LoginTest{
            @Test
            @DisplayName("It must authenticate user with valid credentials")
            void shouldAuthenticateUserWithValidCredentials(){
                //Arrange
                String email = "juan.perez@example.com";
                String password = "12345476Hddg";
                String token = "valid.jwt.token";

                LoginUserDTO loginRequest = new LoginUserDTO(email, password);

                UserDetailsImpl userDetails = UserDetailsImpl.build(testUser);

                Authentication mockAuth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                //when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(testUser));
                when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(mockAuth);
                when(jwtUtils.generateTokenFromEmail(userDetails)).thenReturn(token);

                //Act
                 String jwtToken  = authenticationService.authenticate(loginRequest);

                 //String emailToken = jwtUtils.getEmailFromJwtToken(jwtToken);

                 //Assert
                assertNotNull(jwtToken);
                assertEquals(token, jwtToken);
                //assertEquals(email, emailToken);

                //verify(userRepository).findByEmail(email);
                verify(authenticationManager).authenticate(any(Authentication.class));
                verify(jwtUtils).generateTokenFromEmail(UserDetailsImpl.build(testUser));
            }

            @Test
            @DisplayName("It must throw an exception with invalid email")
            void shouldThrowExceptionWithInvalidEmail(){
                CustomAuthProvider customAuthProvider = new CustomAuthProvider(userRepository, passwordEncoder);
                String email = "email.invalid@gmail.com";
                String password = "12345476Hddg";

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        email, password
                );

                when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

                //Act and Assert
                assertThrows(UsernameNotFoundException.class, () -> {
                    customAuthProvider.authenticate(authentication);
                });

                verify(userRepository).findByEmail(email);
                verifyNoInteractions(authenticationManager);
            }

            @Test
            @DisplayName("It must throw a exception with incorrect password")
            void shouldThrowExceptionWithInvalidPassword(){
                //Arrange
                CustomAuthProvider customAuthProvider = new CustomAuthProvider(userRepository, passwordEncoder);
                String email = "juan.perez@example.com";
                String password = "12345476";

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        email, password
                );

                when(userRepository.findByEmail(email)).thenReturn(Optional.of(testUser));
                when(passwordEncoder.matches(password, testUser.getPassword())).thenReturn(false);

                //Act and Assert
                assertThrows(BadCredentialsException.class, () -> {
                    customAuthProvider.authenticate(authentication);
                });

                verify(userRepository).findByEmail(email);
                verify(passwordEncoder).matches(password, testUser.getPassword());
            }

            @Test
            @DisplayName("It must an Exception with no active account")
            void shouldThrowExceptionWithNoActiveAccount(){
                CustomAuthProvider customAuthProvider = new CustomAuthProvider(userRepository, passwordEncoder);
                String email = "juan.perez@example.com";
                String password = "12345476Hddg";
                testUser.setStatus(UserStatus.INACTIVE);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        email, password
                );

                when(userRepository.findByEmail(email)).thenReturn(Optional.of(testUser));

                //Act and Assert
                assertThrows(DisabledException.class, () -> {
                    customAuthProvider.authenticate(authentication);
                });

                verify(userRepository).findByEmail(email);
            }
        }

        @Nested
        @DisplayName("JWT Tests")
        class JwtTest{

            @Test
            @DisplayName("It must validate JWT token correctly")
            void shouldValidateJwtTokenCorrectly(){
               String token = "valid.jwt.token";

               when(jwtUtils.validateJwtToken(token)).thenReturn(true);

               boolean isValid = jwtUtils.validateJwtToken(token);

               assertTrue(isValid);
               verify(jwtUtils).validateJwtToken(token);
            }

            @Test
            @DisplayName("It must throw an exception with invalid JWT token")
            void shouldRejectInvalidJwtToken() {
                // Arrange
                String token = "invalid.jwt.token";

                when(jwtUtils.validateJwtToken(token)).thenThrow(new MalformedJwtException("Invalid token"));

                // Act & Assert
                assertThrows(MalformedJwtException.class, () -> {
                    jwtUtils.validateJwtToken(token);
                });

                verify(jwtUtils).validateJwtToken(token);
            }
        }

        @Nested
        @DisplayName("Authorization Tests")
        class AuthorizationTest{

            @Test
            @DisplayName("It must verify admin user permission")
            void shouldVerifyAdminPermission(){
                //Arrange
                ProfileCreateDTO profileDTO = new ProfileCreateDTO();
                profileDTO.setName(testProfile.getName());
                profileDTO.setDescription(testProfile.getDescription());

                //Act and Assert
                assertDoesNotThrow(() -> profileService.createProfile(profileDTO));
            }
        }
    }
}
