package com.patch.test.ui.common.core;

import org.openqa.selenium.support.ui.LoadableComponent;

import com.patch.test.ui.common.core.PageBase;

public abstract class PageBase extends LoadableComponent<PageBase>
{
	private String pageUrl;
	public PageBase(String pageUrl)
	{
		this.pageUrl = pageUrl;		
	}
	public PageBase(String domain, String pageUrl)
	{
		this.pageUrl = domain + pageUrl;		
	}
	public String getPageUrl() 
	{
		return pageUrl;
	}

}
