package com.kh.user.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.kh.user.model.dao.UserDao;
import com.kh.user.model.vo.IProject;
import com.kh.user.model.vo.ULecture;
import com.kh.user.model.vo.UProject;
import com.kh.user.model.vo.User;

public class UserService {

	public User loginUser(String emailId, String userPwd) {
		Connection conn = getConnection();

		User loginUser = new UserDao().loginUser(conn, emailId, userPwd);
		close(conn);
		return loginUser;
	}

	public int insertUser(User u) {
		Connection conn = getConnection();

		int result = new UserDao().insertUser(conn, u);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public User selectUser(String emailId) {
		Connection conn = getConnection();

		User u = new UserDao().selectUser(conn, emailId);
		close(conn);
		return u;
	}

	public User updateUser(User u) {
		Connection conn = getConnection();

		User updateUser = null;
		int result = new UserDao().updateMmeber(conn, u);
		if (result > 0) {
			commit(conn);
			updateUser = new UserDao().selectUser(conn, u.getEmailId());
		} else {
			rollback(conn);
		}
		close(conn);
		return updateUser;
	}

	public int deleteUser(String emailId) {
		Connection conn = getConnection();

		int result = new UserDao().deleteUser(conn, emailId);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public int emailIdCheck(String emailId) {
		Connection conn = getConnection();

		int result = new UserDao().emailIdCheck(conn, emailId);

		close(conn);

		return result;
	}

	public ArrayList<ULecture> selectLectureList(String emailId) {
		Connection conn = getConnection();
		ArrayList<ULecture> list = new UserDao().selectLectureList(conn, emailId);
		close(conn);

		return list;
	}

	public ArrayList<IProject> selectIProjectList(String emailId) {
		Connection conn = getConnection();
		ArrayList<IProject> list = new UserDao().selectIProjectList(conn, emailId);
		close(conn);
		return list;
	}

	public ArrayList<UProject> selectUProjectList(String emailId) {
		Connection conn = getConnection();
		ArrayList<UProject> list = new UserDao().selectUProjectList(conn, emailId);
		close(conn);
		return list;
	}

	public ArrayList<User> selectUserList() {
		Connection conn = getConnection();
		ArrayList<User> list = new UserDao().selectUserList(conn);
		close(conn);
		return list;
	}

	public Map<String, Integer> selectCategoryList() {
		Connection conn = getConnection();
		Map<String, Integer> map = new UserDao().selectCategoryList(conn);
		close(conn);
		return map;
	}

	public int chargePoint(int userNo, int recharge) {
		Connection conn = getConnection();
		int result=0;
		
		result = new UserDao().chargePoint(conn,userNo,recharge);
		close(conn);
		return result;
	}

}
