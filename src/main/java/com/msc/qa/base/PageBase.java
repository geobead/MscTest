package com.msc.qa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageBase {
	
	protected String PAGE_URL;
	protected String PAGE_TITLE;
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	/*constructor*/
	public PageBase(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}
	
	/*setters*/
	public void setPageUrl(String url) {
		PAGE_URL = url;
	}
	
	public void setPageTitle(String title) {
		PAGE_TITLE = title;
	}
	
	/*action methods*/
	public String getActualPageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyPageTitle() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));
		System.out.println(getActualPageTitle());
		return getActualPageTitle().equals(this.PAGE_TITLE);
	}
	
	public void setElementText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
		Assert.assertEquals(element.getAttribute("value"), text);
	}
	
	public void selectVisibleTextinDropDown(WebElement dropDown, String text) {
		Select select = new Select(dropDown);
		select.selectByVisibleText(text);
	}
	
	public void selectValueInDropDown(WebElement dropDown, String value) {
		Select select = new Select(dropDown);
		select.selectByValue(value);
	}
	
	public String getSelectedValueInDropDown(WebElement dropDown) {
		Select select = new Select(dropDown);
		return select.getFirstSelectedOption().getAttribute("value");
	}
	
	public String getSelectedTextInDropDown(WebElement dropDown) {
		Select select = new Select(dropDown);
		return select.getFirstSelectedOption().getText();
	}
}
