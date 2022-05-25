package com.example.service.exception;


/**
 * @ClassName: PasswordWrongException
 * @Description: 密码错误
 * @author: LongSheng Li
 * @date: 2022/5/25 17:05
 */
public class PasswordWrongException extends ServiceException {
    public PasswordWrongException() {
        super();
    }

    public PasswordWrongException(String message) {
        super(message);
    }

    public PasswordWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordWrongException(Throwable cause) {
        super(cause);
    }

    protected PasswordWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
