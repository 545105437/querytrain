package com.train.service.impl;

import com.train.Repository.CompanyRepository;
import com.train.coverter.Company2CompanyDTOConverter;
import com.train.dto.CompanyDTO;
import com.train.model.Company;
import com.train.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<CompanyDTO> findByCompanyNameContaining(String name, Pageable pageable) {

        Page<Company> companyPage = companyRepository.findByCompanyNameContaining(name, pageable);

        List<CompanyDTO> companyDTOList = Company2CompanyDTOConverter.convert(companyPage.getContent());

        return new PageImpl<CompanyDTO>(companyDTOList, pageable, companyPage.getTotalElements());
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
