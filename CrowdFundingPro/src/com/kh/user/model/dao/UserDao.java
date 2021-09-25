package com.kh.user.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kh.user.model.vo.IProject;
import com.kh.user.model.vo.ULecture;
import com.kh.user.model.vo.UProject;
import com.kh.user.model.vo.User;

public class UserDao {
	private Properties prop = new Properties();

	public UserDao() {
		String fileName = UserDao.class.getResource("/sql/user/user-query.properties").getPath();
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

	public User loginUser(Connection conn, String emailId, String userPwd) {
		User loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("loginUser");
		// loginUser=SELECT * FROM USER_TB WHERE EMAIL_ID=? AND USER_PWD=? AND STATUS='N' 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emailId);
			pstmt.setString(2, userPwd);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				loginUser = new User();

				loginUser.setUserNo(rset.getInt("USER_NO"));
				loginUser.setUserCode(rset.getString("USER_CODE"));
				loginUser.setEmailId(rset.getString("EMAIL_ID"));
				loginUser.setUserPwd(rset.getString("USER_PWD"));
				loginUser.setUserName(rset.getString("USER_NAME"));
				loginUser.setUserSsn(rset.getString("USER_SSN"));
				loginUser.setUserPhone(rset.getString("PHONE_NUMBER"));
				loginUser.setUserAddress(rset.getString("USER_ADDRESS"));
				loginUser.setPoint(rset.getInt("POINT"));

				if (rset.getString("USER_CODE").equals("03")) {
					loginUser.setbNumber(rset.getString("BUSINESS_NUMBER"));
					loginUser.setbName(rset.getString("BUSINESS_NAME"));
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);

		}
		return loginUser;

	}

	public int insertUser(Connection conn, User u) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertUser");
		// insertUser=INSERT INTO USER_TB VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?,
		//														 ?, ?, ?, ?, ?, ?, ?, DEFAULT,DEFAULT)
		
		int point = (u.getUserCode().equals("02") ?  10000 : 0);

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, u.getUserCode());
			pstmt.setString(2, u.getEmailId());
			pstmt.setString(3, u.getUserPwd());
			pstmt.setString(4, u.getUserName());
			pstmt.setString(5, u.getUserSsn());
			pstmt.setString(6, u.getUserPhone());
			pstmt.setString(7, u.getUserAddress());
			pstmt.setInt(8, point);
			pstmt.setString(9, u.getbNumber());
			pstmt.setString(10, u.getbName());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public User selectUser(Connection conn, String emailId) {
		User u = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectUser");
		// selectUser=SELECT * FROM USER_TB WHERE EMAIL_ID=? AND STATUS='N'
//selectUser=SELECT * FROM USER_TB WHERE EMAIL_ID=? AND STATUS='N'

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, emailId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				u = new User();
				u.setUserNo(rset.getInt("USER_NO"));
				u.setUserCode(rset.getString("USER_CODE"));
				u.setEmailId(emailId);
				u.setUserPwd(rset.getString("USER_PWD"));
				u.setUserSsn(rset.getString("USER_SSN"));
				u.setUserName(rset.getString("USER_NAME"));
				u.setUserPhone(rset.getString("PHONE_NUMBER"));
				u.setUserAddress(rset.getString("USER_ADDRESS"));
				u.setPoint(rset.getInt("POINT"));
			}

			System.out.println("userDao User : " + u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return u;
	}

	public int updateMmeber(Connection conn, User u) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateUser");
