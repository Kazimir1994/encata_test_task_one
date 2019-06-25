package ru.kazimir.bortnik.service_module.exception;

public class RoomDuplicateException extends RuntimeException {
    public RoomDuplicateException(Exception e) {
        super(e);
    }
}
