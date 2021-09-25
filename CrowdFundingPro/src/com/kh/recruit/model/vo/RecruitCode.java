package com.kh.recruit.model.vo;

public class RecruitCode {
	private String code; // 공고 구분
	private int count; // 공고 구분 그룹 갯수

	public RecruitCode() {
	}

	public RecruitCode(String code, int count) {
		super();
		this.code = code;
		this.count = count;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
