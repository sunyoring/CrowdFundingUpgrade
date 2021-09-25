package com.kh.recruit.model.dao;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kh.common.model.vo.Attachment;
import com.kh.recruit.model.vo.RecruitCode;
import com.kh.recruit.model.vo.RecruitMember;
import com.kh.recruit.model.vo.Recruitment;

public class RecruitDao {

	private Properties prop = new Properties();

	public RecruitDao() {
		String fileName = RecruitDao.class.getResource("/sql/recruit/recruit-query.properties").getPath();
		System.out.println("fileName   " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int insertRecruitment(Connection conn, Recruitment r) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertRecruitment");

		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setString(index++, r.getTitle());
			pstmt.setString(index++, r.getCode());
			// util.Date -> sql.Date 변환
			pstmt.setDate(index++, new java.sql.Date(r.getStart().getTime()));
			pstmt.setDate(index++, new java.sql.Date(r.getEnd().getTime()));
			pstmt.setString(index++, r.getTime());
			pstmt.setString(index++, r.getContent1());
			pstmt.setString(index++, r.getContent2());
			pstmt.setString(index++, r.getContent3());
			pstmt.setString(index++, r.getContent4());
			pstmt.setString(index++, r.getContent5());
			pstmt.setString(index++, r.getContent6());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("getListCount");

		try {
			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			if (rset.next()) {
				// 이렇게 쓰면 가독성 문제로 컬럼명으로 보통 한다고 함
				listCount = rset.getInt(1); // count
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return listCount;
	}

	public ArrayList<Recruitment> selectList(Connection conn, int startRow, int endRow) {
		ArrayList<Recruitment> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int id = rset.getInt("R_ID");
				String title = rset.getString("R_TITLE");
				String code = rset.getString("R_CODE");
				Date start = rset.getDate("R_START");
				Date end = rset.getDate("R_END");
				String time = rset.getString("R_TIME");
				String content1 = rset.getString("R_CONTENT1");
				String content2 = rset.getString("R_CONTENT2");
				String content3 = rset.getString("R_CONTENT3");
				String content4 = rset.getString("R_CONTENT4");
				String content5 = rset.getString("R_CONTENT5");
				String content6 = rset.getString("R_CONTENT6");

				list.add(new Recruitment(id, title, code, start, end, time, content1, content2, content3, content4,
						content5, content6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public ArrayList<RecruitCode> selectRecruitCode(Connection conn) {
		ArrayList<RecruitCode> list = new ArrayList<RecruitCode>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectRecruitCode");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new RecruitCode(
							rset.getString("R_CODE"),
							rset.getInt("COUNT")
						));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public Recruitment selectRecruitment(Connection conn, int rid) {
		Recruitment r = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectRecruitment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, rid);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int id = rset.getInt("R_ID");
				String title = rset.getString("R_TITLE");
				String code = rset.getString("R_CODE");
				Date start = rset.getDate("R_START");
				Date end = rset.getDate("R_END");
				String time = rset.getString("R_TIME");
				String content1 = rset.getString("R_CONTENT1");
				String content2 = rset.getString("R_CONTENT2");
				String content3 = rset.getString("R_CONTENT3");
				String content4 = rset.getString("R_CONTENT4");
				String content5 = rset.getString("R_CONTENT5");
				String content6 = rset.getString("R_CONTENT6");

				r = new Recruitment(id, title, code, start, end, time, content1, content2, content3, content4, content5, content6);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return r;
	}

	public int updateRecruitment(Connection conn, Recruitment r) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateRecruitment");

		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setString(index++, r.getTitle());
			pstmt.setString(index++, r.getCode());
			// util.Date -> sql.Date 변환
			pstmt.setDate(index++, new java.sql.Date(r.getStart().getTime()));
			pstmt.setDate(index++, new java.sql.Date(r.getEnd().getTime()));
			pstmt.setString(index++, r.getTime());
			pstmt.setString(index++, r.getContent1());
			pstmt.setString(index++, r.getContent2());
			pstmt.setString(index++, r.getContent3());
			pstmt.setString(index++, r.getContent4());
			pstmt.setString(index++, r.getContent5());
			pstmt.setString(index++, r.getContent6());
			pstmt.setInt(index++, r.getId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteRecruitment(Connection conn, int id) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("deleteRecruitment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, id);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<String> selectAllTitle(Connection conn) {
		ArrayList<String> list = new ArrayList<String>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAllTitle");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(rset.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public Recruitment findRecruitmentWithTitle(Connection conn, String rTitle) {
		Recruitment r = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("findRecruitmentWithTitle");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, rTitle);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int id = rset.getInt("R_ID");
				String title = rset.getString("R_TITLE");
				String code = rset.getString("R_CODE");
				Date start = rset.getDate("R_START");
				Date end = rset.getDate("R_END");
				String time = rset.getString("R_TIME");
				String content1 = rset.getString("R_CONTENT1");
				String content2 = rset.getString("R_CONTENT2");
				String content3 = rset.getString("R_CONTENT3");
				String content4 = rset.getString("R_CONTENT4");
				String content5 = rset.getString("R_CONTENT5");
				String content6 = rset.getString("R_CONTENT6");

				r = new Recruitment(id, title, code, start, end, time, content1, content2, content3, content4, content5, content6);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return r;
	}

	public int insertRecruitMember(Connection conn, RecruitMember rm) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertRecruitMember");

		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setString(index++, rm.getName());
			pstmt.setString(index++, rm.getPhone());
			pstmt.setString(index++, rm.getEducation());
			pstmt.setString(index++, rm.getCareer());
			pstmt.setString(index++, rm.getEmail());
			pstmt.setString(index++, rm.getPassword());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertPortfolio(Connection conn, RecruitMember rm, Attachment at) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertPortfolio");

		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setInt(index++, at.getFileNo());
			pstmt.setInt(index++, rm.getId());

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

		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setInt(index++, 1); // 참조파트 1이 공고
			pstmt.setString(index++, at.getOriginName());
			pstmt.setString(index++, at.getChangeName());
			pstmt.setString(index++, at.getFilePath());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertRecruitStatus(Connection conn, Recruitment r, RecruitMember rm) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertRecruitStatus");

		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setInt(index++, rm.getId());
			pstmt.setInt(index++, r.getId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public RecruitMember findRecruitMemberWithEmail(Connection conn, String email) {
		RecruitMember rm = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("findRecruitMemberWithEmail");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int id = rset.getInt("RM_ID");
				String name = rset.getString("RM_NAME");
				String phone = rset.getString("RM_PHONE");
				String education = rset.getString("RM_EDUCATION");
                String career = rset.getString("RM_CAREER");
			    String password = rset.getString("RM_PASSWORD");
			    
			    rm = new RecruitMember(id, name, phone, education, career, email, password);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return rm;
	}

	public Attachment findAttachmentWithOriginName(Connection conn, String originName) {
		Attachment at = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("findAttachmentWithOriginName");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, originName);
			
			rset = pstmt.executeQuery();

			if (rset.next()) {
				int fileNo = rset.getInt("FILE_NO"); 
				int refNo = rset.getInt("REF_NO");
				String changeName = rset.getString("CHANGE_NAME");
				java.sql.Date uploadDate = rset.getDate("UPLOAD_DATE");
				String filePath = rset.getString("FILE_PATH");
			    
			    at = new Attachment(fileNo, refNo, originName, changeName, uploadDate, filePath);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return at;
	}

	public int getListCountWithCode(Connection conn, String code) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("getListCountWithCode");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				// 이렇게 쓰면 가독성 문제로 컬럼명으로 보통 한다고 함
				listCount = rset.getInt(1); // count
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<Recruitment> selectListWithCode(Connection conn, int startRow, int endRow, String code) {
		ArrayList<Recruitment> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListWithCode");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int id = rset.getInt("R_ID");
				String title = rset.getString("R_TITLE");
				Date start = rset.getDate("R_START");
				Date end = rset.getDate("R_END");
				String time = rset.getString("R_TIME");
				String content1 = rset.getString("R_CONTENT1");
				String content2 = rset.getString("R_CONTENT2");
				String content3 = rset.getString("R_CONTENT3");
				String content4 = rset.getString("R_CONTENT4");
				String content5 = rset.getString("R_CONTENT5");
				String content6 = rset.getString("R_CONTENT6");

				list.add(new Recruitment(id, title, code, start, end, time, content1, content2, content3, content4,
						content5, content6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int getListCountWithTitle(Connection conn, String title) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("getListCountWithTitle");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + title + "%");

			rset = pstmt.executeQuery();

			if (rset.next()) {
				// 이렇게 쓰면 가독성 문제로 컬럼명으로 보통 한다고 함
				listCount = rset.getInt(1); // count
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public int getListCountWithCodeTitle(Connection conn, String code, String title) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("getListCountWithCodeTitle");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, "%" + title + "%");

			rset = pstmt.executeQuery();

			if (rset.next()) {
				// 이렇게 쓰면 가독성 문제로 컬럼명으로 보통 한다고 함
				listCount = rset.getInt(1); // count
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList<Recruitment> selectListWithTitle(Connection conn, int startRow, int endRow, String title) {
		ArrayList<Recruitment> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectListWithTitle");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int id = rset.getInt("R_ID");
				title = rset.getString("R_TITLE");
				Date start = rset.getDate("R_START");
				Date end = rset.getDate("R_END");
				String code = rset.getString("R_CODE");
				String time = rset.getString("R_TIME");
				String content1 = rset.getString("R_CONTENT1");
				String content2 = rset.getString("R_CONTENT2");
				String content3 = rset.getString("R_CONTENT3");
				String content4 = rset.getString("R_CONTENT4");
				String content5 = rset.getString("R_CONTENT5");
				String content6 = rset.getString("R_CONTENT6");

				list.add(new Recruitment(id, title, code, start, end, time, content1, content2, content3, content4,
						content5, content6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public ArrayList<Recruitment> selectListWithCodeTitle(Connection conn, int startRow, int endRow, String code,
			String title) {
		ArrayList<Recruitment> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListWithCodeTitle");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, "%" + title + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				int id = rset.getInt("R_ID");
				Date start = rset.getDate("R_START");
				Date end = rset.getDate("R_END");
				title = rset.getString("R_TITLE");
				String time = rset.getString("R_TIME");
				String content1 = rset.getString("R_CONTENT1");
				String content2 = rset.getString("R_CONTENT2");
				String content3 = rset.getString("R_CONTENT3");
				String content4 = rset.getString("R_CONTENT4");
				String content5 = rset.getString("R_CONTENT5");
				String content6 = rset.getString("R_CONTENT6");

				list.add(new Recruitment(id, title, code, start, end, time, content1, content2, content3, content4,
						content5, content6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public Map<Integer, String> selectAllTitleWithId(Connection conn) {
		Map<Integer, String> map = new HashMap<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAllTitleWithId");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				map.put(rset.getInt(1), rset.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return map;
	}

	public String selectRecruitMemberPasswordWithIdAndEmail(Connection conn, String rid, String email) {
		String password = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRecruitMemberPasswordWithIdAndEmail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(rid));
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				password = rset.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return password;
	}

	public RecruitMember selectRecruitMemberWithIdAndEmail(Connection conn, String rid, String email) {
		RecruitMember rm = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRecruitMemberWithIdAndEmail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(rid));
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int id = rset.getInt("RM_ID");
				String name = rset.getString("RM_NAME");
				String phone = rset.getString("RM_PHONE");
				String education = rset.getString("RM_EDUCATION");
				String career = rset.getString("RM_CAREER");
				String password = rset.getString("RM_PASSWORD");
				
				rm = new RecruitMember(id, name, phone, education, career, email, password);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rm;
	}

	public Attachment selectAttachmentWithIdAndEmail(Connection conn, String rid, String email) {
		Attachment at = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachmentWithIdAndEmail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(rid));
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int fileNo = rset.getInt("FILE_NO");
				int refNo = rset.getInt("REF_NO");
				String originName = rset.getString("ORIGIN_NAME");
				String changeName = rset.getString("CHANGE_NAME");
				java.sql.Date uploadDate = rset.getDate("UPLOAD_DATE");
				String filePath = rset.getString("FILE_PATH");
				
				at = new Attachment(fileNo, refNo, originName, changeName, uploadDate, filePath);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return at;
	}

	public Attachment selectAttachmentFileNo(Connection conn, int fileNo) {
		Attachment at = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachmentFileNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, fileNo);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int refNo = rset.getInt("REF_NO");
				String originName = rset.getString("ORIGIN_NAME");
				String changeName = rset.getString("CHANGE_NAME");
				java.sql.Date uploadDate = rset.getDate("UPLOAD_DATE");
				String filePath = rset.getString("FILE_PATH");
				
				at = new Attachment(fileNo, refNo, originName, changeName, uploadDate, filePath);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return at;
	}

	public int updateRecruitMember(Connection conn, RecruitMember rm) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("updateRecruitMember");
		
		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setString(index++, rm.getName());
			pstmt.setString(index++, rm.getPhone());
			pstmt.setString(index++, rm.getEducation());
			pstmt.setString(index++, rm.getCareer());
			pstmt.setString(index++, rm.getEmail());
			pstmt.setInt(index++, rm.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int updateAttachment(Connection conn, Attachment at, int fileNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setString(index++, at.getOriginName());
			pstmt.setString(index++, at.getChangeName());
			pstmt.setString(index++, at.getFilePath());
			pstmt.setInt(index++, fileNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int updatePortfolioWithFileNo(Connection conn, RecruitMember rm, Attachment at, int fileNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("updatePortfolioWithFileNo");
		
		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;
			pstmt.setInt(index++, at.getFileNo());
			pstmt.setInt(index++, fileNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

}
