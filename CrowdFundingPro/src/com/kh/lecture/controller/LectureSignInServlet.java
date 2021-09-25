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
 * Servlet implementation class LectureSignInServlet
 */
@WebServlet("/signIn.le")
public class LectureSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LectureSignInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int result = 0;
		String lecCode = request.getParameter("code");
		PrintWriter printWriter = response.getWriter();

		User loginUser = (User) request.getSession().getAttribute("loginUser");
		response.setContentType("text/html; charset=UTF-8");

		System.out.println(loginUser.getUserNo());

		Lecture lecture = (lecCode != null) ? new LectureService().selectLecture(lecCode) : null;
		LectureInfo info = new LectureService().getLectureCount(lecture);
		int count = info.getCount();

		if (count < lecture.getLectureNum()) {

			result = (loginUser != null && lecture != null) ? new LectureService().signInLecture(lecture, loginUser)
					: 0;

			if (result > 0) {

				request.setAttribute("msg", "수강 신청 완료되었습니다.");
				request.setAttribute("lecture", lecture);
				request.setAttribute("count", count);

				response.sendRedirect(request.getContextPath() + "/lecture.le");

			} else {
				request.setAttribute("msg", "수강 신청이 정상적으로 수행되지 못했습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("lecture", lecture);
			request.setAttribute("count", count);
			printWriter.println("<script> alert('신청 가능인원을 초과하였습니다.'); location.href='"
					+ "<%=request.getContextPath()%>/lecture.le" + "'</script>");
			printWriter.close();
			RequestDispatcher view = request.getRequestDispatcher("views/lecture/lectureDetailForm.jsp");
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
