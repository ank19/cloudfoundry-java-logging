package io.pivotal.cloudfoundry;

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

	private final String appName;
	private final String spaceName;
	
	public JacksonCFVcapApplicationParser(Environment environment){
		final String vcapAppEnvVar = environment.getEnvironmentVariable("VCAP_APPLICATION");
		
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			Map<String,Object> vcapAppData = objectMapper.readValue(vcapAppEnvVar, Map.class);
			
			appName = (String) vcapAppData.get("application_name");
			spaceName = (String) vcapAppData.get("space_name");
		} catch (Exception e) {
			throw new IllegalStateException("Unble to parse VCAP_APPLICATION environment variable; VCAP_APPLICATION=" + vcapAppEnvVar, e);
		}
	}
	
	private JacksonCFVcapApplicationParser(){
		this(new Environment());
	}
	
	public static JacksonCFVcapApplicationParser getInstance(){
		return instance;
	}

	public String getAppName() {
		return appName;
	}

	public String getSpaceName() {
		return spaceName;
	}
	
	
}
