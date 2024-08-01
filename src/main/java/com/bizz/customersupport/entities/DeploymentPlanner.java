package com.bizz.customersupport.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DeploymentPlanner")
public class DeploymentPlanner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String auther;
	private String performer;
	private String serverName;
	private String application;
	private String deployment;
	private String fileType;
	private String enhancedFor;
	private String comment;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deploymentDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performedBy) {
		this.performer = performedBy;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getDeployment() {
		return deployment;
	}

	public void setDeployment(String deployment) {
		this.deployment = deployment;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getEnhancedFor() {
		return enhancedFor;
	}

	public void setEnhancedFor(String enhancedFor) {
		this.enhancedFor = enhancedFor;
	}

	public Date getDeploymentDate() {
		return deploymentDate;
	}

	public void setDeploymentDate(Date deploymentDate) {
		this.deploymentDate = deploymentDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "DeploymentPlanner [id=" + id + ", auther=" + auther + ", performer=" + performer + ", serverName="
				+ serverName + ", application=" + application + ", deployment=" + deployment + ", fileType=" + fileType
				+ ", enhancedFor=" + enhancedFor + ", comment=" + comment + ", deploymentDate=" + deploymentDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((application == null) ? 0 : application.hashCode());
		result = prime * result + ((auther == null) ? 0 : auther.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((deployment == null) ? 0 : deployment.hashCode());
		result = prime * result + ((deploymentDate == null) ? 0 : deploymentDate.hashCode());
		result = prime * result + ((enhancedFor == null) ? 0 : enhancedFor.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((performer == null) ? 0 : performer.hashCode());
		result = prime * result + ((serverName == null) ? 0 : serverName.hashCode());
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
		DeploymentPlanner other = (DeploymentPlanner) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (auther == null) {
			if (other.auther != null)
				return false;
		} else if (!auther.equals(other.auther))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (deployment == null) {
			if (other.deployment != null)
				return false;
		} else if (!deployment.equals(other.deployment))
			return false;
		if (deploymentDate == null) {
			if (other.deploymentDate != null)
				return false;
		} else if (!deploymentDate.equals(other.deploymentDate))
			return false;
		if (enhancedFor == null) {
			if (other.enhancedFor != null)
				return false;
		} else if (!enhancedFor.equals(other.enhancedFor))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (performer == null) {
			if (other.performer != null)
				return false;
		} else if (!performer.equals(other.performer))
			return false;
		if (serverName == null) {
			if (other.serverName != null)
				return false;
		} else if (!serverName.equals(other.serverName))
			return false;
		return true;
	}

	

}
