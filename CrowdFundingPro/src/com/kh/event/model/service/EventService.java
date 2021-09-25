package com.kh.event.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.event.model.dao.EventDao;
import com.kh.event.model.vo.Event;
import com.kh.user.model.dao.UserDao;
import com.kh.user.model.vo.ULecture;

public class EventService {

	public EventService() {
		// TODO Auto-generated constructor stub
	}

	public int insertEvent(Event event, Attachment at) {

		Connection conn = getConnection();
		
		int result1 = new EventDao().insertEvent(conn, event);
		int result2 = new EventDao().insertAttachment(conn, at);

		if (result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result1;
	}

	public ArrayList<Event> selectEventList() {
		
		Connection conn = getConnection();

		ArrayList<Event> list = new EventDao().selectEventList(conn);
		close(conn);

		return list;
	}

	public Event selectEvent(int eno) {
		Connection conn = getConnection();
		

		Event e = new EventDao().selectEvent(conn, eno);
		close(conn);

		return e;
	}

	public int deleteEvent(int eno) {
		Connection conn = getConnection();

		int result = new EventDao().deleteEvent(conn, eno);
		close(conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public ArrayList<Event> selectEventList(int startRow, int endRow) {
		Connection conn = getConnection();

		ArrayList<Event> list = new EventDao().selectEventList(conn,startRow,endRow);
		System.out.println("이벤트 리스트 서비스 : " + list);

		close(conn);
		return list;
	}

	public int getListCount() {
		Connection conn = getConnection();
		
		int listCount = new EventDao().getListCount(conn);
		
		return listCount;
	}

	

}
