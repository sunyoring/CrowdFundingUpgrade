package com.kh.event.controller;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet implementation class CommentListServlet
 */
@WebServlet("/commentList.ev")
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int eno = Integer.parseInt(request.getParameter("eno"));
		
		ArrayList<EventComment> list = new EventService().selectCommnetList(eno);
		
		JSONArray jArr = new JSONArray();
		JSONObject jObj = null;
		

		for(EventComment ec : list) {
			
			jObj = new JSONObject();
			
			jObj.put("cNum", ec.getCNum());
			jObj.put("eNo", ec.getENo());
			jObj.put("name",ec.getName());
			jObj.put("emailId", ec.getEmailId());
			jObj.put("ePwd", ec.getEPwd());
			jObj.put("cDate", ec.getCDate()+"");
			jObj.put("cParent", ec.getCParent());
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
