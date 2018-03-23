package com.train.form;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author wal
 * @date 2018/3/8
 */
@Data
public class CompanyEditForm {

    @Id
    @GeneratedValue
    private Integer companyId;//公司ID（仅仅只是作为主键，无实际意义）

    private String companyName;//公司名称

    private String companyShortName;//公司简称

    private int companyType;//公司性质

    private String infoSource;//信息来源

    private String companyAddress;//公司地址

    private String businessScope;//经营范围

    private String detailsDescription;//详细描述

    private String number;//访问次数

    private String state;//状态

}
