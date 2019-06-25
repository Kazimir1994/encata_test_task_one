package ru.kazimir.bortnik.service_module.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kazimir.bortnik.repository_module.model.Booking;
import ru.kazimir.bortnik.repository_module.model.Room;
import ru.kazimir.bortnik.service_module.converter.Converter;
import ru.kazimir.bortnik.service_module.model.BookingDTO;
import ru.kazimir.bortnik.service_module.model.RoomDTO;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoomConverterImpl implements Converter<RoomDTO, Room> {
    private final Converter<BookingDTO, Booking> bookingConverter;

    @Autowired
    public RoomConverterImpl(Converter<BookingDTO, Booking> bookingConverter) {
        this.bookingConverter = bookingConverter;
    }

    @Override
    public RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setName(room.getName());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        roomDTO.setPrice(room.getPrice().toString());
        Set<BookingDTO> bookingDTOList = room.getBookingList().
                stream().map(bookingConverter::toDTO).collect(Collectors.toSet());
        roomDTO.setBookingList(bookingDTOList);
        return roomDTO;
    }

    @Override
    public Room fromDTO(RoomDTO roomDTO) {
        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setNumberOfBeds(roomDTO.getNumberOfBeds());
        room.setPrice(BigDecimal.valueOf(Double.valueOf(roomDTO.getPrice())));
        return room;
    }
}
