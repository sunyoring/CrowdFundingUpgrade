package com.kh.event.model.vo;

import java.util.Date;

import lombok.Data;

@Data

public class Event {

	private int eNo;		//이벤트번호	
	private String eName;
//	private String E_NAME;		//이벤트명
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




	@Override
	public String toString() {
		return "Event [eNo=" + eNo + ", eName=" + eName + ", eContent=" + eContent + ", regDate=" + regDate
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}

}
