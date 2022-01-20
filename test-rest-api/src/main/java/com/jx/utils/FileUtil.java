package com.jx.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.XML;

// some comment
public class FileUtil {

	public String convertXMLtoJson(String xmlLocation) throws IOException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(xmlLocation);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        System.out.println(line);
		        JSONObject soapDatainJsonObject = XML.toJSONObject(line);
		        System.out.println(soapDatainJsonObject);
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
		return null;
	}
	
	public static void main (String args[]) {
		
		try {
			new FileUtil().convertXMLtoJson("C:\\works\\management\\s&p\\IndexItems_20210830_0027-split.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
