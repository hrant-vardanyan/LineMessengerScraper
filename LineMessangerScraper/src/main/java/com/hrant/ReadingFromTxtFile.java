package com.hrant;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vahe.TranscriptsEntity;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.hrant.dao.MessangerDAO;
import com.hrant.model.Message;
import com.hrant.utils.ConstantsAndMethods;

public class ReadingFromTxtFile {

	private static final Pattern DATE_PATTERN = Pattern.compile("(.+)\\(");
	private static final Pattern MESSAGE_PATTERN = Pattern.compile("(\\d+:\\d+)\\s([^\\s]+)\\s(.+)", Pattern.DOTALL);
	private static final Pattern NAME_PATTERN = Pattern.compile("\\[LINE\\](.+).txt");
	private static final Pattern Date_PATTERN = Pattern.compile("(\\d+/\\d+/\\d+)");
	private List<String> allPathsInTempFolder;
	private List<String> allNames;
	private TimeZone timeZone;
	private static final Logger LOGGER = Logger.getLogger(ReadingFromTxtFile.class);

	public ReadingFromTxtFile(File pathToDirectory, String timeZoneStr) {
		this.timeZone = TimeZone.getTimeZone(timeZoneStr);
		this.allPathsInTempFolder = getAllPathsInTempFolder(pathToDirectory);
		this.allNames = getAllNames();

	}

	public void writeForAllFiles() {
		for (String path : this.allPathsInTempFolder) {
			try {
				String skippingFiles = StringUtils.substringAfterLast(path, "[LINE]");
				if (ConstantsAndMethods.flag) {
					break;
				}
				if (skippingFiles.startsWith("IA") || skippingFiles.startsWith("NR")) {
					LOGGER.info("skip writting into DB cause people name starts with NR or IA");
				} else {
					writeDataForOneFile(path);

				}

			} catch (Exception e) {
				LOGGER.error("error with writing ", e);
			}
		}
	}

	public void writeDataForOneFile(String filePath) throws IOException {

		List<String> allLines = getDataFromTxtFile(filePath);
		List<List<String>> listOfList = getSubLists(allLines);
		List<Message> allMessagesFromOneFile = new ArrayList<>();
		for (List<String> list : listOfList) {
			if (ConstantsAndMethods.flag) {
				break;
			}
			List<Message> messageList = getMessageList(list);

			setAllRecivers(messageList);
			allMessagesFromOneFile.addAll(messageList);

		}
		MessangerDAO dao = new MessangerDAO();
		int count = 0;
		for (Message message : allMessagesFromOneFile) {
			LOGGER.info("Count = " + ++count + "   " + message.getCitem());
			if (count == 7) {
				LOGGER.info("Test");
			}
			// System.out.println(message);
			if (ConstantsAndMethods.flag) {
				break;
			}
			if (!dao.isExsit(message) && !ConstantsAndMethods.RECEIVER.equals(message.getReceiver())
					&& StringUtils.isNotEmpty(message.getCitem()) && StringUtils.isNotEmpty(message.getReceiver())) {
				dao.addMessage(message);
				TranscriptsEntity te = new TranscriptsEntity();
				te.setChatid(message.getChatid());
				te.setCorrections(0);
				te.setDelaycount(0);
				Timestamp timestamp = new Timestamp(0);
				te.setFchatdate(timestamp);
				te.setLchatdate(timestamp);
				te.setLinescount(0);
				te.setLongdelaycount(0);
				te.setMediumdelaycount(0);
				te.setQueryrun("0");
				te.setSessiondays(0);
				te.setSessionscount(0);
				te.setSlines(0);
				te.setStudent("0");
				te.setTlines(0);
				te.setTotaldelay(0);
				te.setTransactionprocessingdate(timestamp);
				te.setTresponsetime(0);
				te.setTresponses(0);
				te.setTeacher("0");
				try {
					dao.addIfNotExsistTranscriptsEntity(te);
				} catch (Exception e) {
					LOGGER.error("error with tr ", e);
				}
			}
		}
	}

	public void setAllRecivers(List<Message> messagesList) {
		for (Message message : messagesList) {
			String receiver = getReciver(message.getSpeaker(), messagesList);
			message.setReceiver(receiver);
		}

	}

	public String getReciver(String speaker, List<Message> messagesList) {
		for (Message message : messagesList) {
			if (!message.getSpeaker().equals(speaker)) {
				return message.getSpeaker();
			}
		}
		return ConstantsAndMethods.RECEIVER;
	}

