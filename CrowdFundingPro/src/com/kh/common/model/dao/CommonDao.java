package com.kh.common.model.dao;

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
import com.kh.recruit.model.dao.RecruitDao;

public class CommonDao {

	private Properties prop = new Properties();

	public CommonDao() {
		String fileName = RecruitDao.class.getResource("/sql/common/common-query.properties").getPath();
		System.out.println("fileName   " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Attachment> selectAttachment(Connection conn, String refNo) {
		ArrayList<Attachment> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAttachment");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Integer.parseInt(refNo));
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int fileNo = rset.getInt("FILE_NO"); 
				String originName = rset.getString("ORIGIN_NAME");
				String changeName = rset.getString("CHANGE_NAME");
				java.sql.Date uploadDate = rset.getDate("UPLOAD_DATE");
				String filePath = rset.getString("FILE_PATH");
			    
				list.add(new Attachment(fileNo, Integer.parseInt(refNo), originName, changeName, uploadDate, filePath));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int deleteAttachment(Connection conn, String refNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(refNo));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int insertProjectAttachment(Connection conn, Attachment at) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("insertProjectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, at.getRefNo());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int insertLectureAttachment(Connection conn, Attachment at) {
		//insertLectureAttachment=INSERT INTO ATTACHMENT VALUES (SEQ_AT_NO.NEXTCAL, ?,?,?,SYSDATE,?)
		int result = 0;
		
		PreparedStatement pstm = null;
		String sql = prop.getProperty("insertLectureAttachment");
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, at.getOriginName());
			pstm.setString(2, at.getChangeName());
			pstm.setString(3, at.getFilePath());
			
			result = pstm.executeUpdate();
			
		} catch(SQLException e) { e.printStackTrace();} finally { close(pstm);}
		
		
		return result;
	}

	public int updateLectureAttachment(Connection conn, Attachment at,String originName) {
		
		int result = 0;
		//UPDATE ATTACHMENT SET ORIGIN_NAME =?, CHANGE_NAME =?,UPLOAD_DATE=?,FILE_PATH=? WHERE CHANGE_NAME =?
		PreparedStatement pstm = null;
		String sql = prop.getProperty("updateLectureAttachment");
		
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, at.getOriginName());
			pstm.setString(2, at.getChangeName());
			pstm.setString(3, at.getFilePath());
			pstm.setInt(4, at.getFileNo());
			pstm.setString(5, originName);
			
			result = pstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(pstm);
		}
		
		
		return result;
	}

}
