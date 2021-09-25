package com.kh.project.model.vo;

public class UProject {
	private int uProjectId;
	private int userNo;			
	private String emailId;		
	private int projectCode;
	private String projectName;	
	private int amountGoal;		
	private int amountPresent;	
	private String proDetail;  
	
	
	public UProject() {
		
	}

	public UProject(int uProjectId,int userNo, String emailId, int projectCode, String projectName, int amountGoal, int amountPresent,
			String proDetail) {
		super();
		this.uProjectId=uProjectId;
		this.userNo = userNo;
		this.emailId = emailId;
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.amountPresent = amountPresent;
		this.proDetail = proDetail;
	}
	

	public int getuProjectId() {
		return uProjectId;
	}

	public void setuProjectId(int uProjectId) {
		this.uProjectId = uProjectId;
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

	public int getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
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

	@Override
	public String toString() {
		return "UProject["+uProjectId+userNo +emailId +projectCode +projectName +amountGoal+amountPresent+proDetail+"]";
				
			
	}
	
	
	
	
}
