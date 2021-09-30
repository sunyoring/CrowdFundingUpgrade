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
		
		String nickName = null;
		String emailId = null;
		int cPwd = 0;

		if(loginUser == null) {
			nickName = request.getParameter("nickname");
			cPwd = Integer.parseInt(request.getParameter("cPwd"));
		}else {
			emailId = loginUser.getEmailId();

		}
		
		int eno = Integer.parseInt(request.getParameter("eno"));
		int cParent;

		String comment = request.getParameter("comment");

		if(request.getParameter("cId") == null) {
			cParent = 0;
		}else {
			cParent = Integer.parseInt(request.getParameter("cId"));

		}
		
		EventComment ec = new EventComment();
		
		//로그인 상태가 아닐 땐 닉네임과 비밀번호를 객체에 set
		if(nickName != null && nickName !="" && cPwd !=0) {
			ec.setEmailId(nickName);
			ec.setePwd(cPwd);
		}else { //아니라면 로그인한 계정의 이메일 아이디를 넣을 것.
			ec.setEmailId(emailId);
		}		
		
		if(eno != 0 && comment != null && comment !="") {
			ec.seteNo(eno);
			ec.setComment(comment);
		}
	
		
		if(cParent != 0) { //부모 댓글 존재하면 셋팅
			ec.setcParent(cParent);
		}
		
		
		int result = new EventService().enrollComment(ec);
		
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
