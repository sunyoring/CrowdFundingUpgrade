package com.kh.project.model.vo;

import java.util.Date;

public class Project {
	private int projectCode;
	private int userNo;
	private String projectName;
	private int amountGoal;
	private int amountPresent;
	private Date ddln;
	private int deliveryCharge;
	private int supportNum;
	private String detailIntro;
	private String categoryNo;
	private String categoryName; // 카테고리 이름
	private int fileNo;
	private String titleImg;

	public Project() {

	}

	public Project(int projectCode, String projectName, int amountGoal, int amountPresent, Date ddln,
			String detailIntro, String titleImg) {
		super();
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.amountPresent = amountPresent;
		this.ddln = ddln;
		this.detailIntro = detailIntro;
		this.titleImg = titleImg;
	}

	public Project(int projectCode, String projectName, int amountGoal, Date ddln, int deliveryCharge,
			String detailIntro, int fileNo) {
		super();
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.ddln = ddln;
		this.deliveryCharge = deliveryCharge;
		this.detailIntro = detailIntro;
		this.fileNo = fileNo;
	}

	public Project(String projectName, int amountGoal, Date ddln, int deliveryCharge, String detailIntro) {
		super();
		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.ddln = ddln;
		this.deliveryCharge = deliveryCharge;
		this.detailIntro = detailIntro;

	}

	public Project(String projectName, int amountGoal, Date ddln, int deliveryCharge, String detailIntro, int fileNo) {/////
		super();

		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.ddln = ddln;
		this.deliveryCharge = deliveryCharge;
		this.detailIntro = detailIntro;

		this.fileNo = fileNo;

	}

	public Project(String projectName, int amountGoal, int amountPresent, Date ddln, int deliveryCharge,
			String detailIntro, String titleImg) {
		super();
		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.amountPresent = amountPresent;
		this.ddln = ddln;
		this.deliveryCharge = deliveryCharge;
		this.detailIntro = detailIntro;
		this.titleImg = titleImg;
	}

	public Project(String projectName, int amountGoal, Date ddln, int deliveryCharge, String detailIntro,
			String categoryNo) {
		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.ddln = ddln;
		this.deliveryCharge = deliveryCharge;
		this.detailIntro = detailIntro;
		this.categoryNo = categoryNo;

	}

	public Project(int projectCode, int userNo, String projectName, int amountGoal, int amountPresent, Date ddln,
			int deliveryCharge, int supportNum, String detailIntro, String categoryNo) {
		super();
		this.projectCode = projectCode;
		this.userNo = userNo;
		this.projectName = projectName;
		this.amountGoal = amountGoal;
		this.amountPresent = amountPresent;
		this.ddln = ddln;
		this.deliveryCharge = deliveryCharge;
		this.supportNum = supportNum;
		this.detailIntro = detailIntro;
		this.categoryNo = categoryNo;

	}

	public int userCode() {
		return projectCode;
	}

	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public int getProjectCode() {
		// TODO Auto-generated method stub
		return projectCode;
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

	public Date getDdln() {
		return ddln;
	}

	public void setDdln(Date ddln) {
		this.ddln = ddln;
	}

	public int getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(int deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public String getDetailIntro() {
		return detailIntro;
	}

	public void setDetailIntro(String detailIntro) {
		this.detailIntro = detailIntro;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	@Override
	public String toString() {
		return "Project [projectCode=" + projectCode + ", userNo=" + userNo + ", projectName=" + projectName
				+ ", amountGoal=" + amountGoal + ", amountPresent=" + amountPresent + ", ddln=" + ddln
				+ ", deliveryCharge=" + deliveryCharge + ", supportNum=" + supportNum + ", detailIntro=" + detailIntro
				+ ", categoryNo=" + categoryNo + ", categoryName=" + categoryName + ", fileNo=" + fileNo + ", titleImg="
				+ titleImg + "]";
	}

}
