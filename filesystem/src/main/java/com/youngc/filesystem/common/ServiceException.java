package com.youngc.filesystem.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ServiceException extends RuntimeException {

    private String msg;

    public ServiceException(Throwable cause) {
        super(cause);
        msg = null == cause.getMessage() ? "系统异常！" : cause.getMessage();
    }

    public ServiceException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public ServiceException(String msg) {
        super();
        this.msg = msg;
    }

}
