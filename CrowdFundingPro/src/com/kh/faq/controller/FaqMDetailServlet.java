package com.kh.faq.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.faq.model.service.FaqService;
import com.kh.faq.model.vo.Faq;

/**
 * Servlet implementation class FaqMDetailServlet
 */
@WebServlet("/select.fq")
public class FaqMDetailServlet extends HttpServlet {
	 static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqMDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fno = request.getParameter("fno");
		
		Faq faq = new FaqService().selectDetailAll(fno);
		
		String fNo = faq.getfNo();
		char targetUser = faq.getTargetUser();
		String question = faq.getQuestion();
		String answer = faq.getAnswer();
		String creatorId = faq.getCreatorId();
		Date createDate = faq.getCreateDate();
		String updaterId = faq.getUpdaterId();
		Date updateDate = faq.getUpdateDate();
		char showYn = faq.getShowYn();
		
		String target = "";
		if(targetUser == 'S') {
			target = "서포터";
		} 
		else {
			target = "메이커";
		}
		
		String update = "";
		if(updaterId != null) {
			update = "<th>최근 수정인</th><td>" + updaterId + "</td><th>최근 수정일</th><td>" + updateDate + "</td>";
		}
		else {
			update = "<th>최근 수정인</th><td></td><th>최근 수정일</th><td></td>";
		}
		
		String show = "";
		if(showYn == 'Y') {
			show = "<input type='radio' name='showYN' id='y' value='Y' checked><label for='y'>예</label><input type='radio' name='showYN' id='n' value='N'><label for='n'>아니오</label>";
		}
		else {
			show = "<input type='radio' name='showYN' id='y' value='Y'><label for='y'>예</label><input type='radio' name='showYN' id='n' value='N' checked><label for='n'>아니오</label>";
		}
		
		String rTable = "<form method='POST' id='detail'><div id='ddiv'><table id='dTable'> <tr style='height: 50px;'><th style='width: 115px;'>글번호</th><td style='width: 115px;'><input type='input' name='fNo' value='"+ fNo +"' style='border: none; text-align: center; width: 115px;' readonly> </td><th style='width: 115px;'>사용자</th><td style='width: 115px;'>" + target + "</td></tr><tr style='height: 50px;'><th>작성인</th><td>" + creatorId + "</td><th>작성일</th><td>" + createDate + "</td></tr><tr style='height: 80px;'><th>질문내용</th><td colspan='3'><textarea name='question' style='height: 80px; resize: none;' required>" + question + "</textarea></td></tr><tr style='height: 100px;'><th>답변 내용</th><td colspan='3'><textarea name='answer' style='height: 100px; resize: none;' required>" + answer + "</textarea></td></tr><tr style='height: 50px;'>" + update + "</tr><tr><th>노출 여부</th><td colspan='3'>" + show + "</td></tr></table></div></form>";
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(rTable);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
