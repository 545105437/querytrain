package com.train.controller;

import com.train.CompanyException;
import com.train.dto.CompanyDTO;
import com.train.enums.CompanyTypeEnum;
import com.train.form.CompanyEditForm;
import com.train.form.CompanyForm;
import com.train.model.Company;
import com.train.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
     * 获取公司列表（待审核）
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/companylist")
    public ModelAndView companyList(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                    Map<String, Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "companyId");
        PageRequest request = new PageRequest(page - 1, size, sort);
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
    public ModelAndView neworedit(@RequestParam(value = "companyId", required = false) String companyId,
                                Map<String, Object> map){

        if (!StringUtils.isEmpty(companyId)){
            CompanyDTO companyDTO = companyService.findOne(Integer.valueOf(companyId));
            map.put("companyDTO", companyDTO);
        }

        map.put("companyTypeEnum", CompanyTypeEnum.values());

        return new ModelAndView("management/companyneworedit", map);
    }

    @PostMapping("/save")
    public ModelAndView save (@Valid CompanyEditForm form,
                              BindingResult bindingResult,
                              Map<String, Object> map) {

        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/querytrain/management/companylist");
            return new ModelAndView("common/error", map);
        }
        CompanyDTO companyDTO = new CompanyDTO();

        try{
            if (!StringUtils.isEmpty(form.getCompanyId())){
                companyDTO = companyService.findOne(form.getCompanyId());
            }
            if ("".equals(form.getCompanyShortName())) {
                form.setCompanyShortName("待补充");
            }
            if ("".equals(form.getDetailsDescription())) {
                form.setDetailsDescription("暂无");
            }
            if ("".equals(form.getBusinessScope())) {
                form.setBusinessScope("暂无");
            }
            if ("".equals(form.getInfoSource())) {
                form.setInfoSource("网友提供");
            }
            BeanUtils.copyProperties(form, companyDTO);
            Company company = new Company();
            BeanUtils.copyProperties(companyDTO, company);
            companyService.save(company);
        }catch (CompanyException e){
            map.put("msg",e.getMessage());
            map.put("url","/querytrain/management/companylist");
            return new ModelAndView("common/error", map);
        }

        map.put("url","/querytrain/management/companylist");
        return new ModelAndView("common/success", map);
    }

}
