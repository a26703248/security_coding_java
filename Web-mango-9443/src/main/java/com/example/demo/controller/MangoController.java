package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mango")
public class MangoController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/input")
	public String input(Model model, HttpServletRequest req, HttpServletResponse resp) {
//		Encoder encoder = ESAPI.encoder();
//		String myName = encoder.encodeForHTML(req.getParameter("myName"));
		String myName = req.getParameter("myName");
		System.out.println(myName);
		List list = null;
		try {
			String sql = "select myname, amount, cardNo, memo from Mango where myname='" + myName + "'";
			System.out.println(sql);
			list = jdbcTemplate.queryForList(sql);			
		} catch (Exception e) {
			
		}
		model.addAttribute("myName", myName);
		model.addAttribute("list", list);
		
		return "mango/input";
	}
	
	@RequestMapping("/add")
	public String add(Model model, HttpServletRequest req, HttpServletResponse resp) {
		String myName = req.getParameter("myName");
		String cardNo = req.getParameter("cardNo");
		String amount = req.getParameter("amount");		
		String memo = req.getParameter("memo");		
		String sql = "insert into mango(myname, cardno, amount, memo) values(?, ?, ?, ?)";
		int rowCount = jdbcTemplate.update(sql, myName, cardNo, amount, memo);
		System.out.println("rowCount = " + rowCount);
		if(rowCount > 0) {
			Cookie myNameCookie = new Cookie("myName", myName);
			Cookie cardNoCookie = new Cookie("cardNo", cardNo);
			// Part I
			// 加入 cookie 安全設定
			//myNameCookie.setHttpOnly(true); // 防止 local 利用 document.cookie 匯出 cookie 資料
			//cardNoCookie.setHttpOnly(true);
			//myNameCookie.setSecure(true);
			//cardNoCookie.setSecure(true);
			// 將 cookie 傳給瀏覽器
			//resp.addCookie(myNameCookie);
			//resp.addCookie(cardNoCookie);
			
			// Part II
			// 同源才可以使用 Cookie
			resp.addHeader("Set-Cookie", "myName=" + myName + ";Path=/mango/input;HttpOnly=true;Secure=true;SameSite=Strict");
			resp.addHeader("Set-Cookie", "cardNo=" + cardNo + ";Path=/mango/input;HttpOnly=true;Secure=true;SameSite=Strict");
		}
		return "redirect:./input?myName="+myName;
	}
	
	
}
