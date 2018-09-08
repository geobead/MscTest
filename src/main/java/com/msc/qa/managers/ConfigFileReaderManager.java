package com.msc.qa.managers;

public class ConfigFileReaderManager {
	private static ConfigFileReader configFileReader;
	
	public static ConfigFileReader getFileReader() {
		return (configFileReader == null)? configFileReader = new ConfigFileReader(): configFileReader;
	}
}
