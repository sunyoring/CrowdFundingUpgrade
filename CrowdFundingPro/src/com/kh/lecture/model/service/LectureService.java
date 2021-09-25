package com.kh.lecture.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.lecture.model.dao.LectureDao;
import com.kh.lecture.model.vo.Lecture;
import com.kh.lecture.model.vo.LectureInfo;
import com.kh.user.model.vo.ULecture;
import com.kh.user.model.vo.User;


public class LectureService {
	
	public int insertLecture(Lecture lecture){
		
		Connection conn = getConnection();
		int result = new LectureDao().insertLecture(conn,lecture);
		
		if ( result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		
		close(conn);
		
		return result ;
		
	}
	
	public int updateLecture(Lecture lecture) {
		
		Connection conn = getConnection();
		int result = new LectureDao().updateLecture(conn,lecture);
		if ( result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	public ArrayList<Lecture> selectLectureList() {
		
		Connection conn = getConnection();
		ArrayList<Lecture> result = new LectureDao().selectLectureList(conn);
		
		close(conn);
		return result;
	}

	
	public ArrayList<Lecture> selectLectureList(int startRow,int endRow) {
		
		Connection conn = getConnection();
		ArrayList<Lecture> result = new LectureDao().selectLectureList(conn,startRow,endRow);
		
		close(conn);
		return result;
	}

	public int deleteLecture(Lecture lecture) {
		
		Connection conn = getConnection();
		int result = new LectureDao().deleteLecture(conn,lecture);
		
		if ( result > 0 )  commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public Lecture selectLecture(String lecCode) {
		
		Connection conn = getConnection();
		System.out.println(lecCode);
		Lecture lecture = new LectureDao().selectLecture(conn,lecCode);
		close(conn);
		return lecture;
	}

	public int signInLecture(Lecture lecture,User user) {
		
		Connection conn = getConnection();
		int result = new LectureDao().signInLecture(conn,lecture,user);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public LectureInfo getLectureCount(Lecture lecture) {
		Connection conn = getConnection();
		
		LectureInfo result = new LectureDao().getLectureCount(conn,lecture);
		
		close(conn);
		
		return result;
	}

	public ArrayList<Lecture> selectRandomLecture() {
		Connection conn = getConnection();
		
		ArrayList<Lecture> result = new LectureDao().getRandomLecture(conn);
		
		close(conn);
		
		return result;
	}

	public int cancleLectureRegist(Lecture lecture, User loginUser) {
		
		Connection conn = getConnection();
		
		int result = new LectureDao().cancleLectureRegist(conn, lecture, loginUser);
		
		close(conn);
		
		return result;
	}

	public String checkLectureEnrollment(User loginUser,String lecCode) {
		
		Connection conn = getConnection();
		
		String result = new LectureDao().checkLectureEnrollment(conn,loginUser,lecCode);
		
		close(conn);
		
		return result;
	}

	public ArrayList<ULecture> selectLectureList(String lecCode) {
		Connection conn = getConnection();
		ArrayList<ULecture> list = new LectureDao().selectLectureList(conn,lecCode);
		
		close(conn);
		return list;
	}

	public int cancleLectureRegist(String lecCode, int userNo) {
		Connection conn = getConnection();
		
		int result = new LectureDao().cancleLectureRegist(conn,userNo,lecCode);
		
		close(conn);
		
		return result;
	}
	
//	
//	public Lecture chooseLectrue(Lecture lecture) {
//		
//		Lecture l = null;
//		ArrayL
	
//		return l;
//		
//	}
	
}
