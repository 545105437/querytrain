package com.train.enums;


import lombok.Getter;

/**
 * @author wal
 * @date 2018/3/15
 */
@Getter
public enum StateEnum implements CodeEnum{
    FAIL(0,"未通过审批"),
    WAIT(1,"待审批"),
    SUCCESS(2,"已通过审批"),
    ;

    private Integer code;

    private String message;

    StateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
