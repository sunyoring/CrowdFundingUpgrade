package com.kh.lecture.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.lecture.model.service.LectureService;
import com.kh.lecture.model.vo.Lecture;
import com.kh.lecture.model.vo.LectureInfo;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class LectureSignOutServlet
 */
@WebServlet("/lecCancle.le")
public class LectureCancleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureCancleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		String lecCode = request.getParameter("code");
//		Lecture lecture = (Lecture)request.getAttribute("lecture");
		System.out.println("수강 취소 서블릿 강의코드 : " + lecCode);
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		System.out.println("수강 취소 서블릿  로그인유저 : " + loginUser);

		System.out.println(loginUser.getUserNo());
		
//		Lecture lecture = (lecCode != null) ? new LectureService().selectLecture(lecCode) : null;
		
		Lecture lecture = new LectureService().selectLecture(lecCode);
		
		System.out.println("수강 취소 서블릿 강의객체 : " + lecture);

		result = new LectureService().cancleLectureRegist(lecCode, loginUser.getUserNo());
		
		if(result > 0) {
			request.setAttribute("msg", "수강 취소 되었습니다.");
			request.setAttribute("lecture",lecture);
			response.sendRedirect(request.getContextPath() + "/lecture.le");				
		} else {
			request.setAttribute("msg","수강 신청이 정상적으로 수행되지 못했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
