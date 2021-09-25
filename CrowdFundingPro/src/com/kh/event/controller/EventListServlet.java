package com.kh.event.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.event.model.service.EventService;
import com.kh.event.model.vo.Event;

/**
 * Servlet implementation class EventListServlet
 */
@WebServlet("/eList.do")
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// Page 처리
		int listCount = 0; // 총 게시글 갯수
		int currentPage = 1; // 현재 페이지(즉, 요청한 페이지)
		int startPage; // 현재 페이지에 하단에 보여지는 페이징 바의 시작 수
		int endPage; // 현재 페이지에 하단에 보여지는 페이징 바의 끝 수
		int maxPage; // 전체 페이지에서의 가장 마지막 페이지	
		int pageLimit = 3; // 한 페이지 하단에 보여질 페이지 최대 개수
		int boardLimit = 5; // 한 페이지에 보여질 게시글 최대 갯수

		listCount = new EventService().getListCount();

		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println("현재 페이지는 ? " + currentPage);
		
		maxPage = (int) Math.ceil((double) listCount / boardLimit);

		//가장 마지막 페이지는 전체 리스트 나누기 보여질 페이지  게시물이  총 10개이고  3개씩 보여준다고 하면 
		//페이지는 4개가 나온다. 10/ 3 == 3.3333.. 이므로 더블형으로 계산하고 올림처리하여 정수로 만들어준다.
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		// * startPage : 현재 페이지에 보여지는 페이징 바의 시작 수
		/*
		 * ex) pageLimit : 10
		 * 1, 11, 21, 31, ...			=> n * 10 + 1
		 * 
		 * currentPage = 1				=> 0 * 10 + 1
		 * currentPage = 5				=> 0 * 10 + 1
		 * currentPage = 10				=> 0 * 10 + 1
		 * 
		 * currentPage = 11				=> 1 * 10 + 1
		 * currentPage = 12				=> 1 * 10 + 1
		 * currentPage = 20				=> 1 * 10 + 1 
		 * 
		 * currentPage = 1~10			=> n=0
		 * currentPage = 11~20			=> n=1
		 * 
		 * 								   n = (currentPage - 1) / pageLimit
		 */
		endPage = startPage + pageLimit-1;
		//마지막 페이지는 시작 페이지에서 화면에서 보여질 총페이지 갯수-1을 더해주면 된다.
		
		//단, maxPage가 endPage보다 작다면 endPage가 maxPage가 되어야 함.
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);
		
		
		//List처리
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1; 
		//가져올 행 수 : 현재 페이지가 1이면 1~5번 행 가져와야함 
		/*
		 * 현재페이지 -1 = n
		 * 
		 * 0 * 5 + 1 = 1
		 * 1 * 5 + 1 = 6
		 * 2 * 5 + 1 = 11 
		 * 
		 * 
		 * 
		 * */
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		System.out.println("서블릿 시작 행 번호 : " + startRow);
		System.out.println("서블릿 종료 행 번호  : " + endRow);
		
		ArrayList<Event> eList = new EventService().selectEventList(startRow, endRow);

		
		System.out.println("이벤트 리스트 서블릿 : " + eList);
		System.out.println("페이지 정보 서블릿 : " + pi);
		
		request.setAttribute("eList", eList);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("views/event/event.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
