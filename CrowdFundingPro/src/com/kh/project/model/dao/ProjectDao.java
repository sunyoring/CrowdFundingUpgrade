package com.kh.project.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kh.common.model.vo.Attachment;
import com.kh.project.model.vo.IProject;
import com.kh.project.model.vo.Project;

public class ProjectDao {
	private Properties prop = new Properties();

	public ProjectDao() {
		String fileName = ProjectDao.class.getResource("/sql/project/project-query.properties").getPath();
		System.out.println("fileName   " + fileName);
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

	public ArrayList<Project> selectList(Connection conn) {
		ArrayList<Project> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");
		// SELECT PROJECT_CODE,CHANGE_NAME,PROJECT_NAME,AMOUNT_PRESENT
		// FROM PROJECT JOIN ATTACHMENT USING(FILE_NO) ORDER BY PROJECT_CODE
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Project pj = new Project();

				pj.setProjectCode(rset.getInt("PROJECT_CODE"));
				pj.setProjectName(rset.getString("PROJECT_NAME"));
				pj.setAmountPresent(rset.getInt("AMOUNT_PRESENT"));
				pj.setAmountGoal(rset.getInt("AMOUNT_GOAL"));
				pj.setTitleImg(rset.getString("CHANGE_NAME"));

				list.add(pj);

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

//	public Attachment attachmentSelect(Connection conn, String pCode) {
//		Attachment at = null;
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		
//		String sql = prop.getProperty("attachmentSelect");
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, pCode);
//			
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				at = new Attachment();
//				at.setFileNo(rset.getInt("FILE_NO"));
//				at.setOriginName(rset.getString("ORIGIN_NAME"));
//				at.setChangeName(rset.getString("CHANGE_NAME"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			close(rset);
//			close(pstmt);
//		}
//		return at;
//		
//	}

	public int insertProject(Connection conn, Project pj, Attachment at) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProject");
		// INSERT INTO PROJECT VALUES(SEQ_PNO.NEXTVAL, ?,?,?,?,?,?,DEFAULT,?,?, ?)

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pj.getUserNo());
			pstmt.setString(2, pj.getProjectName());
			pstmt.setInt(3, pj.getAmountGoal());
			pstmt.setInt(4, 0);
			pstmt.setDate(5, new java.sql.Date(pj.getDdln().getTime())); // DB에 날짜형을 입력할때에는 java.sql.Date로 형변환 해주어야함!
																			// getTime()메소드도 잊지말기
			pstmt.setInt(6, pj.getDeliveryCharge());
			pstmt.setString(7, pj.getDetailIntro());
			pstmt.setInt(8, Integer.parseInt(pj.getCategoryNo()));
			pstmt.setInt(9, at.getFileNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 3);
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectFileNo(Connection conn, Attachment at) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		System.out.println(at.getChangeName());

