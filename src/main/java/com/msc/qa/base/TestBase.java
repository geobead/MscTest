package com.msc.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.msc.qa.managers.WebDriverManager;

public class TestBase {
	protected static WebDriver driver;
	protected static Properties properties;

	public TestBase() {
		try {
			// read properties
			properties = new Properties();
			FileInputStream inputPropertiesFile;
			inputPropertiesFile = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config/config.properties");
			properties.load(inputPropertiesFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialization() {
		this.driver = WebDriverManager.getInstance();
		driver.get("https://kingofseatcovers.com/");
	}
	
	public void shutdownDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
