package com.kh.lecture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.lecture.model.service.LectureService;
import com.kh.lecture.model.vo.Lecture;

/**
 * Servlet implementation class LectureUpdateFormServlet
 */
@WebServlet("/lecUpdateForm.le")
public class LectureUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lecCode = request.getParameter("code");
		
		Lecture lecture = new LectureService().selectLecture(lecCode);
		
		if (lecture != null) {
		request.setAttribute("lecture", lecture);
		request.getRequestDispatcher("views/lecture/lectureUpdateForm.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "강의 정보를 불러오는데 실패하였습니다.");
			response.sendRedirect("views/common/errorPage.jsp");
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
