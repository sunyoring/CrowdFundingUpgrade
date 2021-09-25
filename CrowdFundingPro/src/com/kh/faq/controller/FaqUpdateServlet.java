package com.kh.faq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.faq.model.service.FaqService;
import com.kh.faq.model.vo.Faq;
import com.kh.user.model.vo.User;

/**
 * Servlet implementation class FaqUpdateServlet
 */
@WebServlet("/update.fq")
public class FaqUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fNo = request.getParameter("fNo");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		String updaterId = loginUser.getEmailId();
		
		char showYN = request.getParameter("showYN").charAt(0);
		
		Faq f = new Faq();
		
		f.setfNo(fNo);
		f.setQuestion(question);
		f.setAnswer(answer);
		f.setUpdaterId(updaterId);
		f.setShowYn(showYN);
		
		int result = new FaqService().updateFaq(f);
		
		if(result > 0) {
			response.sendRedirect("list.fq");
		}
		else {
			request.getSession().setAttribute("msg", "게시물 변경 실패");
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
