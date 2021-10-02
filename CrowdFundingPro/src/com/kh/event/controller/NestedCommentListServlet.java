package com.kh.event.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.event.model.service.EventService;
import com.kh.event.model.vo.EventComment;

/**
 * Servlet implementation class NestedCommentListServlet
 */
@WebServlet("/nestedCommentList.ev")
public class NestedCommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NestedCommentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		System.out.println("부모댓글번호 :" + cno);
		ArrayList<EventComment> list = new EventService().nestedCommentList(cno);
		
		JSONArray jArr = new JSONArray();
		JSONObject jObj = null;
		
		for(EventComment ec : list) {
			
			jObj = new JSONObject();
			
			jObj.put("cNum", ec.getcNum());
			jObj.put("eNo", ec.geteNo());
			jObj.put("name",ec.getName());
			jObj.put("emailId", ec.getEmailId());
			jObj.put("ePwd", ec.getePwd());
			jObj.put("cDate", ec.getcDate()+"");
			jObj.put("cParent", ec.getcParent());
			jObj.put("comment", ec.getComment());
			
			jArr.add(jObj);
		}
		
		System.out.println("jArr : " + jArr);
		//브라우저로 json객체를 전송
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jArr);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
