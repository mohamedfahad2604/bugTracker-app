package com.bizz.customersupport.web.dto;

import java.util.Date;

public class IssueStatusDto {

	private long id;
	private long issueid;
	private String staff;
	private Date createDate;
	private String resolution;
	private String status;
	private String issueTittle;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIssueid() {
		return issueid;
	}
	public void setIssueid(long issueid) {
		this.issueid = issueid;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIssueTittle() {
		return issueTittle;
	}
	public void setIssueTittle(String issueTittle) {
		this.issueTittle = issueTittle;
	}
	@Override
	public String toString() {
		return "IssueStatusDto [id=" + id + ", issueid=" + issueid + ", staff=" + staff + ", createDate=" + createDate
				+ ", resolution=" + resolution + ", status=" + status + ", issueTittle=" + issueTittle + "]";
	}
	public IssueStatusDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IssueStatusDto(long id, long issueid, String staff, Date createDate, String resolution, String status,
			String issueTittle) {
		
		this.id = id;
		this.issueid = issueid;
		this.staff = staff;
		this.createDate = createDate;
		this.resolution = resolution;
		this.status = status;
		this.issueTittle = issueTittle;
	}
}
