package com.train.service;

import com.train.model.Company;

import java.util.List;

/**
 * @author wal
 * @date 2017/12/28
 */
public interface CompanyService {

    List<Company> findByCompanyNameContaining(String name);

    int addOneCompany(Company company);

}
