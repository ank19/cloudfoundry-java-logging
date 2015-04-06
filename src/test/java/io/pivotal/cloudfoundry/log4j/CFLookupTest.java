package io.pivotal.cloudfoundry.log4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CFLookupTest {
	
	@BeforeClass
	public static void beforeClass(){
		System.setProperty("VCAP_APPLICATION", "{\"limits\":{\"mem\":1024,\"disk\":1024,\"fds\":16384},\"application_version\":\"4a2db450-9eef-4e9e-abc5-210bc0cb69f9\",\"application_name\":\"logproducer\",\"application_uris\":[],\"version\":\"4a2db450-9eef-4e9e-abc5-210bc0cb69f9\",\"name\":\"logproducer\",\"space_name\":\"development\",\"space_id\":\"fbcecccf-f718-479d-af10-1bf9ca9a67fe\",\"uris\":[],\"users\":null,\"application_id\":\"884ae01d-efe8-4c16-9550-d331b60aa11b\",\"instance_id\":\"78aa960556b74bf38b5efc91e121d1ad\",\"instance_index\":0,\"host\":\"0.0.0.0\",\"port\":61008,\"started_at\":\"2015-01-13 22:15:58 +0000\",\"started_at_timestamp\":1421187358,\"start\":\"2015-01-13 22:15:58 +0000\",\"state_timestamp\":1421187358}");
	}
	
	private CFLookup lookup;
	
	@Before
	public void setup(){	
		lookup = new CFLookup();
	}
	
	@Test
	public void testLookupStringWithUnknownKeyReturnsUnknownKeyMessage() {
		final String key = "anything";
		final String result = lookup.lookup(key);
		assertNotNull(result);
		assertEquals(String.format("[Unknown key: %s]", key), result);
	}

	@Test
	public void testLookupStringWithKnownKeyReturnsCorrectValue() {
		String result = lookup.lookup("appName");
		assertNotNull(result);
		assertEquals("logproducer", result);
	}

}
