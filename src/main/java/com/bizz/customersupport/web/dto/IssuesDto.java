package com.bizz.customersupport.web.dto;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
public class IssuesDto {
	private Long id;
	private String requestor;
	private String issueStatus;
	private long categoryId;
	private String description;
	private String assignedTo;
	private String resolvedBy;
	private String resolution;
	private String application;
	private String environment;
	private String client;
	private String requestDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private String requestTime;
	private String resolvedDate;
	private String resolvedTime;
	private String issueTittle;
	private String issuetype;
	private String supporttype;
	public String getSupporttype() {
		return supporttype;
	}
	public void setSupporttype(String supporttype) {
		this.supporttype = supporttype;
	}
	public String getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(String issuetype) {
		this.issuetype = issuetype;
	}
	public String getTimetaken() {
		return timetaken;
	}
	public void setTimetaken(String timetaken) {
		this.timetaken = timetaken;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	private String timetaken;
	private String severity;
	public IssuesDto() {
	}
	public IssuesDto(Long id, String requestor, String issueStatus, long categoryId, String description, String assignedTo, String resolvedBy,
			String resolution, String application, String environment, String client, String requestDate, String resolvedDate, String resolvedTime, String issueTittle,
			Date createDate, String issuetype, String timetaken, String severity, String supporttype) {
		super();
		this.id = id;
		this.requestor = requestor;
		this.issueStatus = issueStatus;
		this.categoryId = categoryId;
		this.description = description;
		this.assignedTo = assignedTo;
		this.resolvedBy = resolvedBy;
		this.resolution = resolution;
		this.application = application;
		this.environment = environment;
		this.client = client;
		this.requestDate = requestDate;
		this.createDate = createDate;
		this.requestTime = requestTime;
		this.resolvedDate = resolvedDate;
		this.resolvedTime = resolvedTime;
		this.issueTittle = issueTittle;
		this.issuetype = issuetype;
		this.timetaken = timetaken;
		this.severity = severity;
		this.supporttype = supporttype;
	}
	public IssuesDto(Long id, String requestor, String issueStatus, long categoryId, String description, String assignedTo, String resolvedBy,
			String resolution, String application, String environment, String client, String requestDate, String resolvedDate, String issueTittle,
			Date createDate, String issuetype, String timetaken, String severity, String supporttype) {
		this.id = id;
		this.requestor = requestor;
		this.issueStatus = issueStatus;
		this.categoryId = categoryId;
		this.description = description;
		this.assignedTo = assignedTo;
		this.resolvedBy = resolvedBy;
		this.resolution = resolution;
		this.application = application;
		this.environment = environment;
		this.client = client;
		this.requestDate = requestDate;
		this.resolvedDate = resolvedDate;
		this.issueTittle = issueTittle;
		this.createDate = createDate;
		this.issuetype = issuetype;
		this.timetaken = timetaken;
		this.severity = severity;
		this.supporttype = supporttype;
		/*
		 * SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss"); this.requestTime =
		 * timeDateFormat.format(this.requestDate); if (this.resolvedDate != null) { this.resolvedTime =
		 * timeDateFormat.format(this.resolvedDate); }
		 */
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getResolvedDate() {
		return resolvedDate;
	}
	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	public String getResolvedTime() {
		return resolvedTime;
	}
	public void setResolvedTime(String resolvedTime) {
		this.resolvedTime = resolvedTime;
	}
	public String getIssueTittle() {
		return issueTittle;
	}
	public void setIssueTittle(String issueTittle) {
		this.issueTittle = issueTittle;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "IssuesDto [id=" + id + ", requestor=" + requestor + ", issueStatus=" + issueStatus + ", categoryId=" + categoryId + ", description="
				+ description + ", assignedTo=" + assignedTo + ", resolvedBy=" + resolvedBy + ", resolution=" + resolution + ", application=" + application
				+ ", environment=" + environment + ", client=" + client + ", requestDate=" + requestDate + ", createDate=" + createDate + ", requestTime="
				+ requestTime + ", resolvedDate=" + resolvedDate + ", resolvedTime=" + resolvedTime + ", issueTittle=" + issueTittle + ", issuetype="
				+ issuetype + ", supporttype=" + supporttype + ", timetaken=" + timetaken + ", severity=" + severity + "]";
	}
}