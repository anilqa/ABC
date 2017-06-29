package com.automation.accelerators;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.automation.report.CReporter;
import com.automation.report.ReporterConstants;
import com.automation.support.ExcelReader;
import com.automation.support.MyListener;
/**
 *
 * @author HarishDaggupati
 *
 */

public class TestEngineWeb {
    public static final Logger LOG = Logger.getLogger(TestEngineWeb.class);
    protected AppiumDriver appiumDriver = null;
    public WebDriver WebDriver = null;
    public EventFiringWebDriver Driver=null;
    public RemoteWebDriver RDriver=null;
    protected CReporter reporter = null;

    /*cloud platform*/
    public String browser = null;
    public String version = null;
    public String platform = null;
    public String environment = null;
    public String localBaseUrl = null;
    public String cloudBaseUrl = null;
    public String userName = null;
    public String accessKey = null;
    public String cloudImplicitWait = null;
    public String cloudPageLoadTimeOut = null;
    public String updateJira = null;
    public String buildNumber = "";
    public String jobName = "";
    public String executedFrom = null;
    public String executionType = null;
    public String suiteExecution = null;
    public String suiteStartTime = null;
    public String ExtentReportDirectoryPath = System.getProperty("user.dir")+"\\Reports\\";
    public String reportFileName = new SimpleDateFormat("MMddHHmm'_AdvanceExtentReports.html'").format(new Date());
    public String timeStamp = new SimpleDateFormat("MMddHHmm").format(new Date());
    public String ExtentReportPath = ExtentReportDirectoryPath+reportFileName;
    public ExtentReports extent ;
    public ExtentTest logger;


	/**/

    //private DesiredCapabilities capabilitiesForAppium = new DesiredCapabilities();

    @BeforeSuite(alwaysRun=true)
    public void beforeSuite() throws Throwable{

        PropertyConfigurator.configure(System.getProperty("user.dir")+"\\Log.properties");
        //ReportStampSupport.calculateSuiteStartTime();
        //ObjectRepository.storeIdentification();
        //ObjectRepository.storeValue();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
        String formattedDate = sdf.format(date);
        suiteStartTime = formattedDate.replace(":","_").replace(" ","_");
        System.out.println("Suite time ==============>"+suiteStartTime);

    }
    @BeforeClass(alwaysRun=true)
    @Parameters({"automationName","browser","browserVersion","environment","platformName"})
    public void beforeTest(String automationName, String browser, String browserVersion,String environment,String platformName) throws IOException, InterruptedException
    {
		/*PropertyConfigurator.configure(System.getProperty("user.dir")+"\\Log.properties");
		   System.out.println(System.getProperty("user.dir")+"\\Log.properties");*/

		/*get configuration */
        this.browser = browser;
        this.version = browserVersion;
        this.platform = platformName;
        this.environment = environment;
        this.localBaseUrl = ReporterConstants.APP_BASE_URL;

        this.userName = ReporterConstants.SAUCELAB_USERNAME;
        this.accessKey = ReporterConstants.SAUCELAB_ACCESSKEY;
        this.executedFrom = System.getenv("COMPUTERNAME");
        this.cloudImplicitWait = ReporterConstants.CLOUD_IMPLICIT_WAIT;
        this.cloudPageLoadTimeOut = ReporterConstants.CLOUD_PAGELOAD_TIMEOUT;
        this.updateJira = "";
        extent = new ExtentReports(ExtentReportPath,true);
        //loading the config file directory path
//        extent.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReportConfig.xml"));


		/**/
        System.out.println(environment);

        if(environment.equalsIgnoreCase("local"))
        {
            this.setWebDriverForLocal(browser);
        }
        if(environment.equalsIgnoreCase("cloudSauceLabs"))
        {

            this.setRemoteWebDriverForCloudSauceLabs();
        }
        if(environment.equalsIgnoreCase("grid")){
            this.setWebdriverForGrid(browser);
        }
        if(environment.equalsIgnoreCase("cloudSauceLabsJenkins"))
        {
            this.updateConfigurationForCloudSauceLabsJenkins();
			/*set remoteWebDriver for cloudsaucelabs*/

            this.setRemoteWebDriverForCloudSauceLabs();
        }


        if (environment.equalsIgnoreCase("cloudBrowserStackJenkins"))
        {
			/*TBD: Not Implemented For Running Using Jenkins*/
            this.updateConfigurationForCloudBrowserStackJenkins();
        }
        reporter = CReporter.getCReporter(browser, platformName , environment, true);

        //String Node = "http://10.112.66.52:5555/wd/hub";

        this.Driver = new EventFiringWebDriver(this.WebDriver);
        MyListener myListener = new MyListener();
        this.Driver.register(myListener);
        Driver.get(ReporterConstants.APP_BASE_URL);
        Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        //Driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Driver.manage().window().maximize();
        Driver.manage().deleteAllCookies();
        reporter.calculateSuiteStartTime();
    }


