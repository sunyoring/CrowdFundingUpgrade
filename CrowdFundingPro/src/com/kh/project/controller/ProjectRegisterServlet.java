package com.kh.project.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.model.vo.Attachment;
import com.kh.common.MyFileNamePolicy;
import com.kh.project.model.service.ProjectService;
import com.kh.project.model.vo.Project;
import com.kh.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProjectRegisterServlet
 */
@WebServlet("/register.do")
public class ProjectRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		User loginUser = (User) session.getAttribute("loginUser");
		
		if(ServletFileUpload.isMultipartContent(request)) {
	         int maxSize=10*1024*1024 ;
	         String resources =request.getSession().getServletContext().getRealPath("/resources");
	         //String savePath=resources+ "\\upfiles\\"; 
	         String savePath=resources+ "\\images/project\\"; 
	         
	         MultipartRequest multiRequest=new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileNamePolicy());
	         
	       
	         String name=multiRequest.getParameter("projectName");
	         int amount=Integer.parseInt(multiRequest.getParameter("amountGoal"));
	         int delivery=Integer.parseInt(multiRequest.getParameter("delivery"));
	         String category=multiRequest.getParameter("category");
	         
	         
	         
	         Date dateInput = Date.valueOf(multiRequest.getParameter("dateInput"));
	         

	         
	         String detail=multiRequest.getParameter("detail");
	         
	         String[] confirm=multiRequest.getParameterValues("confirm2"); 
	         
	      
	         
	         
	         if(confirm.length >= 4) {
	            
	        	Project pj=new Project();
	          //프로젝트명 목표금액 베송료 카테고리 프로젝트기간 프로젝트소개 약관동의
	            pj.setProjectName(name);
	            
	            pj.setCategoryNo(category);/////////////
	            
	            pj.setDetailIntro(detail);
	            
	            pj.setDeliveryCharge(delivery);
	            
	            pj.setAmountGoal(amount);
	            
	            pj.setDdln(dateInput);
	            

	            pj.setUserNo(102);//->로그인한 유저가 프로젝트 등록 
	            
	            Attachment at=null;
	            
	            if(multiRequest.getOriginalFileName("titleImg") !=null) {

	               String originName=multiRequest.getOriginalFileName("titleImg");
	               String changeName=multiRequest.getFilesystemName("titleImg");
	               
	               at=new Attachment();
	               at.setFilePath(savePath);
	               at.setOriginName(originName);
	               at.setChangeName(changeName);
	           
	            }
	            
	            
	            int result= new ProjectService().insertProject(pj,at);
	            
	            if(result>0) {
		        	request.getSession().setAttribute("msg", "펀딩 등록이 완료되었습니다.");
		        	

					response.sendRedirect("projectPage.do");
		        	 
		            
		         }else {
		            
		        	 request.getSession().setAttribute("msg", "핀딩 등록에 실패했습니다.");
		            
		            
		            RequestDispatcher view=request.getRequestDispatcher("views/common/errorPage.jsp");
		            view.forward(request, response);
		         }
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
