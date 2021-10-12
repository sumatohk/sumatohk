package com.hkicl.core.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmuMessage implements Serializable {
	String messageId;
	String messageTitle;
	String messageContent;

	public void printMsg() {
		System.out.println("id=" + this.messageId + "\ntitle" + this.messageTitle + "\ncontent=" + this.messageContent);
	}
}
