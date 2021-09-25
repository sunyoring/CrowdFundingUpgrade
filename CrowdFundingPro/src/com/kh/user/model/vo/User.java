package com.kh.user.model.vo;

import java.sql.Date;

public class User {

	private int userNo;
	private String userCode;    // 01 : 관리자 , 02 : 일반회원 , 03 :  사업자회원
	private String emailId;		//이메일아이디
	private String userPwd;		//비밀번호
	private String userName;	//이름
	private String userSsn;		//주민번호
	private String userPhone;	//전화번호
	private String userAddress;	//주소
	private int point;			//적립금
	private String bNumber; 	//사업자번호   **일반회원일 경우 null
	private String bName;		//사업자명   **일반회원일 경우 null
	private Date joinDate; 		//가입일
	private String status;		//상태  N: 가입 회원, Y:탈퇴회원 
	
public User() {
}

//유저 업데이트용 생성자

public User(String emailId, String userPwd, String userPhone, String userAddress) {
	super();
	this.emailId = emailId;
	this.userPwd = userPwd;
	this.userPhone = userPhone;
	this.userAddress = userAddress;
}

//일반회원 생성자

public User(String userCode, String emailId, String userPwd, String userName, String userSsn, String userPhone,
		String userAddress) {
	super();
	this.userCode = userCode;
	this.emailId = emailId;
	this.userPwd = userPwd;
	this.userName = userName;
	this.userSsn = userSsn;
	this.userPhone = userPhone;
	this.userAddress = userAddress;
	
}

//사업자회원 생성자
public User(String userCode, String emailId, String userPwd, String userName, String userSsn, String userPhone,
		String userAddress, String bNumber, String bName) {
	super();
	this.userCode = userCode;
	this.emailId = emailId;
	this.userPwd = userPwd;
	this.userName = userName;
	this.userSsn = userSsn;
	this.userPhone = userPhone;
	this.userAddress = userAddress;
	this.bNumber = bNumber;
	this.bName = bName;
}



public User(String userCode, String emailId, String userName, String userSsn,
		String userPhone,Date joinDate, String status) {
	super();
	this.userCode = userCode;
	this.emailId = emailId;
	this.userName = userName;
	this.userSsn = userSsn;
	this.userPhone = userPhone;
	this.joinDate = joinDate;
	this.status = status;
}

public int getUserNo() {
	return userNo;
}

public void setUserNo(int userNo) {
	this.userNo = userNo;
}

public String getUserCode() {
	return userCode;
}

public void setUserCode(String userCode) {
	this.userCode = userCode;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

public String getUserPwd() {
	return userPwd;
}

public void setUserPwd(String userPwd) {
	this.userPwd = userPwd;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getUserSsn() {
	return userSsn;
}

public void setUserSsn(String userSsn) {
	this.userSsn = userSsn;
}

public String getUserPhone() {
	return userPhone;
}

public void setUserPhone(String userPhone) {
	this.userPhone = userPhone;
}

public String getUserAddress() {
	return userAddress;
}

public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
}

public int getPoint() {
	return point;
}

public void setPoint(int point) {
	this.point = point;
}

public String getbNumber() {
	return bNumber;
}

public void setbNumber(String bNumber) {
	this.bNumber = bNumber;
}

public String getbName() {
	return bName;
}

public void setbName(String bName) {
	this.bName = bName;
}

public Date getJoinDate() {
	return joinDate;
}

public void setJoinDate(Date joinDate) {
	this.joinDate = joinDate;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

@Override
public String toString() {
	return "User [userNo=" + userNo + ", userCode=" + userCode + ", emailId=" + emailId + ", userPwd=" + userPwd
			+ ", userName=" + userName + ", userSsn=" + userSsn + ", userPhone=" + userPhone + ", userAddress="
			+ userAddress + ", point=" + point + ", bNumber=" + bNumber + ", bName=" + bName + ", joinDate=" + joinDate
			+ ", status=" + status + "]";
}




}
