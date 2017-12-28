package com.train.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author wal
 * @date 2017/12/28
 */
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Integer companyId;//公司ID（仅仅只是作为主键，无实际意义）

    @NotNull(message = "公司名称不得为空")
    private String companyName;//公司名称

    private String companyShortName;//公司简称

    private String companyType;//公司性质

    private String infoSource;//信息来源

    private String companyAddress;//公司地址

    private String businessScope;//经营范围

    private String detailsDescription;//详细描述

    private String number;//公司被访问次数

    private Date inputTime;//录入时间

    private Date updateTime;//更新时间

    private Integer state;//状态

    public Company() {
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyShortName='" + companyShortName + '\'' +
                ", companyType='" + companyType + '\'' +
                ", infoSource='" + infoSource + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", detailsDescription='" + detailsDescription + '\'' +
                ", number='" + number + '\'' +
                ", inputTime=" + inputTime +
                ", updateTime=" + updateTime +
                ", state=" + state +
                '}';
    }
}
