package com.patch.test.ui.common.navigator;

import java.net.URISyntaxException;

import com.patch.test.ui.common.core.PageBase;

public interface PatchNavigator 
{
	PageBase open(PageBase target) throws URISyntaxException;
	PageBase get(PageBase target) throws URISyntaxException;
	void start();
	void stop();

}
