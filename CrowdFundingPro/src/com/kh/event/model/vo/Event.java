package com.kh.event.model.vo;

import java.util.Date;

public class Event {

	private int eNo;			//이벤트번호	
	private String eName;		//이벤트명
	private String eContent;	//이벤트내용이미지 
	private Date regDate;
	private Date startDate;
	private Date endDate;	
	private String status;
	
	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Event(int eNo, String eName, String eContent, Date regDate, Date startDate, Date endDate, String status) {
		super();
		this.eNo = eNo;
		this.eName = eName;
		this.eContent = eContent;
		this.regDate = regDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Event(int eNo, String eName, String eContent, Date regDate, Date startDate, Date endDate) {
		super();
		this.eNo = eNo;
		this.eName = eName;
		this.eContent = eContent;
		this.regDate = regDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	


	public Event(String eName,String eContent, Date startDate, Date endDate) {
		super();
		this.eName = eName;
		this.eContent = eContent;
		this.startDate = startDate;
		this.endDate = endDate;
	}




	public int geteNo() {
		return eNo;
	}

	public void seteNo(int eNo) {
		this.eNo = eNo;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteContent() {
		return eContent;
	}

	public void seteContent(String eContent) {
		this.eContent = eContent;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Event [eNo=" + eNo + ", eName=" + eName + ", eContent=" + eContent + ", regDate=" + regDate
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}

}
