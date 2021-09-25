package com.kh.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.service.CommonService;
import com.kh.common.model.vo.Attachment;

/**
 * Servlet implementation class InsertAttachmentServlet
 */
@WebServlet("/insertProjectAttachment.do")
public class InsertProjectAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProjectAttachmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// project 이미지 attachment 테이블에 insert하는 서블릿
		
		String path = "D:\\git\\FundingProject\\CrowdFundingPro\\WebContent\\resources\\images\\project";
		
		File project = new File(path);
		
		File[] fileList = project.listFiles();
		
		// 숫자에 맞게 정렬
		Arrays.sort(fileList, new Comparator<File>() {

			@Override
			public int compare(File f1, File f2) {
				
//				System.out.println(f1.getName().substring(0, f1.getName().indexOf(".")));
//				System.out.println(f2.getName().substring(0, f2.getName().indexOf(".")));

				int n1 = Integer.parseInt(f1.getName().substring(0, f1.getName().indexOf(".")));
				int n2 = Integer.parseInt(f2.getName().substring(0, f2.getName().indexOf(".")));
				
				return n1 - n2;
			}
		});
		
		ArrayList<Attachment> atList = new ArrayList<Attachment>();
		
		for (File file : fileList) {
//			System.out.println(file.getName());
			Attachment at = new Attachment();
			
			at.setRefNo(3); // 펀딩
			at.setOriginName(file.getName());
			at.setChangeName(file.getName());
			at.setFilePath(path);
			
			atList.add(at);
		}
		
		int result = 0;
		
		for (Attachment at : atList) {
			result = new CommonService().insertProjectAttachment(at);
		}
		
		if (result > 0) {
			request.getSession().setAttribute("msg", "프로젝트 첨부 파일 등록 성공 : " + "총 " + atList.size() + "개 파일 등록");
		} else {
			// 첨부파일 초기화 실패
			request.getSession().setAttribute("msg", "첨부 파일 등록 실패");
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
