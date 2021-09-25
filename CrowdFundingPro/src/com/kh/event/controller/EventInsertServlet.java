package com.kh.event.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.event.model.service.EventService;
import com.kh.event.model.vo.Event;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class EventInsertServlet
 */
@WebServlet("/eventInsert.do")
public class EventInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (ServletFileUpload.isMultipartContent(request)) {

			int maxSize = 10 * 2048 * 2048;
			
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "\\upfiles\\";
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new MyFileRenamePolicy());
		
			
			
			String eventStartDate = multiRequest.getParameter("eventStartDate");
			String eventEndDate = multiRequest.getParameter("eventEndDate");
			String eventName = multiRequest.getParameter("eventName");			
			
			if ( 
				eventStartDate == null || eventStartDate.equals("") || 
				eventEndDate == null|| eventEndDate.equals("") || 
				eventName == null || eventName.equals("")) 
				 {
				
				request.getSession().setAttribute("msg", "이벤트 등록 실패");
				response.sendRedirect("views/common/errorPage.jsp");

			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date start = new Date();
			Date end = new Date();
			try {
				start = sdf.parse(eventStartDate);
				end = sdf.parse(eventEndDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}


			// 파일 업로드 처리
			Attachment at = null;
			
			//등록할 이벤트 객체
			Event event = null;
			
			
			
			if (multiRequest.getOriginalFileName("eventio") != null) {
				String originName = multiRequest.getOriginalFileName("eventio");
				String changeName = multiRequest.getFilesystemName("eventio");
				at = new Attachment();
				
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);
				
				event = new Event(eventName, changeName, start, end);

			}
					
			int result = new EventService().insertEvent(event, at);
		

			if (result > 0) {
				request.getSession().setAttribute("msg", "이벤트 등록 성공");
				response.sendRedirect(request.getContextPath() + "/eList.do");


			} else {
				if (at != null) {
					File failedFile = new File(savePath + at.getChangeName());
					failedFile.delete();
					
					// 에러 페이지 처리
					request.getSession().setAttribute("msg", "이벤트 등록 실패");
					response.sendRedirect("views/common/errorPage.jsp");
				}
			}

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
