package com.hrant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hrant.dao.MessangerDAO;
import com.hrant.model.Message;

public class ReadingFromTxtFile {

	private static final Pattern DATE_PATTERN = Pattern.compile("(.+)\\(");
	private static final Pattern MESSAGE_PATTERN = Pattern.compile("(\\d+:\\d+)\\s([^\\s]+)\\s(.+)", Pattern.DOTALL);
	private static final Pattern NAME_PATTERN = Pattern.compile("\\[LINE\\](.+).txt");
	private List<String> allPathsInTempFolder;
	private List<String> allNames;
	private TimeZone timeZone;
	private static final Logger LOGGER = Logger.getLogger(ReadingFromTxtFile.class);
	private int chatid = 3;

	public ReadingFromTxtFile(File pathToDirectory, String timeZoneStr) {
		this.timeZone = TimeZone.getTimeZone(timeZoneStr);
		this.allPathsInTempFolder = getAllPathsInTempFolder(pathToDirectory);
		this.allNames = getAllNames();

	}

	public void writeForAllFiles() {
		for (String path : this.allPathsInTempFolder) {
			// writeDataForOneFile("C:\\LineTemp\\[LINE]OKpanda カスタマーサポート.txt");
			try {
				String skippingFiles = StringUtils.substringAfterLast(path, "[LINE]");
				if(skippingFiles.startsWith("IA") || skippingFiles.startsWith("NR")){
					LOGGER.info("skip writting into DB cause people name starts with NR or IA");
				}else{
					writeDataForOneFile(path);
					this.chatid++;
				}
				
			} catch (IOException e) {
				LOGGER.error("error with writing ", e);
			}
		}
	}

	public void writeDataForOneFile(String filePath) throws IOException {

		List<String> allLines = getDataFromTxtFile(filePath);
		List<List<String>> listOfList = getSubLists(allLines);
		List<Message> allMessagesFromOneFile = new ArrayList<>();
		for (List<String> list : listOfList) {
			allMessagesFromOneFile.addAll(getMessageList(list));
		}
		MessangerDAO dao = new MessangerDAO();
		for (Message message : allMessagesFromOneFile) {
//			System.out.println(message);
			dao.addMessage(message);
		}
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
		String date = list.get(0);
		Matcher matcher = DATE_PATTERN.matcher(date);
		String dateStr = "";
		if (matcher.find()) {
			dateStr = matcher.group(1);
		}
		List<Message> messageList = new ArrayList<>();
		for (int i = 1; i < list.size(); i++) {
			Message message = new Message();
			Matcher timePattern = MESSAGE_PATTERN.matcher(list.get(i));
			if (timePattern.find()) {
				String time = timePattern.group(1);
				Calendar calDate = strToCalendar(dateStr + " " + time);
				calDate.setTimeZone(this.timeZone);
				message.setDate(calDate);
				String nameFirstPart = timePattern.group(2);
				String fullName = getNameByFirstPart(nameFirstPart);
				message.setSpeaker(fullName);
				message.setCitem(StringUtils.substringAfter(timePattern.group().trim(), fullName));
				message.setChatid(this.chatid);
				
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
			LOGGER.error("error with parsing date ",e );
		}
		return DateToCalendar(dateObj);

	}

	private static Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	private List<String> getDataFromTxtFile(String path) throws IOException {

		byte[] readAllBytes = Files.readAllBytes(Paths.get(path));
		String allContent = new String(readAllBytes);
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
