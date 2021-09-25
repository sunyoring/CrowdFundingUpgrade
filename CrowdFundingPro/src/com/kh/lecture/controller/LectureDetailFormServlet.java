package com.kh.lecture.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.lecture.model.service.LectureService;
import com.kh.lecture.model.vo.Lecture;
import com.kh.lecture.model.vo.LectureInfo;
import com.kh.user.model.vo.ULecture;

/**
 * Servlet implementation class LectureDetailFormServlet
 */
@WebServlet("/lectureDetail.le")
public class LectureDetailFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureDetailFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("***********************************************");
		String isDuplicate = null;
		
//		User loginUser = (User)request.getSession().getAttribute("loginUser");
		String lecCode = request.getParameter("code");
		
		System.out.println(lecCode);
		
		Lecture lecture = new LectureService().selectLecture(lecCode);
		
		if(lecture == null) { System.out.println("fail");} 
		else {System.out.println("success");}
		/*
		 * if(loginUser != null) { isDuplicate = new
		 * LectureService().checkLectureEnrollment(loginUser,lecCode); }
		 */
		HttpSession session = request.getSession();
			ArrayList<ULecture> ulList = new LectureService().selectLectureList(lecCode);
			System.out.println("해당 강의의 수강정보 리스트  : " + ulList);
			session.setAttribute("ulList", ulList);		
		
		LectureInfo info = new LectureService().getLectureCount(lecture);
	
	
		int count = info.getCount();
		System.out.println("count : " +  count);
		request.setAttribute("lecture", lecture);
		request.setAttribute("count", count);
		request.setAttribute("isDuplicate", isDuplicate);
		
		RequestDispatcher view = request.getRequestDispatcher("views/lecture/lectureDetailForm.jsp");
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