	private List<List<String>> getSubLists(List<String> allLines) {
		List<List<String>> listOfList = new ArrayList<>();
		int start = 0;
		for (int i = 1; i < allLines.size(); i++) {
			// Pattern pattern = Pattern.compile("^\\d+/\\d+/\\d+.+$",
			// Pattern.DOTALL);
			// allLines.get(i).matches("^\\d+/\\d+/\\d+.+$")
			if (allLines.get(i).matches("^\\d+/\\d+/\\d+.+$")) {
				List<String> subList = allLines.subList(start, i);
				listOfList.add(subList);
				start = i;
			}
		}
		if (start == 0) {
			listOfList.add(allLines);
			return listOfList;
		}

		return listOfList;
	}

	private List<Message> getMessageList(List<String> list) {

		List<Message> messageList = new ArrayList<>();
		String dateStr = "";
		for (int i = 1; i < list.size(); i++) {
			Message message = new Message();
			String date = list.get(i - 1);
			Matcher matcher = DATE_PATTERN.matcher(date);
			if (matcher.find()) {
				String dateStrEnc = matcher.group(1);
				Matcher matcherDatePa = Date_PATTERN.matcher(dateStrEnc);
				if (matcherDatePa.find()) {

					dateStr = matcherDatePa.group(1).trim();
				}
			}

			Matcher timePattern = MESSAGE_PATTERN.matcher(list.get(i));
			if (timePattern.find()) {
				String time = timePattern.group(1);
				Calendar calDate = strToCalendar(dateStr + " " + time);
				calDate.setTimeZone(this.timeZone);
				message.setDate(calDate);
				String nameFirstPart = timePattern.group(2);
				String fullName = getNameByFirstPart(nameFirstPart);
				message.setSpeaker(fullName);
				String substringAfterCitem = StringUtils.substringAfter(timePattern.group().trim(), fullName);
				Matcher matcherDateCitem = Date_PATTERN.matcher(substringAfterCitem);
				if(matcherDateCitem.find()){
					String dt = matcherDateCitem.group(1);
					substringAfterCitem = StringUtils.substringBefore(substringAfterCitem, dt);
				}
			
				message.setCitem(substringAfterCitem);

			}
			messageList.add(message);
		}
		return messageList;
	}

	private Calendar strToCalendar(String dateStr) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date dateObj = null;
		try {
			dateObj = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			LOGGER.error("error with parsing date " + dateStr, e);
		}
		// GregorianCalendar gregorianCalendar = dt.toGregorianCalendar();
		// return gregorianCalendar;
		return DateToCalendar(dateObj);

	}

	private static Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			if (date != null) {

				cal.setTime(date);
			}
		} catch (Exception e) {
			LOGGER.error("error with date ", e);
		}
		return cal;
	}

	private List<String> getDataFromTxtFile(String path) throws IOException {

		byte[] readAllBytes = Files.readAllBytes(Paths.get(path));
		String allContent = new String(readAllBytes, StandardCharsets.UTF_8.toString());

		try {
			allContent = allContent.replaceAll("[LINE].+", "").replaceAll("(Saved time.+)", "");
		} catch (Exception e) {
			LOGGER.error("error with re ", e);
		}

		String[] split = allContent.split("(?=\\d{2}:\\d{2})");
		List<String> allLines = Arrays.asList(split);
		// List<String> allLines = Files.readAllLines(Paths.get(path),
		// StandardCharsets.UTF_8);
		return allLines;
	}

	private static List<String> getAllPathsInTempFolder(File pathToDirectory) {
		File[] listFiles = pathToDirectory.listFiles();
		List<String> allPaths = new ArrayList<>();
		for (File file : listFiles) {
			allPaths.add(file.toString());
		}
		return allPaths;
	}

	private List<String> getAllNames() {
		List<String> allNames = new ArrayList<>();

		for (String path : this.allPathsInTempFolder) {
			Matcher matcher = NAME_PATTERN.matcher(path);
			if (matcher.find()) {
				allNames.add(matcher.group(1));
			}
		}
		return allNames;

	}

	private String getNameByFirstPart(String firstPartOfName) {

		for (String name : this.allNames) {
			if (name.startsWith(firstPartOfName)) {
				return name;
			}
		}
		return firstPartOfName;
	}

}
