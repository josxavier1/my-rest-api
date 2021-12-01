package com.jx.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegexTester {

	public static void main(String[] args) {
		System.out.println(verifyPassword("Geeks@portal20"));
		System.out.println(verifyPassword("Rochele123&"));
		System.out.println(verifyPassword("Rochele&"));
		System.out.println(verifyPassword("123"));
		
	}

	
	public static void replace() {
		String test = "01222930";
		Pattern pattern = Pattern.compile(
				"012229^30-0`31-0`2-0`8-1,8-2,8-3,18-1^31-0`0938,3435,3438,3448^2-0`0^8-1`X`1.25`<`50^8-2`X`2.25`<`25^8-3`X`2.50`<`10^18-1`0`66248\r\n"
						+ "");
		Matcher matcher = pattern.matcher(test);
		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
		}
		Pattern replace = Pattern.compile("\\s+");
		Matcher matcher2 = replace.matcher(test);
		System.out.println(matcher2.replaceAll("\t"));
	}
	
	public static boolean verifyPassword(String password) {
		
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
}
