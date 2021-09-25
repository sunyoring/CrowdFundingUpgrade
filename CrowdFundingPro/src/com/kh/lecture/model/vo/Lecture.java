package com.kh.lecture.model.vo;

import java.sql.Date;

public class Lecture {
	
	
	String lectureCode = null;
	String lectureTitle = null;
	int lectureNum = 0;
	String lectureAddress = null;
	String lectureTopic = null;
	Date lectureDate = null;
	int lectureTime = 0;
	String lectureImage = null;
	String lectureContent = null;
	String lecturer = null;
	
	
	
	
	public Lecture() {

	}


	public Lecture(String lectureCode, String lectureTitle, String lectureAddress, String lectureTopic,
			Date lectureDate) {
		super();
		this.lectureCode = lectureCode;
		this.lectureTitle = lectureTitle;
		this.lectureAddress = lectureAddress;
		this.lectureTopic = lectureTopic;
		this.lectureDate = lectureDate;
	}



	public Lecture(String lectureCode, String lectureTitle, int lectureNum, String lectureAddress, String lectureTopic,
			Date lectureDate, int lectureTime, String lectureImage, String lectureContent) {
		super();
		this.lectureCode = lectureCode;
		this.lectureTitle = lectureTitle;
		this.lectureNum = lectureNum;
		this.lectureAddress = lectureAddress;
		this.lectureTopic = lectureTopic;
		this.lectureDate = lectureDate;
		this.lectureTime = lectureTime;
		this.lectureImage = lectureImage;
		this.lectureContent = lectureContent;
	}
	


	public Lecture(String lectureTitle, int lectureNum, String lectureAddress, String lectureTopic, Date lectureDate,
			int lectureTime, String lectureImage, String lectureContent) {
		super();
		this.lectureTitle = lectureTitle;
		this.lectureNum = lectureNum;
		this.lectureAddress = lectureAddress;
		this.lectureTopic = lectureTopic;
		this.lectureDate = lectureDate;
		this.lectureTime = lectureTime;
		this.lectureImage = lectureImage;
		this.lectureContent = lectureContent;
	}
	
	public Lecture(String lectureTitle, int lectureNum, String lectureAddress, String lectureTopic, Date lectureDate,
			int lectureTime, String lectureImage, String lectureContent, String lecturer) {
		super();
		this.lectureTitle = lectureTitle;
		this.lectureNum = lectureNum;
		this.lectureAddress = lectureAddress;
		this.lectureTopic = lectureTopic;
		this.lectureDate = lectureDate;
		this.lectureTime = lectureTime;
		this.lectureImage = lectureImage;
		this.lectureContent = lectureContent;
		this.lecturer = lecturer;
	}
	
	public Lecture(String lectureCode,String lectureTitle, int lectureNum, String lectureAddress, String lectureTopic, Date lectureDate,
			int lectureTime, String lectureImage, String lectureContent, String lecturer) {
		super();
		this.lectureCode = lectureCode;
		this.lectureTitle = lectureTitle;
		this.lectureNum = lectureNum;
		this.lectureAddress = lectureAddress;
		this.lectureTopic = lectureTopic;
		this.lectureDate = lectureDate;
		this.lectureTime = lectureTime;
		this.lectureImage = lectureImage;
		this.lectureContent = lectureContent;
		this.lecturer = lecturer;
	}
	
	

	public Lecture(String lectureImage,String lectureTopic, String lectureTitle,Date lectureDate,String lecturer,int lectureTime, int lectureNum) {
		super();
		this.lectureImage = lectureImage;
		this.lectureTopic = lectureTopic;
		this.lectureTitle = lectureTitle;
		this.lectureDate = lectureDate;
		this.lecturer = lecturer;
		this.lectureTime = lectureTime;
		this.lectureNum = lectureNum;
	}
	
	
	public Lecture(String lectureImage,String lectureTopic, String lectureTitle,Date lectureDate,String lecturer,int lectureTime, int lectureNum,String lectureCode) {
		super();
		this.lectureImage = lectureImage;
		this.lectureTopic = lectureTopic;
		this.lectureTitle = lectureTitle;
		this.lectureDate = lectureDate;
		this.lecturer = lecturer;
		this.lectureTime = lectureTime;
		this.lectureNum = lectureNum;
		this.lectureCode = lectureCode;
	}
	
	public Lecture(String lectureImage, String lectureCode,String lectureTitle, int lectureNum, String lecturer, String lectureTopic) {
		super();
		this.lectureImage = lectureImage;
		this.lectureCode = lectureCode;
		this.lectureTitle = lectureTitle;
		this.lectureNum = lectureNum;
		this.lecturer = lecturer;
		this.lectureTopic = lectureTopic;
		
	}
	

	

	public String getLectureCode() {
		return lectureCode;
	}
	public void setLectureCode(String lectureCode) {
		this.lectureCode = lectureCode;
	}
	public String getLectureTitle() {
		return lectureTitle;
	}
	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}
	public int getLectureNum() {
		return lectureNum;
	}
	public void setLectureNum(int lectureNum) {
		this.lectureNum = lectureNum;
	}
	public String getLectureAddress() {
		return lectureAddress;
	}
	public void setLectureAddress(String lectureAddress) {
		this.lectureAddress = lectureAddress;
	}
	public String getLectureTopic() {
		return lectureTopic;
	}
	public void setLectureTopic(String lectureTopic) {
		this.lectureTopic = lectureTopic;
	}
	public Date getLectureDate() {
		return lectureDate;
	}
	public void setLectureDate(Date lectureDate) {
		this.lectureDate = lectureDate;
	}
	public int getLectureTime() {
		return lectureTime;
	}
	public void setLectureTime(int lectureTime) {
		this.lectureTime = lectureTime;
	}
	public String getLectureImage() {
		return lectureImage;
	}
	public void setLectureImage(String lectureImage) {
		this.lectureImage = lectureImage;
	}
	public String getLectureContent() {
		return lectureContent;
	}
	public void setLectureContent(String lectureContent) {
		this.lectureContent = lectureContent;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	
	
	
	
	
}
