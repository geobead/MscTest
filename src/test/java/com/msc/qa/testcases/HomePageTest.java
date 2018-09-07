package com.msc.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.msc.qa.base.TestBase;
import com.msc.qa.pages.HomePage;

public class HomePageTest extends TestBase {

	HomePage homePage;
	final String expectedPageTitle="King of Seat Covers - Custom Seat Covers for Every Car Make and Model | King of Seat Covers";
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage(driver);
		homePage.setPageTitle(expectedPageTitle);
	}
	
	@Parameters({"browserType"})
	@Test(priority=1)
	public void verifyPageTitleTest(@Optional("chrome") String browserType) {
		Assert.assertTrue(homePage.verifyPageTitle());
	}
	
	@Test(priority=2, expectedExceptions= {IOException.class})
	public void expectedToFailTest() {
		System.out.println("expected to fail");
	}
	
	boolean failTest = true;
	@Test(priority=3)
	public void expectedToEventuallyPassTest() {
		if(failTest) {
			failTest=!failTest; //pass next time
			Assert.assertTrue(false);
		}
	}
	
	
	@AfterMethod
	public void tearDown() {
		shutdownDriver();
	}
}
