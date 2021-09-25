package com.kh.common.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.dao.CommonDao;
import com.kh.common.model.vo.Attachment;

public class CommonService {

	public ArrayList<Attachment> selectAttachment(String refNo) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> at = new CommonDao().selectAttachment(conn, refNo);
		
		close(conn);
		
		return at;
	}

	public int deleteAttachment(String refNo) {
		Connection conn = getConnection();
		
		int result = new CommonDao().deleteAttachment(conn, refNo);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int insertProjectAttachment(Attachment at) {
		Connection conn = getConnection();
		
		int result = new CommonDao().insertProjectAttachment(conn, at);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int insertLectureAttachment(Attachment at) {
		Connection conn = getConnection();
		
		int result = new CommonDao().insertLectureAttachment(conn, at);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public int updateLectureAttachment(Attachment at,String originName) {
		Connection conn = getConnection();
		
		int result = new CommonDao().updateLectureAttachment(conn,at,originName);
		
		if ( result > 0 ) commit (conn);
		else rollback(conn);
		
		
		return result;
	}

}
