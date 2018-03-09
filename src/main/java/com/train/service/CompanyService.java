package com.train.service;

import com.train.dto.CompanyDTO;
import com.train.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<CompanyDTO> findByCompanyNameContaining(String name, Pageable pageable);

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

    /**
     * 按公司id查询
     * @param companyId
     * @return
     */
    CompanyDTO findOne(Integer companyId);
}
