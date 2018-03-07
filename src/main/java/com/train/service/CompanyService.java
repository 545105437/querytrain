package com.train.service;

import com.train.model.Company;

import java.util.List;

/**
 * @author wal
 * @date 2017/12/28
 */
public interface CompanyService {

    /**
     * 按照公司名称模糊查询
     * @param name
     * @return
     */
    List<Company> findByCompanyNameContaining(String name);

    /**
     * 根据前端页面传递的对象参数，保存公司信息
     * @param company
     * @return
     */
    int addOneCompany(Company company);

    /**
     * 按公司名查询公司
     * @param name
     * @return
     */
    List<Company> findByCompanyName(String name);
}
