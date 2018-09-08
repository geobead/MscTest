package com.msc.qa.managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	private final String configFilePath = System.getProperty("user.dir")+"/src/main/resources/config/config.properties";
	private Properties properties;
	
	public ConfigFileReader() {
		properties = new Properties();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(configFilePath));
			properties.load(bufferedReader);
			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getBrowserType() {
		return properties.getProperty("browserType");
	}
	
	public boolean getRunRemote() {
		return Boolean.parseBoolean(properties.getProperty("runRemote"));
	}
}
