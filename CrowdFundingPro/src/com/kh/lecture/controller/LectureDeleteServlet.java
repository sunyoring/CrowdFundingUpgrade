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

/**
 * Servlet implementation class LectureDeleteServlet
 */
@WebServlet("/lectureDelete.le")
public class LectureDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lecCode = request.getParameter("code");
		Lecture lecture = new LectureService().selectLecture(lecCode);
		int result = (lecture != null) ? new LectureService().deleteLecture(lecture) : 0 ;
		
		if ( result > 0 ) {
			request.setAttribute("msg", "성공적으로 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/lecture.le");
		} else {
			request.setAttribute("msg","강의 삭제 실패. 해당되는 강의가 존재하지 않거나 잘못된 정보를 받았습니다.");
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
