package com.hrant.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.hrant.ConnectToDB;
import com.hrant.JpaUtil;
import com.hrant.model.Message;

public class MessangerDAO {
	
//	public static void main(String[] args) {
//		GregorianCalendar calendar= new GregorianCalendar();
//		calendar.set(2014, 4, 29, 16,55,0);
//		Message message = new Message();
//		message.setReceiver("Max");
//		message.setSpeaker("Michi");
//		message.setChatid(5);
//		
//		Message message2 = new Message();
//		message2.setReceiver("Max");
//		message2.setSpeaker("Midchi");
//		message2.setChatid(2);
//		
//		
////		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
////		String format = simpleDateFormat.format(calendar.getTime());
////		System.out.println(format);
//		
//		ConnectToDB.ssh("C:\\Users\\Hrant\\Desktop\\line_scrapper.pem");
//		MessangerDAO dao = new MessangerDAO();
//		dao.addMessage(message);
//		dao.addMessage(message2);
////		System.out.println(dao.getChatId(message));
//		System.out.println(dao.getMaxChatId());
//		
//	}

	public  int getMaxChatId(){
		EntityManager entityManager = null;
		
		try{
			
			entityManager = JpaUtil.getEMF().createEntityManager();
			Integer max = entityManager.createQuery("SELECT max(chatid) FROM Message ", Integer.class ).getSingleResult();
			if(max == null){
				return 1;
			}
			return max;
			
		} finally {
			if(entityManager != null){
				entityManager.close();
			}
		}
	}
	
	public void addMessage(Message message){
		
		int chatId = getChatId(message);
		if(chatId != -1){
			message.setChatid(chatId);
		} else {
			int maxChatId = getMaxChatId();
			message.setChatid(maxChatId + 1);
		}
		EntityManager entityManager = null;
		
		try{
			
			entityManager = JpaUtil.getEMF().createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(message);
			entityManager.getTransaction().commit();
		} finally {
			if(entityManager != null){
				entityManager.close();
			}
		}
		
	}
	
	public boolean isExsit(Message message){
		EntityManager entityManager = null;
		try{
			
			entityManager = JpaUtil.getEMF().createEntityManager();
			entityManager.getTransaction().begin();
			
			TypedQuery<Message> typedQuery = entityManager.createQuery("select m from Message m where m.speaker = ? and m.citem = ? and m.date = ?", Message.class);
			typedQuery.setParameter(1, message.getSpeaker());
			typedQuery.setParameter(2, message.getCitem());
			typedQuery.setParameter(3, message.getDate());
			List<Message> resultList = typedQuery.getResultList();
			if(!resultList.isEmpty()){
				return true;
			} else {
				return false;
			}
			
			
		} finally {
			if(entityManager != null){
				entityManager.close();
			}
		}
	}
	
	public int getChatId(Message message){
		EntityManager entityManager = null;
		try{
			
			entityManager = JpaUtil.getEMF().createEntityManager();
			entityManager.getTransaction().begin();
			
			TypedQuery<Message> typedQuery = entityManager.createQuery("select m from Message m where m.speaker = ? and m.receiver = ?", Message.class);
			typedQuery.setParameter(1, message.getSpeaker());
			typedQuery.setParameter(2, message.getReceiver());
			List<Message> resultList = typedQuery.getResultList();
		
			if(!resultList.isEmpty()){
//				String chatid = typedQuery.ge;
				
				return resultList.get(0).getChatid();
			} else {
				return -1;
			}
			
			
		} finally {
			if(entityManager != null){
				entityManager.close();
			}
		}
	}
	
	
}
