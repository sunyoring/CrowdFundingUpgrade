package com.kh.lecture.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.lecture.model.vo.Lecture;
import com.kh.lecture.model.vo.LectureInfo;
import com.kh.user.model.vo.ULecture;
import com.kh.user.model.vo.User;

public class LectureDao {

	private static Properties prop = new Properties();

	public LectureDao() {
		String fileName = LectureDao.class.getResource("/sql/lecture/lecture-query.properties").getPath();
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

	public int insertLecture(Connection conn, Lecture lecture) {

		int result = 0;
		PreparedStatement pstm = null;
		String sql = prop.getProperty("insertLecture");

//		insertLecture=INSERT INTO LECTURE VALUES(SEQ_LECTURE_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)

//		LECTURE_CODE
//		LECTURE_TITLE
//		LECTURE_NUM
//		LECTURE_ADDRESS
//		LECTURE_TOPIC
//		LECTURE_DATE
//		LECTURE_TIME
//		LECTURE_CONTENT
//		LECTURER
//		LECTURE_IMAGE
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, lecture.getLectureTitle());
			pstm.setInt(2, lecture.getLectureNum());
			pstm.setString(3, lecture.getLectureAddress());
			pstm.setString(4, lecture.getLectureTopic());
			pstm.setDate(5, lecture.getLectureDate());
			pstm.setInt(6, lecture.getLectureTime());
			pstm.setString(7, lecture.getLectureContent());
			pstm.setString(8, lecture.getLecturer());
			pstm.setString(9, lecture.getLectureImage());

			result = pstm.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstm);
		}

		return result;
	}

	public int updateLecture(Connection conn, Lecture lecture) {

		int result = 0;
		PreparedStatement pstm = null;
		String sql = prop.getProperty("updateLecture");

//		insertLecture=INSERT INTO LECTURE VALUES(SEQ_LECTURE_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?,?,?)
//		updateLecture=UPDATE LECTURE SET LECTURE_TITLE=?, LECTURE_NUM=?, LECTURE_ADDRESS=?, LECTURE_TOPIC=?, LECTURE_DATE=?, LECTURE_TIME=?, LECTURE_CONTENT=?, LECTURER=? , LECTURE_IMAGE=? WHERE LECTURE_CODE=?

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, lecture.getLectureTitle());
			pstm.setInt(2, lecture.getLectureNum());
			pstm.setString(3, lecture.getLectureAddress());
			pstm.setString(4, lecture.getLectureTopic());
			pstm.setDate(5, lecture.getLectureDate());
			pstm.setInt(6, lecture.getLectureTime());
			pstm.setString(7, lecture.getLectureContent());
			pstm.setString(8, lecture.getLecturer());
			pstm.setString(9, lecture.getLectureImage());
			pstm.setString(10, lecture.getLectureCode());

			result = pstm.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstm);
		}

		return result;
	}

	public ArrayList<Lecture> selectLectureList(Connection conn, int startRow, int endRow) {

		ArrayList<Lecture> result = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = prop.getProperty("selectLectureList");
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);

			rs = pstm.executeQuery();
			while (rs.next()) {

				result.add(new Lecture(rs.getString("LECTURE_IMAGE"), rs.getString("LECTURE_TOPIC"),
						rs.getString("LECTURE_TITLE"), rs.getDate("LECTURE_DATE"), rs.getString("LECTURER"),
						rs.getInt("LECTURE_TIME"), rs.getInt("LECTURE_NUM"), rs.getString("LECTURE_CODE")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		return result;
	}

	public ArrayList<Lecture> selectLectureList(Connection conn) {

		ArrayList<Lecture> result = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = prop.getProperty("selectLectureAllList");
		/*
		 * image,topic,title,date,er,time,num
		 */
		try {
			pstm = conn.prepareStatement(sql);

			rs = pstm.executeQuery();

			while (rs.next()) {

				result.add(new Lecture(rs.getString("LECTURE_IMAGE"), rs.getString("LECTURE_TOPIC"),
						rs.getString("LECTURE_TITLE"), rs.getDate("LECTURE_DATE"), rs.getString("LECTURER"),
						rs.getInt("LECTURE_TIME"), rs.getInt("LECTURE_NUM")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		return result;
	}

	public Lecture selectLecture(Connection conn, String lecId) {

		PreparedStatement pstm = null;
		ResultSet rs = null;
		Lecture lecture = null;
		String sql = prop.getProperty("selectLecture");

		try {

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, lecId);
			rs = pstm.executeQuery();

			lecture = (rs.next()) ? new Lecture(rs.getString("LECTURE_CODE"), rs.getString("LECTURE_TITLE"),
					rs.getInt("LECTURE_NUM"), rs.getString("LECTURE_ADDRESS"), rs.getString("LECTURE_TOPIC"),
					rs.getDate("LECTURE_DATE"), rs.getInt("LECTURE_TIME"), rs.getString("LECTURE_IMAGE"),
					rs.getString("LECTURE_CONTENT"), rs.getString("LECTURER")) : null;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		if (lecture == null) {
			System.out.println("why the hack Mr.Null come out");
		}
		return lecture;
	}

	public int deleteLecture(Connection conn, Lecture lecture) {

		int result = 0;
		PreparedStatement pstm = null;
		String sql = prop.getProperty("deleteLecture");
		// deleteLecture=DELETE FROM LECTURE WHERE LECTURE_CODE=?
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, lecture.getLectureCode());

			result = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
		}

		return result;
	}

	public int signInLecture(Connection conn, Lecture lecture, User user) {

		int result = 0;
		PreparedStatement pstm = null;
		String sql = prop.getProperty("signInLecture");
		// INSERT INTO LECTURE_ENROLLMENT
		// VALUES(SEQ_LECTURE_ENROLL.NEXTVAL,DEFAULT,?,?);

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getUserNo());
			pstm.setString(2, lecture.getLectureCode());

			result = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);

		}

		return result;
	}

	public LectureInfo getLectureCount(Connection conn, Lecture lecture) {

		LectureInfo result = null;

		PreparedStatement pstm = null;
		String sql = prop.getProperty("selectLecCount");
		ResultSet rs = null;
		System.out.println(lecture);
		System.out.println(lecture.getLectureCode());
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, lecture.getLectureCode());

			rs = pstm.executeQuery();

			if (rs.next()) {
				result = new LectureInfo(lecture.getLectureCode(), rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);

		}

		return result;
	}

	public ArrayList<Lecture> getRandomLecture(Connection conn) {

		ArrayList<Lecture> result = new ArrayList<>();
		Lecture lecture = null;

		Statement stm = null;
		ResultSet rs = null;

		
		String sql = prop.getProperty("selectRandomLecture"); 

//		String sql = "SELECT * FROM (SELECT * FROM LECTURE A JOIN (SELECT COUNT(*),C.LECTURE_CODE, D.LECTURE_NUM FROM LECTURE_ENROLLMENT C JOIN LECTURE D ON C.LECTURE_CODE = D.LECTURE_CODE GROUP BY C.LECTURE_CODE,D.LECTURE_NUM HAVING COUNT(*) < D.LECTURE_NUM ) B ON A.LECTURE_CODE = B.LECTURE_CODE ORDER BY DBMS_RANDOM.RANDOM)WHERE ROWNUM <= 5";

		try {

			stm = conn.createStatement();

			rs = stm.executeQuery(sql);
			/*
			 * lectureCode: "1006" lectureNum: 80 lectureTime: 0
			 * lectureTitle:"획기적인 것과 창의적인 것의 차이" lectureTopic: "펀딩오픈강의" lecturer: "Ms.Kwon"
			 */

			while (rs.next()) {
				result.add(new Lecture(rs.getString("LECTURE_IMAGE"), rs.getString("LECTURE_CODE"),
						rs.getString("LECTURE_TITLE"), rs.getInt("LECTURE_NUM"), rs.getString("LECTURER"),
						rs.getString("LECTURE_TOPIC")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(stm);
		}

		return result;
	}

	public int cancleLectureRegist(Connection conn, Lecture lecture, User loginUser) {

		int result = 0;
		// cancleLectureEn=DELETE FROM LECTURE_ENROLLMENT WHERE LECTURE_CODE = ? and
		// USER_NO = ?
		PreparedStatement pstm = null;
		String sql = "cancleLectureEn";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, lecture.getLectureCode());
			pstm.setInt(2, loginUser.getUserNo());

			result = pstm.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			close(pstm);

		}

		return 0;
	}

	public String checkLectureEnrollment(Connection conn, User loginUser, String lecCode) {

		PreparedStatement pstm = null;
		String sql = "checkLectureEnrollment";
		// SELECT * FROM LECTURE_ENROLLMENT WHERE USER_NO = ? AND LECTURE_CODE = ?
		ResultSet rs = null;
		String result = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, loginUser.getUserNo());
			pstm.setString(2, lecCode);

			rs = pstm.executeQuery();

			if (rs.next()) {
				result = (rs.getString("ENROLL_CODE") != null && rs.getString("ENROLL_CODE") != "") ? "yes" : "no";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<ULecture> selectLectureList(Connection conn, String lecCode) {
		ArrayList<ULecture> result = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ULecture u = null;
		String sql ="SELECT * FROM LECTURE_ENROLLMENT WHERE LECTURE_CODE = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, lecCode);
			rs = pstm.executeQuery();
			while (rs.next()) {

				u = new ULecture();
				u.setLCode(lecCode);
				u.setUserNo(rs.getInt("USER_NO"));
				
				result.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		return result;
	}

	public int cancleLectureRegist(Connection conn, int userNo, String lecCode) {
		int result = 0;
		PreparedStatement pstm = null;
		String sql = "DELETE FROM LECTURE_ENROLLMENT WHERE USER_NO = ? AND LECTURE_CODE = ?";
		// SELECT * FROM LECTURE_ENROLLMENT WHERE USER_NO = ? AND LECTURE_CODE = ?
		ResultSet rs = null;

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userNo);
			pstm.setString(2, lecCode);
				
			result= pstm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
