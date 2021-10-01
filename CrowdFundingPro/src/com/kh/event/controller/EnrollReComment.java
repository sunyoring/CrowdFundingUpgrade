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
 * Servlet implementation class EnrollReComment
 */
@WebServlet("/enrollReComment.ev")
public class EnrollReComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollReComment() {
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
		String cParent = request.getParameter("cNum");
		System.out.println("답글 서블릿 부모 댓글 번호: " + cParent);
		int cParentNo = Integer.parseInt(cParent);
		String comment = request.getParameter("comment");

		if(cParentNo != 0) { //부모 댓글 존재하면 셋팅
			ec.setcParent(cParentNo);
		}
		
		if(eno != 0 && comment != null && comment !="") {
			ec.seteNo(eno);
			ec.setComment(comment);
		}
	

		if(loginUser == null) {
			nickName = request.getParameter("nickname");
			cPwd = Integer.parseInt(request.getParameter("cPwd"));
			System.out.println(nickName);
			ec.setName(nickName);
			ec.setePwd(cPwd);
			result = new EventService().anonymousReComment(ec);

		}else {
			emailId = loginUser.getEmailId();
			ec.setEmailId(emailId);
			result = new EventService().enrollReComment(ec);

		}
		
		  if(result > 0) {
		  response.sendRedirect(request.getContextPath()+"/eDetail.do?eno="+eno);
		  
		  }else { request.getSession().setAttribute("msg", "댓글 등록 실패");
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
