package com.msc.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.msc.qa.base.PageBase;

public class CartPage extends PageBase{
	
	@FindBy(css="a span.shopping-cart")
	WebElement miniCartButton;
	
	@FindBy(linkText="Proceed to Checkout")
	WebElement checkoutButton;
	
	/*Cart Item Elements*/
	@FindBy(css=".product-name")
	List<WebElement> cartProducts;
	
	@FindBy(css="dd.variation-Year")
	WebElement carYear;
	
	@FindBy(css="dd.variation-Make")
	WebElement carMake;
	
	@FindBy(css="dd.variation-Model")
	WebElement carModel;
	
	public CartPage(WebDriver driver) {
		super(driver);
		setPageTitle("Cart - King of Seat Covers | King of Seat Covers");
		PageFactory.initElements(driver, this);
	}
	
	/*action methods*/
	public void clickOnMiniCartButton(){
		miniCartButton.click();
	}
	
	public void clickOnCheckoutButton() {
		checkoutButton.click();
	}
	
	public boolean verifyPageTitle() {
		
		return getActualPageTitle().equals(this.PAGE_TITLE);
	}
	
	public void verifyProductConfiguration(String year, String make, String model) {
		Assert.assertTrue(carYear.getText().equalsIgnoreCase(year));
		Assert.assertTrue(carMake.getText().equalsIgnoreCase(make));
		Assert.assertTrue(carModel.getText().equalsIgnoreCase(model));
	}
	
}
