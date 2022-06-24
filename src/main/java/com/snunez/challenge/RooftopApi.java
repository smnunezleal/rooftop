package com.snunez.challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class RooftopApi {
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String GET_METHOD = "GET";
	public static final String POST_METHOD = "POST";
	public static final String TOKEN_PARAM_NAME = "token";
	public static final String CHECK_ENDPOINT = "check";
	public static final String BLOCKS_ENDPOINT = "blocks";
	public static final String URL_CHALLENGE = "https://rooftop-career-switch.herokuapp.com/";
	private final String token;

	public RooftopApi(String token) {
		this.token = token;
	}

	@SuppressWarnings("unchecked")
	public String[] getBlocks() {
		String response = performRequest(token, BLOCKS_ENDPOINT, GET_METHOD, null);
		List<String> list = (List<String>) responseToMap(response).get("data");
		return list.toArray(new String[0]);
	}

	public boolean validate(String[] ordered) {
		String body = "{\"encoded\": [\"" + String.join("", ordered) + "\"]}";
		String response = performRequest(token, CHECK_ENDPOINT, POST_METHOD, body);
		return (Boolean) responseToMap(response).get("message");
	}

	public boolean inOrder(String first, String second) {
		String body = "{\"blocks\": [\"" + first + "\", \"" + second + "\"]}";
		String response = performRequest(token, CHECK_ENDPOINT, POST_METHOD, body);
		return (Boolean) responseToMap(response).get("message");
	}

	private static Map responseToMap(String response) {
		Gson gson = new Gson();
		return gson.fromJson(response, Map.class);
	}

	private static String performRequest(String token, String endpoint, String method, String body) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(TOKEN_PARAM_NAME, token);
		return ConnectionHelper.performRequest(URL_CHALLENGE, parameters, endpoint, method, CONTENT_TYPE_JSON, body);
	}
}
