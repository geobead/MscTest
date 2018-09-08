package com.msc.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.msc.qa.base.PageBase;

public class ProductsArchivePage extends PageBase{

	@FindBy(css="ul.products>li.product")
	List<WebElement> products;
	
	@FindBy(css="ul.products>li.product>a.add_to_cart_button")
	List<WebElement> productAddToCartButton;
	
	@FindBy(css="h1.page-title")
	WebElement carHeading;
	
	public ProductsArchivePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
	
	//actions
	
	/**
	 * Click on a product of name
	 * 
	 * @param name Name of product to be found
	 * */
	public void selectProductByName(String name) {
		String productName = null;
		for(WebElement product: products) {
			productName = product.findElement(By.cssSelector("a>h2")).getText();
			if(productName.equals(name)) {
				product.click();
				break;
			}
		}
	}
	
	public void selectProductByIndex(int index) {
		products.get(index).click();
	}
	
	public boolean verifyCarHeading(String year, String make, String model) {
		String carInfo = year+" "+make+" "+model;
		return carHeading.getText().contains(carInfo);
	}
}
