package com.snunez.challenge;

public class Solution
{
	private Solution() {
	}

	public static String[] check(String[] blocks, String token) {
		RooftopApi api = new RooftopApi(token);
		orderByApi(blocks, api);
		return blocks;
	}

	public static void orderByApi(String[] blocks, RooftopApi api) {
		int length = blocks.length;
		for (int i = 0; i < length; i++) {
			String first = blocks[i];
			for (int j = length - 1; j > i + 1; j--) {
				String second = blocks[j];
				if (api.inOrder(first, second)) {
					String temp = blocks[i + 1];
					blocks[i + 1] = second;
					blocks[j] = temp;
					break;
				}
			}
		}
	}


}
