package com.msc.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.msc.qa.base.PageBase;

public class ProductPage extends PageBase{
	
	@FindBy(how=How.CSS, using="h1.product_title")
	WebElement productTitle;
	
	
	/* Product Configurations WebElements*/
	@FindBy(id="selectYears")
	WebElement yearSelect;
	
	@FindBy(id="selectMakes")
	WebElement makeSelect;
	
	@FindBy(id="selectModels")
	WebElement modelSelect;
	
	@FindBy(id="pa_insert-color")
	WebElement insertColorDropDown;
	
	@FindBy(id="pa_color")
	WebElement trimColorDropDown;
	
	@FindBy(id="pa_rows")
	WebElement rowsDropDown;
	
	@FindBy(name="attribute_pa_comments")
	WebElement commentsField;
	
	@FindBy(css="button.single_add_to_cart_button")
	WebElement addToCartButton;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getProductName() {
		return productTitle.getText();
	}
	
	public String getCarYear() {
		return getSelectedTextInDropDown(yearSelect);
	}
	
	public String getCarModel() {
		return getSelectedTextInDropDown(modelSelect);
	}
	
	public String getCarMake() {
		return getSelectedTextInDropDown(makeSelect);
	}
	
	public void setInsertColor(String color) {
		selectValueInDropDown(insertColorDropDown, color);
	}
	
	public void setTrimColor(String color) {
		selectValueInDropDown(trimColorDropDown, color);
	}
	
	public void setRows(String rows) {
		//front-only, front-and-rear, rears-only
		selectValueInDropDown(rowsDropDown, rows);
	}
	
	public void setComment(String comment) {
		setElementText(commentsField, comment);
	}
	
	public void verifyCarConfiguration(String year, String make, String model) {
		Assert.assertEquals(getCarYear(), year);
		Assert.assertEquals(getCarMake(), make);
		Assert.assertEquals(getCarModel(), model);
	}
	
	public void configureProduct(String insertColor, String trimColor, String rows) {
		setInsertColor(insertColor);
		setTrimColor(trimColor);
		setRows(rows);
	}
	
	public void configureProduct(String insertColor, String trimColor, String rows, String comments) {
		setInsertColor(insertColor);
		setTrimColor(trimColor);
		setRows(rows);
		setComment(comments);
	}
	
	public void addProductToCart() {
		addToCartButton.click();
	}
}
