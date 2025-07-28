package proyecto.spring.asugestionsocios.UserTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proyecto.spring.asugestionsocios.mapper.UserMapper;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.*;
import proyecto.spring.asugestionsocios.model.entity.*;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;
import proyecto.spring.asugestionsocios.repository.SubcommitteeRepository;
import proyecto.spring.asugestionsocios.repository.UserRepository;
import proyecto.spring.asugestionsocios.service.PhoneService;
import proyecto.spring.asugestionsocios.service.UserService;
import proyecto.spring.asugestionsocios.util.MemberNumberGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    //Injecciones de dependecia necesaria
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private SubcommitteeRepository subcommitteeRepository;
    @Mock
    private MemberNumberGenerator memberNumberGenerator;
    @Mock
    private PhoneService phoneService;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldCreateUserSuccessfully(){
        //Organizar
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setFirstName("Juan");
        userCreateDTO.setLastName("Pérez");
        userCreateDTO.setEmail("juan.perez@example.com");
        userCreateDTO.setDocumentType(DocumentType.CI);
        userCreateDTO.setDocument("65481140");
        userCreateDTO.setBirthdate(LocalDate.of(1990, 5, 20));
        userCreateDTO.setStreet("25 de agosto 718");
        userCreateDTO.setApartment("B2");
        userCreateDTO.setHouseNumber("303");
        userCreateDTO.setPassword("12345476Hddg");

        PhoneRequestDTO phone = new PhoneRequestDTO();
        phone.setNumber("099123456");
        phone.setPhoneType(PhoneType.MOBILE);
        phone.setLocationId(10L);

        userCreateDTO.setPhones(List.of(phone));
        userCreateDTO.setProfileId(1L);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setName("ADMINISTRADOR");
        profile.setDescription("Usuario administrador");
        profile.setStatus(Status.ACTIVE);

        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Actuar
        userService.createUser(userCreateDTO);

        //Afirmar
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals("juan.perez@example.com", savedUser.getEmail());
        assertEquals("65481140", savedUser.getDocument());
        assertEquals(UserStatus.UNVALIDATED, savedUser.getStatus());
    }

    @Test
    public void shouldCreateUserMemberSuccessfully(){
        //Organizar
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setFirstName("Juan");
        userCreateDTO.setLastName("Pérez");
        userCreateDTO.setEmail("juan.perez@example.com");
        userCreateDTO.setDocumentType(DocumentType.CI);
        userCreateDTO.setDocument("65481140");
        userCreateDTO.setBirthdate(LocalDate.of(1990, 5, 20));
        userCreateDTO.setStreet("25 de agosto 718");
        userCreateDTO.setApartment("B2");
        userCreateDTO.setHouseNumber("303");
        userCreateDTO.setPassword("12345476Hddg");
        userCreateDTO.setHasHearingImpairment(true);
        userCreateDTO.setUsesSignLanguage(true);
        userCreateDTO.setBelongsToCommittee(true);
        userCreateDTO.setSubcommitteeId(3L);

        PhoneRequestDTO phone = new PhoneRequestDTO();
        phone.setNumber("099123456");
        phone.setPhoneType(PhoneType.MOBILE);
        phone.setLocationId(13L);

        userCreateDTO.setPhones(List.of(phone));
        userCreateDTO.setProfileId(2L);

        Profile profile = new Profile();
        profile.setId(2L);
        profile.setName("SOCIO");
        profile.setDescription("Usuario socio");
        profile.setStatus(Status.ACTIVE);

        Subcommittee subcommittee = new Subcommittee();
        subcommittee.setId(3L);
        subcommittee.setName("Comisión Académica");
        subcommittee.setDescription("Supervisa programas educativos y desarrollo académico.");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        when(profileRepository.findById(2L)).thenReturn(Optional.of(profile));
        when(subcommitteeRepository.findById(3L)).thenReturn(Optional.of(subcommittee));
        when(memberNumberGenerator.memberNumberGenerator(2L)).thenReturn("SOC-2025-0001");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //Actuar
        userService.createUser(userCreateDTO);

        //Afirmar
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals("juan.perez@example.com", savedUser.getEmail());
        assertEquals("65481140", savedUser.getDocument());
        assertEquals(UserStatus.UNVALIDATED, savedUser.getStatus());
        assertEquals("SOC-2025-0001", savedUser.getMemberNumber());
    }


    @Test
    public void shouldGetAllUser(){
        //organizar

        //perfiles
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setName("SOCIO");
        profile.setDescription("Usuario socio");
        profile.setStatus(Status.ACTIVE);

        Profile profile2 = new Profile();
        profile2.setId(2L);
        profile2.setName("ADMINISTRADOR");
        profile2.setDescription("Usuario administrador");
        profile2.setStatus(Status.ACTIVE);

        //subcommittee
        Subcommittee subcommittee = new Subcommittee();
        subcommittee.setId(3L);
        subcommittee.setName("Comisión Académica");
        subcommittee.setDescription("Supervisa programas educativos y desarrollo académico.");

        Country country = new Country();
        country.setId(1L);
        country.setName("Uruguay");
        country.setPrefix("+598");

        Location location = new Location();
        location.setId(1L);
        location.setName("Durazno");
        location.setAreaCode("36");
        location.setCountry(country);

        User user1 = new User();
        user1.setFirstName("Juan");
        user1.setLastName("Pérez");
        user1.setEmail("juan.perez@example.com");
        user1.setDocumentType(DocumentType.CI);
        user1.setDocument("65481140");
        user1.setBirthdate(LocalDate.of(1990, 5, 20));
        user1.setStreet("25 de agosto 718");
        user1.setApartment("B2");
        user1.setHouseNumber("303");
        user1.setPassword("12345476Hddg");
        user1.setHasHearingImpairment(true);
        user1.setUsesSignLanguage(true);
        user1.setBelongsToCommittee(true);
        user1.setSubcommittee(subcommittee);
        user1.setMemberNumber("SOC-2025-0001");
        user1.setStatus(UserStatus.ACTIVE);
        user1.setProfile(profile);

        Phone phone = new Phone();
        phone.setNumber("099123456");
        phone.setPhoneType(PhoneType.MOBILE);
        phone.setLocation(location);
        phone.setUser(user1);

        User user2 = new User();
        user2.setFirstName("Andres");
        user2.setLastName("Gomez");
        user2.setEmail("andres.gomez@example.com");
        user2.setDocumentType(DocumentType.PASSPORT);
        user2.setDocument("A0987626");
        user2.setBirthdate(LocalDate.of(2000, 12, 20));
        user2.setStreet("25 de agosto 718");
        user2.setApartment("B3");
        user2.setHouseNumber("103");
        user2.setPassword("12345476Hddg");
        user2.setStatus(UserStatus.ACTIVE);
        user2.setProfile(profile2);

        Phone phone2 = new Phone();
        phone2.setNumber("099123876");
        phone2.setPhoneType(PhoneType.MOBILE);
        phone2.setLocation(location);
        phone2.setUser(user2);


        List<User> users = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        ProfileDTO profileDTO1 = new ProfileDTO();
        profileDTO1.setId(1L);
        profileDTO1.setName("SOCIO");
        profileDTO1.setDescription("Usuario socio");
        profileDTO1.setStatus(Status.ACTIVE);

        ProfileDTO profileDTO2 = new ProfileDTO();
        profileDTO2.setId(2L);
        profileDTO2.setName("ADMINISTRADOR");
        profileDTO2.setDescription("Usuario administrador");
        profileDTO2.setStatus(Status.ACTIVE);

        SubcommitteeDTO subcommitteeDTO = new SubcommitteeDTO();
        subcommitteeDTO.setId(3L);
        subcommitteeDTO.setName("Comisión Académica");
        subcommitteeDTO.setDescription("Supervisa programas educativos y desarrollo académico.");

        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(1L);
        countryDTO.setName("Uruguay");
        countryDTO.setPrefix("+598");

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(1L);
        locationDTO.setName("Durazno");
        locationDTO.setAreaCode("36");
        locationDTO.setCountry(countryDTO);

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("099123456");
        phoneDTO.setPhoneType(PhoneType.MOBILE);
        phoneDTO.setLocation(locationDTO);

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setFirstName("Juan");
        userDTO1.setLastName("Pérez");
        userDTO1.setEmail("juan.perez@example.com");
        userDTO1.setDocumentType(DocumentType.CI);
        userDTO1.setDocument("65481140");
        userDTO1.setBirthdate(LocalDate.of(1990, 5, 20));
        userDTO1.setStreet("25 de agosto 718");
        userDTO1.setApartment("B2");
        userDTO1.setHouseNumber("303");
        userDTO1.setHasHearingImpairment(true);
        userDTO1.setUsesSignLanguage(true);
        userDTO1.setBelongsToCommittee(true);
        userDTO1.setSubcommittee(subcommitteeDTO);
        userDTO1.setMemberNumber("SOC-2025-0001");
        userDTO1.setStatus(UserStatus.ACTIVE);
        userDTO1.setProfile(profileDTO1);
        userDTO1.setPhones(List.of(phoneDTO));

        PhoneDTO phoneDTO2 = new PhoneDTO();
        phoneDTO2.setNumber("099123345");
        phoneDTO2.setPhoneType(PhoneType.MOBILE);
        phoneDTO2.setLocation(locationDTO);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setFirstName("Andres");
        userDTO2.setLastName("Gomez");
        userDTO2.setEmail("andres.gomez@example.com");
        userDTO2.setDocumentType(DocumentType.PASSPORT);
        userDTO2.setDocument("A0987626");
        userDTO2.setBirthdate(LocalDate.of(2000, 12, 20));
        userDTO2.setStreet("25 de agosto 718");
        userDTO2.setApartment("B3");
        userDTO2.setHouseNumber("103");
        userDTO2.setStatus(UserStatus.ACTIVE);
        userDTO2.setProfile(profileDTO2);
        userDTO2.setPhones(List.of(phoneDTO2));


        when(userMapper.toDto(user1)).thenReturn(userDTO1);
        when(userMapper.toDto(user2)).thenReturn(userDTO2);

        //actuar
        List<UserDTO> result = userService.getAllUsers();


        //afirmar
        assertEquals(2, result.size());
        assertEquals("juan.perez@example.com", result.get(0).getEmail());
        assertEquals("andres.gomez@example.com", result.get(1).getEmail());
        assertEquals("099123345", result.get(1).getPhones().getFirst().getNumber());

        verify(userRepository).findAll();
        verify(userMapper).toDto(user1);
        verify(userMapper).toDto(user2);
    }
}
