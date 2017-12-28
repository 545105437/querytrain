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
        System.out.println("进入service的findByCompany_nameContaining");
        companyRepository.findByCompanyNameContaining(name);
        return null;
    }
}
