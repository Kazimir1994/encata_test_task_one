package ru.kazimir.bortnik.service_module.converter.impl;

import org.springframework.stereotype.Component;
import ru.kazimir.bortnik.repository_module.model.Role;
import ru.kazimir.bortnik.service_module.converter.Converter;
import ru.kazimir.bortnik.service_module.model.RoleDTO;

@Component
public class RoleConverterImpl implements Converter<RoleDTO, Role> {

    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        roleDTO.setId(role.getId());
        return roleDTO;
    }

    @Override
    public Role fromDTO(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setId(roleDTO.getId());
        return role;
    }
}
