package com.kh.common.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.project.model.service.ProjectService;
import com.kh.project.model.vo.Project;

/**
 * Servlet implementation class SearchAllServlet
 */
@WebServlet("/search.do")
public class SearchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//세션정보를 가져와 담아주고 세션에 유저의 정보를 담는다.

		String keyword = request.getParameter("keyword");
		System.out.println("전체검색 서블릿 키워드 : " + keyword);
		ArrayList<Project> pList = new ProjectService().searchList(keyword);
//		ArrayList<Lecture> lList = new LectureService().searchList(keyword);
		session.setAttribute("keyword", keyword);

			session.setAttribute("pListResult", pList);
			response.sendRedirect("views/common/searchResult.jsp");

		
//		session.setAttribute("lListResult", lList);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
