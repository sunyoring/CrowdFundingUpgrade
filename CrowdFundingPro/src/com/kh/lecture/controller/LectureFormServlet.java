package com.kh.lecture.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.lecture.model.service.LectureService;
import com.kh.lecture.model.vo.Lecture;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class LectureFormServlet
 */
@WebServlet("/lecture.le")
public class LectureFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LectureFormServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		String title = request.getParameter("title");
		String lectureCode = request.getParameter("lectureCode");
		int listCount = (new LectureService().selectLectureList()).size();
		int currentPage; // 현재 페이지(즉, 요청한 페이지)
		int startPage; // 현재 페이지에 하단에 보여지는 페이징 바의 시작 수
		int endPage; // 현재 페이지에 하단에 보여지는 페이징 바의 끝 수
		int maxPage; // 전체 페이지에서의 가장 마지막 페이ㅣㅈ
		int pageLimit; // 한 페이지 하단에 보여질 페이지 최대 개수
		int lectureLimit; // 한 페이지에 보여질 강의 최대 갯수
		
		currentPage = 1;
		
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 10;
		lectureLimit = 5;
		maxPage = (int) Math.ceil((double)listCount / lectureLimit);
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, lectureLimit);
		
		// List 처리
		int startRow = (currentPage - 1) * lectureLimit + 1;
		int endRow = startRow + lectureLimit - 1;
		
		ArrayList<Lecture> list = null;
		
		if ((lectureCode == null ||lectureCode.equals("")) && (title == null || title.equals(""))) {
			list = new LectureService().selectLectureList(startRow, endRow);
		}
		
		request.setAttribute("pi", pi);
		request.setAttribute("lectureList", list);
		request.setAttribute("loginUser", loginUser);
		RequestDispatcher view = request.getRequestDispatcher("views/lecture/lectureForm.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
