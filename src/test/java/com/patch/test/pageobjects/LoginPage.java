package com.patch.test.pageobjects;

import static org.junit.Assert.assertFalse;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.patch.test.pageobjects.HomePage;
import com.patch.test.pageobjects.ApplicationPageBase;
import com.patch.test.ui.common.driver.BrowserDriver;
import com.patch.test.pageobjects.LoginPage;

public class LoginPage extends ApplicationPageBase 
{
	private static final Logger LOG = Logger.getLogger(LoginPage.class);
	private final static String pageUrl = "/login";
	
	@FindBy(how=How.NAME, using="email")
	private WebElement email;
	
	@FindBy(how=How.NAME, using="password")
	private WebElement password;
	
	@FindBy(how=How.ID, using="signin")
	private WebElement signin;
	

	private WebDriver driver;
	
	public LoginPage()
	{
		super(pageUrl);
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage(String domain)
	{			
		super(domain, pageUrl);	
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);		
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertFalse(email.equals(Exception.class));
		assertFalse(password.equals(Exception.class));
		assertFalse(signin.equals(Exception.class));
	}
	
	@Override
	protected void load() 
	{		
		
	}
	
	public HomePage LoginAs(String userName, String pwd) throws Exception
	{		
		email.sendKeys(userName);
		LOG.info("Test step - Enter value in Email field : Passed ");
		
		password.sendKeys(pwd);
		LOG.info("Test step - Enter value in Passowrd field : Passed ");
		
		signin.click();
		LOG.info("Test step - Click the Sign In button : Passed ");
				
		return new HomePage();		
	}

}
