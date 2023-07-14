package ru.tinkoff.summer.authmicroservice.util;

import org.springframework.stereotype.Service;
import ru.tinkoff.summer.authmicroservice.dto.UserDTO;
import ru.tinkoff.summer.authmicroservice.entity.UserCredential;

@Service
public class UserMapper {
    public UserDTO mapToDTO(UserCredential userCredential) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(userCredential.getId());
        userDTO.setUsername(userCredential.getUsername());
        userDTO.setEmail(userCredential.getEmail());

        return userDTO;
    }
}
