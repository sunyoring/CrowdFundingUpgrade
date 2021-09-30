package com.kh.event.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.event.model.service.EventService;

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet("/delete.eco")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eno =  Integer.parseInt(request.getParameter("eno"));
		int cno = Integer.parseInt(request.getParameter("cno"));
		
		int result = new EventService().deleteComment(cno);
		
		response.setContentType("text/html;charset=utf-8");
		
		
		/*
		 * 	PageUrl = "views/user/userLoginForm.jsp";
			writer.println("<script>alert('로그인이 필요합니다.'); location.href='" + PageUrl
					+ "';</script>");
			writer.close();
		 * */
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
		    out.print("<script>alert('댓글을 성공적으로 삭제하였습니다.');</script>");
		    out.print("location.href = '<%=request.getContextPath()%>/eDetail.do?eno="+eno);
			response.getWriter().close();
			
		}else {
			response.getWriter().print("<script>alert('댓글 삭제 실패하였습니다.')</script>");
			
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
