package proyecto.spring.asugestionsocios.service;

import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.mapper.UserMapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;
import proyecto.spring.asugestionsocios.model.entity.*;
import proyecto.spring.asugestionsocios.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();

        List<UserDTO> userDtos = new ArrayList<>();
        for (User user : users){
            userDtos.add(userMapper.toDto(user));
        }

        return userDtos;
    }
}