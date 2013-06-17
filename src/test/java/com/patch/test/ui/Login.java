package com.patch.test.ui;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import com.patch.test.pageobjects.HomePage;
import com.patch.test.pageobjects.LoginPage;
import com.patch.test.ui.BaseTestCase;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:definition/main/application-context.xml" })
//@TestExecutionListeners

public class Login extends BaseTestCase
{
	private static final Logger LOG = Logger.getLogger(Login.class);
	private LoginPage loginPage;
	private HomePage homePage;
	String pageUrl = "http://bellmore.staging.i.patch.com";
	
	@Test
	public void testLogin () throws Exception
	{
		LOG.info("****************************************************");
		LOG.info("LOGIN - TEST STARTED");
		LOG.info("****************************************************");
		
		loginPage = (LoginPage) getNavigator().open(new LoginPage(pageUrl));
		homePage = (HomePage) getNavigator().get(loginPage.LoginAs("qa_automation+user+1@patch.com","passwordautomated1!!"));		
		
	}

}
