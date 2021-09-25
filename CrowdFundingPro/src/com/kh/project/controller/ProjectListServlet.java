package com.kh.project.controller;

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
import com.kh.project.model.service.ProjectService;
import com.kh.project.model.vo.Project;

/**
 * Servlet implementation class ProjectListServlet
 */
@WebServlet("/projectList.do")
public class ProjectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 기존 코드 -> ajax로 수정함 projectPage 서블릿을 새로 만듬
//		ArrayList<Project> list=new ProjectService().selectList();
//		request.setAttribute("list", list);
//		request.getRequestDispatcher("views/project/projectListView.jsp").forward(request, response);

		// Page 처리
		int curPage = 1; // 현재 페이지 (요청한 페이지)

		if (request.getParameter("page") != null) {
			curPage = Integer.parseInt(request.getParameter("page"));
		}

		// Category 처리
		String categoryNo = request.getParameter("categoryNo");
//		System.out.println(categoryNo);

		// Search 검색어 처리
		String searchValue = request.getParameter("searchValue");
		if (searchValue == null) {
			searchValue = "";
		}

		// System.out.println(searchValue);

		int projectLimit = 30; // 한번 요청에 보여질 프로젝트 최대 수

		int startRow = (curPage - 1) * projectLimit + 1;
		int endRow = startRow + projectLimit - 1;

		// System.out.println(startRow + " " + endRow + " " + categoryNo);

		// 원하는 수만큼 한번에 가져오기
		ArrayList<Project> list = null;

		int searchCnt = -1;

		String categoryName;

		if (categoryNo == null || categoryNo.equals("0")) {
			categoryName = "전체";
			// 검색어가 없는 경우 전체 조회
			if (searchValue.equals("")) {
				list = new ProjectService().selectProjectList(startRow, endRow);
			} else {
				list = new ProjectService().selectProjectListWithSearchValue(startRow, endRow, searchValue);
				searchCnt = new ProjectService().getSearchCount(searchValue);
			}
		} else {
			categoryName = new ProjectService().getCategoryName(categoryNo);

			// 검색어가 없는 경우 카테고리별 조회
			if (searchValue.equals("")) {
				list = new ProjectService().selectProjectListWithCategory(startRow, endRow,
						Integer.parseInt(categoryNo));
			} else {
				list = new ProjectService().selectProjectListWithCategoryAndSearchValue(startRow, endRow,
						Integer.parseInt(categoryNo), searchValue);
				searchCnt = new ProjectService().getSearchCountWithCategoryNo(Integer.parseInt(categoryNo),
						searchValue);
			}
		}

		Map<String, String> search = new HashMap<>();
		search.put("categoryName", categoryName);
		search.put("value", searchValue);
		search.put("cnt", String.valueOf(searchCnt));

		// System.out.println(categoryName);

		Map<String, Object> map = new HashMap<>();
		map.put("project", list);
		map.put("search", search);

		response.setContentType("application/json; charset=utf-8");

		new Gson().toJson(map, response.getWriter());
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
