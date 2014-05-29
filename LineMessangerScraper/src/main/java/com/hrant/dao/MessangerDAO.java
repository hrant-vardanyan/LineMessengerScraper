package com.hrant.dao;

import javax.persistence.EntityManager;

import com.hrant.JpaUtil;
import com.hrant.model.Message;

public class MessangerDAO {

	
	public void addMessage(Message message){
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
	
}