    @Parameters({"browser"})
    @AfterClass(alwaysRun=true)
    //public void close(String browser) throws Exception{
    //@AfterTest

    public void afterTest(String browser) throws Exception
    {
        if (browser.equalsIgnoreCase("firefox")) {
            Driver.quit();
			/*Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
			Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe"); */
        }
        else{
            Driver.quit();
        }
        //Driver.close();

        reporter.calculateSuiteExecutionTime();
        reporter.createHtmlSummaryReport(ReporterConstants.APP_BASE_URL,true);
        reporter.closeSummaryReport();


    }

    @BeforeMethod

    public void beforeMethod(Method method)
    {
        //get browser info

        //reporter = CReporter.getCReporter(deviceName, platformName, platformVersion, true);
        reporter.initTestCase(this.getClass().getName().substring(0,this.getClass().getName().lastIndexOf(".")), method.getName(), null, true);
    }

    @AfterMethod

    public void afterMethod(ITestResult result) throws IOException
    {
        if(result.getStatus()==ITestResult.FAILURE){
            logger.log(LogStatus.FAIL,"Test case failed is: "+result.getName());
           // logger.log(LogStatus.FAIL, result.getThrowable());
            /*String screenShotPath = capture(Driver, "screenShot"+timeStamp);
            System.out.println("screenShotPath====>"+screenShotPath);
            String image=logger.addScreenCapture(screenShotPath);
            logger.log(LogStatus.FAIL, "Snapshot below: " +image );*/
        }
        else if(result.getStatus()==ITestResult.SKIP){
            logger.log(LogStatus.FAIL,"Test case skipped is: "+result.getName());
        }
        extent.endTest(logger);
        extent.flush();
        //get browser info

        //reporter = CReporter.getCReporter(deviceName, platformName, platformVersion, true);

        reporter.calculateTestCaseExecutionTime();
        reporter.closeDetailedReport();
        reporter.updateTestCaseStatus();
    }



