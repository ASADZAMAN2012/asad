package com.patch.test.ui;

import org.junit.After;
import org.junit.Before;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.patch.test.navigator.impl.PatchNavigatorImpl;
import com.patch.test.ui.common.navigator.*;

//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
//@Transactional(propagation=Propagation.REQUIRED)

public class BaseTestCase
{
	public static final String ENVIRONMENT_KEY="TargetEnvironment";
	private PatchNavigator navigator;
	private String environment;
	
	@Before
	public void start() 
	{		
		startBrowser();	
	
	
	}
	
	public void startBrowser() 
	{		
		navigator = new PatchNavigatorImpl();
		navigator.start();		
	}
	
	@After
	public void stop() 
	{
		navigator.stop();
	}
	
	public PatchNavigator getNavigator()
	{
		return navigator;
	}
	
	public String getEnvironment()
	{		
		return environment;
	}
}
