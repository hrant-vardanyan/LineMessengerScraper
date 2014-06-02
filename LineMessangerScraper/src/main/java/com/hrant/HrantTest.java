package com.hrant;

import java.util.GregorianCalendar;

import com.hrant.dao.MessangerDAO;
import com.hrant.model.Message;

public class HrantTest {

	public static void main(String[] args) {
		CTestDriver cTestDriver = new  CTestDriver();
		
		
		GregorianCalendar date =  new GregorianCalendar();
		date.set(1990, 5, 20);
		
		MessangerDAO dao = new MessangerDAO();
		Message message = new Message();
		message.setCitem("ffxjn");
		message.setSpeaker("xvbnxcv");
		message.setDate(date);
		dao.addMessage(message);

	}

}
