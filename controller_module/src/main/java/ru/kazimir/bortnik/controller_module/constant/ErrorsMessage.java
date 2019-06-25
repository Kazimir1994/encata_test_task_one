package ru.kazimir.bortnik.controller_module.constant;

public class ErrorsMessage {
    public static final String ERROR_GET_FREE_ROMA_LOCAl_DATE_TIME = "You entered incorrect data.\n" +
            "The date of check-in must be early than the date of check-out, but no early than the current date";
    public static final String ERROR_HOTEL_NOT_WORKING  = "Host is down at this time";
    private ErrorsMessage() {
    }
}
