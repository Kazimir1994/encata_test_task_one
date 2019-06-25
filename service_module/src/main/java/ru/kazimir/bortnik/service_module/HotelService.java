package ru.kazimir.bortnik.service_module;

import ru.kazimir.bortnik.service_module.exception.RoomNotFoundException;
import ru.kazimir.bortnik.service_module.model.BookingDTO;
import ru.kazimir.bortnik.service_module.model.HotelDTO;
import ru.kazimir.bortnik.service_module.model.RoomDTO;
import ru.kazimir.bortnik.service_module.model.UpdateWorkingHoursDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface HotelService {
    HotelDTO getHotel();

    void addRoom(RoomDTO roomDTO);

    void updateWorkingHours(UpdateWorkingHoursDTO updateWorkingHoursDTO);

    List<RoomDTO> getFreeRooms(LocalDateTime from, LocalDateTime to);

    void reservationRoom(BookingDTO reservationDTO) throws RoomNotFoundException;
}
