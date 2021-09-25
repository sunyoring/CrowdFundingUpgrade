package com.kh.recruit.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.recruit.model.service.RecruitService;
import com.kh.recruit.model.vo.Recruitment;

/**
 * Servlet implementation class RecruitUpdateServlet
 */
@WebServlet("/recruitUpdate.do")
public class RecruitUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("recruitId"));
		String title = request.getParameter("recruitName"); 
		String code = request.getParameter("recruitCode"); 
		String startStr = request.getParameter("recruitStartDate");
		String endStr = request.getParameter("recruitEndDate"); 
		String time = request.getParameter("recruitTime");
		String content1 = request.getParameter("recruitContent1");
		String content2 = request.getParameter("recruitContent2");
		String content3 = request.getParameter("recruitContent3");
		String content4 = request.getParameter("recruitContent4");
		String content5 = request.getParameter("recruitContent5");
		String content6 = request.getParameter("recruitContent6");
		
		if (request.getParameter("recruitId") == null || request.getParameter("recruitId").equals("") ||
			title == null || title.equals("") ||
			code == null || code.equals("") ||
			startStr == null || startStr.equals("") ||	
			endStr == null || endStr.equals("") ||	
			time == null || time.equals("") ||	
			content1 == null || content1.equals("") ||	
			content2 == null || content2.equals("") ||	
			content3 == null || content3.equals("") ||	
			content4 == null || content4.equals("") ||	
			content5 == null || content5.equals("") ||	
			content6 == null || content6.equals("")) {
			// 공고 수정 실패
			request.getSession().setAttribute("msg", "공고 수정 실패");
			response.sendRedirect(request.getContextPath() + "/recruitContentList.do?rid=" + id);
		}
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = new Date();
		Date end = new Date();
		try {
			start = sdf.parse(startStr);
			end = sdf.parse(endStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Recruitment rm = new Recruitment(id, title, code, start, end, time, content1, content2, content3, content4, content5, content6); 

		int result = new RecruitService().updateRecruitment(rm);
		
		if (result > 0) {
			request.getSession().setAttribute("msg", "공고 수정 성공");
			response.sendRedirect(request.getContextPath() + "/recruitContentList.do?rid=" + id);
		} else {
			// 에러 페이지 처리
			request.getSession().setAttribute("msg", "공고 수정 실패");
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
