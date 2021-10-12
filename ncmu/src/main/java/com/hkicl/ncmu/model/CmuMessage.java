package com.hkicl.ncmu.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CmuMessage implements Serializable {
	private String messageId;
	private String messageTitle;
	private String messageContent;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public void printMsg() {
		System.out.println("id=" + this.getMessageId() + "\ntitle" + this.getMessageTitle() + "\ncontent="
				+ this.getMessageContent());
	}
}
