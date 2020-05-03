package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;
	JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		jsUtil.flash(element);
		return element;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.sendKeys(element, value).perform();
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doActionsClick(By locator) {
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.click(element).perform();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator){
		return getElement(locator).isDisplayed();
	}

	/**
	 * 
	 * @param locator
	 */
	public void getDropDownValues(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("total values in drop down: " + optionsList.size());
		for (int i = 0; i < optionsList.size(); i++) {
			System.out.println(optionsList.get(i).getText());
		}
	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */
	public void selectDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);

	}

	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public void selectDropDown(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */
	public void selectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> valuesList = select.getOptions();
		System.out.println(valuesList.size());
		for (int i = 0; i < valuesList.size(); i++) {
			if (valuesList.get(i).getText().equals(value)) {
				valuesList.get(i).click();
				break;
			}
		}
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getDropDownValuesList(By locator) {
		List<String> ar = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (int i = 0; i < optionsList.size(); i++) {
			ar.add(optionsList.get(i).getText());
		}

		return ar;

	}

	/**
	 * this method is used to select the value from the drop down - without
	 * using Select class in Selenium
	 * 
	 * @param locator
	 * @param locatorValue
	 * @param value
	 */
	public void doSelectValueFromDropDownWithOutSelect(String locator, String locatorValue, String value) {
		List<WebElement> valuesList = null;
		if (locator.equals("xpath")) {
			valuesList = driver.findElements(By.xpath(locatorValue));
		} else if (locator.equals("css")) {
			valuesList = driver.findElements(By.cssSelector(locatorValue));
		}

		System.out.println(valuesList.size());

		for (int i = 0; i < valuesList.size(); i++) {
			if (valuesList.get(i).getText().equals(value)) {
				valuesList.get(i).click();
				break;
			}
		}

	}

	// wait custom methods: wait utils:

	public List<WebElement> visiblilityOfAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(locator)));
		return driver.findElements(locator);
	}

	public WebElement waitForElementToBePresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator);
	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator);
	}

	public WebElement waitForElementToBeVisbile(By locator, int timeout) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement waitForElementVisibilityLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return getElement(locator);
	}

	public String waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}

	public boolean waitAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}

	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}

	public String waitForTitleToBePresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

}
