package com.kh.faq.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.faq.model.dao.FaqDao;
import com.kh.faq.model.vo.Faq;


public class FaqService {

	public ArrayList<Faq> selectList(String userCode) {
		Connection conn = getConnection();
		
		ArrayList<Faq> list = new FaqDao().selectList(conn, userCode);
		
		close(conn);

		return list;
	}

	public Faq selectFaq(String fNo) {

		Connection conn = getConnection();
		
		Faq f = new FaqDao().selectFaq(conn, fNo);
		
		close(conn);

		return f;
	}

	public Faq selectDetailAll(String fno) {
		Connection conn = getConnection();
		
		Faq faq = new FaqDao().selectDetailAll(conn, fno);
		
		close(conn);

		return faq;
	}

	public int insertFaq(Faq f) {
		Connection conn = getConnection();
		
		int result = new FaqDao().insertFaq(conn, f);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int updateFaq(Faq f) {
		Connection conn = getConnection();
		
		int result = new FaqDao().updateFaq(conn, f);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int deleteFaq(String fNo) {
		Connection conn = getConnection();
		
		int result = new FaqDao().deleteFaq(conn, fNo);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}



}
