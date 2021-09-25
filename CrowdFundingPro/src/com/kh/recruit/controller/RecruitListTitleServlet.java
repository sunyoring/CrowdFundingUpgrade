package com.kh.recruit.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.kh.recruit.model.service.RecruitService;

/**
 * Servlet implementation class RecruitListTitleServlet
 */
@WebServlet("/recruitListTitle.do")
public class RecruitListTitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecruitListTitleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void practice(HttpServletRequest request) throws IOException {
//		BufferedReader br = request.getReader();
//		
//		System.out.println(br.toString());
//		
//		Object obj = JSONValue.parse(br);
//		
//		JSONObject jobj = (JSONObject) obj;
//		
//		System.out.println(jobj.toString());
//		
////		Iterator<String> it = jobj.keySet().iterator();
////		
////		while (it.hasNext()) {
////			String key = it.next();
////			System.out.println(key + " " + jobj.get(key));
////		}
////		
////		response.getWriter().print("test");
//		
//		// gson은 사실 특별히 필요하진 않을거 같음
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String jsonOutput = gson.toJson(jobj);
//		System.out.println(jsonOutput);
		
		JSONObject obj = (JSONObject) JSONValue.parse(request.getReader());
		
		System.out.println(obj.get("name"));
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 테스트
//		practice(request);
		
		// Recruitment의 id와 title 가져오는 servlet
		
		Map<Integer, String> map = new RecruitService().selectAllTitleWithId();
		
		response.setContentType("application/json; charset=utf-8");
		
		new Gson().toJson(map, response.getWriter());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
