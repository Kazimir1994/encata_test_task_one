package ru.kazimir.bortnik.service_module.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kazimir.bortnik.repository_module.model.Role;
import ru.kazimir.bortnik.repository_module.model.User;
import ru.kazimir.bortnik.service_module.converter.Converter;
import ru.kazimir.bortnik.service_module.model.RoleDTO;
import ru.kazimir.bortnik.service_module.model.UserDTO;

@Component
public class UserConverterImpl implements Converter<UserDTO, User> {
    private final Converter<RoleDTO, Role> roleConverter;

    @Autowired
    public UserConverterImpl(Converter<RoleDTO, Role> roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleDTO(roleConverter.toDTO(user.getRole()));
        return userDTO;
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(roleConverter.fromDTO(userDTO.getRoleDTO()));
        return user;
    }
}
