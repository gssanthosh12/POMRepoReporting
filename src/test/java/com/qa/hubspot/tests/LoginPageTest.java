package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class LoginPageTest {
	Properties prop;
	BasePage basepage;
	WebDriver driver;
	LoginPage loginpage;
	
	@BeforeTest
	public void setUp(){
		
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
	}
	
	@Test(priority =1)
	public void VerifyLoginPageTitleTest(){
		String title=loginpage.getLoginPageTitle();
		System.out.println("login Page Title is "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);;
			
	}
	
	
	@Test(priority =2)
	public void verifySignUpLink(){
		Assert.assertTrue(loginpage.checkSignupLink());		
	}
	
	@Test(priority =3)
	public void LoginTest(){
		HomePage homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.getAccountName(), prop.getProperty("accountname"),"Login is failed ....");
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
		
	}
	
	
	
	
	
	
	
	

}
