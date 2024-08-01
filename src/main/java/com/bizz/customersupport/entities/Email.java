package com.bizz.customersupport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Title;
	private String subject;
	@Column(columnDefinition="text")
	private String contant;
	private String emails;
	private String pathLocation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContant() {
		return contant;
	}
	public void setContant(String contant) {
		this.contant = contant;
	}
	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public String getPathLocation() {
		return pathLocation;
	}
	public void setPathLocation(String pathLocation) {
		this.pathLocation = pathLocation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Title == null) ? 0 : Title.hashCode());
		result = prime * result + ((contant == null) ? 0 : contant.hashCode());
		result = prime * result + ((emails == null) ? 0 : emails.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pathLocation == null) ? 0 : pathLocation.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Email other = (Email) obj;
		if (Title == null) {
			if (other.Title != null)
				return false;
		} else if (!Title.equals(other.Title))
			return false;
		if (contant == null) {
			if (other.contant != null)
				return false;
		} else if (!contant.equals(other.contant))
			return false;
		if (emails == null) {
			if (other.emails != null)
				return false;
		} else if (!emails.equals(other.emails))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pathLocation == null) {
			if (other.pathLocation != null)
				return false;
		} else if (!pathLocation.equals(other.pathLocation))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Email [id=" + id + ", Title=" + Title + ", subject=" + subject + ", contant=" + contant + ", emails="
				+ emails + ", pathLocation=" + pathLocation + "]";
	}
	
	
	

}
