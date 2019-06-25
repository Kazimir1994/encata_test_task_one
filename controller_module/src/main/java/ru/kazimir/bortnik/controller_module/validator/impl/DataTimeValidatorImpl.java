package ru.kazimir.bortnik.controller_module.validator.impl;

import org.springframework.stereotype.Component;
import ru.kazimir.bortnik.controller_module.validator.DataTimeValidator;
import ru.kazimir.bortnik.repository_module.model.WorkingHours;
import ru.kazimir.bortnik.service_module.model.HotelDTO;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class DataTimeValidatorImpl implements DataTimeValidator {
    @Override
    public boolean timeIsValid(LocalDateTime from, LocalDateTime to) {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        return currentLocalDateTime.isBefore(from) && from.isBefore(to);
    }
}