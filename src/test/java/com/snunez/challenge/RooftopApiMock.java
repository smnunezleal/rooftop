package com.snunez.challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RooftopApiMock extends RooftopApi {

	private static final String[] EXPECTED = new String[] { "f319", "46ec", "c1c7", "3720", "c7df", "c4ea", "4e3e", "80fd" };
	private static final Map<String, String> ORDER = new HashMap<>();
	static {
		ORDER.put("f319", "46ec");
		ORDER.put("46ec", "c1c7");
		ORDER.put("c1c7", "3720");
		ORDER.put("3720", "c7df");
		ORDER.put("c7df", "c4ea");
		ORDER.put("c4ea", "4e3e");
		ORDER.put("4e3e", "80fd");
	}

	public RooftopApiMock(String token) {
		super(token);
	}

	@Override
	public String[] getBlocks() {
		return new String[] {"f319", "3720", "4e3e", "46ec", "c7df", "c1c7", "80fd", "c4ea"};
	}

	@Override
	public boolean validate(String[] ordered) {
		return Arrays.equals(ordered, EXPECTED);
	}

	@Override
	public boolean inOrder(String first, String second) {
		if (!ORDER.containsKey(first)) {
			return false;
		}
		return ORDER.get(first).equals(second);
	}

}
