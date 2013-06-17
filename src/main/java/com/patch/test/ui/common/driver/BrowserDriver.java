package com.patch.test.ui.common.driver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.patch.test.ui.common.driver.BrowserDriver;
import com.patch.test.ui.common.enums.BrowserType;

public final class BrowserDriver 
{
	public static final String BROWSER_KEY = "selenium.browser";
	public static final String IMPLICIT_WAIT_TIME_KEY = "selenium.implicitWaitTime";
	public static final String PAGE_LOAD_TIME_KEY = "selenium.pageLoadWaitTime";
	public static final String AJAX_LOAD_TIME_KEY = "selenium.ajaxLoadWaitTime";
	private static final String REMOTE_URL = System.getProperty("RemoteUrl");
	private static int implicitWaitDefaultTime = 30;
	private static int pageLoadWaitDefaultTime = 60;
	private static int ajaxWaitDefaultTime = 60;
	private static WebDriver webdriver;
	private static WebDriverWait waitPageLoad;
	private static WebDriverWait waitAjaxLoad;
	private static JavascriptExecutor javaScriptExecutor;
	
	private BrowserDriver()
	{
		
	}
	
	public static String getCurrentWindowHandle()
	{
		return webdriver.getWindowHandle();
	}
	
	public static String getPageSource()
	{
		return webdriver.getPageSource();
	}
	
	public static String getTitle()
	{
		return webdriver.getTitle();
	}
	
	public static String getUrl()
	{
		return webdriver.getCurrentUrl();
	}
	
	public static Set<String> getWindowHandles()
	{
		return webdriver.getWindowHandles();
	}
	
	private static WebDriver launchDriver()
	{
		switch (getBrowserType())
		{
			case InternetExplorer:
			{
				webdriver = launchInternetExplorer();
				break;
			}
			case Chrome:
			{
				webdriver = launchChrome();
				break;
			}
			
			default:
			{
				webdriver = launchFirefox();
				break;	
			}
		}
		
		webdriver.manage().timeouts().implicitlyWait(getImplicitWaitTime(), TimeUnit.SECONDS);
		waitPageLoad = new WebDriverWait(webdriver, getPageLoadWaitTime());
		waitAjaxLoad = new WebDriverWait(webdriver, getAjaxLoadWaitTime());
		javaScriptExecutor = (JavascriptExecutor) webdriver;
		
		return webdriver;
	}
	
	private static int getImplicitWaitTime()
	{
		String implicitWaitTime = System.getProperty(IMPLICIT_WAIT_TIME_KEY);
		
		if (implicitWaitTime == null || implicitWaitTime.isEmpty())
		{ 
			return implicitWaitDefaultTime; 
		}
		else
		{
			return Integer.parseInt(implicitWaitTime);
		}
	}
	
	private static int getPageLoadWaitTime()
	{
		String pageLoadWaitTime = System.getProperty(PAGE_LOAD_TIME_KEY);
		
		if (pageLoadWaitTime == null || pageLoadWaitTime.isEmpty())
		{ 
			return pageLoadWaitDefaultTime;
		}
		else
		{
			return Integer.parseInt(pageLoadWaitTime);
		}
	}
	
	private static int getAjaxLoadWaitTime()
	{
		String ajaxLoadWaitTime = System.getProperty(AJAX_LOAD_TIME_KEY);
		
		if (ajaxLoadWaitTime == null || ajaxLoadWaitTime.isEmpty())
		{ 
			return ajaxWaitDefaultTime;
		}
		else
		{
			return Integer.parseInt(ajaxLoadWaitTime);
		}
	}
	
	private static WebDriver launchFirefox()
	{
		if (REMOTE_URL != null && !REMOTE_URL.equals("")) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			try {

				return new RemoteWebDriver(new URL(System.getProperty("RemoteUrl")), capabilities);
			}


			catch (MalformedURLException e) {

				e.printStackTrace();
				return null;
			}
		}

