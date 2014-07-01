package com.hrant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class HrantTest {

	public static void main(String[] args) {
//		String dateStr = "2014/06/11 18:03";
//
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//		Date dateObj = null;
//		try {
//			dateObj = simpleDateFormat.parse(dateStr);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		System.out.println(dateObj);
		
		try {
			byte[] readAllBytes = Files.readAllBytes(Paths.get("C:/LineTemp/08_cbjvz_Sachie.txt"));
			String  string = new String(readAllBytes, StandardCharsets.UTF_8.toString());
			System.out.println(string);
			String substringBefore = StringUtils.substringBefore(string, "(Fri)");
			System.out.println(substringBefore);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date dateObj = null;
			try {
				dateObj = simpleDateFormat.parse(substringBefore);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(dateObj);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
