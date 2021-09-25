package com.kh.recruit.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MailService;
import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.recruit.model.service.RecruitService;
import com.kh.recruit.model.vo.RecruitMember;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class RecruitMemberUpdateServlet
 */
@WebServlet("/recruitMemberUpdate.do")
public class RecruitMemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitMemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 10 * 1024 * 1024;
			
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "\\upfiles\\";
//			System.out.println("savePath : " + savePath);
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// parameter 처리
			String rmId = multiRequest.getParameter("recruitMemberId");
			String rId = multiRequest.getParameter("recruitId");
			String atNo = multiRequest.getParameter("recruitAttachmentNo");
			
			String name = multiRequest.getParameter("recruitMemberName");
			String phone = multiRequest.getParameter("recruitMemberPhone");
			String email = multiRequest.getParameter("recruitMemberEmail");
			String education = multiRequest.getParameter("recruitMemberEducation");
			String career = multiRequest.getParameter("recruitMemberCareer");
			
			if (multiRequest.getParameter("recruitId") == null || multiRequest.getParameter("recruitId").equals("") ||
					name == null || name.equals("") || 
					phone == null || phone.equals("") || 
					email == null || email.equals("") || 
					education == null || education.equals("") || 
					career == null || career.equals("")) {
					// 지원서 수성 실패
					request.getSession().setAttribute("msg", "지원서 등록 실패");
					response.sendRedirect(request.getContextPath() + "/recruitPage.do");
			}
			
			// 공고 지원서 처리
			RecruitMember rm = new RecruitMember(name, phone, education, career, email);
			rm.setId(Integer.parseInt(rmId));
					
			// 파일 업로드 처리 
			// 기존의 파일을 먼저 가져와야 함
			Attachment at = new RecruitService().selectAttachmentFileNo(Integer.parseInt(atNo));
			
			boolean isAtNew = false;
			File oldFile = null;
			// null이 아니면 새로 첨부
			if (multiRequest.getOriginalFileName("recruitPortfolio") != null) {
				// 기존 파일이 있는 경우 삭제 후 수정
				if (at != null) {
					oldFile = new File(at.getFilePath() + at.getChangeName());
				} else {
					at = new Attachment();
					isAtNew = true;
				}
				
				String originName = multiRequest.getOriginalFileName("recruitPortfolio");
				String changeName = multiRequest.getFilesystemName("recruitPortfolio");
				
				at.setFilePath(savePath);
				at.setOriginName(originName);
				at.setChangeName(changeName);
			}
			
			// 이력서 내 자신에게 보내기 체크
			String sendMyResume = multiRequest.getParameter("sendMyResume");
			// 체크한 경우
			if (sendMyResume != null && sendMyResume.equals("on")) {
				MailService.setPagePath(getServletContext().getRealPath(""));
				MailService.sendResume(Integer.parseInt(rId), rm, at);
			}
			
			int result = new RecruitService().updateRecruitMember(rm, at, Integer.parseInt(atNo), isAtNew);
			
			if (result > 0) {
				request.getSession().setAttribute("msg", "지원서 수정 성공");
				response.sendRedirect(request.getContextPath() + "/recruitPage.do");
				if (isAtNew == false && oldFile != null) {
					oldFile.delete();
				}
			} else {
				if (at != null) {
					File failedFile = new File(savePath + at.getChangeName());
					failedFile.delete();
					
					// 에러 페이지 처리
					request.getSession().setAttribute("msg", "지원서 수정 실패");
					response.sendRedirect("views/common/errorPage.jsp");
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
