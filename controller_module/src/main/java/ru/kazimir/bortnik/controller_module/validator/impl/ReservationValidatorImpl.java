package ru.kazimir.bortnik.controller_module.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kazimir.bortnik.controller_module.validator.DataTimeValidator;
import ru.kazimir.bortnik.repository_module.model.WorkingHours;
import ru.kazimir.bortnik.service_module.HotelService;
import ru.kazimir.bortnik.service_module.model.BookingDTO;
import ru.kazimir.bortnik.service_module.model.HotelDTO;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;

import static ru.kazimir.bortnik.controller_module.constant.ErrorsMessage.ERROR_GET_FREE_ROMA_LOCAl_DATE_TIME;
import static ru.kazimir.bortnik.controller_module.constant.ErrorsMessage.ERROR_HOTEL_NOT_WORKING;

@Component
public class ReservationValidatorImpl implements Validator {
    private final DataTimeValidator dataTimeValidator;
    private final HotelService hotelService;

    public ReservationValidatorImpl(DataTimeValidator dataTimeValidator, HotelService hotelService) {
        this.dataTimeValidator = dataTimeValidator;
        this.hotelService = hotelService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BookingDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            BookingDTO reservationDTO = (BookingDTO) target;
            if (!dataTimeValidator.timeIsValid(reservationDTO.getFrom(), reservationDTO.getTo())) {
                errors.rejectValue("from", null, ERROR_GET_FREE_ROMA_LOCAl_DATE_TIME);
            } else {
                LocalDateTime currentLocalDateTime = LocalDateTime.now();
                HotelDTO hotelDTO = hotelService.getHotel();
                Map<DayOfWeek, WorkingHours> scheduleWorking = hotelDTO.getScheduleWorking();

                WorkingHours workingHours = scheduleWorking.get(currentLocalDateTime.getDayOfWeek());
                System.out.println(workingHours);
                System.out.println(currentLocalDateTime.toLocalTime());
                if (workingHours.getFrom().isBefore(currentLocalDateTime.toLocalTime()) &&
                        !workingHours.getTo().isAfter(currentLocalDateTime.toLocalTime())) {
                    errors.rejectValue("from", null, ERROR_HOTEL_NOT_WORKING);
                }
            }
        }
    }
}
