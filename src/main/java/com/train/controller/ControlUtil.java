package com.train.controller;

import com.train.model.Company;
import com.train.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@GetMapping(value = "/search/{companyName}")
	public List<Company> search( @PathVariable("companyName") String companyName) {
		System.out.println("进入"+companyName);
		List<Company> list =  companyService.findByCompanyNameContaining(companyName);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		return list;//companyService.findByCompanyNameContaining(companyName);
	}
	
}
