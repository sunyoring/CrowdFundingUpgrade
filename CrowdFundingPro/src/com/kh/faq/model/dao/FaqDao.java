package com.kh.faq.model.dao;

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

import com.kh.faq.model.vo.Faq;

public class FaqDao {

	private Properties prop = new Properties();

	public FaqDao() {
		String fileName = FaqDao.class.getResource("/sql/faq/faq-query.properties").getPath();
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
	
	public ArrayList<Faq> selectList(Connection conn, String userCode) {
		
		ArrayList<Faq> list = new ArrayList<Faq>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		try {
			if(userCode.equals("01")) {
				sql = prop.getProperty("selectAll");
				
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Faq(rset.getString("F_NO"),
									rset.getString("QUESTION"),
									rset.getString("CREATOR_ID")
							));
				}
			}
			else{
				sql = prop.getProperty("selectList");
				
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Faq(rset.getString("F_NO"),
									rset.getString("TARGET_USER").charAt(0),
									rset.getString("QUESTION")
							));
				}
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

	public Faq selectFaq(Connection conn, String fNo) {
		
		Faq f = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				f = new Faq(rset.getString("F_NO"),
							rset.getString("TARGET_USER").charAt(0),
							rset.getString("QUESTION"),
							rset.getString("ANSWER")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(conn);
		}
		
		return f;
	}

	public Faq selectDetailAll(Connection conn, String fno) {
		Faq faq = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDetailAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fno);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				faq = new Faq(rset.getString("F_NO"),
							rset.getString("TARGET_USER").charAt(0),
							rset.getString("QUESTION"),
							rset.getString("ANSWER"),
							rset.getString("CREATOR_ID"),
							rset.getDate("CREATE_DATE"),
							rset.getString("UPDATER_ID"),
							rset.getDate("UPDATE_DATE"),
							rset.getString("SHOW_YN").charAt(0)
						);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(conn);
		}
		
		return faq;
	}

	public int insertFaq(Connection conn, Faq f) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, String.valueOf(f.getTargetUser()));
			pstmt.setString(2, f.getQuestion());
			pstmt.setString(3, f.getAnswer());
			pstmt.setString(4, f.getCreatorId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateFaq(Connection conn, Faq f) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, f.getQuestion());
			pstmt.setString(2, f.getAnswer());
			pstmt.setString(3, f.getUpdaterId());
			pstmt.setString(4, String.valueOf(f.getShowYn()));
			pstmt.setString(5, f.getfNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteFaq(Connection conn, String fNo) {

		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fNo);
			
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
