package com.jx.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class RestTester {
	
	private static String getOAuthToken(HttpURLConnection conn, String tokenURL, String clientId, String clientSecret) {

		String token = null;
		String content = "grant_type=client_credentials";
		String auth = clientId + ":" + clientSecret;
		BufferedReader reader = null;
		Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");

		try {
			// creating request
			String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
			URL url = new URL(tokenURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Basic " + authentication);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "application/json");
			PrintStream os = new PrintStream(conn.getOutputStream());
			os.print(content);
			os.close();
			// reading response
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			StringWriter out = new StringWriter(conn.getContentLength() > 0 ? conn.getContentLength() : 2048);
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			String response = out.toString();
			Matcher matcher = pat.matcher(response);
			if (matcher.matches() && matcher.groupCount() > 0) {
				token = matcher.group(1);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(token);
		return token;
	}

	public static void testRestAPI(String urlStr) {

		try {

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");

			StringBuffer postBody = new StringBuffer();
			postBody.append("{\"Operation\": \"divide\",\"Term1\": 6,\"Term2\": 12}");
			conn.setDoOutput(true);
			conn.getOutputStream().write(postBody.toString().getBytes());

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	private static void testAnotherRestApi(String urlStr, String tokenURL, String clientId, String clientSecret) {

		HttpURLConnection conn = null;
		try {

			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			String token = null;

			if (tokenURL != null) {
				// getting token
				token = getOAuthToken(conn, tokenURL, clientId, clientSecret);
				// setting bearer token
				conn.setRequestProperty("Authorization", "Bearer " + token);
			}
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("error : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("output.....");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		finally {
			conn.disconnect();
		}
	}



	private static void testAnotherRestApi(String urlStr) {
		testAnotherRestApi(urlStr, null, null, null);
	}

	public static void main(String[] args) {

		// testRestAPI("http://corpwesbdv001:9142/Calculator");
		// accessing secure api using bearer token
		// testAnotherRestApi("http://10.100.12.216:8280/dnh-time/v1/now",
		// "c2631a54-2447-3e04-85f6-6328974fcbe1");

		
		// try {
		// String tokenURL = "http://10.100.12.216:8280/token";
		// URL url = new URL(tokenURL);
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// System.out.println(getOAuthToken(conn, tokenURL,
		// "ESkxuPOSTTy0l6sNnuLTf_fhOp8a", "1UzNsucmzU3YxFhCf9nf4988W8Ea"));
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		
		//testAnotherRestApi("http://jcheraparambil-eval-test.apigee.net/joe-test-2");
		
		for (int i =0; i < 1000; i++) {
//		testAnotherRestApi("http://10.100.12.216:8280/wb/v1/countries/AUS", "http://10.100.12.216:8280/token",
//				"ESkxuPOSTTy0l6sNnuLTf_fhOp8a", "1UzNsucmzU3YxFhCf9nf4988W8Ea");
		testAnotherRestApi("https://dev01.apimanager.dandh.net/api/oauth/authorize", "https://dev01.apimanager.dandh.net/api/oauth/token",
				"a0295838-5b06-41a4-a8e5-6ea31c26dfff", "c778c70d-503f-4e3f-9eeb-0963eabf6a92");

		}
	}
}
