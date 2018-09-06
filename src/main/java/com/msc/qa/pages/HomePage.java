package com.msc.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.msc.qa.base.PageBase;

public class HomePage extends PageBase{

	/*
		//Alternative way
		@FindBy(how=How.ID, using="someId")
		WebElement seatCoversLink;
	*/
	
	@FindBy(id="selectYears")
	WebElement yearSelect;
	
	@FindBy(id="selectMakes")
	WebElement makeSelect;
	
	@FindBy(id="selectModels")
	WebElement modelSelect;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this); 
	}
	
	public void selectVehicleYear(String year) {
		Select yearDropDown = new Select(yearSelect);
		yearDropDown.selectByVisibleText(year);
	}
	
	public void selectVehicleMake(String make) {
		Select makeDropDown = new Select(makeSelect);
		makeDropDown.selectByVisibleText(make);
	}
	
	public void selectVehicleModel(String model) {
		Select modelDropDown = new Select(modelSelect);
		modelDropDown.selectByVisibleText(model);
	}
	
	public void setUserVehicle(String year, String make, String model) {
		selectVehicleYear(year);
		selectVehicleMake(make);
		selectVehicleModel(model);
	}
}
