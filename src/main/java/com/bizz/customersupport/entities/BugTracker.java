package com.bizz.customersupport.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BugTracker")
public class BugTracker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author")
	private User author;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "assignee")
	private User assignee;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "createdDate")
	private Date createdDate;

	private String applicationName;

	private String serverName;

	private String bugName;

	private String description;

	private String bugNo;

	private String bugStatus;

	private String priority;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "estimatedFixDate")
	private Date estimatedFixDate;
	
	private String ticketType;
	private long estimatedDays;
	private long estimatedHours;
	
	private String emailStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
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

	public String getBugName() {
		return bugName;
	}

	public void setBugName(String bugName) {
		this.bugName = bugName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBugNo() {
		return bugNo;
	}

	public void setBugNo(String bugNo) {
		this.bugNo = bugNo;
	}

	public String getBugStatus() {
		return bugStatus;
	}

	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getEstimatedFixDate() {
		return estimatedFixDate;
	}

	public void setEstimatedFixDate(Date estimatedFixDate) {
		this.estimatedFixDate = estimatedFixDate;
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



	@Override
	public String toString() {
		return "BugTracker [id=" + id + ", author=" + author + ", assignee=" + assignee + ", createdDate=" + createdDate
				+ ", applicationName=" + applicationName + ", serverName=" + serverName + ", bugName=" + bugName
				+ ", description=" + description + ", bugNo=" + bugNo + ", bugStatus=" + bugStatus + ", priority="
				+ priority + ", estimatedFixDate=" + estimatedFixDate + ", ticketType=" + ticketType
				+ ", estimatedDays=" + estimatedDays + ", estimatedHours=" + estimatedHours + "]";
	}

	public BugTracker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BugTracker(long id, User author, User assignee, Date createdDate, String applicationName, String serverName,
			String bugName, String description, String bugNo, String bugStatus, String priority, Date estimatedFixDate,
			String ticketType, long estimatedDays, long estimatedHours) {
		super();
		this.id = id;
		this.author = author;
		this.assignee = assignee;
		this.createdDate = createdDate;
		this.applicationName = applicationName;
		this.serverName = serverName;
		this.bugName = bugName;
		this.description = description;
		this.bugNo = bugNo;
		this.bugStatus = bugStatus;
		this.priority = priority;
		this.estimatedFixDate = estimatedFixDate;
		this.ticketType = ticketType;
		this.estimatedDays = estimatedDays;
		this.estimatedHours = estimatedHours;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

}