//		updateUser=UPDATE USER_TB SET USER_PWD=?, PHONE_NUMBER=?, USER_ADDRESS=?, WHERE EMAIL_ID=?

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, u.getUserPwd());
			pstmt.setString(2, u.getUserPhone());
			pstmt.setString(3, u.getUserAddress());
			pstmt.setString(4, u.getEmailId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteUser(Connection conn, String emailId) {
		int result = 0;
		System.out.println("userDao delete result : " + emailId);

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteupdateUser");
//		deleteupdateUser=UPDATE USER_TB SET STATUS='Y' WHERE EMAIL_ID=?

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, emailId);

			result = pstmt.executeUpdate();
			System.out.println("userDao delete result : " + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int emailIdCheck(Connection conn, String emailId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String sql = prop.getProperty("idCheck");
		// idCheck=SELECT COUNT(*) FROM USER_TB WHERE EMAIL_ID=? AND STATUS='N'
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, emailId);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt(1);
			}

			System.out.println("rset.getInt(1) result = " + result);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public ArrayList<ULecture> selectLectureList(Connection conn, String emailId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ULecture> list = new ArrayList<ULecture>();
		String sql = prop.getProperty("selectLectureList");
		//SELECT * FROM VW_LEC_INFO WHERE EMAIL_ID =?
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, emailId);

			rset = pstmt.executeQuery();
			while (rset.next()) {

				list.add(new ULecture(rset.getInt("USER_NO"), rset.getString("EMAIL_ID"),
						rset.getString("LECTURE_CODE"), rset.getString("LECTURE_TITLE"),
						rset.getString("LECTURE_TOPIC"), rset.getDate("LECTURE_DATE")));

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

	public ArrayList<IProject> selectIProjectList(Connection conn, String emailId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<IProject> list = new ArrayList<IProject>();		
		String sql = prop.getProperty("selectIProjectList");
//		String sql = "SELECT * FROM VW_INTER_PRO WHERE EMAIL_ID=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, emailId);

			rset = pstmt.executeQuery();
			while (rset.next()) {

				list.add(new IProject(rset.getInt("USER_NO"), rset.getString("EMAIL_ID"),
						rset.getString("PROJECT_CODE"), rset.getString("PROJECT_NAME"), rset.getInt("AMOUNT_GOAL"),
						rset.getInt("AMOUNT_PRESENT"), rset.getString("DETAIL_INTRO")

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

	public ArrayList<UProject> selectUProjectList(Connection conn, String emailId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<UProject> list = new ArrayList<UProject>();
		String sql = prop.getProperty("selectUProjectList");
		//String sql = "SELECT * FROM VW_SIGN_PRO WHERE EMAIL_ID=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, emailId);

			rset = pstmt.executeQuery();
			while (rset.next()) {

				list.add(new UProject(rset.getInt("USER_NO"), rset.getString("EMAIL_ID"),
						rset.getString("PROJECT_CODE"), rset.getString("PROJECT_NAME"), rset.getInt("AMOUNT_GOAL"),
						rset.getInt("AMOUNT_PRESENT"), rset.getString("DETAIL_INTRO")

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

	public ArrayList<User> selectUserList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM USER_TB";

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new User(			
						rset.getString("USER_CODE"),
						rset.getString("EMAIL_ID"),
						rset.getString("USER_NAME"),
						rset.getString("USER_SSN"), 
						rset.getString("PHONE_NUMBER"),
						rset.getDate("JOIN_DATE"),
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

	public Map<String, Integer> selectCategoryList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Map<String, Integer> map = new HashMap<>();
		String sql = prop.getProperty("selectCategoryList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				String categoryName = rset.getString("CATEGORY_NAME");
				int count = rset.getInt(2);
				
				map.put(categoryName, count);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return map;
	}

	public int minusPoint(Connection conn, int userNo, int price) {
		int result = 0;
		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("minusPoint");
		String sql = "UPDATE USER_TB SET POINT = POINT-? WHERE USER_NO=?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, price);
			pstmt.setInt(2, userNo);
			

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int chargePoint(Connection conn, int userNo, int recharge) {
		int result = 0;
		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("plusPoint");
		String sql = "UPDATE USER_TB SET POINT = POINT+? WHERE USER_NO=?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, recharge);
			pstmt.setInt(2, userNo);
			

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
