package com.kh.project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.project.model.service.ProjectService;
import com.kh.project.model.vo.Project;
//import com.kh.user.model.vo.User;

/**
 * Servlet implementation class ProjectUpdateFormServlet
 */
@WebServlet("/update.do")
public class ProjectUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pCode=Integer.parseInt(request.getParameter("pCode"));
		//System.out.println("p="+pCode);//
		int fn=Integer.parseInt(request.getParameter("fileNo"));
		//System.out.println("f="+fn);
		
		
		
		
		
		
		//projectSelect
		Attachment at=new ProjectService().updateASelect(fn);
		
		if(at!=null) {
			Project pj=new ProjectService().updatePSelect(fn);
			
			if(pj !=null) {
				request.setAttribute("pj", pj);
				request.setAttribute("at", at);
				request.getRequestDispatcher("views/project/updateFormView.jsp").forward(request, response);
			}else {
				
				
				request.getSession().setAttribute("msg", "프로젝트를 불러오는데 실패했습니다");
				response.sendRedirect("views/common/errorPage.jsp");
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
