package com.msc.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.msc.qa.base.TestBase;
import com.msc.qa.pages.HomePage;
import com.msc.qa.pages.ProductsArchivePage;

public class ProductsArchivePageTest extends TestBase {

	HomePage homePage;
	ProductsArchivePage productsArchivePage;
	final String expectedPageTitle="Products Archive - King of Seat Covers Archive | King of Seat Covers";
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage(driver);
		homePage.setUserVehicle("2017", "TOYOTA", "CAMRY");
		productsArchivePage = new ProductsArchivePage(driver);
		productsArchivePage.setPageTitle(expectedPageTitle);
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest() {
		Assert.assertTrue(productsArchivePage.verifyPageTitle());
	}
	
	@AfterMethod
	public void tearDown() {
		shutdownDriver();
	}
}
