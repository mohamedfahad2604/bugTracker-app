package com.bizz.customersupport.web.dto;

import java.util.Objects;

import com.bizz.customersupport.entities.User;

public class DailyTaskDto {

	private Long id;

	private User userName;

	public String getTaskname() {
		return taskname;
	}

	public DailyTaskDto(Long id, User userName, String taskname, String date, String task, String hours, String bugNo) {
		super();
		this.id = id;
		this.userName = userName;
		this.taskname = taskname;
		this.date = date;
		this.task = task;
		this.hours = hours;
		this.bugNo = bugNo;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	private String taskname;
	private String date;

	private String task;

	private String hours;
	private String bugNo;
	public String getBugNo() {
		return bugNo;
	}

	public void setBugNo(String bugNo) {
		this.bugNo = bugNo;
	}

	public Long getId() {
		return id;
	}

	
	public DailyTaskDto() {
		super();

	}

	public DailyTaskDto(Long id, User userName, String taskname, String date, String task, String hours) {
		super();
		this.id = id;
		this.userName = userName;
		this.taskname = taskname;
		this.date = date;
		this.task = task;
		this.hours = hours;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
		DailyTaskDto other = (DailyTaskDto) obj;
		return Objects.equals(bugNo, other.bugNo) && Objects.equals(date, other.date)
				&& Objects.equals(hours, other.hours) && Objects.equals(id, other.id)
				&& Objects.equals(task, other.task) && Objects.equals(taskname, other.taskname)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "DailyTaskDto [id=" + id + ", userName=" + userName + ", taskname=" + taskname + ", date=" + date
				+ ", task=" + task + ", hours=" + hours + ", bugNo=" + bugNo + "]";
	}

	
	

}
