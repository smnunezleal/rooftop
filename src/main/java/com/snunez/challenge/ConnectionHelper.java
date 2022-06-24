package com.snunez.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ConnectionHelper {

	private ConnectionHelper() {}

	public static String performRequest(String urlString, Map<String, String> parameters, String endpoint,
			String method, String contentType, String body) {
		String query;
		try {
			query = getParamsString(parameters);
			URL url = new URL(urlString + endpoint + "?" + query);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(method);
			con.setDoOutput(true);
			if (body != null) {
				con.setRequestProperty("Content-Type", contentType);
				addBody(con, body);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
			StringBuilder responseBuilder = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				responseBuilder.append(responseLine.trim());
			}
			return responseBuilder.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}

	private static void addBody(HttpURLConnection con, String jsonInputString) throws IOException {
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}
	}
}
