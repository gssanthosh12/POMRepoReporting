package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsManager;
import com.qa.hubspot.utils.TimeUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	
	public WebDriver driver;
	public Properties prop;
	OptionsManager optionsManager;
	
	/**
	 * This Method is used to Initalise the driver on the basis of given browser
	 * @param browser
	 * @return driver
	 */
	
	public WebDriver init_driver(Properties prop){
		
		String browser = prop.getProperty("browser");		
		System.out.println("Browser name is "+browser);
		
		optionsManager = new OptionsManager(prop);
		
		if(browser.equalsIgnoreCase("chrome")){
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			
		}
		
		if(browser.equalsIgnoreCase("Firefox")){
			
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		}		
		
		else{
			System.out.println(browser +" is not found , pass the correct Browser");
		}
		
		driver.get((prop.getProperty("url")));
		TimeUtil.Mediumwait();
		driver.manage().window().maximize();
		
		return driver;
			
	}
	
	/**
	 * This method is used to initalise or load properties from config file
	 * @return prop
	 */
	
	public Properties init_prop(){
		
		prop = new Properties();
		try {
			FileInputStream ip =new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		return prop;
		
	}
	
	
}
