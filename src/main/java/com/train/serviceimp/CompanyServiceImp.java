package com.train.serviceimp;

import com.train.Repository.CompanyRepository;
import com.train.model.Company;
import com.train.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wal
 * @date 2017/12/28
 */
@Service
public class CompanyServiceImp implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findByCompanyNameContaining(String name) {

        return companyRepository.findByCompanyNameContaining(name);
    }

    @Override
    public int addOneCompany(Company company) {
        //先查是否有同名公司，再做判断
        List<Company> companyList = findByCompanyName(company.getCompanyName());
        Company newCompany = null;
        if(companyList.size() == 0)
            newCompany = companyRepository.save(company);
        else
            return -1;//表示有重复
        if(newCompany == null)
            return 0;//表示失败
        return 1;//表示成功
    }

    @Override
    public List<Company> findByCompanyName(String name) {
        return companyRepository.findByCompanyName(name);
    }
}
