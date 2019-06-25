package ru.kazimir.bortnik.controller_module.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.kazimir.bortnik.controller_module.constant.ApiURLConstants.API_403_URL;

@RestController
public class APIGeneralController {
    @GetMapping(API_403_URL)
    public ResponseEntity error403() {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
