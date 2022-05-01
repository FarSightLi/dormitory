package com.example.service.exception;

/**
 * @ClassName: Exception
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/1 19:08
 */

public class Exception extends RuntimeException {
    public Exception() {
        super();
    }

    public Exception(String message) {
        super(message);
    }

    public Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception(Throwable cause) {
        super(cause);
    }

    protected Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
