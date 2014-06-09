package com.hrant.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="details")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar date;
	private String speaker;
	@Column(length=10_000)
	private String citem;
	private String receiver;
	private int chatid;


	

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getCitem() {
		return citem;
	}

	public void setCitem(String citem) {
		this.citem = citem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChatid() {
		return chatid;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setChatid(int chatid) {
		this.chatid = chatid;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", date=" + date + ", speaker=" + speaker + ", citem=" + citem + ", reciver="
				+ receiver + ", chatid=" + chatid + "]";
	}

	
	
	
	
	
}
