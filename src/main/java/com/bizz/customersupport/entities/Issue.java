package com.bizz.customersupport.entities;
import java.util.Date;
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
@Table(name = "issue")
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String requestor;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date requestDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date resolvedDate;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	private String description;
	private String assignedTo;
	private String resolution;
	private String application;
	private String environment;
	private String status;
	private String client;
	private String resolvedBy;
	private String issueTittle;
	private String issuetype;
	private String timetaken;
	private String severity;
	private String supporttype;
	public Issue(long id, String requestor, Date requestDate, Date createDate, Date resolvedDate, Category category, String description, String assignedTo,
			String resolution, String application, String environment, String status, String client, String resolvedBy, String issueTittle, String issuetype,
			String timetaken, String severity, String supporttype) {
		super();
		this.id = id;
		this.requestor = requestor;
		this.requestDate = requestDate;
		this.createDate = createDate;
		this.resolvedDate = resolvedDate;
		this.category = category;
		this.description = description;
		this.assignedTo = assignedTo;
		this.resolution = resolution;
		this.application = application;
		this.environment = environment;
		this.status = status;
		this.client = client;
		this.resolvedBy = resolvedBy;
		this.issueTittle = issueTittle;
		this.issuetype = issuetype;
		this.timetaken = timetaken;
		this.severity = severity;
		this.supporttype = supporttype;
	}
	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue", cascade = CascadeType.ALL)
	 *
	 * @JsonManagedReference(value = "issue-issue_audit_history") private List<IssueStatusHistory> issueStatusHistories;
	 *
	 * public List<IssueStatusHistory> getIssueStatusHistories() { return issueStatusHistories; }
	 *
	 * public void setIssueStatusHistories(List<IssueStatusHistory> issueStatusHistories) { this.issueStatusHistories =
	 * issueStatusHistories; }
	 */
	public String getSupporttype() {
		return supporttype;
	}
	public void setSupporttype(String supporttype) {
		this.supporttype = supporttype;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getResolvedDate() {
		return resolvedDate;
	}
	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	public String getIssueTittle() {
		return issueTittle;
	}
	public void setIssueTittle(String issueTittle) {
		this.issueTittle = issueTittle;
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
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((application == null) ? 0 : application.hashCode());
		result = prime * result + ((assignedTo == null) ? 0 : assignedTo.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((environment == null) ? 0 : environment.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((issueTittle == null) ? 0 : issueTittle.hashCode());
		result = prime * result + ((issuetype == null) ? 0 : issuetype.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + ((requestor == null) ? 0 : requestor.hashCode());
		result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
		result = prime * result + ((resolvedBy == null) ? 0 : resolvedBy.hashCode());
		result = prime * result + ((resolvedDate == null) ? 0 : resolvedDate.hashCode());
		result = prime * result + ((severity == null) ? 0 : severity.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((supporttype == null) ? 0 : supporttype.hashCode());
		result = prime * result + ((timetaken == null) ? 0 : timetaken.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (assignedTo == null) {
			if (other.assignedTo != null)
				return false;
		} else if (!assignedTo.equals(other.assignedTo))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (environment == null) {
			if (other.environment != null)
				return false;
		} else if (!environment.equals(other.environment))
			return false;
		if (id != other.id)
			return false;
		if (issueTittle == null) {
			if (other.issueTittle != null)
				return false;
		} else if (!issueTittle.equals(other.issueTittle))
			return false;
		if (issuetype == null) {
			if (other.issuetype != null)
				return false;
		} else if (!issuetype.equals(other.issuetype))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (requestor == null) {
			if (other.requestor != null)
				return false;
		} else if (!requestor.equals(other.requestor))
			return false;
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		if (resolvedBy == null) {
			if (other.resolvedBy != null)
				return false;
		} else if (!resolvedBy.equals(other.resolvedBy))
			return false;
		if (resolvedDate == null) {
			if (other.resolvedDate != null)
				return false;
		} else if (!resolvedDate.equals(other.resolvedDate))
			return false;
		if (severity == null) {
			if (other.severity != null)
				return false;
		} else if (!severity.equals(other.severity))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (supporttype == null) {
			if (other.supporttype != null)
				return false;
		} else if (!supporttype.equals(other.supporttype))
			return false;
		if (timetaken == null) {
			if (other.timetaken != null)
				return false;
		} else if (!timetaken.equals(other.timetaken))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Issue [id=" + id + ", requestor=" + requestor + ", requestDate=" + requestDate + ", createDate=" + createDate + ", resolvedDate=" + resolvedDate
				+ ", category=" + category + ", description=" + description + ", assignedTo=" + assignedTo + ", resolution=" + resolution + ", application="
				+ application + ", environment=" + environment + ", status=" + status + ", client=" + client + ", resolvedBy=" + resolvedBy + ", issueTittle="
				+ issueTittle + ", issuetype=" + issuetype + ", timetaken=" + timetaken + ", severity=" + severity + ", supporttype=" + supporttype + "]";
	}
}