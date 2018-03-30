package com.train.service.impl;

import com.train.Repository.CompanyRepository;
import com.train.coverter.Company2CompanyDTOConverter;
import com.train.dto.CompanyDTO;
import com.train.enums.ResultEnum;
import com.train.enums.StateEnum;
import com.train.exception.CompanyException;
import com.train.model.Company;
import com.train.service.CompanyService;
import com.train.service.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wal
 * @date 2017/12/28
 */
@Service
@Slf4j
public class CompanyServiceImp implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private WebSocket webSocket;

    @Override
    public Page<CompanyDTO> findByCompanyNameContaining(String name,Integer state, Pageable pageable) {

        Page<Company> companyPage = companyRepository.findByCompanyNameContainingAndState(name, state, pageable);

        List<CompanyDTO> companyDTOList = Company2CompanyDTOConverter.convert(companyPage.getContent());

        return new PageImpl<CompanyDTO>(companyDTOList, pageable, companyPage.getTotalElements());
    }

    @Override
    @Transactional
    public Company addOneCompany(Company company) {
        //先查是否有同名公司，再做判断
        List<Company> companyList = findByCompanyName(company.getCompanyName());
        Company newCompany = null;
        if(companyList.size() == 0) {
            newCompany = companyRepository.save(company);
            if(newCompany == null){
                log.error("【提交培训机构】提交培训机构失败");
                throw new CompanyException(ResultEnum.FAIL_TO_SUBMIT);
            }
        }else{
            log.error("【提交培训机构】有重复数据");
            throw new CompanyException(ResultEnum.DUPLICATE_DATA);
        }

        //发送websocket消息
        webSocket.sendMessage(newCompany.getCompanyName());

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
            log.error("【公司查询】companyId不存在,companyId = {}",companyId);
        }

        CompanyDTO companyDTO = Company2CompanyDTOConverter.convert(company);

        return companyDTO;
    }

    @Override
    @Transactional
    public Company increaseNumber(Integer companyId) {
        Company company = companyRepository.findOne(companyId);
        company.setNumber(company.getNumber() + 1);
        company = companyRepository.save(company);
        return company;
    }

    @Override
    public Page<CompanyDTO> findList( Pageable pageable) {
        Page<Company> companyPage = companyRepository.findAll(pageable);
        List<CompanyDTO> companyDTOList = Company2CompanyDTOConverter.convert(companyPage.getContent());
        return new PageImpl<>(companyDTOList, pageable, companyPage.getTotalElements());
    }

    @Override
    @Transactional
    public Company passed(Integer companyId) {
        Company company = companyRepository.findOne(companyId);

        //修改公司状态为审批通过
        company.setState(StateEnum.SUCCESS.getCode());
        Company updateResult = companyRepository.save(company);

        if (updateResult == null){
            log.error("【更新培训机构状态】更新公司状态失败，companyName = {}", company.getCompanyName());
            throw new CompanyException(ResultEnum.FAIL_TO_UPDATE);
        }
        return company;
    }

    @Override
    @Transactional
    public Company rejected(Integer companyId) {
        Company company = companyRepository.findOne(companyId);

        //修改公司状态为未审批通过
        company.setState(StateEnum.FAIL.getCode());
        Company updateResult = companyRepository.save(company);

        if (updateResult == null){
            log.error("【更新培训机构状态】更新公司状态失败，companyName = {}", company.getCompanyName());
            throw new CompanyException(ResultEnum.FAIL_TO_UPDATE);
        }
        return company;
    }

    @Override
    @Transactional
    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
