package io.pivotal.cloudfoundry.log4j;

import io.pivotal.cloudfoundry.JacksonCFVcapApplicationParser;

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
@Plugin(name = "cf", category = StrLookup.CATEGORY)
public class CFLookup implements StrLookup{

	private final JacksonCFVcapApplicationParser vcapApplicationParser;
	
	public CFLookup(){
		this.vcapApplicationParser = JacksonCFVcapApplicationParser.getInstance();
	}
	
	public String lookup(String key) {
		if("appName".equalsIgnoreCase(key)){
			return vcapApplicationParser.getAppName();
		}else if("spaceName".equalsIgnoreCase(key)){
			return vcapApplicationParser.getSpaceName();
		}else{
			return String.format("[Unknown key: %s]", key);
		}
	}

	public String lookup(LogEvent event, String key) {
		return lookup(key);
	}

}
