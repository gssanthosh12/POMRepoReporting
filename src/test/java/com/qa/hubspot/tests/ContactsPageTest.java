package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactsPageTest {

	Properties prop;
	BasePage basepage;
	WebDriver driver;
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactspage;
	
	@BeforeTest
	public void setUp(){
		
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactspage = homepage.gotoContactsPage();
	}
	
	@Test(priority=1)
	public void VerifyContactsPageTitleTest(){
		
		String title=contactspage.getContactsPageTitle();
		System.out.println("Contacts Page Title is  "+title);
		Assert.assertEquals(title, Constants.CONSTANTS_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getContactsTestData(){
		Object data[][]=ExcelUtil.getTestDataMethod(Constants.CONSTANTS_SHEET_NAME);
		return data;
	}
	
	@Test(priority=2, dataProvider = "getContactsTestData")
	public void createNewcontactTest(String emailID, String firstname, String lastname, String jobtitle){
		contactspage.createNewContact(emailID,firstname,lastname,jobtitle);
		
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
		
	}
}
