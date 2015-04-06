package io.pivotal.cloudfoundry.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class CFLookupIntegrationTests {

	private static final Logger logger = LogManager.getLogger(CFLookupIntegrationTests.class);

	@Test
	public void testLogAMessage() {
		logger.debug("Let's see if we get the app name logged");
	}

}
