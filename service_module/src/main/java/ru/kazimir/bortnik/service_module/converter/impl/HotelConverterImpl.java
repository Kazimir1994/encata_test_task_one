package ru.kazimir.bortnik.service_module.converter.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.kazimir.bortnik.repository_module.model.Hotel;
import ru.kazimir.bortnik.repository_module.model.Room;
import ru.kazimir.bortnik.service_module.converter.Converter;
import ru.kazimir.bortnik.service_module.model.HotelDTO;
import ru.kazimir.bortnik.service_module.model.RoomDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelConverterImpl implements Converter<HotelDTO, Hotel> {
    private final Converter<RoomDTO, Room> roomConverter;

    public HotelConverterImpl(@Qualifier("roomConverterImpl") Converter<RoomDTO, Room> roomConverter) {
        this.roomConverter = roomConverter;
    }

    @Override
    public HotelDTO toDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setScheduleWorking(hotel.getScheduleWorking());
        List<RoomDTO> roomDTOList = hotel.getRooms().
                stream().map(roomConverter::toDTO).collect(Collectors.toList());
        hotelDTO.setRooms(roomDTOList);
        return hotelDTO;
    }

    @Override
    public Hotel fromDTO(HotelDTO hotelDTO) {
        throw new UnsupportedOperationException();
    }
}
