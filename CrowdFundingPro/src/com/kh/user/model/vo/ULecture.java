package com.kh.user.model.vo;

import java.sql.Date;

public class ULecture {
	
	private int userNo;		//유저번호
	private String emailId; //이메일아이디
	private String LCode;	//강의코드
	private String LTitle;	//강의제목
	private String LTopic;	//강의토픽
	private Date LDate;		//강의날짜

public ULecture() {
	// TODO Auto-generated constructor stub
}
	public ULecture(int userNo, String emailId, String lCode, String lTitle, String lTopic, Date lDate) {
		super();
		this.userNo = userNo;
		this.emailId = emailId;
		LCode = lCode;
		LTitle = lTitle;
		LTopic = lTopic;
		LDate = lDate;
	}


	public String getLCode() {
		return LCode;
	}

	public void setLCode(String lCode) {
		LCode = lCode;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLTitle() {
		return LTitle;
	}

	public void setLTitle(String lTitle) {
		LTitle = lTitle;
	}

	public String getLTopic() {
		return LTopic;
	}

	public void setLTopic(String lTopic) {
		LTopic = lTopic;
	}

	public Date getLDate() {
		return LDate;
	}

	public void setLDate(Date lDate) {
		LDate = lDate;
	}

	@Override
	public String toString() {
		return "Lecture [userNo=" + userNo + ", emailId=" + emailId + ", LTitle=" + LTitle + ", LTopic=" + LTopic
				+ ", LDate=" + LDate + "]";
	}
	
	
	
}
