package com.patch.test.common.pageobjects;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.patch.test.ui.common.driver.BrowserDriver;


public class Header extends LoadableComponent<Header> 
{
	@FindBy(how=How.LINK_TEXT, using="Log Out")
	private WebElement logout;

	private WebDriver driver;
	
	public Header()
	{
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertTrue(logout != null);
	}

	@Override
	protected void load() 
	{
		
	}
	
	public void logout() 
	{
		load();
		logout.click();	
	}
	
}