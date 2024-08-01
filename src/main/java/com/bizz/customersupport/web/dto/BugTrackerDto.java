package com.bizz.customersupport.web.dto;

import java.util.Date;
import java.util.Objects;

import com.bizz.customersupport.entities.User;

public class BugTrackerDto {

	private Long id;

	private User author;

	private Date createdDate;

	private String applicationName;

	private String serverName;

	private String bugName;

	private String description;

	private String bugNo;

	private String bugStatus;

	private String priority;

	private User assignee;

	private String estimatedFixDate;
	
	private String ticketType;
	
	private long estimatedDays;
	
	private long estimatedHours;
	
	private String emailStatus;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public String getEstimatedFixDate() {
		return estimatedFixDate;
	}

	public void setEstimatedFixDate(String estimatedFixDate) {
		this.estimatedFixDate = estimatedFixDate;
	}

	public String getBugNo() {
		return bugNo;
	}

	public void setBugNo(String bugNo) {
		this.bugNo = bugNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBugStatus() {
		return bugStatus;
	}

	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}

	public String getBugName() {
		return bugName;
	}

	public void setBugName(String bugName) {
		this.bugName = bugName;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public long getEstimatedDays() {
		return estimatedDays;
	}

	public void setEstimatedDays(long estimatedDays) {
		this.estimatedDays = estimatedDays;
	}

	public long getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(long estimatedHours) {
		this.estimatedHours = estimatedHours;
	}



	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	@Override
	public String toString() {
		return "BugTrackerDto [id=" + id + ", author=" + author + ", createdDate=" + createdDate + ", applicationName="
				+ applicationName + ", serverName=" + serverName + ", bugName=" + bugName + ", description="
				+ description + ", bugNo=" + bugNo + ", bugStatus=" + bugStatus + ", priority=" + priority
				+ ", assignee=" + assignee + ", estimatedFixDate=" + estimatedFixDate + ", ticketType=" + ticketType
				+ ", estimatedDays=" + estimatedDays + ", estimatedHours=" + estimatedHours + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(applicationName, assignee, author, bugName, bugNo, bugStatus, createdDate, description,
				estimatedDays, estimatedFixDate, estimatedHours, id, priority, serverName, ticketType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BugTrackerDto other = (BugTrackerDto) obj;
		return Objects.equals(applicationName, other.applicationName) && Objects.equals(assignee, other.assignee)
				&& Objects.equals(author, other.author) && Objects.equals(bugName, other.bugName)
				&& Objects.equals(bugNo, other.bugNo) && Objects.equals(bugStatus, other.bugStatus)
				&& Objects.equals(createdDate, other.createdDate) && Objects.equals(description, other.description)
				&& estimatedDays == other.estimatedDays && Objects.equals(estimatedFixDate, other.estimatedFixDate)
				&& estimatedHours == other.estimatedHours && Objects.equals(id, other.id)
				&& Objects.equals(priority, other.priority) && Objects.equals(serverName, other.serverName)
				&& Objects.equals(ticketType, other.ticketType);
	}

	public BugTrackerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BugTrackerDto(Long id, User author, Date createdDate, String applicationName, String serverName,
			String bugName, String description, String bugNo, String bugStatus, String priority, User assignee,
			String estimatedFixDate, String ticketType, long estimatedDays, long estimatedHours, String emailStatus) {
		super();
		this.id = id;
		this.author = author;
		this.createdDate = createdDate;
		this.applicationName = applicationName;
		this.serverName = serverName;
		this.bugName = bugName;
		this.description = description;
		this.bugNo = bugNo;
		this.bugStatus = bugStatus;
		this.priority = priority;
		this.assignee = assignee;
		this.estimatedFixDate = estimatedFixDate;
		this.ticketType = ticketType;
		this.estimatedDays = estimatedDays;
		this.estimatedHours = estimatedHours;
		this.emailStatus = emailStatus;
	}

	

	
	
	

	
	
}
