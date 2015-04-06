package io.pivotal.cloudfoundry;

public class Environment {
	
	//Environment shouldn't ever need to be constructed, since it's more of a utility class than anything
	private Environment(){}
	
	/**
	 * Attempts to get the value of the given variableName from System.getenv first, System.getProperty second,
	 * and finally will return null if it can't find a non-null value after attempting to find a value in either
	 * of those two. 
	 * 
	 * @param variableName
	 * @return
	 */
	public static String getEnvironmentVariable(String variableName){
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
