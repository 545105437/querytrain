package com.train.controller;

import com.alibaba.fastjson.JSONObject;
import com.train.dto.CompanyDTO;
import com.train.model.Company;
import com.train.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author wal
 * @date 2018/3/8
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 公司列表（按公司名模糊查询）
     * @param page 第几页，从第一页开始
     * @param size 一页有多少条数据
     * @param map  查询
     * @return
     */
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("companyName") String companyName,
                               @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                               Map<String, Object> map) {

        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<CompanyDTO> companyDTOPage = companyService.findByCompanyNameContaining(companyName,pageRequest);
        map.put("companyDTOPage", companyDTOPage);
        map.put("companyName", companyName);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("company/search", map);
    }

    /**
     * 公司详情
     * @param companyId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("companyId") Integer companyId,
                               Map<String, Object> map){

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO = companyService.findOne(companyId);

        map.put("companyDTO", companyDTO);

        return new ModelAndView("company/detail", map);
    }
}
