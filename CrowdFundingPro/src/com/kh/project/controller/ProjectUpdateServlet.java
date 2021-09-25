package com.kh.project.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileNamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.project.model.service.ProjectService;
import com.kh.project.model.vo.Project;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProjectUpdateServlet
 */
@WebServlet("/updateB.do")
public class ProjectUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub//UPDATE
		if(ServletFileUpload.isMultipartContent(request)) {
				int maxSize=10*1024*1024 ;
					
				//전달된 파일을 저장할 서버의 폴더 경로
				String resources =request.getSession().getServletContext().getRealPath("/resources");
				String savePath=resources+ "\\images/project\\";
					
				MultipartRequest multiRequest=new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileNamePolicy());
				
				
				int pCode=Integer.parseInt(multiRequest.getParameter("pCode"));
				System.out.println("수정하기pCode:"+pCode);
				
				String projectName=multiRequest.getParameter("projectName");
				int amountGoal=Integer.parseInt(multiRequest.getParameter("amountGoal"));
				int delivery=Integer.parseInt(multiRequest.getParameter("delivery"));
				//String category=multiRequest.getParameter("category");
				Date dateInput =Date.valueOf(multiRequest.getParameter("dateInput"));
				String detail=multiRequest.getParameter("detail");
				
				Project pj=new Project();
				pj.setProjectCode(pCode);//
				pj.setProjectName(projectName);
				pj.setAmountGoal(amountGoal);
				pj.setDeliveryCharge(delivery);
				//pj.setCategoryNo(category);
				pj.setDdln(dateInput);
				pj.setDetailIntro(detail);
				
				
				
				Attachment at=null;
				if(multiRequest.getOriginalFileName("uploadfile") !=null) {//새 파일이 등록된 경우
					at=new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName("uploadfile"));
					at.setChangeName(multiRequest.getFilesystemName("uploadfile"));
					at.setFilePath(savePath);
					
					
					if(multiRequest.getParameter("titleImg")!=null) {//기존의 파일 존재하는 경우
						
						File deleteFile=new File(savePath+multiRequest.getParameter("titleImg"));
						
						
						deleteFile.delete();// 올라가있는  파일 삭제
						
						//다시 셋팅
						at.setFileNo(Integer.parseInt(multiRequest.getParameter("fileNo")));
						
						
						
					}
					
				}
				
				int result=new ProjectService().updateProject(pj,at);
				
				if(result>0) {
					
					request.setAttribute("pj", pj);
					
					request.getRequestDispatcher("detail.do?pCode="+pCode).forward(request, response);
					//response.sendRedirect("detail.do");
				}else {
					request.getSession().setAttribute("msg", "UPDATE ERROR");
					response.sendRedirect("views/common/errorPage.jsp");
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
