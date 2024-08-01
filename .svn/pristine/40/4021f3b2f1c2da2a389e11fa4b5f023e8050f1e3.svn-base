package com.bizz.customersupport.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "issue_audit_history")
public class IssueStatusHistory {

	
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
//		@ManyToOne(fetch=FetchType.LAZY)
//		@JoinColumn(name="issue_id")
//		@JsonBackReference(value="issue-issue_audit_history")
//		private Issue issue;
		
		@Column(name="issue_id")
		private long issueid;
		
		@Column(name="updatedBy")
		private String staff;
		@Column(name="updatedTime")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date createDate;
		
		private String resolution;
		private String status;
		@Column(name="name")
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
//		public Issue getIssue() {
//			return issue;
//		}
//		public void setIssue(Issue issue) {
//			this.issue = issue;
//		}
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
		
		protected IssueStatusHistory()
		{
			
		}
		
		public IssueStatusHistory( long issueid, String staff, Date createDate, String resolution,
				String status, String issueTittle) {
			super();
		
			this.issueid = issueid;
			this.staff = staff;
			this.createDate = createDate;
			this.resolution = resolution;
			this.status = status;
			this.issueTittle = issueTittle;
		}
		@Override
		public String toString() {
			return "IssueStatusHistory [ issueid=" +issueid + ", staff=" + staff + ", createDate="
					+ createDate + ", resolution=" + resolution + ", status=" + status + ", issueTittle=" + issueTittle
					+ "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((issueTittle == null) ? 0 : issueTittle.hashCode());
			result = prime * result + (int) (issueid ^ (issueid >>> 32));
			result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
			result = prime * result + ((staff == null) ? 0 : staff.hashCode());
			result = prime * result + ((status == null) ? 0 : status.hashCode());
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
			IssueStatusHistory other = (IssueStatusHistory) obj;
			if (createDate == null) {
				if (other.createDate != null)
					return false;
			} else if (!createDate.equals(other.createDate))
				return false;
			if (id != other.id)
				return false;
			if (issueTittle == null) {
				if (other.issueTittle != null)
					return false;
			} else if (!issueTittle.equals(other.issueTittle))
				return false;
			if (issueid != other.issueid)
				return false;
			if (resolution == null) {
				if (other.resolution != null)
					return false;
			} else if (!resolution.equals(other.resolution))
				return false;
			if (staff == null) {
				if (other.staff != null)
					return false;
			} else if (!staff.equals(other.staff))
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			return true;
		}
}
