package io.pivotal.cloudfoundry;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A simple utility class that looks for an environment variable called VCAP_APPLICATION,
 * which is expected to be a json String, and parses useful values from it. 
 * 
 * @author dmalone<dmalone@pivotal.io>
 *
 */
public class JacksonCFVcapApplicationParser {

	private static final JacksonCFVcapApplicationParser instance = new JacksonCFVcapApplicationParser();
	
	Map<String, Object> vcapAppData  = Collections.EMPTY_MAP;
	
	private JacksonCFVcapApplicationParser(){
		
		final String vcapAppEnvVar = Environment.getEnvironmentVariable("VCAP_APPLICATION");
		
		try {
		
			if (null != vcapAppEnvVar){
				final ObjectMapper objectMapper = new ObjectMapper();
				vcapAppData = objectMapper.readValue(vcapAppEnvVar, Map.class);
				
			}
		} catch (Exception e) {
			throw new IllegalStateException("Unable to parse VCAP_APPLICATION environment variable; VCAP_APPLICATION=" + vcapAppEnvVar, e);
		}
	}

	
	public static JacksonCFVcapApplicationParser getInstance(){
		return instance;
	}

	
	public String lookup(String key){
		return String.valueOf(vcapAppData.get(key)) ;
	}
	
}
