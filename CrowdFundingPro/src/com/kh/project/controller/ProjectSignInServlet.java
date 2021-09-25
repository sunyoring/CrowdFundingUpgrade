package com.kh.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.project.model.service.ProjectService;
import com.kh.project.model.vo.Project;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class ProjectSignInServlet
 */
@WebServlet("/signIn.do")
public class ProjectSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectSignInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		User loginUser = (User) session.getAttribute("loginUser");

		// public Project projectSelect(int pCode) {
//		Project pj=new ProjectService().projectSelect(pCode); //--2 프로젝트 가져오기
		// SELECT
		// PROJECT_NAME,AMOUNT_GOAL,AMOUNT_PRESENT,DDLN,DELIVERY_CHARGE,DETAIL_INTRO,FILE_NO,CHANGE_NAME

//		System.out.println("1.pj:"+pj);



		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String PageUrl = "projectPage.do";
		
		if(loginUser != null) {
			
			int point = loginUser.getPoint();
			int price = ((Project) session.getAttribute("pj")).getDeliveryCharge();

			System.out.println("신청하기 버튼 loginUser:" + loginUser);
			int userNo = loginUser.getUserNo();

//			try {
//				
//			}catch (Exception e ){
//				
//			}
//			
//			
			int pCode = ((Project) session.getAttribute("pj")).getProjectCode();
	

			if (price <= point) {

				if (pCode != 0 && userNo != 0) {
					int result = new ProjectService().insertSUP(userNo, pCode);

					if (result > 0) {

						request.setAttribute("msg", "INSERT SUCCESS");

						writer.println("<script>alert('펀딩 신청이 완료되었습니다. 신청하신 내역은 마이페이지에서 조회 가능합니다. '); location.href='"
								+ PageUrl + "';</script>");
						writer.close();

					}
				}
			} else {

				writer.println("<script>alert('잔액이 부족합니다. 마이페이지에서 충전 가능합니다. '); location.href='" + PageUrl
						+ "';</script>");
				writer.close();

			}

		}else {
			PageUrl = "views/user/userLoginForm.jsp";
			writer.println("<script>alert('로그인이 필요합니다.'); location.href='" + PageUrl
					+ "';</script>");
			writer.close();
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
