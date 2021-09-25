package com.kh.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.project.model.service.ProjectService;
import com.kh.project.model.vo.Project;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class ProjectDeleteInter
 */
@WebServlet("/deleteInter.pro")
public class ProjectDeleteInter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectDeleteInter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		int pCode = ((Project)request.getSession().getAttribute("pj")).getProjectCode();
		int userNo = ((User)request.getSession().getAttribute("loginUser")).getUserNo();
		
		int result =  new ProjectService().deleteInterPro(pCode,userNo);
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter writer = response.getWriter();
		String PageUrl = "projectPage.do";
		

		if(result>0) {
			
			request.setAttribute("msg","INSERT SUCCESS" );
			writer.println("<script>alert('마이페이지에서 확인 가능합니다.'); location.href='" + PageUrl + "';</script>");
			writer.close();
			
			
			//request.getRequestDispatcher("projectPage.do").forward(request, response);
		}else {
			writer.println("<script>alert('실패'); location.href='" + PageUrl+ "';</script>");
			writer.close();
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
