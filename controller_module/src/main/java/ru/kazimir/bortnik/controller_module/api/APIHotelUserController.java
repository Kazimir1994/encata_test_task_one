package ru.kazimir.bortnik.controller_module.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kazimir.bortnik.controller_module.validator.DataTimeValidator;
import ru.kazimir.bortnik.service_module.HotelService;
import ru.kazimir.bortnik.service_module.model.BookingDTO;
import ru.kazimir.bortnik.service_module.model.RoomDTO;
import ru.kazimir.bortnik.service_module.model.UserDetail;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static ru.kazimir.bortnik.controller_module.constant.Messages.ROOM_RESERVED_MESSAGE;
import static ru.kazimir.bortnik.controller_module.constant.ApiURLConstants.API_HOTEL_FREE_ROOMS_USER_URL;
import static ru.kazimir.bortnik.controller_module.constant.ApiURLConstants.API_HOTEL_RESERVATION_ROOM_USER_URL;
import static ru.kazimir.bortnik.controller_module.constant.ApiURLConstants.API_HOTEL_USER_URL;
import static ru.kazimir.bortnik.controller_module.constant.ErrorsMessage.ERROR_GET_FREE_ROMA_LOCAl_DATE_TIME;

@RestController
@RequestMapping(API_HOTEL_USER_URL)
public class APIHotelUserController {
    private final static Logger logger = LoggerFactory.getLogger(APIHotelAdminController.class);
    private final DataTimeValidator dataTimeValidator;
    private final HotelService hotelServiceImpl;
    private final Validator reservationValidator;

    @Autowired
    public APIHotelUserController(DataTimeValidator dataTimeValidator,
                                  HotelService hotelServiceImpl,
                                  @Qualifier("reservationValidatorImpl") Validator reservationValidator) {
        this.dataTimeValidator = dataTimeValidator;
        this.hotelServiceImpl = hotelServiceImpl;
        this.reservationValidator = reservationValidator;
    }

    @GetMapping(API_HOTEL_FREE_ROOMS_USER_URL)
    public ResponseEntity<Object> getFreeRooms(
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        logger.info("request for free rooms in the time range from:= {}, to:= {}", from, to);
        if (!dataTimeValidator.timeIsValid(from, to)) {
            logger.info("Error := invalid data");
            return new ResponseEntity<>(ERROR_GET_FREE_ROMA_LOCAl_DATE_TIME, HttpStatus.BAD_REQUEST);
        }
        List<RoomDTO> rooms = hotelServiceImpl.getFreeRooms(from, to);
        logger.info("list of free rooms := {}", rooms);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping(API_HOTEL_RESERVATION_ROOM_USER_URL)
    public ResponseEntity<Object> reservationRoom(@RequestBody @Valid BookingDTO reservationDTO, BindingResult bindingResult,
                                                  @AuthenticationPrincipal UserDetail userPrincipal) {
        logger.info("Request for room reservation := {}, User := {}", reservationDTO, userPrincipal.getUserDTO());
        reservationValidator.validate(reservationDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.info("Error := {}", bindingResult.getAllErrors());
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        reservationDTO.setUserDTO(userPrincipal.getUserDTO());
        hotelServiceImpl.reservationRoom(reservationDTO);
        return new ResponseEntity<>(ROOM_RESERVED_MESSAGE, HttpStatus.OK);
    }
}
