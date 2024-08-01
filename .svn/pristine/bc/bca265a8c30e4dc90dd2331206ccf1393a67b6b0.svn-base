package com.bizz.customersupport.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taskcreation")
public class AdminTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String taskName;
	private String description;
	private String tasktype;
	private String taskstatus;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + ((taskstatus == null) ? 0 : taskstatus.hashCode());
		result = prime * result + ((tasktype == null) ? 0 : tasktype.hashCode());
		return result;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminTask other = (AdminTask) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (taskstatus == null) {
			if (other.taskstatus != null)
				return false;
		} else if (!taskstatus.equals(other.taskstatus))
			return false;
		if (tasktype == null) {
			if (other.tasktype != null)
				return false;
		} else if (!tasktype.equals(other.tasktype))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	@Override
	public String toString() {
		return "AdminTask [id=" + id + ", taskName=" + taskName + ", description=" + description + ", tasktype=" + tasktype + ", taskstatus=" + taskstatus
				+ "]";
	}

}
