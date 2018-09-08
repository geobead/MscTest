package com.msc.qa.managers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManager {
	private static WebDriver driver;

	public static WebDriver getInstance() {
		if (driver == null || hasQuit(driver)) {
			driver = createInstance();
		}
		return driver;
	}

	private static WebDriver createInstance() {
		boolean runRemote = ConfigFileReaderManager.getFileReader().getRunRemote();

		if (runRemote) {
			System.out.println("Running Remote");
			driver = createRemoteInstance();
		} else {
			System.out.println("Running Local");
			driver = createLocalInstance();
		}
		return driver;
	}

	public static WebDriver createLocalInstance() {
		// read properties file and get desired instance
		String browserType = ConfigFileReaderManager.getFileReader().getBrowserType();

		if (browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Mr Truong\\Desktop\\selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Mr Truong\\Desktop\\selenium\\geckodriver-v0.21.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			throw new RuntimeException("Unknown browser requested");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	// uses selenium grid
	public static WebDriver createRemoteInstance() {
		// read properties file and get desired instance
		String browserType = ConfigFileReaderManager.getFileReader().getBrowserType();
		URL hubUrl = null;
		try {
			hubUrl = new URL("http://localhost:4444/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setBrowserName(browserType);
		desiredCapabilities.setPlatform(Platform.WINDOWS);
		driver = new RemoteWebDriver(hubUrl,desiredCapabilities);
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static boolean hasQuit(WebDriver driver) {
		return ((RemoteWebDriver) driver).getSessionId() == null;
	}
}
