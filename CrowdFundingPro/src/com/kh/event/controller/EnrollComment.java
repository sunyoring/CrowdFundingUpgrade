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
		
		int eno = Integer.parseInt(request.getParameter("eno"));
		int cParent;
		String emailId = loginUser.getEmailId();
		String comment = request.getParameter("comment");
		System.out.println(eno);
		System.out.println(emailId);
		System.out.println(comment);
		if(request.getParameter("cId") == null) {
			cParent = 0;
		}else {
			cParent = Integer.parseInt(request.getParameter("cId"));

		}
		
		int result = 0;
		
		if(eno != 0 && emailId != null && emailId != "" &&
		   comment != null && comment !="") {
			
		result = new EventService().enrollComment(new EventComment(eno,emailId,cParent,comment));
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
