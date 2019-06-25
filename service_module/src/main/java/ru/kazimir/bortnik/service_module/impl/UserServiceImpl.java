package ru.kazimir.bortnik.service_module.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.kazimir.bortnik.repository_module.UserRepository;
import ru.kazimir.bortnik.repository_module.model.User;
import ru.kazimir.bortnik.service_module.UserService;
import ru.kazimir.bortnik.service_module.converter.Converter;
import ru.kazimir.bortnik.service_module.model.UserDTO;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Converter<UserDTO, User> userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           Converter<UserDTO, User> userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userConverter.toDTO(user);
    }
}
