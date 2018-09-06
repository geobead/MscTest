package com.msc.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.msc.qa.base.TestBase;
import com.msc.qa.pages.CartPage;
import com.msc.qa.pages.CheckoutPage;
import com.msc.qa.pages.HomePage;
import com.msc.qa.pages.ProductPage;
import com.msc.qa.pages.ProductsArchivePage;

public class CheckoutPageTest extends TestBase {

	HomePage homePage;
	ProductsArchivePage productsArchivePage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	
	final String expectedPageTitle="Checkout - King of Seat Covers | King of Seat Covers";
	
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage(driver);
		homePage.setUserVehicle("2017", "TOYOTA", "CAMRY");
		productsArchivePage = new ProductsArchivePage(driver);
		productsArchivePage.selectProductByName("PROTECT VINYL");
		productPage = new ProductPage(driver);
		productPage.configureProduct("blue", "blue", "front-and-rear","Are these caltrend covers?");
		productPage.addProductToCart();
		cartPage = new CartPage(driver);
		cartPage.clickOnMiniCartButton();
		cartPage.clickOnCheckoutButton();
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.setPageTitle(expectedPageTitle);
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest() {
		Assert.assertTrue(checkoutPage.verifyPageTitle());
		
	}
	
	@AfterMethod
	public void tearDown() {
		shutdownDriver();
	}
}
