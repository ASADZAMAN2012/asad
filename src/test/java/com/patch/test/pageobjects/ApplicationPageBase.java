package com.patch.test.pageobjects;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.patch.test.common.pageobjects.Footer;
import com.patch.test.common.pageobjects.Header;
import com.patch.test.ui.common.core.PageBase;

public abstract  class ApplicationPageBase extends PageBase
{
	private Header header;	
	private Footer footer;
	
	private static final Logger LOG = Logger.getLogger(ApplicationPageBase.class);

	public ApplicationPageBase(String pageUrl) 
	{		
		super(pageUrl);		
	}
	
	public ApplicationPageBase(String pageUrl, String domain) 
	{
		super(pageUrl, domain);		
	}
	
	@Override
	protected void load() 
	{
		
	}
	
	public void logout()
	{
		
	}

}