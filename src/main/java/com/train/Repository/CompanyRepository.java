package com.train.Repository;

import com.train.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wal
 * @date 2017/12/28
 */
public interface CompanyRepository extends JpaRepository<Company,Integer>{

    /**
     * 按照公司名模糊查询（方法名解析的方法）
     * @param name
     * @param pageable
     * @return
     */
    Page<Company> findByCompanyNameContaining(String name, Pageable pageable);


    /**
     * 按公司名查询公司
     * @param name
     * @return
     */
    List<Company> findByCompanyName(String name);
}
