package com.kh.lecture.model.vo;

public class LectureInfo {
	
	String lectureCode = null;
	int count = 0;
	public String getLectureCode() {
		return lectureCode;
	}
	public void setLectureCode(String lectureCode) {
		this.lectureCode = lectureCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public LectureInfo(String lectureCode, int count) {
		super();
		this.lectureCode = lectureCode;
		this.count = count;
	}
	
	public LectureInfo() {
		super();
	}
	@Override
	public String toString() {
		return "LectureInfo [lectureCode=" + lectureCode + ", count=" + count + "]";
	}
}
