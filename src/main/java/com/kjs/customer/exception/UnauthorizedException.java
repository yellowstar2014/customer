package com.kjs.customer.exception;

/**
 * UnauthorizedException未授权
 *
 * @author yellow
 * @version 1.0
 * @since 2019/1/22
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
