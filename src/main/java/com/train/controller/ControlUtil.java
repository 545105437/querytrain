package com.train.controller;

import com.alibaba.fastjson.JSONObject;
import com.train.model.Company;
import com.train.model.DataParam;
import com.train.service.CompanyService;
import com.train.util.Result_train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class ControlUtil {

	@Autowired
	private CompanyService companyService;



	/**
	 *  按照公司名称模糊查询
	 * @param companyName
	 * @return
	 */
	@GetMapping(value = "/search/{companyName}",produces = "text/html;charset=UTF-8")
	public void search(@PathVariable("companyName") String companyName, HttpServletRequest req, HttpServletResponse res) {
//		List<Company> list = companyService.findByCompanyNameContaining(companyName);
//		/*if(list.size() == 0){
//			return new Result_train("0","查询结果为空，无匹配公司");
//		}else
//			return  new Result_train("0", "接口返回成功", list);*/
//
//		String str  = "Hello world";
//		JSONObject json = new JSONObject();
//		json.put("code", 0);
//		if(list.size() == 0) {
//			json.put("message", "查询结果为空，无匹配公司");
//			json.put("data", list);
//		}else{
//			json.put("message", "接口返回成功");
//			json.put("data", list);
//		}
//		try {
//			//解决乱码问题
//			res.setCharacterEncoding("UTF-8");
//			res.setContentType("text/html;charset=UTF-8");
//			//前台定义的jsonp:'Succcess'
//			res.getWriter().print("Success("+json.toJSONString()+")");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
/*	public Result_train search(@PathVariable("companyName") String companyName) throws UnsupportedEncodingException {
		List<Company> list = companyService.findByCompanyNameContaining(companyName);
		if(list.size() == 0){
			return new Result_train("0","查询结果为空，无匹配公司");
		}else
			return  new Result_train("0", "接口返回成功", list);
	}*/

	/**
	 *  根据前端页面传递的对象参数，保存公司信息
	 * @param companyList
	 * @return
	 */
	@PostMapping(value = "/addCompany")
	public Result_train addCompany(@RequestBody DataParam<Company> companyList){
//		List<Company> lst = companyList.getData();
//		Company company = lst.get(0);
//		int count =  companyService.addOneCompany(company);
//		if(count == 1)
//			return new Result_train("0", "成功插入 " + count +" 条数据。");
//		else if(count == -1)
//			return new Result_train("1","有重复数据,新增失败" );
//		else if(count == 0)
//			return new Result_train("-1","新增过程中失败");

		return new Result_train();
	}

	/**
	 *  按照公司名称查询（排重）
	 * @param companyName
	 * @return
	 */
	@GetMapping(value = "/selectOne/{companyName}")
	public Result_train selectOne(@PathVariable("companyName") String companyName) throws UnsupportedEncodingException {
		List<Company> list = companyService.findByCompanyName(companyName);
		if(list.size() == 0){
			return new Result_train("0","查询结果为空，无匹配公司");
		}else
			return  new Result_train("0", "接口返回成功", list);
	}
}
