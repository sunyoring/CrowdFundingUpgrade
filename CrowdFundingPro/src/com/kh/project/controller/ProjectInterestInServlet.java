package com.kh.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.project.model.service.ProjectService;
import com.kh.project.model.vo.Project;
import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class ProjectInterestInServlet
 */
@WebServlet("/interestIn.do")
public class ProjectInterestInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectInterestInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
		
		User loginUser = (User) session.getAttribute("loginUser");
		
		int userNo = loginUser.getUserNo();
		
		
		
		
		int pCode = ((Project) session.getAttribute("pj")).getProjectCode();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String PageUrl = "projectPage.do";
		
		
		if(pCode !=0 && userNo !=0) {
			int result=new ProjectService().insertIP(userNo,pCode);
			
			if(result>0) {
				
				request.setAttribute("msg","INSERT SUCCESS" );
				
				writer.println("<script>alert('마이페이지에서 조회 가능합니다.'); location.href='" + PageUrl + "';</script>");
				writer.close();
				
				
				//request.getRequestDispatcher("projectPage.do").forward(request, response);
			}else {
				writer.println("<script>alert('실패'); location.href='" + PageUrl+ "';</script>");
				writer.close();
			}
			
			
		}else {
			//request.getSession().setAttribute("msg", "FAILED");
			//request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		
			PageUrl = "views/user/userLoginForm.jsp";
			writer.println("<script>alert('로그인이 필요합니다.'); location.href='" + PageUrl
					+ "';</script>");
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
