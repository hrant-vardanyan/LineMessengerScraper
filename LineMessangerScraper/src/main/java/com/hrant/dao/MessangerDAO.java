package com.hrant.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.vahe.TranscriptsEntity;

import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import com.hrant.ConnectToDB;
import com.hrant.JpaUtil;
import com.hrant.ReadingFromTxtFile;
import com.hrant.model.Message;

public class MessangerDAO {
	private static final Logger LOGGER = Logger.getLogger(MessangerDAO.class);

	public int getMaxChatId() {
		EntityManager entityManager = null;
		try {
			entityManager = JpaUtil.getEMF().createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<Integer> createQuery = entityManager.createQuery("SELECT max(chatid) FROM Message ",
					Integer.class);
			Integer max = createQuery.getSingleResult();
			entityManager.getTransaction().commit();
			if (max == null) {
				return 1;
			}
			return max;

		} catch (Exception e) {
			LOGGER.error("error", e);
			return 1;
		} finally {
			if (entityManager != null) {
				LOGGER.info("closing");
				entityManager.close();
			}
		}
	}

	public void addIfNotExsistTranscriptsEntity(TranscriptsEntity transcriptsEntity) {

		EntityManager entityManager = null;
		try {

			//
			// DateTimeFormatter formatter =
			// DateTimeFormat.forPattern("yyyy/MM/dd HH:mm");
			// DateTime dt = formatter.parseDateTime("﻿2014/06/16 16:00");
			// System.out.println(dt);

			entityManager = JpaUtil.getEMF().createEntityManager();
			entityManager.getTransaction().begin();
			TypedQuery<TranscriptsEntity> query = entityManager.createQuery(
					"SELECT e from TranscriptsEntity e where e.chatid = ?", TranscriptsEntity.class);
			query.setParameter(1, transcriptsEntity.getChatid());
			List<TranscriptsEntity> resultList = query.getResultList();
			if (resultList.isEmpty()) {
				entityManager.persist(transcriptsEntity);
			}
			entityManager.getTransaction().commit();

		} finally {
			if (entityManager != null) {
				LOGGER.info("closing");
				entityManager.close();
			}
		}

	}

	public void addMessage(Message message) {

		int chatId = getChatId(message);
		if (chatId != -1) {
			message.setChatid(chatId);
		} else {
			int maxChatId = getMaxChatId();
			message.setChatid(maxChatId + 1);
		}
		EntityManager entityManager = null;

		try {

			entityManager = JpaUtil.getEMF().createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(message);
			entityManager.getTransaction().commit();
		} finally {
			if (entityManager != null) {
				LOGGER.info("closing");
				entityManager.close();
			}
		}

	}

	public boolean isExsit(Message message) {
		EntityManager entityManager = null;
		try {

			entityManager = JpaUtil.getEMF().createEntityManager();
			entityManager.getTransaction().begin();

			TypedQuery<Message> typedQuery = entityManager.createQuery(
					"select m from Message m where m.speaker = ? and m.citem = ? and m.date = ?", Message.class);
			typedQuery.setParameter(1, message.getSpeaker());
			typedQuery.setParameter(2, message.getCitem());
			typedQuery.setParameter(3, message.getDate());
			List<Message> resultList = typedQuery.getResultList();
			entityManager.getTransaction().commit();
			if (!resultList.isEmpty()) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (entityManager != null) {
				LOGGER.info("closing");
				entityManager.close();
			}
		}
	}

	public int getChatId(Message message) {
		EntityManager entityManager = null;
		try {

			entityManager = JpaUtil.getEMF().createEntityManager();
			entityManager.getTransaction().begin();

			TypedQuery<Message> typedQuery = entityManager
					.createQuery(
							"select m from Message m where (m.speaker = :speak and m.receiver = :res) or (m.speaker = :res and m.receiver = :speak)",
							Message.class);
			typedQuery.setParameter("speak", message.getSpeaker());
			typedQuery.setParameter("res", message.getReceiver());
			List<Message> resultList = typedQuery.getResultList();
			entityManager.getTransaction().commit();
			if (!resultList.isEmpty()) {
				// String chatid = typedQuery.ge;
				return resultList.get(0).getChatid();
			} else {
				return -1;
			}

		} finally {
			if (entityManager != null) {
				LOGGER.info("closing");
				entityManager.close();
			}
		}
	}

	/*
	 * public static void main(String[] args) { MessangerDAO dao = new
	 * MessangerDAO(); ConnectToDB.ssh("D:/line_scrapper.pem");
	 * TranscriptsEntity te = new TranscriptsEntity(); te.setChatid(6);
	 * te.setCorrections(0); te.setDelaycount(0); Timestamp timestamp = new
	 * Timestamp(0); te.setFchatdate(timestamp); te.setLchatdate(timestamp);
	 * te.setLinescount(0); te.setLongdelaycount(0); te.setMediumdelaycount(0);
	 * te.setQueryrun("0"); te.setSessiondays(0); te.setSessionscount(0);
	 * te.setSlines(0); te.setStudent("0"); te.setTlines(0);
	 * te.setTotaldelay(0); te.setTransactionprocessingdate(timestamp);
	 * te.setTresponsetime(0); te.setTresponses(0); te.setTeacher("0"); //
	 * dao.addIfNotExsistTranscriptsEntity(te); for (int i = 1; i <= 101; i++) {
	 * System.out.println(i);
	 * 
	 * dao.addIfNotExsistTranscriptsEntity(te); } }
	 */

}
