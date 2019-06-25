package ru.kazimir.bortnik.repository_module;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.kazimir.bortnik.repository_module.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role getByName(String name);
}
