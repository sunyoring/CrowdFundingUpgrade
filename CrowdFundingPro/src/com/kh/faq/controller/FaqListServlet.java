package com.kh.faq.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.faq.model.service.FaqService;
import com.kh.faq.model.vo.Faq;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class FaqListServlet
 */
@WebServlet("/list.fq")
public class FaqListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 글번호, 질문, 타겟유저
		
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		String userCode = null;
		
		//loginUser의 userCode 가져오기
		if(loginUser == null) {
			userCode = "00";
		}
		else {
			userCode = loginUser.getUserCode();
		}
		
		ArrayList<Faq> list = new FaqService().selectList(userCode);
		
		request.setAttribute("list", list);

		if(loginUser == null) { //로그인 안했을 때
			
			request.getRequestDispatcher("views/faq/faqList.jsp").forward(request,response);
		}
		else {	//로그인 했을 때
			
			if(userCode.equals("01")) {	//01:관리자 
				//관리자 페이지 호출
				request.getRequestDispatcher("views/faq/faqManagement.jsp").forward(request, response);
			} 
			else {	//관리자 이외의 사용자 (02, 03)
				
				request.getRequestDispatcher("views/faq/faqList.jsp").forward(request,response); 
			}
			 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
 