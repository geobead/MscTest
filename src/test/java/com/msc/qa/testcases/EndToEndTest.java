package com.msc.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.msc.qa.base.TestBase;
import com.msc.qa.pages.CartPage;
import com.msc.qa.pages.CheckoutPage;
import com.msc.qa.pages.HomePage;
import com.msc.qa.pages.ProductPage;
import com.msc.qa.pages.ProductsArchivePage;

public class EndToEndTest extends TestBase{
	
	/*Pages*/
	private HomePage homePage;
	private ProductsArchivePage productsArchivePage;
	private ProductPage productPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;
	
	
	/*TODO: optimize later with a car class*/
	private String carYear;
	private String carMake;
	private String carModel;
	
	/*
	 * Marks a method as a factory that returns objects that will be used by TestNG
	 * as Test classes. The method must return Object[].
	 */
	@Factory(dataProvider="carDataProvider")
	public EndToEndTest(String year, String make, String model) {
		this.carYear = year;
		this.carMake = make;
		this.carModel = model;
	}
	
	@DataProvider
	public static Object[][] carDataProvider(){
		return new Object[][]{
			{"1998","HONDA","CIVIC"},
			{"2015","TOYOTA","CAMRY"},
			{"2010","CHEVY","MALIBU"},
			{"2014","FORD","MUSTANG"},
		};
	}
	
	@BeforeClass
	public void setUp() {
		System.out.print("setup()");
		initialization(); 
	}
	
	@Test
	public void setVehicleOnHomePage() {
		homePage = new HomePage(driver);
		homePage.setUserVehicle(this.carYear, this.carMake, this.carModel);
	}
	
	@Test(dependsOnMethods= {"setVehicleOnHomePage"})
	public void selectSeatCover() {
		String productName = "PROTECT VINYL";
		productsArchivePage = new ProductsArchivePage(driver);
		Assert.assertTrue(productsArchivePage.verifyCarHeading(this.carYear, this.carMake, this.carModel));
		productsArchivePage.selectProductByName(productName);
	}
	
	@Test(dependsOnMethods= {"selectSeatCover"})
	public void configureSeatCover() {
		productPage = new ProductPage(driver);
		productPage.verifyCarConfiguration(this.carYear,this.carMake,this.carModel);
		productPage.configureProduct("blue", "blue", "front-and-rear","Are these caltrend covers?");
		productPage.addProductToCart();
	}
	
	@Test(dependsOnMethods= {"configureSeatCover"})
	public void verifyCart() {
		cartPage = new CartPage(driver);
		cartPage.clickOnMiniCartButton();
		cartPage.verifyProductConfiguration(this.carYear, this.carMake, this.carModel);
		cartPage.clickOnCheckoutButton();
	}
	
	@Test(dependsOnMethods= {"verifyCart"})
	public void checkoutAndSubmitOrder() throws InterruptedException {
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.fillOutOrderForm();
		checkoutPage.verifyOrderSummary(carYear, carMake, carModel);
		checkoutPage.clickSubmitOrder();
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("tearDown()");
		shutdownDriver();
	}
}
