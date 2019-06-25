package ru.kazimir.bortnik.repository_module;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.kazimir.bortnik.repository_module.model.Room;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    //   @Query("{ '$or' : [ { 'bookingList':{ $size: 0 } }, {'bookingList':{$elemMatch:{from:{$date : ':0?'}} } } ]}")
    List<Room> findAllBy();
}