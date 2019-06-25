package ru.kazimir.bortnik.service_module.converter.impl;

import org.springframework.stereotype.Component;
import ru.kazimir.bortnik.repository_module.model.Room;
import ru.kazimir.bortnik.service_module.converter.Converter;
import ru.kazimir.bortnik.service_module.model.RoomDTO;

@Component
public class AvailableRoomsConverterImpl implements Converter<RoomDTO, Room> {
    @Override
    public RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        roomDTO.setName(room.getName());
        roomDTO.setPrice(room.getPrice().toString());
        roomDTO.setId(room.getId());
        return roomDTO;
    }

    @Override
    public Room fromDTO(RoomDTO roomDTO) {
        throw new UnsupportedOperationException();
    }
}
