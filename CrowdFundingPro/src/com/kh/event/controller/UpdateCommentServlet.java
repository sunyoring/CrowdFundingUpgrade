package com.kh.event.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.event.model.service.EventService;
import com.kh.event.model.vo.Event;

/**
 * Servlet implementation class UpdateCommentServlet
 */
@WebServlet("/update.eco")
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int eno =  Integer.parseInt(request.getParameter("eno"));
		int cno = Integer.parseInt(request.getParameter("cno"));
		String comment = request.getParameter("comment");
		

		int result = new EventService().updateCommnet(cno,comment);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/eDetail.do?eno="+eno);
		}else {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>alert('댓글 수정을 실패하였습니다.')</script>");
			
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
