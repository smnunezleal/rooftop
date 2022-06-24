package com.snunez.challenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChallengeTest
{
	@Test
	public void orderByApiTest()
	{
		RooftopApi api = new RooftopApiMock(null);
		String[] blocks = api.getBlocks();
		assertFalse(api.validate(blocks));
		Solution.orderByApi(blocks, api);
		assertTrue(api.validate(blocks));
	}
}
