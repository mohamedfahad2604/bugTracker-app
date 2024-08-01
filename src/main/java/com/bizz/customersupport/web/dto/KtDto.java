package com.bizz.customersupport.web.dto;

public class KtDto {
	
	private Long id;
	private Long issueid;
	private String createdBy;
	private String ktTopic;
	
	private String resolution;
	private String ktDate;
	
	
	private String ktTo;
	private String ktTimeTaken;
	private String status;
	private String ktTime;
	
	public String getKtTime() {
		return ktTime;
	}


	public void setKtTime(String ktTime) {
		this.ktTime = ktTime;
	}


	


public Long getIssueid() {
	return issueid;
}


public void setIssueid(Long issueid) {
	this.issueid = issueid;
}



	private String createDate;
	
	
	public KtDto() {

	}
	
	
	public KtDto( Long id,String createdBy, String ktTopic, String resolution, 
			 String ktTo, String ktTimeTaken, String status, 
			String createDate,String ktTime) {
		super();
		
	
		this.id = id;
		this.createdBy = createdBy;
		this.ktTopic = ktTopic;
		
		this.resolution = resolution;
		
		
		
		this.ktTo = ktTo;
		this.ktTimeTaken = ktTimeTaken;
		this.status = status;
	
		this.createDate = createDate;
	
		this.ktTime = ktTime;
	}
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getKtTopic() {
		return ktTopic;
	}
	public void setKtTopic(String ktTopic) {
		this.ktTopic = ktTopic;
	}
	
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getKtDate() {
		return ktDate;
	}
	public void setKtDate(String ktDate) {
		this.ktDate = ktDate;
	}
	
	
	public String getKtTo() {
		return ktTo;
	}
	public void setKtTo(String ktTo) {
		this.ktTo = ktTo;
	}
	public String getKtTimeTaken() {
		return ktTimeTaken;
	}
	public void setKtTimeTaken(String ktTimeTaken) {
		this.ktTimeTaken = ktTimeTaken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	


	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



	@Override
	public String toString() {
		return "KtDto [id=" + id + ", issueid=" + issueid + ", createdBy=" + createdBy + ", ktTopic=" + ktTopic
				+ ", resolution=" + resolution + ", ktDate=" + ktDate + ", ktTo=" + ktTo
				+ ", ktTimeTaken=" + ktTimeTaken + ", status=" + status + ", ktTime=" + ktTime + ", createDate=" + createDate + "]";
	}

	
}
