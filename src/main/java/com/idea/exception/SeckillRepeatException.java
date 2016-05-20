package com.idea.exception;

/**
 * Created by Administrator on 2016/5/21.
 */
public class SeckillRepeatException extends SeckillException {
    public SeckillRepeatException(String message) {
        super(message);
    }

    public SeckillRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
