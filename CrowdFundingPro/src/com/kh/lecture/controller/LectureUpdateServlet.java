package com.kh.lecture.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.service.CommonService;
import com.kh.common.model.vo.Attachment;
import com.kh.lecture.model.service.LectureService;
import com.kh.lecture.model.vo.Lecture;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class LectureUpdateServlet
 */
@WebServlet("/lectureUpdate.le")
public class LectureUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10 * 1024 * 1024;
			
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "\\lectureImage\\";
			System.out.println("savePath"+ savePath);

			MultipartRequest multiRequest = new MultipartRequest(request, savePath,maxSize ,"UTF-8",new MyFileRenamePolicy());
	
			Attachment at = null;
			String originName = null;
			String changeName = null;
			
			if( multiRequest.getOriginalFileName("selectImg") != null || multiRequest.getParameter("lectureImage") != null) {
				
				String str = null;
				if (multiRequest.getOriginalFileName("selectImg") == null) {
					changeName = multiRequest.getParameter("lectureImage");
				} else {
					str = "selectImg";
				
						
				originName = multiRequest.getOriginalFileName(str);
				changeName = multiRequest.getFilesystemName(str);
				
				at = new Attachment();
				
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);
				
				int result2 = new CommonService().updateLectureAttachment(at,originName);
				
				}
			
				String title = multiRequest.getParameter("lectureTitle");
				int number = 0;
				try {
				number = Integer.parseInt(multiRequest.getParameter("lectureNumber"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				String lectureCode = multiRequest.getParameter("lectureCode");
				String address = multiRequest.getParameter("lectureAddress");
				String topic = multiRequest.getParameter("lectureTopic");
				Date date = Date.valueOf(multiRequest.getParameter("lectureDate"));
				int time = Integer.parseInt(multiRequest.getParameter("lectureTime"));
				String image = changeName;
				String content = multiRequest.getParameter("lectureContent");
				String lecturer = multiRequest.getParameter("lecturer");
				
				Lecture lecture = new Lecture(lectureCode, title,number,address,topic,date,time,image,content,lecturer);
				
				
				int result = new LectureService().updateLecture(lecture);
				
				if ( result != 0) {
					System.out.println("수정 성공");
					response.sendRedirect("lecture.le");
				} else {
					System.out.println("수정 실패");
					request.setAttribute("msg", "Failed to create new lecture");
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
					
				}
			}
				} else {
				
				System.out.println("MultiRequest Error");
				request.setAttribute("msg", "Failed to call the multiRequest");
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
