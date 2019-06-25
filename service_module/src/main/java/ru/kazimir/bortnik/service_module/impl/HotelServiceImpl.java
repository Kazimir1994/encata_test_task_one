package ru.kazimir.bortnik.service_module.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.kazimir.bortnik.repository_module.HotelRepository;
import ru.kazimir.bortnik.repository_module.RoomRepository;
import ru.kazimir.bortnik.repository_module.model.Booking;
import ru.kazimir.bortnik.repository_module.model.Hotel;
import ru.kazimir.bortnik.repository_module.model.Room;
import ru.kazimir.bortnik.repository_module.model.WorkingHours;
import ru.kazimir.bortnik.service_module.HotelService;
import ru.kazimir.bortnik.service_module.converter.Converter;
import ru.kazimir.bortnik.service_module.exception.RoomDuplicateException;
import ru.kazimir.bortnik.service_module.exception.RoomNotFoundException;
import ru.kazimir.bortnik.service_module.exception.RoomReservedException;
import ru.kazimir.bortnik.service_module.model.BookingDTO;
import ru.kazimir.bortnik.service_module.model.HotelDTO;
import ru.kazimir.bortnik.service_module.model.RoomDTO;
import ru.kazimir.bortnik.service_module.model.UpdateWorkingHoursDTO;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.kazimir.bortnik.service_module.exception.messageexception.ErrorMessagesService.ROOM_NOT_FOUND_ERROR_MESSAGE;
import static ru.kazimir.bortnik.service_module.exception.messageexception.ErrorMessagesService.ROOM_RESERVED_ERROR_MESSAGE;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepositoryImpl;
    private final RoomRepository roomRepositoryImpl;
    private final Converter<HotelDTO, Hotel> hotelConverter;
    private final Converter<RoomDTO, Room> roomConverter;
    private final Converter<RoomDTO, Room> availableRoomsConverter;
    private final Converter<BookingDTO, Booking> bookingConverter;

    public HotelServiceImpl(HotelRepository findFirstBy,
                            RoomRepository roomRepositoryImpl,
                            Converter<HotelDTO, Hotel> hotelConverter,
                            @Qualifier("roomConverterImpl") Converter<RoomDTO, Room> roomConverter,
                            @Qualifier("availableRoomsConverterImpl") Converter<RoomDTO, Room> availableRoomsConverter,
                            Converter<BookingDTO, Booking> bookingConverter) {
        this.hotelRepositoryImpl = findFirstBy;
        this.roomRepositoryImpl = roomRepositoryImpl;
        this.hotelConverter = hotelConverter;
        this.roomConverter = roomConverter;
        this.availableRoomsConverter = availableRoomsConverter;
        this.bookingConverter = bookingConverter;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public HotelDTO getHotel() {
        Hotel hotel = hotelRepositoryImpl.findFirstBy();
        return hotelConverter.toDTO(hotel);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addRoom(RoomDTO roomDTO) {
        Room persistentRoom;
        try {
            Room room = roomConverter.fromDTO(roomDTO);
            persistentRoom = roomRepositoryImpl.insert(room);
        } catch (Exception e) {
            throw new RoomDuplicateException(e);
        }
        Hotel hotel = hotelRepositoryImpl.findFirstBy();
        hotel.getRooms().add(persistentRoom);
        hotelRepositoryImpl.save(hotel);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateWorkingHours(UpdateWorkingHoursDTO updateWorkingHoursDTO) {
        Hotel hotel = hotelRepositoryImpl.findFirstBy();

        Map<DayOfWeek, WorkingHours> scheduleWorking = hotel.getScheduleWorking();
        WorkingHours workingHours = scheduleWorking.get(updateWorkingHoursDTO.getDayOfWeek());
        workingHours.setFrom(updateWorkingHoursDTO.getFrom());
        workingHours.setTo(updateWorkingHoursDTO.getTo());

        scheduleWorking.put(updateWorkingHoursDTO.getDayOfWeek(), workingHours);
        hotel.setScheduleWorking(scheduleWorking);
        hotelRepositoryImpl.save(hotel);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<RoomDTO> getFreeRooms(LocalDateTime from, LocalDateTime to) {
        List<Room> roomList = roomRepositoryImpl.findAllBy();
        return roomList.stream()
                .filter(room -> filterIsFreeRoom(room.getBookingList(), from, to))
                .map(availableRoomsConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void reservationRoom(BookingDTO reservationDTO) {
        Optional<Room> roomOptional = roomRepositoryImpl.findById(reservationDTO.getRoomId());
        if (!roomOptional.isPresent()) {
            throw new RoomNotFoundException(ROOM_NOT_FOUND_ERROR_MESSAGE);
        }
        Room room = roomOptional.get();
        if (filterIsFreeRoom(room.getBookingList(), reservationDTO.getFrom(), reservationDTO.getTo())) {
            Booking booking = bookingConverter.fromDTO(reservationDTO);
            room.getBookingList().add(booking);
            roomRepositoryImpl.save(room);
        } else {
            throw new RoomReservedException(ROOM_RESERVED_ERROR_MESSAGE);
        }
    }

    private boolean filterIsFreeRoom(Set<Booking> bookingList, LocalDateTime from, LocalDateTime to) {
        List<Booking> booking;
        if (!bookingList.isEmpty()) {
            booking = bookingList.stream().filter(
                    booking1 ->
                            (!from.isBefore(booking1.getFrom()) || !to.isBefore(booking1.getFrom())) &&
                                    (!from.isAfter(booking1.getTo()) || !to.isAfter(booking1.getTo()))
            ).collect(Collectors.toList());
            return booking.isEmpty();
        }
        return true;
    }
}
