package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.TimeUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//1. Define all Bylocators
	By username = By.id("username");
	By password = By.id("password");
	By LoginButton = By.id("loginBtn");
	By signupLink = By.linkText("Sign up");
	
	//2.constructors of page class
	public LoginPage(WebDriver driver){
		
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. Page Actions or methods
	
	public String getLoginPageTitle(){
		
		return elementUtil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}
	
	public boolean checkSignupLink(){
		return elementUtil.doIsDisplayed(signupLink);
		
	}
	
	public HomePage doLogin(String un, String pwd){
	
		elementUtil.doSendKeys(username, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(LoginButton);
		
		TimeUtil.Mediumwait();
		
		return new HomePage(driver);
	}
	
	
}
