package com.kh.recruit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.model.vo.PageInfo;
import com.kh.recruit.model.service.RecruitService;
import com.kh.recruit.model.vo.RecruitCode;
import com.kh.recruit.model.vo.Recruitment;

/**
 * Servlet implementation class RecruitListServlet
 */
@WebServlet("/recruitList.do")
public class RecruitListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// title 구분
		String title = request.getParameter("title");
		
		// code 구분
		String code = request.getParameter("code");
		
		// Page 처리
		int listCount; // 총 게시글 갯수
		int currentPage; // 현재 페이지(즉, 요청한 페이지)
		int startPage; // 현재 페이지에 하단에 보여지는 페이징 바의 시작 수
		int endPage; // 현재 페이지에 하단에 보여지는 페이징 바의 끝 수
		int maxPage; // 전체 페이지에서의 가장 마지막 페이ㅣㅈ
		
		int pageLimit; // 한 페이지 하단에 보여질 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글 최대 갯수
		
		if ((code == null || code.equals("")) && (title == null || title.equals(""))) {
			listCount = new RecruitService().getListCount();
		} else if ((code == null || code.equals(""))) {
			listCount = new RecruitService().getListCountWithTitle(title);
		} else if ((title == null || title.equals(""))) {
			listCount = new RecruitService().getListCountWithCode(code);
		} else {
			listCount = new RecruitService().getListCountWithCodeTitle(code, title);
		}
		
		currentPage = 1;
		
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		pageLimit = 3;
		
		boardLimit = 5;
		
		maxPage = (int) Math.ceil((double)listCount / boardLimit);
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		
		if (maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);
		
		// List 처리
		int startRow = (currentPage - 1) * boardLimit + 1;
		int endRow = startRow + boardLimit - 1;
		
		ArrayList<Recruitment> list = null;
		
		if ((code == null || code.equals("")) && (title == null || title.equals(""))) {
			list = new RecruitService().selectList(startRow, endRow);
		} else if ((code == null || code.equals(""))) {
			list = new RecruitService().selectListWithTitle(startRow, endRow, title);
		} else if ((title == null || title.equals(""))) {
			list = new RecruitService().selectListWithCode(startRow, endRow, code);
		} else {
			list = new RecruitService().selectListWithCodeTitle(startRow, endRow, code, title);
		}
		
		response.setContentType("application/json; charset=utf-8");
		
		Map<String, Object> result = new HashMap<>();
		result.put("pi", pi);
		result.put("list", list);
		
		new Gson().toJson(result, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
