package com.train.controller;

import com.train.dto.CompanyDTO;
import com.train.enums.ResultEnum;
import com.train.enums.StateEnum;
import com.train.exception.CompanyException;
import com.train.form.CompanyForm;
import com.train.model.Company;
import com.train.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author wal
 * @date 2018/3/8
 */
@Controller
@RequestMapping("/company")
@Slf4j
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
                               @RequestParam(value = "size", defaultValue = "8") Integer size,
                               Map<String, Object> map) {

        if (StringUtils.isEmpty(companyName)){
            log.error(ResultEnum.SERACH_NOT_EMTITY.getMessage());
            return new ModelAndView("company/main");
        }
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<CompanyDTO> companyDTOPage = companyService.findByCompanyNameContaining(companyName, StateEnum.SUCCESS.getCode(), pageRequest);
        if (companyDTOPage.getTotalPages() == 0){
            map.put("msg","无"+companyName+"相关信息！");
            map.put("url","/querytrain/main");
            return new ModelAndView("company/emtity", map);
        }
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

        //访问公司详情，被访问次数 + 1
        companyService.increaseNumber(companyId);

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO = companyService.findOne(companyId);
        if (companyDTO == null){
            //抛出异常，跳转错误页面，跳转主页面
        }

        map.put("companyDTO", companyDTO);

        return new ModelAndView("company/detail", map);
    }

    /**
     * 提交数据
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save (@Valid CompanyForm form,
                      BindingResult bindingResult,
                      Map<String, Object> map) {

        if (bindingResult.hasErrors()){
            log.error("【创建新记录】参数不正确，form = {}", form);
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/querytrain/submitData");
            return new ModelAndView("common/error", map);
        }

        String companyName = form.getCompanyName();
        List<Company> list = companyService.findByCompanyName(companyName);

        if (list.size() != 0) {
            log.error("【创建新记录】有重复公司名称，相同名称个数：{}", list.size());
            map.put("msg","有重复公司名称，请重新填写");
            map.put("url","/querytrain/submitData");
            return new ModelAndView("common/error", map);
        }

        CompanyDTO companyDTO = new CompanyDTO();

        try{
            if ("".equals(form.getCompanyShortName())) {
                form.setCompanyShortName("待补充");
            }
            if ("".equals(form.getBusinessScope())) {
                form.setBusinessScope("暂无");
            }
            if ("".equals(form.getDetailsDescription())) {
                form.setDetailsDescription("暂无");
            }
            BeanUtils.copyProperties(form, companyDTO);
            Company company = new Company();
            BeanUtils.copyProperties(companyDTO, company);
            companyService.addOneCompany(company);
            log.info("【提交数据】提交的数据为，company = {}", company);
        }catch (CompanyException e){
            log.error("【创建新记录】数据转换错误");
            map.put("msg",e.getMessage());
            map.put("url","/querytrain/submitData");
            return new ModelAndView("common/error", map);
        }

        map.put("url","/querytrain/main");
        return new ModelAndView("common/success", map);
    }
}
