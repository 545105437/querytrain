package com.train.Repository;

import com.train.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    Page<Company> findByCompanyNameContainingAndState(String name,Integer state, Pageable pageable);


    /**
     * 按公司名查询公司
     * @param name
     * @return
     */
    List<Company> findByCompanyName(String name);

    /**
     * 这一条是实验的  后面改
     * @param name
     * @return
     */
    @Query(value = "select * from company c where c.company_name=?1", nativeQuery = true)
    List<Company> selectMore(String name);
}
