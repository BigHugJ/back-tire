package com.chatting;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String message;
	private String messageSender;
	private String messageReceiver;
	private Date messageDate;

	public Message() {
	}

	public Message(String message, String messageSender, String messageReceiver, Date messageDate) {
		super();
		this.message = message;
		this.messageSender = messageSender;
		this.messageReceiver = messageReceiver;
		this.messageDate = messageDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(String messageSender) {
		this.messageSender = messageSender;
	}

	public String getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(String messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((messageDate == null) ? 0 : messageDate.hashCode());
		result = prime * result + ((messageReceiver == null) ? 0 : messageReceiver.hashCode());
		result = prime * result + ((messageSender == null) ? 0 : messageSender.hashCode());
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
		Message other = (Message) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageDate == null) {
			if (other.messageDate != null)
				return false;
		} else if (!messageDate.equals(other.messageDate))
			return false;
		if (messageReceiver == null) {
			if (other.messageReceiver != null)
				return false;
		} else if (!messageReceiver.equals(other.messageReceiver))
			return false;
		if (messageSender == null) {
			if (other.messageSender != null)
				return false;
		} else if (!messageSender.equals(other.messageSender))
			return false;
		return true;
	}
}
