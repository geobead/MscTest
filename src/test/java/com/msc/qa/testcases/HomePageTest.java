package com.msc.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.msc.qa.base.TestBase;
import com.msc.qa.pages.HomePage;

public class HomePageTest extends TestBase {

	HomePage homePage;

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	@Test(priority = 0)
	public void PageTitleTest() {
		Assert.assertTrue(homePage.verifyTitle());
	}
	
	@Test(priority = 1)
	public void SetVehicleTest() {
		homePage.setUserVehicle("2016", "HONDA", "CIVIC SEDAN");
	}

	@AfterMethod
	public void tearDown() {
		shutdown();
	}
}
