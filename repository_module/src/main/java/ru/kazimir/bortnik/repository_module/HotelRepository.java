package ru.kazimir.bortnik.repository_module;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.kazimir.bortnik.repository_module.model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
    Hotel findFirstBy();
}
