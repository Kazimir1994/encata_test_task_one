package ru.kazimir.bortnik.controller_module.ExceptionHandlers.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kazimir.bortnik.controller_module.api.APIHotelAdminController;
import ru.kazimir.bortnik.controller_module.api.APIHotelUserController;
import ru.kazimir.bortnik.service_module.exception.RoomDuplicateException;
import ru.kazimir.bortnik.service_module.exception.RoomNotFoundException;
import ru.kazimir.bortnik.service_module.exception.RoomReservedException;

import static ru.kazimir.bortnik.controller_module.constant.Messages.ROOM_DUPLICATE_NAME_MESSAGE;
import static ru.kazimir.bortnik.controller_module.constant.Messages.ROOM_NOT_FOUND_ERROR_MESSAGE;
import static ru.kazimir.bortnik.controller_module.constant.Messages.ROOM_RESERVED_ERROR_MESSAGE;

@ControllerAdvice(assignableTypes = {APIHotelAdminController.class, APIHotelUserController.class})
public class HotelsAPIExceptionHandlers {
    private final static Logger logger = LoggerFactory.getLogger(HotelsAPIExceptionHandlers.class);

    @ExceptionHandler(RoomDuplicateException.class)
    public final ResponseEntity<Object> notExistentHandler(Exception ex) {
        logger.info("Error:= {} ", ex.getMessage());
        return new ResponseEntity<>(ROOM_DUPLICATE_NAME_MESSAGE, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public final ResponseEntity<Object> notFound(Exception ex) {
        logger.info("Error:= {} ", ex.getMessage());
        return new ResponseEntity<>(ROOM_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoomReservedException.class)
    public final ResponseEntity<Object> roomReservedException(Exception ex) {
        logger.info("Error:= {} ", ex.getMessage());
        return new ResponseEntity<>(ROOM_RESERVED_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}

