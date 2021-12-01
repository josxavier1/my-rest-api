package com.jx.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LargeFileReader {

	private static void consumeLargeFile(String filePath) {

		FileInputStream inputStream = null;
		Scanner sc = null;

		try {
			inputStream = new FileInputStream(filePath);
			sc = new Scanner(inputStream, "UTF-16");
			int lineNum = 0;
			long requiredCnt = 0;

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				lineNum++;
				long cnt = line.chars().filter(ch -> ch == '|').count();
				if (lineNum == 1) {
					requiredCnt = cnt;
					System.out.println("Required:" + cnt);
				}
				if (cnt != requiredCnt) {
					System.out.println("LINE:" + lineNum + "  Pipe Count:" + cnt + "  Line data:" + line);
				}
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}
		}
	}

//	private static void calculateNumberOfGroups(String filePath, String keyColumn, String separator) {
//
//		Map<String, String> poMap = new HashMap<String, String>();
//		FileInputStream inputStream = null;
//		Scanner sc = null;
//
//		try {
//			inputStream = new FileInputStream(filePath);
//			sc = new Scanner(inputStream, "UTF-16");
//			int lineNum = 0;
//			int beginSeparator = 0;
//			int endSeparator = 0;
//
//			while (sc.hasNextLine()) {
//				String line = sc.nextLine();
//				lineNum++;
//				if (lineNum == 1) {
//					beginSeparator = line.indexOf(separator + keyColumn + separator);
//				} else {
//					
//					List<String> tempList = Arrays.asList(line.split("|"));
//					String poNumber = tempList.get(poIndex);
//					if (!poMap.containsKey(poNumber)) {
//						poMap.put(poNumber, line);
//					}
//				}
//			}
//			System.out.println("Number of POs" + poMap.size());
//			Iterator<String> iterator = poMap.keySet().iterator();
//			while (iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
//			// note that Scanner suppresses exceptions
//			if (sc.ioException() != null) {
//				throw sc.ioException();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		finally {
//			if (inputStream != null) {
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//
//					e.printStackTrace();
//				}
//			}
//			if (sc != null) {
//				sc.close();
//			}
//		}
//	}

	public static void main(String[] args) {

		// consumeLargeFile("C:\\works\\EA\\bpm\\739-FDBE\\testfiles\\20200302\\Drop
		// Ship Report_Open PO_CA 20200302.csv");
//		calculateNumberOfGroups(
//				"C:\\works\\EA\\bpm\\739-FDBE\\testfiles\\20200302\\Drop Ship Report_Open PO_CA 20200302.csv",
//				"AP PO Number");
	}

}
