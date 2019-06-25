package ru.kazimir.bortnik.service_module;

import ru.kazimir.bortnik.service_module.model.UserDTO;

public interface UserService {
    UserDTO getByEmail(String email);
}
