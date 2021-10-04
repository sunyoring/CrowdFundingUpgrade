package com.kh.event.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.event.model.service.EventService;
import com.kh.event.model.vo.EventComment;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class EnrollComment
 */
@WebServlet("/enrollComment.ev")
public class EnrollComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loginUser = (User)(request.getSession().getAttribute("loginUser"));
		EventComment ec = new EventComment();
		String nickName = null;
		String emailId = null;
		int cPwd = 0;
		int result = 0;
		
		int eno = Integer.parseInt(request.getParameter("eno"));
		int cParent;
		String comment = request.getParameter("comment");

		if(request.getParameter("cId") == null) {
			cParent = 0;
		}else {
			cParent = Integer.parseInt(request.getParameter("cId"));

		}
		
		if(eno != 0 && comment != null && comment !="") {
			ec.setENo(eno);
			ec.setComment(comment);
		}
	
		
		if(cParent != 0) { //부모 댓글 존재하면 셋팅
			ec.setCParent(cParent);
		}
		
		if(loginUser == null) {
			nickName = request.getParameter("nickname");
			cPwd = Integer.parseInt(request.getParameter("cPwd"));
			System.out.println(nickName);
			ec.setName(nickName);
			ec.setEPwd(cPwd);
			result = new EventService().anonymousComment(ec);

		}else {
			emailId = loginUser.getEmailId();
			ec.setEmailId(emailId);
			result = new EventService().enrollComment(ec);

		}

				
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/eDetail.do?eno="+eno);

		}else {
			request.getSession().setAttribute("msg", "댓글 등록 실패");
			response.sendRedirect("views/common/errorPage.jsp");

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
