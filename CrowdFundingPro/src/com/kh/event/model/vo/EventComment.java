package com.kh.event.model.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class EventComment {
//	C_NUM	NUMBER	댓글 번호
//	E_NO	NUMBER	글 번호
//	C_ID	VARCHAR2(100 BYTE)	작성자
//	C_PWD	NUMBER	댓글 암호
//	C_DATE	DATE	등록일
//	C_PARENT	NUMBER	부모 댓글 번호
//	C_CONTENT	VARCHAR2(1000 BYTE)	댓글 내용
	
	
	int cNum;
	int eNo;
	String name;
	String emailId;
	int ePwd;
	Date cDate;
	int cParent;
	String comment;
	
	public EventComment() {
		// TODO Auto-generated constructor stub
	}
	




	public EventComment(int cNum, int eNo, String name,  String emailId, int ePwd, Date cDate, int cParent, String comment) {
		super();
		this.cNum = cNum;
		this.eNo = eNo;
		this.name = name;
		this.emailId = emailId;
		this.ePwd = ePwd;
		this.cDate = cDate;
		this.cParent = cParent;
		this.comment = comment;
	}
	
	//이벤트 등록용 
	public EventComment(int eNo, String emailId,int cParent, String comment) {
		super();
		this.eNo = eNo;
		this.emailId = emailId;
		this.cParent = cParent;
		this.comment = comment;
	}


	@Override
	public String toString() {
		return "EventComment [cNum=" + cNum + ", eNo=" + eNo + ", emailId=" + emailId + ", ePwd=" + ePwd + ", cDate="
				+ cDate + ", cParent=" + cParent + ", comment=" + comment + "]";
	}


	
	
}
