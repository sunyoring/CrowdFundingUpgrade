package com.kh.recruit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.kh.common.model.vo.Attachment;
import com.kh.recruit.model.service.RecruitService;
import com.kh.recruit.model.vo.RecruitMember;

/**
 * Servlet implementation class RecruitMemberListServlet
 */
@WebServlet("/recruitMemberList.do")
public class RecruitMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject obj = (JSONObject) JSONValue.parse(request.getReader());
		
		String rid = (String) obj.get("rid");
		String email = (String) obj.get("email");
		String password = (String) obj.get("password");
		
		// 공고 지원서 가져오기
		RecruitMember rm = new RecruitService().selectRecruitMemberWithIdAndEmail(rid, email);
		
		// 첨부파일 가져오기
		Attachment at = new RecruitService().selectAttachmentWithIdAndEmail(rid, email);
		
		if (rm == null) {
			// 조회 되지 않은 경우
			response.getWriter().print("search fail");
		} else if (!rm.getPassword().equals(password)) {
			// 비밀번호가 틀린 경우
			response.getWriter().print("password fail");
		} else {
			response.setContentType("application/json; charset=utf-8");
			Gson gson = new Gson();
			
			JSONObject jobj = new JSONObject();
			jobj.put("rm", rm);
			jobj.put("at", at);
			
			//response.getWriter().print(jobj);
			new Gson().toJson(jobj, response.getWriter());
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
