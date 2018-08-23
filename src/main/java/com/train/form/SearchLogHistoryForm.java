package com.train.form;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @author wal
 * @date 2018/3/8
 */
@Data
public class SearchLogHistoryForm {

    @Id
    @GeneratedValue
    private Long logId;//日志ID（仅仅只是作为主键，无实际意义）

    private String actionType;//操作类型

    private String action;//操作

    private String url;//调用的url

    private String args;//查询参数

    private String ip;//访问者IP

}
