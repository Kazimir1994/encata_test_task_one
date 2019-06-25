package ru.kazimir.bortnik.controller_module.validator;

import java.time.LocalDateTime;

public interface DataTimeValidator {

    boolean timeIsValid(LocalDateTime from, LocalDateTime to);
}
