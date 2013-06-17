package com.patch.test.common.pageobjects;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.patch.test.ui.common.driver.BrowserDriver;

public class Footer extends LoadableComponent<Footer> 
{
	private static final Logger LOG = Logger.getLogger(Footer.class);
	
	@FindBy(how=How.LINK_TEXT, using="ISOnet Home")
	private WebElement isoNetHome;
	
	@FindBy(how=How.LINK_TEXT, using="ISO Corporate Home")
	private WebElement isoCorporateHome;
	
	private WebDriver driver;
	
	public Footer()
	{
		this.driver = BrowserDriver.getDriver();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error 
	{
		assertTrue(isoNetHome != null);
		assertTrue(isoCorporateHome != null);
		
	}

	@Override
	protected void load() 
	{
		LOG.info("Loading footer...");
		
	}
	
	
}