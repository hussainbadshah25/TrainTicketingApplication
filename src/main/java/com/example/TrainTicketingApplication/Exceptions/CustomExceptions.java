package com.example.TrainTicketingApplication.Exceptions;

// Public class
public class CustomExceptions {
    public static class TicketNotFoundException extends RuntimeException {
        public TicketNotFoundException(String message) {
            super(message);
        }
    }

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class SeatModifyException extends RuntimeException {
        public SeatModifyException(String message) {
            super(message);
        }
    }
}
