package com.example.service.exception;

/**
 * 两次密码不一致
 */
public class PasswordDifferentException extends ServiceException {
    public PasswordDifferentException() {
        super();
    }

    public PasswordDifferentException(String message) {
        super(message);
    }

    public PasswordDifferentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordDifferentException(Throwable cause) {
        super(cause);
    }

    protected PasswordDifferentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
