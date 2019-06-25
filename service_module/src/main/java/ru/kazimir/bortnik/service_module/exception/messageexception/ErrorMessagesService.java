package ru.kazimir.bortnik.service_module.exception.messageexception;

public class ErrorMessagesService {
    public static final String ROOM_NOT_FOUND_ERROR_MESSAGE = "Room with id := %s is not found.";
    public static final String ROOM_RESERVED_ERROR_MESSAGE = "This room is already reserved.";

    private ErrorMessagesService() {
    }
}
