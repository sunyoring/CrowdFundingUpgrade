package com.kh.recruit.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recruitment {
	private int id; // 공고번호
	private String title; // 공고명
	private String code; // 직무구분
	private Date start; // 공고시작일
	private Date end; // 공고종료일
	private String time; // 공고종류
	private String content1; // 공고소개
	private String content2; // 주요업무
	private String content3; // 자격요건
	private String content4; // 우대사항
	private String content5; // 혜택 및 복지
	private String content6; // 기타사항

	public Recruitment() {
	}

	public Recruitment(int id, String title, String code, Date start, Date end, String time, String content1,
			String content2, String content3, String content4, String content5, String content6) {
		super();
		this.id = id;
		this.title = title;
		this.code = code;
		this.start = start;
		this.end = end;
		this.time = time;
		this.content1 = content1;
		this.content2 = content2;
		this.content3 = content3;
		this.content4 = content4;
		this.content5 = content5;
		this.content6 = content6;
	}

	public Recruitment(String title, String code, Date start, Date end, String time, String content1, String content2,
			String content3, String content4, String content5, String content6) {
		super();
		this.title = title;
		this.code = code;
		this.start = start;
		this.end = end;
		this.time = time;
		this.content1 = content1;
		this.content2 = content2;
		this.content3 = content3;
		this.content4 = content4;
		this.content5 = content5;
		this.content6 = content6;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	public String getStartDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(start);
	}

	public String getEndDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(end);
	}
	
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd");
		return sdf.format(start) + " ~ " + sdf.format(end);
	}
	
	public String getDateWithDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd(E)");
		return sdf.format(start) + " ~ " + sdf.format(end);		
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent1() {
		return content1 == null ? "" : content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2 == null ? "" : content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getContent3() {
		return content3 == null ? "" : content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public String getContent4() {
		return content4 == null ? "" : content4;
	}

	public void setContent4(String content4) {
		this.content4 = content4;
	}

	public String getContent5() {
		return content5 == null ? "" : content5;
	}

	public void setContent5(String content5) {
		this.content5 = content5;
	}

	public String getContent6() {
		return content6 == null ? "" : content6;
	}

	public void setContent6(String content6) {
		this.content6 = content6;
	}

	@Override
	public String toString() {
		return "Recruitment [title=" + title + ", code=" + code + ", start=" + start + ", end=" + end + ", time=" + time
				+ ", content1=" + content1 + ", content2=" + content2 + ", content3=" + content3 + ", content4="
				+ content4 + ", content5=" + content5 + ", content6=" + content6 + "]";
	}

}
