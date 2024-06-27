package com.token.auth.mail.model;

import java.util.Map;

/**
 * This is model class for email functionality
 * 
 * @author Ashish Ghorpade
 * 
 */
public class Email {

	private String to;
	private String[] toAll;
	private String from;
	private String subject;
	private String text;
	private String template;
	private String ccMailId;
	private String attachment;
	private String msgBody;

	Map<String, Object> properties;

	public Email() {
		super();
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the ccMailId
	 */
	public String getCcMailId() {
		return ccMailId;
	}

	/**
	 * @param ccMailId the ccMailId to set
	 */
	public void setCcMailId(String ccMailId) {
		this.ccMailId = ccMailId;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the msgBody
	 */
	public String getMsgBody() {
		return msgBody;
	}

	/**
	 * @param msgBody the msgBody to set
	 */
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	/**
	 * @return the properties
	 */
	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	/**
	 * @return the toAll
	 */
	public String[] getToAll() {
		return toAll;
	}

	/**
	 * @param toAll the toAll to set
	 */
	public void setToAll(String[] toAll) {
		this.toAll = toAll;
	}

}
