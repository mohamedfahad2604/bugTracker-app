package com.bizz.customersupport.entities;

import java.util.List;

public class Notification {

	private Long id;
	private String sender;
	private String recipient;
	private String clientCode;

	private List<String> recipients;
	private List<String> copyRecipients;
	private List<String> blindCarbonCopy;
	
	private String subject;
	private String message;

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public List<String> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	public List<String> getCopyRecipients() {
		return copyRecipients;
	}
	public void setCopyRecipients(List<String> copyRecipients) {
		this.copyRecipients = copyRecipients;
	}
	public List<String> getBlindCarbonCopy() {
		return blindCarbonCopy;
	}
	public void setBlindCarbonCopy(List<String> blindCarbonCopy) {
		this.blindCarbonCopy = blindCarbonCopy;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
	
}
