package com.train.model;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author wal
 * @date 2017/12/28
 */
@Entity
@Data
@DynamicUpdate
public class Company {

    @Id
    @GeneratedValue
    private Integer companyId;//公司ID（仅仅只是作为主键，无实际意义）

    private String companyName;//公司名称

    @Column
    private String companyShortName;//公司简称

    private int companyType;//公司性质

    private String infoSource;//信息来源

    private String companyAddress;//公司地址

    private String businessScope;//经营范围

    private String detailsDescription;//详细描述

    private int number;//公司被访问次数

    private Date inputTime;//录入时间

    private Date updateTime;//更新时间

    private int state;//状态

}
