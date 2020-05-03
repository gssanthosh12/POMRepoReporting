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

public class HomePageTest {
	
	Properties prop;
	BasePage basepage;
	WebDriver driver;
	LoginPage loginpage;
	HomePage homepage;
	
	@BeforeTest
	public void setUp(){
		
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void VerifyHomePageTitleTest(){
		
		String title = homepage.getHomePageTitle();
		System.out.println("Home Page Title is  "+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void VerifyHomePageHeader(){
		String header = homepage.getHomePageHeader();
		System.out.println("Homepage Header is +"+header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);	
		
	}
	
//	@Test(priority=3)
//	public void verifyLoggedinUser(){
//		String accountname = homepage.getAccountName();
//		System.out.println("Account Name is "+accountname);
//		Assert.assertEquals(accountname, prop.getProperty("accountname"));
	//}

	@AfterTest
	public void tearDown(){
		driver.quit();
		
	}
	
}
