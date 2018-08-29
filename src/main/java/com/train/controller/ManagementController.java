package com.train.controller;

import com.train.dto.CompanyDTO;
import com.train.dto.SearchLogHistoryDTO;
import com.train.enums.CompanyTypeEnum;
import com.train.enums.ResultEnum;
import com.train.enums.StateEnum;
import com.train.exception.CompanyException;
import com.train.form.CompanyEditForm;
import com.train.model.Company;
import com.train.service.CompanyService;
import com.train.service.SearchLogHistoryService;
import lombok.extern.slf4j.Slf4j;
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
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author wal
 * @date 2018/3/15
 */
@Controller
@RequestMapping("/management")
@Slf4j
public class ManagementController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private SearchLogHistoryService searchLogHistoryService;

    /**
     * 获取公司列表（待审核）
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/companylist")
    public ModelAndView companyList(@RequestParam(value = "companyName" ,defaultValue = "") String companyName,
                                    @RequestParam(value = "state" ,defaultValue = "S") String state,
                                    @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                    Map<String, Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "companyId");
        PageRequest request = new PageRequest(page - 1, size, sort);
//        Page<CompanyDTO> companyDTOPage = companyService.findList(companyName, request);
        //对state进行转换
        int newState = StateEnum.SUCCESS.getCode();
        if(state.equals("W"))
            newState = StateEnum.WAIT.getCode();
        else if(state.equals("F"))
            newState = StateEnum.FAIL.getCode();
        Page<CompanyDTO> companyDTOPage = companyService.findByCompanyNameContaining(companyName, newState, request);
        map.put("companyDTOPage", companyDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("companyName", companyName);
        map.put("state", state);

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
        }catch (CompanyException e){
           log.error("【通过审核】审批失败，companyName = {}",company.getCompanyName());

            map.put("msg", ResultEnum.FAIL_TO_CHECK.getMessage());
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
        }catch (CompanyException e){
            log.error("【审核不通过】审批失败，companyName = {}",company.getCompanyName());

            map.put("msg", ResultEnum.FAIL_TO_CHECK.getMessage());
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
            log.error("【后台创建新记录】数据转换错误");
            map.put("msg",ResultEnum.FAIL_TO_DATACHANGE.getMessage());
            map.put("url","/querytrain/management/companylist");
            return new ModelAndView("common/error", map);
        }

        map.put("url","/querytrain/management/companylist");
        return new ModelAndView("common/success", map);
    }


    @GetMapping("/searchLogHistoryList")
    public ModelAndView SearchLogHistoryList(@RequestParam(value = "startTime" ,defaultValue = "N") String startTime,
                                    @RequestParam(value = "endTime" ,defaultValue = "N") String endTime,
                                    @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "20") Integer size,
                                    Map<String, Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "logId");
        PageRequest request = new PageRequest(page - 1, size,sort);
//        Page<CompanyDTO> companyDTOPage = companyService.findList(companyName, request);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       /* */
        Calendar c = Calendar.getInstance();
        Date startTimed = null;
        Date endTimed = null;

        if(startTime == "" || startTime == null){
            startTime = "N";
        }
        if(endTime == "" || endTime == null){
            endTime = "N";
        }

       if("N".equals(startTime)&&"N".equals(endTime)){
           endTimed = new Date();//获取当前时间
           c.setTime(new Date());
           c.add(Calendar.DATE, - 7);
           startTimed = c.getTime();//获取7天前时间
           startTime = formatter.format(startTimed);
           endTime = formatter.format(endTimed);
       }else if(!"N".equals(startTime)&&!"N".equals(endTime)){
           ParsePosition pos1 = new ParsePosition(0);
           ParsePosition pos2 = new ParsePosition(0);
           startTimed = formatter.parse(startTime, pos1);
           endTimed = formatter.parse(endTime, pos2);
       }else{
           if("N".equals(startTime)){
               startTime = "";
               startTimed = null;
               ParsePosition pos3 = new ParsePosition(0);
               endTimed = formatter.parse(endTime, pos3);
               endTime = formatter.format(endTimed);
           }
           if("N".equals(endTime)){
               endTime="";
               ParsePosition pos4 = new ParsePosition(0);
               startTimed = formatter.parse(startTime, pos4);
               endTimed = null;
                startTime = formatter.format(startTimed);
           }
       }

        Page<SearchLogHistoryDTO> searchLogHistoryDTOPage = null;
       if (startTimed == null && endTimed != null){
           searchLogHistoryDTOPage = searchLogHistoryService.findSearchLogHistoriesByVisitTimeLessThanEqual(endTimed,request);
       }else if(endTimed == null && startTimed != null){
           searchLogHistoryDTOPage = searchLogHistoryService.findSearchLogHistoriesByVisitTimeGreaterThanEqual(startTimed,request);
       }else if(startTimed != null && endTimed != null){
           searchLogHistoryDTOPage = searchLogHistoryService.findSearchLogHistoriesByVisitTimeBetween(startTimed,endTimed,request);
       }else{
           searchLogHistoryDTOPage = searchLogHistoryService.findAll(request);
       }
        map.put("searchLogHistoryDTOPage", searchLogHistoryDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("startTime", startTime);
        map.put("endTime",endTime);

        return new ModelAndView("management/searchloghistory", map);
    }

}
