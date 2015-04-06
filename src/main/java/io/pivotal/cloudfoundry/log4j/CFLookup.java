package io.pivotal.cloudfoundry.log4j;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

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
