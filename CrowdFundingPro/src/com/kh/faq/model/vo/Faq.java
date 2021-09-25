package com.kh.faq.model.vo;

import java.util.Date;

public class Faq {

	private String fNo;
	private char targetUser;
	private String question;
	private String answer;
	private String creatorId;
	private Date createDate;
	private String updaterId;
	private Date updateDate;
	private char showYn;

	public Faq() {
		
	}

	public Faq(String fNo, char targetUser, String question, String answer, String creatorId, Date createDate,
			String updaterId, Date updateDate, char showYn) {
		super();
		this.fNo = fNo;
		this.targetUser = targetUser;
		this.question = question;
		this.answer = answer;
		this.creatorId = creatorId;
		this.createDate = createDate;
		this.updaterId = updaterId;
		this.updateDate = updateDate;
		this.showYn = showYn;
	}


	public Faq(String fNo, char targetUser, String question) {
		super();
		this.fNo = fNo;
		this.targetUser = targetUser;
		this.question = question;
	}

	public Faq(String fNo, char targetUser, String question, String answer) {
		super();
		this.fNo = fNo;
		this.targetUser = targetUser;
		this.question = question;
		this.answer = answer;
	}


	public Faq(String fNo, String question, String creatorId) {
		super();
		this.fNo = fNo;
		this.question = question;
		this.creatorId = creatorId;
	}

	public String getfNo() {
		return fNo;
	}

	public void setfNo(String fNo) {
		this.fNo = fNo;
	}

	public char getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(char targetUser) {
		this.targetUser = targetUser;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public char getShowYn() {
		return showYn;
	}

	public void setShowYn(char showYn) {
		this.showYn = showYn;
	}

	@Override
	public String toString() {

		return fNo + " / " + targetUser + " / " + question  + " / " + answer + " / " 
				+ creatorId + " / " + createDate + " / " + updaterId + " / " + updateDate + " / " + showYn;
	}
	
	
}
