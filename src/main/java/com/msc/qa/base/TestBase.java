package com.msc.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Mr Truong\\Desktop\\selenium\\chromedriver_win32\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://kingofseatcovers.com/");
	}
	
	public void shutdownDriver() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
