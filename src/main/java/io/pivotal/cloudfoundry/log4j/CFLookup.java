package io.pivotal.cloudfoundry.log4j;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

/**
 * A log4j v2 plugin that will lookup values from Cloud Foundry's VCAP_APPLICATION 
 * environment variable so that those values can be used as custom placeholders in 
 * your log4j pattern layouts. 
 * @author dmalone <dmalone@pivotal.io>
 *
 */
@Plugin(name = "cf", category = "Lookup")
public class CFLookup implements StrLookup{

	public CFLookup(){
		System.out.println("Initializing CFLookup plugin");
	}
	
	public String lookup(String key) {
		return CFVcapApplication.getInstance().lookup(key);
	}

	public String lookup(LogEvent event, String key) {
		return lookup(key);
	}

}