    public void setWebdriverForGrid(String browser) throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        if(browser.equalsIgnoreCase("IE")){
            caps = DesiredCapabilities.internetExplorer();
        }
        else if(browser.equalsIgnoreCase("Firefox")){
            caps = DesiredCapabilities.firefox();
        }
        else if(browser.equalsIgnoreCase("chrome")){
            caps = DesiredCapabilities.chrome();
        }
        else {
            caps = DesiredCapabilities.safari();
        }
        String Node = "http://172.16.28.74:4444/wd/hub";
        //URL commandExecutorUri = new URL("http://ondemand.saucelabs.com/wd/hub");
        this.WebDriver = new RemoteWebDriver(new URL(Node), caps);
    }

    private void setWebDriverForLocal(String browser) throws IOException, InterruptedException
    {
        switch(browser)
        {
            case "firefox":
                System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
                this.WebDriver = new FirefoxDriver();
                break;
            case "ie":

                DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
                capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                capab.internetExplorer().setCapability("ignoreProtectedModeSettings", true);
                //capab.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, INIT_PAGE);

                File file = new File("Drivers\\IE32BIT\\IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
                capab.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capab.setJavascriptEnabled(true);
                capab.setCapability("requireWindowFocus", true);
                capab.setCapability("enablePersistentHover", false);

                this.WebDriver = new InternetExplorerDriver(capab);
			/* Process p = Runtime
						.getRuntime()
						.exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
				p.waitFor();*/
                Thread.sleep(1000);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                this.WebDriver = new ChromeDriver(capabilities);

                break;
            case "Safari":
                WebDriver=new SafariDriver();
			
		/*	for(int i=1;i<=10;i++){

				try{
					WebDriver driver=new SafariDriver();
					//WebDriver=new SafariDriver();

					break;
				}catch(Exception e1){
					Runtime.getRuntime().exec("taskkill /F /IM Safari.exe");
					Thread.sleep(3000);
					Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
					Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe"); 

					continue;   

				}

			}*/

        }

    }

    private void setRemoteWebDriverForCloudSauceLabs() throws IOException, InterruptedException
    {
        if (this.browser.equalsIgnoreCase("Safari")) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, this.browser);
            desiredCapabilities.setCapability(CapabilityType.VERSION, this.version);
            desiredCapabilities.setCapability(CapabilityType.PLATFORM, this.platform);
            desiredCapabilities.setCapability("username", this.userName);
            desiredCapabilities.setCapability("accessKey", this.accessKey);
            desiredCapabilities.setCapability("accessKey", this.accessKey);
            desiredCapabilities.setCapability("name", this.executedFrom + " - " /*+ this.jobName + " - " + this.buildNumber*/+this.platform +" - " +this.browser);
            URL commandExecutorUri = new URL("http://ondemand.saucelabs.com/wd/hub");
            for(int i=1;i<=10;i++){

                try{
                    this.WebDriver = new RemoteWebDriver(commandExecutorUri, desiredCapabilities);

                    break;
                }catch(Exception e1){
                    Runtime.getRuntime().exec("taskkill /F /IM Safari.exe");
                    Thread.sleep(3000);
                    Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
                    Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");

                    continue;

                }
            }
        }
        else{

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, this.browser);
            desiredCapabilities.setCapability(CapabilityType.VERSION, this.version);
            desiredCapabilities.setCapability(CapabilityType.PLATFORM, this.platform);
            desiredCapabilities.setCapability("username", this.userName);
            desiredCapabilities.setCapability("accessKey", this.accessKey);
            desiredCapabilities.setCapability("accessKey", this.accessKey);
            desiredCapabilities.setCapability("name", this.executedFrom + " - " /*+ this.jobName + " - " + this.buildNumber*/+this.platform +" - " +this.browser);
            URL commandExecutorUri = new URL("http://ondemand.saucelabs.com/wd/hub");
            this.WebDriver = new RemoteWebDriver(commandExecutorUri, desiredCapabilities);
        }
    }

    private void updateConfigurationForCloudSauceLabsJenkins()
    {
        this.browser = System.getenv("SELENIUM_BROWSER");
        this.version = System.getenv("SELENIUM_VERSION");
        this.platform = System.getenv("SELENIUM_PLATFORM");
        this.userName = System.getenv("SAUCE_USER_NAME");
        this.accessKey = System.getenv("SAUCE_API_KEY");
        this.buildNumber = System.getenv("BUILD_NUMBER");
        this.jobName = System.getenv("JOB_NAME");

		/*For Debug Purpose*/
        LOG.info("Debug: browser = " + this.browser);
        LOG.info("Debug: version = " + this.version);
        LOG.info("Debug: platform = " + this.platform);
        LOG.info("Debug: userName = " + this.userName);
        LOG.info("Debug: accessKey = " + this.accessKey);
        LOG.info("Debug: executedFrom = " + this.executedFrom);
        LOG.info("Debug: BUILD_NUMBER = " + this.buildNumber);
        LOG.info("Debug: jobName = " + this.jobName);
    }

    /*TBD: Not Implemented For Running Using Jenkins*/
    private void updateConfigurationForCloudBrowserStackJenkins()
    {

    }


    public  String capture(WebDriver driver,String screenShotName) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\Reports\\ErrorScreenshots\\"+screenShotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        return dest;
    }

}
