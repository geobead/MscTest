package com.msc.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.msc.qa.base.PageBase;

public class CheckoutPage extends PageBase{

	@FindBy(id="billing_first_name")
	WebElement billingFirstName;
	
	@FindBy(id="billing_last_name")
	WebElement billingLastName;
	
	@FindBy(id="billing_email")
	WebElement billingEmail;
	
	@FindBy(id="billing_phone")
	WebElement billingPhone;
	
	@FindBy(id="s2id_billing_country")
	WebElement billingCountryDropDownArrow;
	
	@FindBy(css="#select2-results-1 > li.select2-result-selectable")
	List<WebElement> billingCountries;
	
	@FindBy(id="billing_address_1")
	WebElement billingAddress;
	
	@FindBy(id="billing_city")
	WebElement billingCity;
	
	@FindBy(id="s2id_billing_state")
	WebElement billingStateDropDownArrow;
	
	@FindBy(css="#select2-results-2 > li.select2-result-selectable")
	List<WebElement> billingStates;
	
	@FindBy(id="billing_postcode")
	WebElement billingZip;
	
	@FindBy(id="ship-to-different-address-checkbox")
	WebElement shipToDifferentAddressCheckBox;
	
	@FindBy(id="order_comments")
	WebElement orderNotes;
	
	@FindBy(id="place_order")
	WebElement submitOrderButton;
	
	/*Payment Gateway elements*/
	@FindBy(id="payment_method_paypal")
	WebElement paypalRadioButton;
	
	@FindBy(id="payment_method_helcimjs")
	WebElement creditcardRadioButton;
	
	
	/*Order Summary Elements*/
	@FindBy(css="dd.variation-Year")
	WebElement carYear;
	
	@FindBy(css="dd.variation-Make")
	WebElement carMake;
	
	@FindBy(css="dd.variation-Model")
	WebElement carModel;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.setPageTitle("Checkout - King of Seat Covers | King of Seat Covers");
		PageFactory.initElements(driver, this);
	}
	
	//element action methods
	public void clickShipToADifferentAddress() {
		shipToDifferentAddressCheckBox.click();
	}
	
	public void clickSubmitOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(submitOrderButton));
		submitOrderButton.click();
	}
	
	public void selectPaymentMethod(String paymentMethod) {
		if(paymentMethod.equalsIgnoreCase("paypal")) {
			paypalRadioButton.click();
		}
		else {
			creditcardRadioButton.click();
		}
	}
	
	public boolean setBillingCountry(String country) {
		billingCountryDropDownArrow.click();
		for(WebElement billingCountry:billingCountries) {
			if(billingCountry.getText().trim().equals(country)){
				billingCountry.click();
				return true;
			}
		}
		return false;
	}
	
	public boolean setBillingState(String state) {
		billingStateDropDownArrow.click();
		for(WebElement billingState:billingStates) {
			System.out.println(billingState.getText());
			if(billingState.getText().trim().equals(state)){
				billingState.click();
				return true;
			}
		}
		return false;
	}
	
	public void fillOutOrderForm() {
		setElementText(billingFirstName, "Jose");
		setElementText(billingLastName,"Valdez");
		setElementText(billingEmail,"gordojose@gmail.com");
		setElementText(billingPhone,"1234567890");
		setBillingCountry("United States (US)");
		setElementText(billingAddress,"13 la raza lane");
		setElementText(billingCity,"ontoros");
		setBillingState("Nevada");
		setElementText(billingZip,"12345");
		setElementText(orderNotes, "Are these caltrends");
	}
	
	public void verifyOrderSummary(String year, String make, String model) {
		Assert.assertTrue(carYear.getText().equalsIgnoreCase(year));
		Assert.assertTrue(carMake.getText().equalsIgnoreCase(make));
		Assert.assertTrue(carModel.getText().equalsIgnoreCase(model));
	}
}
