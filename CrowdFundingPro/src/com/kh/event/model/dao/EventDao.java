package com.kh.event.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.Attachment;
import com.kh.event.model.vo.Event;
import com.kh.user.model.dao.UserDao;
import com.kh.user.model.vo.IProject;

public class EventDao {

	private Properties prop = new Properties();

	public EventDao() {
		String fileName = UserDao.class.getResource("/sql/event/event-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int insertEvent(Connection conn, Event event) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertEvent");
//		insertEvent= INSERT INTO EVENT_TB VALUES (SEQ_EVENT_NO.NEXTVAL,?,?,DEFAULT,'Y',?,?)

        long startDate = event.getStartDate().getTime();
        long endDate = event.getEndDate().getTime();
//
//        E_NO			NUMBER				Yes					1	이벤트번호
//        E_NAME		VARCHAR2(200 BYTE)	No					2	이벤트명
//        E_CONTENT		VARCHAR2(1024 BYTE)	No					3	컨텐츠파일명
//        REG_DATE		DATE				Yes	SYSDATE			4	등록날짜
//        STATUS		CHAR(4 BYTE)		Yes					5	진행상황
//        START_DATE	DATE				Yes	SYSDATE			6	시작일
//        END_DATE		DATE				Yes	SYSDATE			7	종료일
//        
//        
        
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, event.geteName());
			pstmt.setString(2, event.geteContent());
			pstmt.setDate(3, new java.sql.Date(startDate));
			pstmt.setDate(4, new java.sql.Date(endDate));

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertAttachment(Connection conn, Attachment at) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertAttachment");
//		insertAttachment=INSERT INTO ATTACHMENT VALUES (SEQ_P_NO.NEXTVAL, ?, ?, ?, SYSDATE, ?)

		try {
			pstmt = conn.prepareStatement(sql);


			pstmt.setInt(1, 4); //참조파트 4 : 행사(이벤트)
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Event> selectEventList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Event> list = new ArrayList<Event>();
		String sql = prop.getProperty("selectEventList");
//		selectEventList=SELECT * FROM EVENT_TB WHERE STATUS='Y'
		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new Event(
						rset.getInt("E_NO"),
						rset.getString("E_NAME"),
						rset.getString("E_CONTENT"),
						rset.getDate("REG_DATE"),
						rset.getDate("START_DATE"),
						rset.getDate("END_DATE"),
						rset.getString("STATUS")
				));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Event selectEvent(Connection conn, int eno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Event event = new Event();
		String sql = prop.getProperty("selectEvent");
//		selectEvent=SELECT * FROM EVENT_TB WHERE E_NO=?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eno);
			
			rset = pstmt.executeQuery();
			if (rset.next()) {
				event.seteNo(eno);
				event.seteName(rset.getString("E_NAME"));
				event.seteContent(rset.getString("E_CONTENT"));
				event.setStartDate(rset.getDate("START_DATE"));
				event.setEndDate(rset.getDate("END_DATE"));
				event.setStatus(rset.getString("STATUS"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return event;
	}

	public int deleteEvent(Connection conn, int eno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteEvent");
//		deleteEvent=UPDATE EVENT_TB SET STATUS='N' WHERE E_NO=?

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, eno);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Event> selectEventList(Connection conn, int startRow, int endRow) {

		ArrayList<Event> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM(SELECT ROWNUM RNUM, A.* FROM EVENT_TB A WHERE STATUS ='Y') WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				/*
				 	E_NO		NUMBER				이벤트번호
					E_NAME		VARCHAR2(200 BYTE)	이벤트명
					E_CONTENT	VARCHAR2(1024 BYTE)	컨텐츠파일명
					REG_DATE	DATE				등록날짜
					STATUS		CHAR(4 BYTE)		진행상황
					START_DATE	DATE				시작일
					END_DATE	DATE				종료일
				 * 
				 * */
				list.add(new Event(
						rset.getInt("E_NO"),
						rset.getString("E_NAME"),
						rset.getString("E_CONTENT"),
						rset.getDate("REG_DATE"),
						rset.getDate("START_DATE"),
						rset.getDate("END_DATE")
						));
			}
			System.out.println("이벤트 리스트 Dao : " + list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int getListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int listCount = 0;
		String sql = "SELECT COUNT(*) FROM EVENT_TB WHERE STATUS = 'Y'";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("dao 총 게시글 갯수 : " + listCount );
		return listCount;
	}

}