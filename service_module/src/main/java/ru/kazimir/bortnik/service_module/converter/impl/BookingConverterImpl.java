package ru.kazimir.bortnik.service_module.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kazimir.bortnik.repository_module.model.Booking;
import ru.kazimir.bortnik.repository_module.model.User;
import ru.kazimir.bortnik.service_module.converter.Converter;
import ru.kazimir.bortnik.service_module.model.BookingDTO;
import ru.kazimir.bortnik.service_module.model.UserDTO;

@Component
public class BookingConverterImpl implements Converter<BookingDTO, Booking> {
    private final Converter<UserDTO, User> userConverter;

    @Autowired
    public BookingConverterImpl(Converter<UserDTO, User> userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setTo(booking.getTo());
        bookingDTO.setFrom(booking.getFrom());
        bookingDTO.setUserDTO(userConverter.toDTO(booking.getUser()));
        return bookingDTO;
    }

    @Override
    public Booking fromDTO(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setTo(bookingDTO.getTo());
        booking.setFrom(bookingDTO.getFrom());
        booking.setUser(userConverter.fromDTO(bookingDTO.getUserDTO()));
        return booking;
    }
}
