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
	private int chatid;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar date;
	private String speaker;
	@Column(length=10_000)
	private String citem;
	


	public int getChatid() {
		return chatid;
	}

	public void setChatid(int chatid) {
		this.chatid = chatid;
	}

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

	@Override
	public String toString() {
		return "Message [speaker=" + speaker + ", citem=" + citem + ", date=" + date + "]";
	}
	
	
	
	
}