		else
		{
			FirefoxProfile profile = new FirefoxProfile();
			profile.setAcceptUntrustedCertificates(true);

			return new FirefoxDriver(profile);
		}
	}
	
	private static WebDriver launchInternetExplorer()
	{
		if (REMOTE_URL != null && !REMOTE_URL.equals("")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			try {

				return new RemoteWebDriver(new URL(System.getProperty("RemoteUrl")), capabilities);
			}


			catch (MalformedURLException e) {

				e.printStackTrace();
				return null;
			}
		}

		else
		{
		URL IEDriverURL = BrowserDriver.class.getResource("/drivers/IEDriverServer.exe");
		File file = new File(IEDriverURL.getFile()); 
		System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, file.getAbsolutePath());
		
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		return new InternetExplorerDriver(capabilities);
		}			
	}
	
	private static WebDriver launchChrome()
	{
		if (REMOTE_URL != null && !REMOTE_URL.equals("")) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			try {

				return new RemoteWebDriver(new URL(System.getProperty("RemoteUrl")), capabilities);
			}


			catch (MalformedURLException e) {

				e.printStackTrace();
				return null;
			}
		}

		else
		{
		URL chromeDriverURL = BrowserDriver.class.getResource("/drivers/chromedriver.exe");
		File file = new File(chromeDriverURL.getFile()); 
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, file.getAbsolutePath());
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		
		return new ChromeDriver(options);
		}
	}
	
	private static WebDriver launchRemoteDriver()
	{		
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		try {
			if (REMOTE_URL != null && !REMOTE_URL.equals("")) {
				return new RemoteWebDriver(new URL(System.getProperty("RemoteUrl")), capabilities);
			}
			else
				return new InternetExplorerDriver();
		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;
		}		
		
	}
	
	private static BrowserType getBrowserType()
	{
		String browserType = System.getProperty(BROWSER_KEY);
		if (browserType == null || browserType.isEmpty())
		{ 
			return BrowserType.Firefox;
		}
		else
		{
			int type = Integer.parseInt(browserType);
			
			if (BrowserType.InternetExplorer.getCode() == type)
			{
				return BrowserType.InternetExplorer;
			}
			else if (BrowserType.Chrome.getCode() == type)
			{
				return BrowserType.Chrome;
			}
			
			else
			{
				return BrowserType.Firefox;
			}
			
		}
	}
	
	public static void start()
	{
		if (webdriver == null)
		{
			//webdriver = launchDriver();
			webdriver = new FirefoxDriver();
		}
	}
	
	public static void open(String url)
	{
		webdriver.navigate().to(url);
	}
	
	public static void reload()
	{
		webdriver.navigate().refresh();
	}
	
	public static void back()
	{
		webdriver.navigate().back();
	}
	
	public static void forward()
	{
		webdriver.navigate().forward();
	}
	
	public static String getAlert()
	{
		return webdriver.switchTo().alert().getText();
	}
	
	public static void clickOkOnAlert()
	{
		webdriver.switchTo().alert().accept();
	}
	
	public static void clickCancelOnAlert()
	{
		webdriver.switchTo().alert().dismiss();
	}
	
	public static boolean isElementPresent(By selector)
	{
		try
		{
			webdriver.findElement(selector);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public static void waitForPageToLoad()
	{
		waitPageLoad.until(executeJavaScript("return document.readyState;", "complete"));
	}
	
	private static ExpectedCondition<Boolean> executeJavaScript(final String javascript, final String expectedString) 
	{
		return new ExpectedCondition<Boolean>() 
				{				
					public Boolean apply(WebDriver driver) 
					{
						try
						{
							return expectedString.equalsIgnoreCase(javaScriptExecutor.executeScript(javascript).toString());
						}
						catch (Exception e)
						{
							return false;
						}
					}
				};
	}
	
	public static WebElement waitForElement(WebElement we, int seconds) 
	{
		WebDriverWait wait = new WebDriverWait(webdriver, seconds);

		return wait.until(waitForElementToBeDisplayed(we));
	}
	
	private static ExpectedCondition<WebElement> waitForElementToBeDisplayed(final WebElement we) 
	{
		return new ExpectedCondition<WebElement>() 
				{
					public WebElement apply(WebDriver driver) 
					{
						if(we.isDisplayed()){

							return we;

							}

							return null;
					}
				};
	}
	
	public static void maximizeWindow()
	{
		int width = ((Long) javaScriptExecutor.executeScript("return screen.width;")).intValue();
        int height = ((Long) javaScriptExecutor.executeScript("return screen.height;")).intValue();

        switch (getBrowserType())
        {
        	case Firefox:
            {
            	maximizeFirefoxWindow(width, height);
                break;
            }
            
            case InternetExplorer:
            {
            	maximizeInternetExplorerWindow(width, height);
                break;
            }
        }
	}
	
	private static void maximizeFirefoxWindow(int screenWidth, int screenHeight)
    {
        webdriver.manage().window().setPosition(new Point(0, 0));
        webdriver.manage().window().setSize(new Dimension(screenWidth, screenHeight));
    }
	
	 private static void maximizeInternetExplorerWindow(int screenWidth, int screenHeight)
	 {
	    javaScriptExecutor.executeScript("return window.moveTo(0, 0)");
	    javaScriptExecutor.executeScript("return window.resizeTo(" + Integer.toString(screenWidth) + ", " + Integer.toString(screenHeight) + ")");
	 }
	 
	public static void switchWindow(String windowName)
	{
		webdriver.switchTo().window(windowName);
	}
	
	public static void closeWindow(String windowName)
	{
		webdriver.switchTo().window(windowName).close();
	}
	
	public static void stop()
	{
		if (webdriver != null)
		{
			webdriver.quit();
			
			webdriver = null;
		}
	}
	
	public static WebDriver  getDriver()
	{
		return webdriver;
	}
	
	public static Alert waitForAlert(int seconds) 
	{
		FluentWait<WebDriver> wait = new WebDriverWait(webdriver, seconds).ignoring(NullPointerException.class);
		Alert A = wait.until(waitforAlertPresent(webdriver));
		if (A!=null)
		{
			return A;
		}
		else
		{
			return null;
		}
	}
	
	public static ExpectedCondition<Alert> waitforAlertPresent(final WebDriver driver){

		return new ExpectedCondition<Alert>() {

			public Alert apply(WebDriver d) {

				Alert alert = driver.switchTo().alert();
				alert.getText();
				return alert;

			}
		};
	}
	
	public static boolean verifyTextPresent(String value)
    {
        boolean textFound = webdriver.getPageSource().contains(value);
        if(textFound)
        {
        return true;
        }
        else
        {
            return false;
        }
    }
	

}
