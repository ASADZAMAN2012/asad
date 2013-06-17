package com.patch.test.ui.common.enums;

public enum BrowserType 
{
	Firefox(1), InternetExplorer(2), Chrome(3);
	private int code;
	BrowserType(int code)
	{
		this.code = code;
	}
	public int getCode()
	{
		return code;
	}
}
