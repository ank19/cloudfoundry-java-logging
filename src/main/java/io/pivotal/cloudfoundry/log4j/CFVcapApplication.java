package io.pivotal.cloudfoundry.log4j;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CFVcapApplication {

	private static final CFVcapApplication instance = new CFVcapApplication();
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final String appName;
	private final String spaceName;
	
	private CFVcapApplication(){
		final String vcapAppEnvVar = System.getenv("VCAP_APPLICATION");
		
		try {
			Map<String,Object> vcapAppData = objectMapper.readValue(vcapAppEnvVar, Map.class);
			
			appName = (String) vcapAppData.get("application_name");
			spaceName = (String) vcapAppData.get("space_name");
		} catch (Exception e) {
			throw new IllegalStateException("Unble to parse VCAP_APPLICATION environment variable; VCAP_APPLICATION=" + vcapAppEnvVar, e);
		}
	}
	
	public static CFVcapApplication getInstance(){
		return instance;
	}

	public String lookup(String key) {
		if("appName".equalsIgnoreCase(key)){
			return getAppName();
		}else if("spaceName".equalsIgnoreCase(key)){
			return getSpaceName();
		}else{
			return String.format("[Unknown key: %s]", key);
		}
	}
	
	public String getAppName() {
		return appName;
	}

	public String getSpaceName() {
		return spaceName;
	}
	
	
}
