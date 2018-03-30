package com.train.enums;

import lombok.Getter;

/**
 * @author wal
 * @date 2018/3/30
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    SERACH_NOT_EMTITY(1,"查询条件不允许为空"),
    FAIL_TO_CHECK(2,"审核发生异常"),
    FAIL_TO_DATACHANGE(3,"数据转换错误"),
    DUPLICATE_DATA(4,"存在重复数据"),
    FAIL_TO_SUBMIT(5,"提交数据失败"),
    FAIL_TO_UPDATE(5,"更新培训机构失败"),
    ;

    private Integer code;

    private String message;

    ResultEnum() {
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
