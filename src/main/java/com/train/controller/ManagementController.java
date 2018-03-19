package com.train.controller;

import com.train.dto.CompanyDTO;
import com.train.enums.CompanyTypeEnum;
import com.train.model.Company;
import com.train.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author wal
 * @date 2018/3/15
 */
@Controller
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    private CompanyService companyService;

    /**
     * 获取公司列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/companylist")
    public ModelAndView companyList(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                    Map<String, Object> map){
        PageRequest request = new PageRequest(page - 1, size);
        Page<CompanyDTO> companyDTOPage = companyService.findList(request);
        map.put("companyDTOPage", companyDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("management/companylist", map);
    }

    /**
     * 通过审批
     * @param companyId
     * @param map
     * @return
     */
    @GetMapping("pass")
    public ModelAndView passed(@RequestParam("companyId") Integer companyId,
                               Map<String, Object> map){
        Company company = new Company();
        try{
            company = companyService.passed(companyId);
        }catch (Exception e){
            e.printStackTrace();

            map.put("msg", "审核发生异常");
            map.put("url","/querytrain/management/companylist");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "公司："+company.getCompanyName()+" 经过审核，结果为：通过！");
        map.put("url","/querytrain/management/companylist");
        return new ModelAndView("common/success", map);
    }

    /**
     * 审批不通过
     * @param companyId
     * @param map
     * @return
     */
    @GetMapping("/reject")
    public ModelAndView rejected(@RequestParam("companyId") Integer companyId,
                               Map<String, Object> map){
        Company company = new Company();
        try{
            company = companyService.rejected(companyId);
        }catch (Exception e){
            e.printStackTrace();

            map.put("msg", "审核发生异常");
            map.put("url","/querytrain/management/companylist");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "公司："+company.getCompanyName()+"经过审核，结果为：不通过");
        map.put("url","/querytrain/management/companylist");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/neworedit")
    public ModelAndView neworedit(@RequestParam(value = "companyId", required = false) Integer companyId,
                                Map<String, Object> map){

        if (!StringUtils.isEmpty(companyId)){
            CompanyDTO companyDTO = companyService.findOne(companyId);
//            for (CompanyTypeEnum anEnum : CompanyTypeEnum.values()) {
//                System.out.println(anEnum.getCode()+" : "+anEnum.getMessage());
//            }
            map.put("companyTypeEnum", CompanyTypeEnum.values());
            map.put("companyDTO", companyDTO);
        }

        return new ModelAndView("management/companyneworedit", map);
    }

}
