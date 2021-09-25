package com.kh.user.model.vo;

public class UProject {

	private int userNo;			//유저번호
	private String emailId;		//유저이메일아이디
	private String projectCode;	//프로젝트코드
	private String projectName;	//프로젝트이름
	private int amountGoal;		//목표금액
	private int amountPresent;	//현재금액
	private String proDetail;  	//프로젝트 설명
	
	public UProject() {
		// TODO Auto-generated constructor stub
	}
	




	public UProject(int userNo, String emailId, String projectCode, String projectName, int amountGoal,
			int amountPresent, String proDetail) {
		super();
		this.userNo = userNo;
		this.emailId = emailId;
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.amountPresent = amountPresent;
		this.proDetail = proDetail;
	}





	public String getEmailId() {
		return emailId;
	}





	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}





	public String getProjectName() {
		return projectName;
	}





	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}





	public int getAmountGoal() {
		return amountGoal;
	}





	public void setAmountGoal(int amountGoal) {
		this.amountGoal = amountGoal;
	}





	public int getAmountPresent() {
		return amountPresent;
	}





	public void setAmountPresent(int amountPresent) {
		this.amountPresent = amountPresent;
	}





	public String getProDetail() {
		return proDetail;
	}





	public void setProDetail(String proDetail) {
		this.proDetail = proDetail;
	}





	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public String getProjectCode() {
		return projectCode;
	}


	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}





	@Override
	public String toString() {
		return "UProject [userNo=" + userNo + ", emailId=" + emailId + ", projectCode=" + projectCode + ", projectName="
				+ projectName + ", amountGoal=" + amountGoal + ", amountPresent=" + amountPresent + ", proDetail="
				+ proDetail + "]";
	}




	
}
