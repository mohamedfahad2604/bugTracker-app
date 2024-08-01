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
@Table(name = "kt_details")
public class Ktdetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    public long getIssueid() {
		return issueid;
	}

	public void setIssueid(long issueid) {
		this.issueid = issueid;
	}



	private long issueid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ktDate;

	private String createdBy;
	private String ktTopic;

	private String resolution;
	
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

	public Ktdetails() {
		
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getKtDate() {
		return ktDate;
	}

	public void setKtDate(Date ktDate) {
		this.ktDate = ktDate;
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

	
	


	public Ktdetails( long issueid,String createdBy, String ktTopic, String resolution, 
			 String ktTo, String ktTimeTaken, String status, 
			Date createDate,String ktTime) {
		super();
		
		this.issueid = issueid;
		this.createdBy = createdBy;
		this.ktTopic = ktTopic;
		
		this.resolution = resolution;
		
		
		
		this.ktTo = ktTo;
		this.ktTimeTaken = ktTimeTaken;
		this.status = status;
	
		this.createDate = createDate;
	
		this.ktTime = ktTime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (issueid ^ (issueid >>> 32));
		result = prime * result + ((ktDate == null) ? 0 : ktDate.hashCode());
		result = prime * result + ((ktTime == null) ? 0 : ktTime.hashCode());
		result = prime * result + ((ktTimeTaken == null) ? 0 : ktTimeTaken.hashCode());
		result = prime * result + ((ktTo == null) ? 0 : ktTo.hashCode());
		result = prime * result + ((ktTopic == null) ? 0 : ktTopic.hashCode());
		
		result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
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
		Ktdetails other = (Ktdetails) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (id != other.id)
			return false;
		if (issueid != other.issueid)
			return false;
		if (ktDate == null) {
			if (other.ktDate != null)
				return false;
		} else if (!ktDate.equals(other.ktDate))
			return false;
		if (ktTime == null) {
			if (other.ktTime != null)
				return false;
		} else if (!ktTime.equals(other.ktTime))
			return false;
		if (ktTimeTaken == null) {
			if (other.ktTimeTaken != null)
				return false;
		} else if (!ktTimeTaken.equals(other.ktTimeTaken))
			return false;
		if (ktTo == null) {
			if (other.ktTo != null)
				return false;
		} else if (!ktTo.equals(other.ktTo))
			return false;
		if (ktTopic == null) {
			if (other.ktTopic != null)
				return false;
		} else if (!ktTopic.equals(other.ktTopic))
			return false;
		
		
		
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "Ktdetails [id=" + id + ", issueid=" + issueid + ", createDate=" + createDate + ", ktDate=" + ktDate
				+ ", createdBy=" + createdBy + ", ktTopic=" + ktTopic + ", resolution=" + resolution + ", ktTo=" + ktTo
				+ ", ktTimeTaken=" + ktTimeTaken + ", status=" + status + ", ktTime=" + ktTime + "]";
	}

}