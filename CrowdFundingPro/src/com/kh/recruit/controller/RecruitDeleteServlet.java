package com.kh.recruit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.recruit.model.service.RecruitService;

/**
 * Servlet implementation class RecruitDeleteServlet
 */
@WebServlet("/recruitDelete.do")
public class RecruitDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("recruitId"));
		
		if (request.getParameter("recruitId") == null) {
			// 공고 삭제 실패
			request.getSession().setAttribute("msg", "공고 삭제 실패");
			response.sendRedirect(request.getContextPath() + "/recruitPage.do");
		}
		
		int result = new RecruitService().deleteRecruitment(id);
		
		if (result > 0) {
			request.getSession().setAttribute("msg", "공고 삭제 성공");
			response.sendRedirect(request.getContextPath() + "/recruitPage.do");
		} else {
			// 에러 페이지 처리
			request.getSession().setAttribute("msg", "공고 삭제 실패");
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