		String sql = prop.getProperty("selectFileNo");
		// SELECT FILE_NO FROM ATTACHMENT WHERE CHANGE_NAME = ?
		System.out.println(sql);

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getChangeName());

			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("FILE_NO");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public Project projectSelect(Connection conn, int pCode) {
		Project pj = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("projectSelect");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pCode);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				pj = new Project();
				pj.setProjectCode(pCode);
				pj.setProjectName(rset.getString("PROJECT_NAME"));
				pj.setAmountGoal(rset.getInt("AMOUNT_GOAL"));
				pj.setAmountPresent(rset.getInt("AMOUNT_PRESENT"));
				pj.setDdln(rset.getDate("DDLN"));
				pj.setDeliveryCharge(rset.getInt("PRICE"));
				pj.setDetailIntro(rset.getNString("DETAIL_INTRO"));
				pj.setFileNo(rset.getInt("FILE_NO"));
				pj.setTitleImg(rset.getNString("CHANGE_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pj;
	}

	public Project updatePSelect(Connection conn, int fn) {
		// TODO Auto-generated method stub

		Project pj = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("updatePSelect");
		// SELECT PROJECT_NAME,AMOUNT_GOAL,DDLN,PRICE,DETAIL_INTRO,FILE_NO
		// FROM PROJECT JOIN ATTACHMENT USING(FILE_NO) WHERE FILE_NO=?

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fn);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				pj = new Project(rset.getInt("PROJECT_CODE"), rset.getString("PROJECT_NAME"),
						rset.getInt("AMOUNT_GOAL"), rset.getDate("DDLN"), rset.getInt("PRICE"),
						rset.getString("DETAIL_INTRO"), rset.getInt("FILE_NO")

				);

//				pj.setProjectName(rset.getString("PROJECT_NAME"));
//				pj.setAmountGoal(rset.getInt("AMOUNT_GOAL"));
//				pj.setDdln(rset.getDate("DDLN"));
//				pj.setDeliveryCharge(rset.getInt("PRICE"));
//				pj.setDetailIntro(rset.getString("DETAIL_INTRO"));
//				pj.setFileNo(rset.getInt("FILE_NO"));
//				pj.setTitleImg(rset.getString("CHANGE_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return pj;
	}

	public Attachment updateASelect(Connection conn, int fn) {
		// TODO Auto-generated method stub
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("updateASelect");
		// SELECT FILE_NO,ORIGIN_NAME,CHANGE_NAME FROM ATTACHMENT WHERE FILE_NO=?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fn);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				at = new Attachment();

				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
	}

	public int attachmentUpdate(Connection conn, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("attachmentUpdate");
		// UPDATE ATTACHMENT SET ORIGIN_NAME=?, CHANGE_NAME=?, FILE_PATH=? WHERE
		// FILE_NO=?
		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int projectUpdate(Connection conn, Project pj, Attachment at) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("projectUpdate");
		// UPDATE PROJECT SET
		// PROJECT_NAME=?,AMOUNT_GOAL=?,DDLN=?,PRICE=?,DETAIL_INTRO=? WHERE
		// PROJECT_CODE=?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pj.getProjectName());
			pstmt.setInt(2, pj.getAmountGoal());
			pstmt.setDate(3, new java.sql.Date(pj.getDdln().getTime()));
			pstmt.setInt(4, pj.getDeliveryCharge());
			pstmt.setString(5, pj.getDetailIntro());
			pstmt.setInt(6, pj.getProjectCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getListCount(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("getListCount");

		try {
			pstmt = conn.prepareStatement(sql);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Project> selectProjectList(Connection conn, int startRow, int endRow) {
		ArrayList<Project> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectProjectList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Project pj = new Project();

				pj.setProjectCode(rset.getInt("PROJECT_CODE"));
				pj.setProjectName(rset.getString("PROJECT_NAME"));
				pj.setAmountPresent(rset.getInt("AMOUNT_PRESENT"));
				pj.setAmountGoal(rset.getInt("AMOUNT_GOAL"));
				pj.setTitleImg(rset.getString("CHANGE_NAME"));
				pj.setCategoryName(rset.getString("CATEGORY_NAME"));
				pj.setDdln(rset.getDate("DDLN"));

				list.add(pj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Map<Integer, String> selectCategoryList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Map<Integer, String> map = new HashMap<>();
		String sql = prop.getProperty("selectCategoryList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int categoryNo = rset.getInt("CATEGORY_NO");
				String categoryName = rset.getString("CATEGORY_NAME");

				map.put(categoryNo, categoryName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return map;
	}

	public ArrayList<Project> selectProjectListWithCategory(Connection conn, int startRow, int endRow, int categoryNo) {
		ArrayList<Project> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectProjectListWithCategory");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Project pj = new Project();

				pj.setProjectCode(rset.getInt("PROJECT_CODE"));
				pj.setProjectName(rset.getString("PROJECT_NAME"));
				pj.setAmountPresent(rset.getInt("AMOUNT_PRESENT"));
				pj.setAmountGoal(rset.getInt("AMOUNT_GOAL"));
				pj.setTitleImg(rset.getString("CHANGE_NAME"));
				pj.setCategoryName(rset.getString("CATEGORY_NAME"));
				pj.setDdln(rset.getDate("DDLN"));

				list.add(pj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Project> selectRandomList(Connection conn) {

		ArrayList<Project> list = new ArrayList<Project>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Project p = null;

		String sql = "SELECT PROJECT_CODE,PROJECT_NAME,AMOUNT_GOAL,AMOUNT_PRESENT,DDLN,DETAIL_INTRO,CHANGE_NAME,CATEGORY_NAME FROM "
				+ "(SELECT *FROM PROJECT ORDER BY DBMS_RANDOM.VALUE ) LEFT JOIN ATTACHMENT USING(FILE_NO)"
				+ "LEFT JOIN CATEGORY USING(CATEGORY_NO) WHERE ROWNUM <=9 AND DDLN >= TO_CHAR(SYSDATE,'YY/MM/DD')";

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			/*
			 * PROJECT_CODE NUMBER 프로젝트코드 USER_NO VARCHAR2(3 BYTE) 담당자회원번호 PROJECT_NAME
			 * VARCHAR2(4000 BYTE) 프로젝트명 AMOUNT_GOAL NUMBER 목표금액 AMOUNT_PRESENT NUMBER 현재금액
			 * DDLN DATE 마감일 PRICE NUMBER 배송료 SUPPORT_NUM NUMBER 서포터수 DETAIL_INTRO
			 * VARCHAR2(4000 BYTE) 프로젝트세부내용 CATEGORY_NO NUMBER 카테고리번호 FILE_NO NUMBER 파일번호
			 */

			while (rset.next()) {

				p = new Project(rset.getInt("PROJECT_CODE"), rset.getString("PROJECT_NAME"), rset.getInt("AMOUNT_GOAL"),
						rset.getInt("AMOUNT_PRESENT"), rset.getDate("DDLN"), rset.getString("DETAIL_INTRO"),
						rset.getString("CHANGE_NAME"));
				p.setCategoryName(rset.getString("CATEGORY_NAME"));

				list.add(p);

			}

			System.out.println("랜덤 프로젝트 dao: " + list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<Project> selectRankList(Connection conn) {
		ArrayList<Project> list = new ArrayList<Project>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Project p = null;
		String sql = "SELECT PROJECT_CODE,PROJECT_NAME,DETAIL_INTRO,DDLN,AMOUNT_GOAL,AMOUNT_PRESENT,CATEGORY_NAME,B.CHANGE_NAME,RANK() OVER (ORDER BY SUPPORT_NUM DESC) RANK   FROM PROJECT A LEFT JOIN ATTACHMENT B ON A.FILE_NO= B.FILE_NO LEFT JOIN CATEGORY USING(CATEGORY_NO) WHERE ROWNUM <= 6 AND DDLN >= TO_CHAR(SYSDATE,'YY/MM/DD') ORDER BY RANK";
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				p = new Project(rset.getInt("PROJECT_CODE"), rset.getString("PROJECT_NAME"), rset.getInt("AMOUNT_GOAL"),
						rset.getInt("AMOUNT_PRESENT"), rset.getDate("DDLN"), rset.getString("DETAIL_INTRO"),
						rset.getString("CHANGE_NAME"));
				p.setCategoryName(rset.getString("CATEGORY_NAME"));

				list.add(p);
			}

			System.out.println("랭크 프로젝트 dao: " + list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public Map<Integer, Integer> getListCountWithCategoryNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Map<Integer, Integer> map = new HashMap<>();
		String sql = prop.getProperty("getListCountWithCategoryNo");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int categoryNo = rset.getInt("CATEGORY_NO");
				int categoryCnt = rset.getInt(2);

				map.put(categoryNo, categoryCnt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return map;
	}

	public ArrayList<Project> selectProjectListWithSearchValue(Connection conn, int startRow, int endRow,
			String searchValue) {
		ArrayList<Project> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String str = '%' + searchValue + '%';

		String sql = prop.getProperty("selectProjectListWithSearchValue");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Project pj = new Project();

				pj.setProjectCode(rset.getInt("PROJECT_CODE"));
				pj.setProjectName(rset.getString("PROJECT_NAME"));
				pj.setAmountPresent(rset.getInt("AMOUNT_PRESENT"));
				pj.setAmountGoal(rset.getInt("AMOUNT_GOAL"));
				pj.setTitleImg(rset.getString("CHANGE_NAME"));
				pj.setCategoryName(rset.getString("CATEGORY_NAME"));
				pj.setDdln(rset.getDate("DDLN"));

				list.add(pj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Project> selectProjectListWithCategoryAndSearchValue(Connection conn, int startRow, int endRow,
			int categoryNo, String searchValue) {
		ArrayList<Project> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String str = '%' + searchValue + '%';

		String sql = prop.getProperty("selectProjectListWithCategoryAndSearchValue");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, str);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Project pj = new Project();

				pj.setProjectCode(rset.getInt("PROJECT_CODE"));
				pj.setProjectName(rset.getString("PROJECT_NAME"));
				pj.setAmountPresent(rset.getInt("AMOUNT_PRESENT"));
				pj.setAmountGoal(rset.getInt("AMOUNT_GOAL"));
				pj.setTitleImg(rset.getString("CHANGE_NAME"));
				pj.setCategoryName(rset.getString("CATEGORY_NAME"));
				pj.setDdln(rset.getDate("DDLN"));

				list.add(pj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getSearchCount(Connection conn, String searchValue) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getSearchCount");

		String str = "%" + searchValue + "%";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public int getSearchCountWithCategoryNo(Connection conn, int categoryNo, String searchValue) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getSearchCountWithCategoryNo");

		String str = "%" + searchValue + "%";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			pstmt.setString(2, str);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public String getCategoryName(Connection conn, String categoryNo) {
		String result = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getCategoryName");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(categoryNo));

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public ArrayList<Project> searchList(Connection conn, String keyword) {
		System.out.println("전체검색 서블릿 DAO : " + keyword);

		ArrayList<Project> list = new ArrayList<Project>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM PROJECT LEFT JOIN CATEGORY USING(CATEGORY_NO) LEFT JOIN ATTACHMENT USING(FILE_NO) WHERE (PROJECT_NAME LIKE ? OR DETAIL_INTRO LIKE ?) AND DDLN >= TO_CHAR(SYSDATE,'YY/MM/DD')";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {

				Project p = new Project();

				p.setProjectName(rset.getString("PROJECT_NAME"));
				p.setProjectCode(rset.getInt("PROJECT_CODE"));
				p.setAmountGoal(rset.getInt("AMOUNT_GOAL"));
				p.setAmountPresent(rset.getInt("AMOUNT_PRESENT"));
				p.setDdln(rset.getDate("DDLN"));
				p.setDetailIntro(rset.getString("DETAIL_INTRO"));
				p.setCategoryName(rset.getString("CATEGORY_NAME"));
				p.setTitleImg(rset.getString("CHANGE_NAME"));

				list.add(p);
			}

			System.out.println("전체검색 프로젝트 dao: " + list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	// =================================================================================

	public int insertSUP(Connection conn, int userNo, int pCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertSUP");

		try {
			pstmt = conn.prepareStatement(sql);
			// INSERT INTO SIGN_UP_PRO VALUES(SEQ_SIGN_PRO.NEXTVAL,?,?)

			pstmt.setInt(1, userNo);
			pstmt.setInt(2, pCode);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	// [관심프로젝트]
//	public int insertIP(Connection conn, User user, Project pj) {
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("insertIP");
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			// INSERT INTO SIGN_UP_PRO VALUES(SEQ_SIGN_PRO.NEXTVAL,?,?)
//
//			pstmt.setInt(1, user.getUserNo());
//			pstmt.setInt(2, pj.getProjectCode());
//
//			result = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//
//	}
	
	public int insertIP(Connection conn, int userNo, int pCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertIP");
//		insertIP=INSERT INTO INTEREST_IN_PRO VALUES(SEQ_INTER_PRO.NEXTVAL,?,?)

		try {
			pstmt = conn.prepareStatement(sql);
			// INSERT INTO SIGN_UP_PRO VALUES(SEQ_SIGN_PRO.NEXTVAL,?,?)

			pstmt.setInt(1, userNo);
			pstmt.setInt(2, pCode);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	

	public int plusSupport(Connection conn, int pCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("plusSupport");

		try {
			pstmt = conn.prepareStatement(sql);
//			UPDATE PROJECT SET SUPPORT_NUM = SUPPORT_NUM+1 WHERE PROJECT_CODE=? 

			pstmt.setInt(1, pCode);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteSUP(Connection conn, String signProNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteSUP");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, signProNo);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteInterPro(Connection conn, int pCode, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteInterPro");
		// DELETE FROM INTEREST_IN_PRO WHERE PROJECT_CODE = ? AND USER_NO = ? 
		
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pCode);
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

	public ArrayList<IProject> interProList(Connection conn, int pCode) {
		System.out.println("디테일 관심 유저조회 dao: " + pCode );
		ArrayList<IProject> list = new ArrayList<IProject>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM INTEREST_IN_PRO WHERE PROJECT_CODE = ?";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pCode);

			rset = pstmt.executeQuery();

			while (rset.next()) {

			list.add(new IProject(rset.getInt("USER_NO"),rset.getInt("PROJECT_CODE")));
				
			}

			System.out.println("디테일 관심 유저조회 dao: " + list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	

}
