package com.train.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author wal
 * @date 2018/3/8
 */
@Entity
@Data
@DynamicUpdate
public class SearchLogHistory {

    @Id
    @GeneratedValue
    private Long logId;//日志ID（仅仅只是作为主键，无实际意义）

    private String actionType;//操作类型

    private String action;//操作

    private String url;//调用的url

    private String args;//查询参数

    private String ip;//访问者IP

    private Date visitTime;// = new Date();//访问时间

}
