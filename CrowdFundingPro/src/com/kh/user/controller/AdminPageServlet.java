package com.kh.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class UserPageServlet
 */
@WebServlet("/userList.do")
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<User> UList = new UserService().selectUserList();

		
		if(!UList.isEmpty()) {
			
			request.setAttribute("UserList", UList);
			request.getRequestDispatcher("views/user/adminPage.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "관리자페이지로 이동이 실패했습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
		
		/*
		JSONArray jArr = new JSONArray();

		JSONObject jsonUser = null;

		for (User u : UList) {
				
				jsonUser = new JSONObject();
		
				jsonUser.put("유형", (!u.getUserCode().equals("02") ? (u.getUserCode().equals("01") ? "관리자" : "사업자회원" ) : "일반회원" ));
				jsonUser.put("이름", u.getUserName());
				jsonUser.put("이메일", u.getEmailId());
				jsonUser.put("생년월일", u.getUserSsn().substring(0, 6));
				jsonUser.put("연락처", u.getUserPhone());
				jsonUser.put("가입일자", u.getJoinDate()+"");
				jsonUser.put("탈퇴여부", (u.getStatus().equals("N") ? false : true ));

				jArr.add(jsonUser);
			
		}

		JSONObject jsonMap = new JSONObject();

		if (jArr != null) {

			jsonMap.put("jArr", jArr);
		}
		System.out.println("jArr : " + jArr);
		System.out.println("jsonMap : " + jsonMap);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonMap);
	
		*/
		
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
