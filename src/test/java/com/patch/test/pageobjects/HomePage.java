package com.patch.test.pageobjects;

import static org.junit.Assert.assertFalse;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.patch.test.ui.common.driver.BrowserDriver;
import com.patch.test.pageobjects.HomePage;
import com.patch.test.pageobjects.ApplicationPageBase;

public class HomePage extends ApplicationPageBase
{
	private static final Logger LOG = Logger.getLogger(HomePage.class);
	private final static String pageUrl = "/home/home.asp";
	@FindBy(how=How.LINK_TEXT, using="Account Management")
	private WebElement AccountManagement;
	
	@FindBy(how=How.LINK_TEXT, using="Claims Reporting")
	private WebElement ClaimsReporting;
	
	private WebDriver driver;

	public HomePage() 
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public HomePage(String domain) 
	{
		super(domain, pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertFalse(AccountManagement.equals(Exception.class));
		assertFalse(ClaimsReporting.equals(Exception.class));	
	}
	
	@Override
	protected void load() 
	{
		super.load();
	}
	

}
