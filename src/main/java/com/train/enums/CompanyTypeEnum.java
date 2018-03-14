package com.train.enums;

import lombok.Getter;

/**
 * @author wal
 * @date 2018/3/9
 */
@Getter
public enum CompanyTypeEnum implements CodeEnum{
    TRAINING(0,"培训机构"),
    RECRUITMENT_TRANSFER_TRAINING(1,"招聘转培训"),
    SCHOOL_QI_COOPERATION(2,"校企合作"),
    Suspected_pyramid_sale(3,"疑似传销"),
    TRAINING_OUTSOURCING(4,"皮包公司外包培训"),
    BLACKLIST(5,"黑名单"),
    ;

    private Integer code;

    private String message;

    CompanyTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
