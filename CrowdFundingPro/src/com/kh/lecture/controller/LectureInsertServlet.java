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
 * Servlet implementation class LectureInsertServlet
 */
@WebServlet("/lectureInsert.le")
public class LectureInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureInsertServlet() {
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
			
			if( multiRequest.getOriginalFileName("selectImg") != null || multiRequest.getOriginalFileName("lectureImage") != null) {
				
				String str = null;
				if (multiRequest.getOriginalFileName("selectImg") == null) {
					str = "lectureImage";
				} else {
					str = "selectImg";
				}
				
				at = new Attachment();
				
				originName = multiRequest.getOriginalFileName(str);
				changeName = multiRequest.getFilesystemName(str);
				
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);
				
				int result2 = new CommonService().insertLectureAttachment(at);
				
			
				String title = multiRequest.getParameter("lectureTitle");
				System.out.print("얘는 값이 뭐니:" + multiRequest.getParameter("lectureNumber"));
				int number = 0;
				try {
				number = Integer.parseInt(multiRequest.getParameter("lectureNumber"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				String address = multiRequest.getParameter("lectureAddress");
				String topic = multiRequest.getParameter("lectureTopic");
				Date date = Date.valueOf(multiRequest.getParameter("lectureDate"));
				int time = Integer.parseInt(multiRequest.getParameter("lectureTime"));
				String image = changeName;
				String content = multiRequest.getParameter("lectureContent");
				String lecturer = multiRequest.getParameter("lecturer");
				
				Lecture lecture = new Lecture(title,number,address,topic,date,time,image,content,lecturer);
				
//				this.lectureTitle = lectureTitle;
//				this.lectureNum = lectureNum;
//				this.lectureAddress = lectureAddress;
//				this.lectureTopic = lectureTopic;
//				this.lectureDate = lectureDate;
//				this.lectureTime = lectureTime;
//				this.lectureImage = lectureImage;
//				this.lectureContent = lectureContent;
				
				int result = new LectureService().insertLecture(lecture);
				
				if ( result != 0 ) {
					System.out.println("등록성공");
					response.sendRedirect("lecture.le");
				} else {
					System.out.println("등록실패");
					request.setAttribute("msg", "Failed to create new lecture");
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
					
				}
			} else {
				
				System.out.println("MultiRequest Error");
				request.setAttribute("msg", "Failed to call the multiRequest");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
			}
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
