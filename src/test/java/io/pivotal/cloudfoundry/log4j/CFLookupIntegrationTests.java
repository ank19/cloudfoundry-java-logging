package io.pivotal.cloudfoundry.log4j;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Big thanks to andrew-flower.com for his blog and example code.
 * http://andrew-flower.com/blog/Create_Custom_Log4j_Plugins
 * 
 * @author andrew.flower
 */
public class CFLookupIntegrationTests {

	private static final String APP_NAME = "logproducer";
	private static final String SPACE_NAME = "development";
	private static final String INSTANCE_INDEX = "0";

	@BeforeClass
	public static void beforeClass() {
		System.setProperty(
				"VCAP_APPLICATION",
				"{\"limits\":{\"mem\":1024,\"disk\":1024,\"fds\":16384},\"application_version\":\"4a2db450-9eef-4e9e-abc5-210bc0cb69f9\",\"application_name\":\"logproducer\",\"application_uris\":[],\"version\":\"4a2db450-9eef-4e9e-abc5-210bc0cb69f9\",\"name\":\"logproducer\",\"space_name\":\"development\",\"space_id\":\"fbcecccf-f718-479d-af10-1bf9ca9a67fe\",\"uris\":[],\"users\":null,\"application_id\":\"884ae01d-efe8-4c16-9550-d331b60aa11b\",\"instance_id\":\"78aa960556b74bf38b5efc91e121d1ad\",\"instance_index\":0,\"host\":\"0.0.0.0\",\"port\":61008,\"started_at\":\"2015-01-13 22:15:58 +0000\",\"started_at_timestamp\":1421187358,\"start\":\"2015-01-13 22:15:58 +0000\",\"state_timestamp\":1421187358}");
		
		
		
	}
	
	@Test
	public void logReplacesKeyWithCFAppName() throws Exception {
		testPlaceholderIsReplaced("${cf:application_name}", APP_NAME, LogManager.getRootLogger());
	}
	
	@Test
	public void logReplacesKeyWithCFSpaceName() throws Exception {
		testPlaceholderIsReplaced("${cf:space_name}", SPACE_NAME, LogManager.getRootLogger());
	}
	
	private void testPlaceholderIsReplaced(String placeholder, String placeholderValue, Logger logger) {
		// Get the RootLogger which, if you don't have log4j2-test.xml defined,
		// will only log ERRORs
		
		// Create a String Appender to capture log output
		StringAppender appender = StringAppender.createStringAppender("[" + placeholder + "] %m");
		appender.addToLogger(logger.getName(), Level.INFO);
		appender.start();

		// Log to the string appender
		logger.error("Test");

		assertEquals(String.format("[%s] Test", placeholderValue), appender.getOutput());
		appender.removeFromLogger(LogManager.getRootLogger().getName());
	}

}
