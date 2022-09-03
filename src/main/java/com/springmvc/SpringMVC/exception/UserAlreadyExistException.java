package com.springmvc.SpringMVC.exception;

/**
 * Exception thrown by system in case someone tries to register with already existing username
 *  in the system.
 */
public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException() {
        super();
    }


    public UserAlreadyExistException(String message) {
        super(message);
    }


    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}