package com.train.controller;

import com.train.model.Company;
import com.train.model.DataParam;
import com.train.service.CompanyService;
import com.train.util.Result_train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class ControlUtil {

	@Autowired
	private CompanyService companyService;

	@RequestMapping("/main")
	public String mainPage(){
		System.out.println("进入主页面");
		return "main";
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs(){
		
		return "aboutUs";
	}
	
	@RequestMapping("/submitData")
	public String submitData(){
		
		return "submitData";
	}

	/**
	 *  按照公司名称模糊查询
	 * @param companyName
	 * @return
	 */
	@GetMapping(value = "/search/{companyName}")
	public Result_train search(@PathVariable("companyName") String companyName) throws UnsupportedEncodingException {
		List<Company> list = companyService.findByCompanyNameContaining(companyName);
		if(list.size() == 0){
			return new Result_train("0","查询结果为空，无匹配公司");
		}else
			return  new Result_train("0", "接口返回成功", list);
	}

	/**
	 *  根据前端页面传递的对象参数，保存公司信息
	 * @param companyList
	 * @return
	 */
	@PostMapping(value = "/addCompany")
	public Result_train addCompany(@RequestBody DataParam<Company> companyList){
		List<Company> lst = companyList.getData();
		Company company = lst.get(0);
		int count =  companyService.addOneCompany(company);
		if(count == 1)
			return new Result_train("0", "成功插入 " + count +" 条数据。");

		return new Result_train();
	}
	
}
