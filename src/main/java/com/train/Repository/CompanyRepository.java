package com.train.Repository;

import com.train.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wal
 * @date 2017/12/28
 */
public interface CompanyRepository extends JpaRepository<Company,Integer>{

    /**
     * 按照公司名模糊查询
     * @param name
     * @return
     */
    public List<Company> findByCompanyNameContaining(String name);

    public List<Company> findAll();

}
