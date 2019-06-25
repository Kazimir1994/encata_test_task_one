package ru.kazimir.bortnik.service_module.exception;

public class RoomReservedException extends RuntimeException {
    public RoomReservedException(String message) {
        super(message);
    }
}
