package com.kh.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.project.model.service.ProjectService;

/**
 * Servlet implementation class CategoryListServlet
 */
@WebServlet("/categoryList.do")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 카테고리번호와 이름을 가져오는 서블릿
		Map<Integer, String> category = new ProjectService().selectCategoryList();
		
		// 카테고리별 갯수 가져오기
		Map<Integer, Integer> categoryCnt = new ProjectService().getListCountWithCategoryNo();
		
//		System.out.println(map.size());
		
		Map<String, Object> result = new HashMap<>();
		result.put("category", category);
		result.put("categoryCnt", categoryCnt);
		
		response.setContentType("application/json; charset=utf-8");
		
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
