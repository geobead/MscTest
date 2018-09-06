package com.msc.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.msc.qa.base.TestBase;
import com.msc.qa.pages.HomePage;
import com.msc.qa.pages.ProductPage;
import com.msc.qa.pages.ProductsArchivePage;

public class ProductPageTest extends TestBase {

	HomePage homePage;
	ProductsArchivePage productsArchivePage;
	ProductPage productPage;
	final String expectedPageTitle="Vinyl Custom Seat Covers - Perfect for Active Lifestyle";
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage(driver);
		homePage.setUserVehicle("2017", "TOYOTA", "CAMRY");
		productsArchivePage = new ProductsArchivePage(driver);
		productsArchivePage.selectProductByName("PROTECT VINYL");
		productPage = new ProductPage(driver);
		productPage.setPageTitle(expectedPageTitle);
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest() {
		Assert.assertTrue(productPage.verifyPageTitle());
	}
	
	@AfterMethod
	public void tearDown() {
		shutdownDriver();
	}
}
