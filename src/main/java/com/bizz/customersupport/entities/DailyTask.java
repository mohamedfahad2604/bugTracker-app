package com.bizz.customersupport.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class DailyTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User userName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "taskDate")
	private Date date;

	private String task;
	@Column(name = "taskHours")
	private String hours;
	
	public DailyTask() {
		super();
		this.id = id;
		this.userName = userName;
		this.date = date;
		this.task = task;
		this.hours = hours;
		this.taskname = taskname;
		this.bugNo = bugNo;
	}

	private String taskname;

	private String bugNo;
	public String getBugNo() {
		return bugNo;
	}

	public void setBugNo(String bugNo) {
		this.bugNo = bugNo;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserName() {
		return userName;
	}

	public void setUserName(User userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "DailyTask [id=" + id + ", userName=" + userName + ", date=" + date + ", task=" + task + ", hours="
				+ hours + ", taskname=" + taskname + ", bugNo=" + bugNo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bugNo, date, hours, id, task, taskname, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyTask other = (DailyTask) obj;
		return Objects.equals(bugNo, other.bugNo) && Objects.equals(date, other.date)
				&& Objects.equals(hours, other.hours) && Objects.equals(id, other.id)
				&& Objects.equals(task, other.task) && Objects.equals(taskname, other.taskname)
				&& Objects.equals(userName, other.userName);
	}

}
