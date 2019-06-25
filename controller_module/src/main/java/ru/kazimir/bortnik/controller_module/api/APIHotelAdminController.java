package ru.kazimir.bortnik.controller_module.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kazimir.bortnik.service_module.HotelService;
import ru.kazimir.bortnik.service_module.model.HotelDTO;
import ru.kazimir.bortnik.service_module.model.RoomDTO;
import ru.kazimir.bortnik.service_module.model.UpdateWorkingHoursDTO;

import javax.validation.Valid;

import static ru.kazimir.bortnik.controller_module.constant.ApiURLConstants.API_HOTEL_ADMIN_URL;
import static ru.kazimir.bortnik.controller_module.constant.Messages.HOTEL_UPDATE_SCHEDULE_WORKING_SUCCESSFUL_MESSAGE;
import static ru.kazimir.bortnik.controller_module.constant.Messages.ROOM_SUCCESSFULLY_CREATED_MESSAGE;

@RestController
@RequestMapping(API_HOTEL_ADMIN_URL)
public class APIHotelAdminController {
    private final static Logger logger = LoggerFactory.getLogger(APIHotelAdminController.class);
    private final HotelService hotelServiceImpl;

    @Autowired
    public APIHotelAdminController(HotelService hotelService) {
        this.hotelServiceImpl = hotelService;
    }

    @GetMapping
    public ResponseEntity<HotelDTO> getHotel() {
        logger.info("Request for receiving hotel");
        HotelDTO hotelDTO = hotelServiceImpl.getHotel();
        logger.info("The resulting hotel := {}", hotelDTO);
        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> creationRoom(@RequestBody @Valid RoomDTO roomDTO, BindingResult bindingResult) {
        logger.info("Request for add hotel := {}", roomDTO);
        if (bindingResult.hasErrors()) {
            logger.info("Error := {}", bindingResult.getAllErrors());
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        hotelServiceImpl.addRoom(roomDTO);
        return new ResponseEntity<>(ROOM_SUCCESSFULLY_CREATED_MESSAGE, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateWorkingHourHotel(@RequestBody @Valid UpdateWorkingHoursDTO updateWorkingHoursDTO, BindingResult bindingResult) {
        logger.info("Request for update hotel working hour := {}", updateWorkingHoursDTO);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        hotelServiceImpl.updateWorkingHours(updateWorkingHoursDTO);
        return new ResponseEntity<>(HOTEL_UPDATE_SCHEDULE_WORKING_SUCCESSFUL_MESSAGE, HttpStatus.ACCEPTED);
    }
}
