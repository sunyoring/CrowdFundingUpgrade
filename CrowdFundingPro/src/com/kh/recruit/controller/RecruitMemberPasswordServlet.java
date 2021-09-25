package com.kh.recruit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.kh.common.MailService;
import com.kh.recruit.model.service.RecruitService;

/**
 * Servlet implementation class RecruitMemberPasswordServlet
 */
@WebServlet("/recruitMemberPassword.do")
public class RecruitMemberPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitMemberPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recruitment Id와 RecruitMember Email을 입력받아서 
		// password 반환하는 서블릿
		
		JSONObject obj = (JSONObject) JSONValue.parse(request.getReader());
		
		String rid = (String) obj.get("rid");
		String email = (String) obj.get("email");
		
		String password = new RecruitService().selectRecruitMemberPasswordWithIdAndEmail(rid, email);
		
		PrintWriter out = response.getWriter();
		
		if (password == null) {
			out.print("fail");
		} else {
			
			// 이메일 발송 
			// 여기도 추후에 꾸며도 나쁘진 않을듯
			MailService.send("[CROWD FUNDING] 공고 지원서 비밀번호 발송", "비밀번호 : " + password, email);
			
			out.print("success");
		}
		
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
