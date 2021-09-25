package com.kh.event.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.event.model.service.EventService;
import com.kh.event.model.vo.Event;

/**
 * Servlet implementation class EventDeleteServlet
 */
@WebServlet("/eDelete.do")
public class EventDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		
		int eno = Integer.parseInt(request.getParameter("eno"));
		System.out.println("eno : " + eno);
		int result = new EventService().deleteEvent(eno);
		ArrayList<Event> eList = new EventService().selectEventList();
		request.setAttribute("eList", eList);
		
		if(result > 0 ) {
			session.setAttribute("msg", "이벤트 삭제 완료");
			request.getRequestDispatcher("views/event/event.jsp").forward(request, response);
			

		}else {
			request.setAttribute("msg", "이벤트 삭제 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
