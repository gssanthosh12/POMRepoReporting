package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage  {
	
	WebDriver driver;
	ElementUtil elementUtil;
	By header = By.cssSelector("h1.private-page__title");
	By accountName = By.cssSelector("span.account-name ");
	By ContactsLinkPrimary = By.id("nav-primary-contacts-branch");
	By ContactsLinkSecondary = By.id("nav-secondary-contacts");
	
	public  HomePage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle(){
		
		return driver.getTitle();
	}
	
	
	public String getHomePageHeader(){
		if(driver.findElement(header).isDisplayed()){
			return driver.findElement(header).getText();
		}
		return null;
	}
	
	public String getAccountName(){
		if(driver.findElement(accountName).isDisplayed()){
			return driver.findElement(accountName).getText();
		}
		return null;

	}
	
	public ContactsPage gotoContactsPage(){
		clickOnContacts();	
		return new ContactsPage(driver);
		
	}
	
	public void clickOnContacts(){
		elementUtil.waitForElementToBePresent(ContactsLinkPrimary, 10);
		elementUtil.doClick(ContactsLinkPrimary);
		elementUtil.waitForElementToBePresent(ContactsLinkSecondary, 10);
		elementUtil.doClick(ContactsLinkSecondary);
		
	}
	
}
