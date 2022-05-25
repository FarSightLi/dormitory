package com.example.service.exception;

/**
 * @ClassName: UsernameNullException
 * @Description: 用户名为空
 * @author: LongSheng Li
 * @date: 2022/5/25 17:05
 */

public class UsernameNullException extends ServiceException {
    public UsernameNullException() {
        super();
    }

    public UsernameNullException(String message) {
        super(message);
    }

    public UsernameNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNullException(Throwable cause) {
        super(cause);
    }

    protected UsernameNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
