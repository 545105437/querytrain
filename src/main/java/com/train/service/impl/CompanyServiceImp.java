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

        Page<Company> companyPage = companyRepository.findByCompanyNameContainingAndState(name,1, pageable);

        List<CompanyDTO> companyDTOList = Company2CompanyDTOConverter.convert(companyPage.getContent());

        return new PageImpl<CompanyDTO>(companyDTOList, pageable, companyPage.getTotalElements());
    }

    @Override
    public Company addOneCompany(Company company) {
        //先查是否有同名公司，再做判断
        List<Company> companyList = findByCompanyName(company.getCompanyName());
        Company newCompany = null;
        if(companyList.size() == 0) {
            newCompany = companyRepository.save(company);
        }

        if(newCompany == null){

        }

        return newCompany;
    }

    @Override
    public List<Company> findByCompanyName(String name) {
        return companyRepository.findByCompanyName(name);
    }

    @Override
    public CompanyDTO findOne(Integer companyId) {

        Company company = companyRepository.findOne(companyId);
        if (company == null){
            System.out.println("companyId不存在");
        }

        CompanyDTO companyDTO = Company2CompanyDTOConverter.convert(company);

        return companyDTO;
    }

    @Override
    public Company increaseNumber(Integer companyId) {
        Company company = companyRepository.findOne(companyId);
        company.setNumber(company.getNumber() + 1);
        company = companyRepository.save(company);
        return company;
    }
}
