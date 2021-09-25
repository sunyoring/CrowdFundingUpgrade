package com.kh.project.model.vo;

public class IProject {

	
	private int inProNo;
	private int userNo;
	private int pCode;
	
	public IProject() {
		// TODO Auto-generated constructor stub
	}

	public int getInProNo() {
		return inProNo;
	}

	public void setInProNo(int inProNo) {
		this.inProNo = inProNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "IProject [inProNo=" + inProNo + ", userNo=" + userNo + ", pCode=" + pCode + "]";
	}

	public IProject(int inProNo, int userNo, int pCode) {
		super();
		this.inProNo = inProNo;
		this.userNo = userNo;
		this.pCode = pCode;
	}
	public IProject( int userNo, int pCode) {
		super();
		this.userNo = userNo;
		this.pCode = pCode;
	}

	public int getpCode() {
		return pCode;
	}

	public void setpCode(int pCode) {
		this.pCode = pCode;
	}
	
	
}
