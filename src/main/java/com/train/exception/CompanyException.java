package com.train.exception;

import com.train.enums.ResultEnum;
import lombok.Getter;

/**
 * @author wal
 * @date 2018/3/30
 */
@Getter
public class CompanyException extends  RuntimeException{
    private Integer code;

    public CompanyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public CompanyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
