package com.kh.recruit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.recruit.model.service.RecruitService;
import com.kh.recruit.model.vo.RecruitCode;
import com.kh.recruit.model.vo.Recruitment;

/**
 * Servlet implementation class RecruitContentListServlet
 */
@WebServlet("/recruitContentList.do")
public class RecruitContentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitContentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("rid") == null) {
			// 에러 페이지 처리
			request.getSession().setAttribute("msg", "공고 조회 실패");
			response.sendRedirect("views/common/errorPage.jsp");
		}
		
		// Recruitment 조회
		int rid = Integer.parseInt(request.getParameter("rid"));
		
		Recruitment r = new RecruitService().selectRecruitment(rid);
		request.setAttribute("r", r);
		
		// Recruit Code 처리
		ArrayList<RecruitCode> code = new RecruitService().selectRecruitCode();
		request.setAttribute("code", code);
		
		// RecruitmentTitle 조회
		ArrayList<String> titles = new RecruitService().selectAllTitle();
		request.setAttribute("titles", titles);
		
		request.getRequestDispatcher("views/recruit/recruit_content.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
