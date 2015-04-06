package io.pivotal.cloudfoundry.log4j;

import static org.junit.Assert.*;

import org.junit.Test;

public class CFLookupTest {
	
	@Test
	public void testLookupStringWithUnknownKeyReturnsUnknownKeyMessage() {
		final CFLookup lookup = new CFLookup();
		final String key = "anything";
		final String result = lookup.lookup(key);
		assertNotNull(result);
		assertEquals(String.format("[Unknown key: %s]", key), result);
	}

	@Test
	public void testLookupStringWithKnownKeyReturnsCorrectValue() {
		CFLookup lookup = new CFLookup();
		String result = lookup.lookup("appName");
		assertNotNull(result);
		assertEquals("logproducer", result);
	}

}
