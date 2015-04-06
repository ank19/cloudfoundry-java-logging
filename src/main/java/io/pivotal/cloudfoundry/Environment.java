package io.pivotal.cloudfoundry;

public class Environment {
	
	public String getEnvironmentVariable(String variableName){
		String value = null;
		
		value = System.getenv(variableName);
		
		if(value != null){
			return value;
		}
		
		value = System.getProperty(variableName);
		
		if(value != null){
			return value;
		}
		
		return value;
	}
	
}
