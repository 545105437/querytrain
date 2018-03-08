package com.train.coverter;

import com.train.dto.CompanyDTO;
import com.train.model.Company;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wal
 * @date 2018/3/8
 */
public class Company2CompanyDTOConverter {

    public static CompanyDTO convert(Company company){
        CompanyDTO companyDTO = new CompanyDTO();
        BeanUtils.copyProperties(company, companyDTO);
        return companyDTO;
    }

    public static List<CompanyDTO> convert(List<Company> companyList){
        return companyList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
