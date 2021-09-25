package com.kh.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.service.CommonService;
import com.kh.common.model.vo.Attachment;

/**
 * Servlet implementation class ResetAttachmentServlet
 */
@WebServlet("/resetAttachment.do")
public class ResetAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetAttachmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 참조번호에 따라 첨부파일 프로젝트 내에서 제거 및 DB에서 삭제
		
		String refNo = request.getParameter("refNo");
		
		// 참조 가능한 번호들
		String refKind = "1234";
		
		if (refNo == null || refNo.equals("") || 
				// refNo 가 1자리 수가 아니고 1234 중 하나가 아닐 때 처리
				(refNo.length() != 1 || !refKind.contains(refNo))) {
			// 첨부파일 초기화 실패
			request.getSession().setAttribute("msg", "첨부 파일 초기화 실패");
			response.sendRedirect("views/common/errorPage.jsp");
		}
		
		// 첨부파일 조회
		ArrayList<Attachment> atList = new CommonService().selectAttachment(refNo);
		
//		for (Attachment attachment : atList) {
//			System.out.println(attachment.getOriginName());
//		}
		
		int fileCnt = atList.size();
		
		// 첨부파일 DB 삭제
		int result = 0;
		
		if(atList.size() > 0) {
			result = new CommonService().deleteAttachment(refNo);
		} else {
			request.getSession().setAttribute("msg", "첨부 파일 초기화 실패");
			response.sendRedirect("views/common/errorPage.jsp");
		}
		
		if (result > 0) {
			// 첨부파일 삭제
			for (Attachment at : atList) {
				File file = new File(at.getFilePath() + at.getChangeName());
				if (file.exists()) {
					file.delete();
				}
			}
			
			request.getSession().setAttribute("msg", "첨부 파일 초기화 성공 : " + "참조번호 " + refNo + " 의 파일 " + fileCnt +" 삭제  완료");
		} else {
			// 첨부파일 초기화 실패
			request.getSession().setAttribute("msg", "첨부 파일 초기화 실패");
		}
		
		response.sendRedirect("views/common/errorPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
