package com.train.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author wal
 * @date 2018/3/8
 */
@Entity
@Data
public class CompanyDTO {

    @Id
    @GeneratedValue
    private Integer companyId;//公司ID（仅仅只是作为主键，无实际意义）

    @NotNull(message = "公司名称不得为空")
    private String companyName;//公司名称

    @Column
    private String companyShortName = "待补充";//公司简称

    private String companyType;//公司性质

    private String infoSource = "网友提供";//信息来源

    private String companyAddress;//公司地址

    private String businessScope;//经营范围

    private String detailsDescription = "暂无";//详细描述

    private int number = 0;//公司被访问次数

    private Date inputTime = new Date();//录入时间

    private Date updateTime = new Date();//更新时间

    private Integer state = 1;//状态

}
