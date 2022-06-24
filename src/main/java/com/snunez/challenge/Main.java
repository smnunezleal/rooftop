package com.snunez.challenge;

public class Main {
	private static final String TOKEN_PARAM = "596156de-369e-4b4e-b74a-2e1ed6741eaf";

	public static void main(String[] args) {
		RooftopApi api = new RooftopApi(TOKEN_PARAM);
		String[] ordered = Solution.check(api.getBlocks(), TOKEN_PARAM);
		System.out.println(api.validate(ordered));
	}

}
