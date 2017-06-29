
package com.automation.accelerators;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionEngine extends TestEngineWeb {
    private static final Logger LOG = Logger.getLogger(ActionEngine.class);

    private final String msgClickSuccess = "Successfully Clicked On ";
    private final String msgClickFailure = "Unable To Click On ";
    private final String msgTypeSuccess = "Successfully Typed On ";
    private final String msgTypeFailure = "Unable To Type On ";
    private final String msgIsElementFoundSuccess = "Successfully Found Element ";
    private final String msgIsElementFoundFailure = "Unable To Found Element ";
    protected static Integer pauseS = 15;

    /**
     *
     * @param locator
     * @param locatorName
     * @return
     * @throws Throwable
     */
    public boolean selectByIndex(By locator, int index,
                                 String locatorName) throws Throwable {
        boolean flag = false;
        try {
            Select s = new Select(Driver.findElement(locator));
            s.selectByIndex(index);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (!flag) {
                this.reporter.failureReport("Select Value from the Dropdown"+locatorName, "Option at index " + index
                        + " is Not Select from the DropDown" + locatorName);


            } else if (flag) {
                this.reporter.SuccessReport("Select Value from the Dropdown"+locatorName, "Option at index " + index
                        + "is Selected from the DropDown" + locatorName);

            }
        }
    }

    public boolean click(By locator, String locatorName) throws Throwable
    {
        boolean status = false;
        try
        {
			/*((JavascriptExecutor) this.Driver).executeScript(
					"arguments[0].scrollIntoView();", locator);*/
            System.out.println(this.msgClickSuccess+locatorName);

            this.Driver.findElement(locator).click();
            this.reporter.SuccessReport("Click on "+locatorName , this.msgClickSuccess + locatorName);
            status = true;
        }
        catch(Exception e)
        {
            status = false;
            LOG.info(e.getMessage());
            this.reporter.failureReport("Click on "+locatorName, this.msgClickFailure + locatorName, this.Driver);

        }
        return status;

    }

    public boolean isElementPresent(By by, String locatorName,boolean expected) throws Throwable
    {
        boolean status = false;
        try
        {
            this.Driver.findElement(by);
            this.reporter.SuccessReport("isElementPresent" , this.msgIsElementFoundSuccess + locatorName);
            status = true;
        }
        catch(Exception e)
        {
            status = false;
            LOG.info(e.getMessage());
            if(expected == status)
            {
                this.reporter.SuccessReport("isElementPresent", "isElementPresent");
            }
            else
            {
                this.reporter.failureReport("isElementPresent", this.msgIsElementFoundFailure + locatorName, this.Driver);
            }
        }
        return status;
    }
    public boolean VerifyElementPresent(By by, String locatorName,boolean expected) throws Throwable
    {
        boolean status = false;
        try
        {
            if(this.Driver.findElement(by).isDisplayed()){
                this.reporter.SuccessReport("VerifyElementPresent "+locatorName , this.msgIsElementFoundSuccess + locatorName);
                status = true;
            }
        }
        catch(Exception e)
        {
            status = false;
            LOG.info(e.getMessage());
			/*if(expected == status)
			{
				this.reporter.SuccessReport("isElementPresent", "isElementPresent");
			}
			else
			{
				this.reporter.failureReport("isElementPresent", this.msgIsElementFoundFailure + locatorName, this.Driver);
			}*/
        }
        return status;
    }




    public boolean type(By locator, String testdata, String locatorName) throws Throwable
    {
        boolean status = false;
        try
        {
			/*this.Driver.findElement(locator).clear();
			this.Driver.findElement(locator).sendKeys(testdata);*/
            Driver.findElement(locator).clear();
            Driver.findElement(locator).sendKeys(testdata);
            this.reporter.SuccessReport("Enter text in "+locatorName , this.msgTypeSuccess + locatorName);
            status = true;
        }
        catch(Exception e)
        {
            status = false;
            LOG.info(e.getMessage());
            this.reporter.failureReport("Enter text in "+locatorName, this.msgTypeFailure + locatorName, this.Driver);
        }

        return status;
    }

    /**
     * Moves the mouse to the middle of the element. The element is scrolled
     * into view and its location is calculated using getBoundingClientRect.
     *
     * @param locator
     *            : Action to be performed on element (Get it from Object
     *            repository)
     *
     * @param locatorName
     *            : Meaningful name to the element (Ex:link,menus etc..)
     *
     */
    public boolean mouseover(By locator, String locatorName)
            throws Throwable {
        boolean flag = false;
        try {
            WebElement mo = this.Driver.findElement(locator);
            new Actions(this.Driver).moveToElement(mo).build().perform();
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag == false) {
                this.reporter.failureReport("MouseOver on"+locatorName,
                        "MouseOver action is not perform on" + locatorName,this.Driver);

            } else if (flag == true) {

                this.reporter.SuccessReport("MouseOver on"+locatorName,
                        "MouserOver Action is Done on" + locatorName);
            }
        }
    }

    public  boolean JSClick(By locator, String locatorName)
            throws Throwable {
        boolean flag = false;
        try {
            WebElement element = this.Driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) this.Driver;
            executor.executeScript("arguments[0].click();", element);
            // driver.executeAsyncScript("arguments[0].click();", element);

            flag = true;

        }

        catch (Exception e) {


        } finally {
            if (flag == false) {
                this.reporter.failureReport("MouseOver",
                        "MouseOver action is not perform on" + locatorName);
                return flag;
            } else if (flag == true) {
                this.reporter.SuccessReport("MouseOver",
                        "MouserOver Action is Done on" + locatorName);
                return flag;
            }
        }
        return flag;
    }
    public void sleep(int time) throws InterruptedException{
        Thread.sleep(time);
    }
    public boolean waitForElementPresent(By by, String locator, int secs)
            throws Throwable {
        boolean status = false;

        try {

            WebDriverWait wait = new WebDriverWait(this.Driver, 60);
            wait.until(ExpectedConditions.elementToBeClickable(by));
			/*((JavascriptExecutor) Driver).executeScript(
					"arguments[0].scrollIntoView();", by);*/

            for (int i = 0; i < secs/2; i++)
            {
                List<WebElement> elements = this.Driver.findElements(by);
                if (elements.size()>0)
                {
                    status = true;
                    return status;

                }
                else
                {
                    this.Driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                }
            }


        }
        catch (Exception e) {

            return status;
        }

        return status;

    }

    /**
     * Binding to get Xpath, CSS, Link, Partial link element
     *
     * @param locator locator of element in xpath=locator; css=locator etc
     * @return found WebElement
     */
    protected WebElement getElement(final String locator) {
        return getElement(locator, true);
    }


    /**
     * Get "By" object to locate element
     *
     * @param locator locator of element in xpath=locator; css=locator etc
     * @return by object
     */
    protected By byLocator(final String locator) {
        String prefix = locator.substring(0, locator.indexOf('='));
        String suffix = locator.substring(locator.indexOf('=') + 1);

        switch (prefix) {
            case "xpath":
                return By.xpath(suffix);
            case "css":
                return By.cssSelector(suffix);
            case "link":
                return By.linkText(suffix);
            case "partLink":
                return By.partialLinkText(suffix);
            case "id":
                return By.id(suffix);
            case "name":
                return By.name(suffix);
            case "tag":
                return By.tagName(suffix);
            case "className":
                return By.className(suffix);
            default:
                return null;
        }
    }

    /**
     * @param locator          locator of element in xpath=locator; css=locator etc
     * @param screenShotOnFail make screenshot on failed attempt
     * @return found WebElement
     */
    protected WebElement getElement(final String locator, boolean screenShotOnFail) {
        try {
            return Driver.findElement(byLocator(locator));
        } catch (Exception e) {
            if (screenShotOnFail);
            throw e;
        }
    }
    /**
     * Takes screenshot with default name
     *
     * @return url (or path for local machine) to saved screenshot
     */
    /*protected String takeScreenshot() {
        return takeScreenshot(randomStringTime(23));
    }

    *//**
     * Takes screenshot of current page. Screenshots are placed in /screenshots directory of project's root
     *
     * @param fileName name to give for screenshot.
     * @return url (or path for local machine) to saved screenshot
     *//*
   *//* protected String takeScreenshot(String fileName) {
        try {
            //Capture Screenshot
            TakesScreenshot driver = !getGrid() || getMobile() ?
                    (TakesScreenshot) Driver :
                    (TakesScreenshot) new Augmenter().augment(Driver);

            File tempFile = driver.getScreenshotAs(OutputType.FILE);
            saveAllureScreenshot(driver.getScreenshotAs(OutputType.BYTES));
            //Name and save file
            String path = getRelativeScreenshotPath(fileName);
            File screenShotFile = new File(path);
            FileUtils.copyFile(tempFile, screenShotFile);

            String strace = "";
            for (StackTraceElement el : Thread.currentThread().getStackTrace())
                strace += el.toString() + System.lineSeparator()
                        ;
            log.debug(strace);


            //Create link to screenshot
            String url = getScreenshotUrl(screenShotFile, fileName);
            log.info("SCREENSHOT: " + url);
            return url;

        } catch (Exception e) {
            //Suppress exception no need to fail test
            log.warn("takeScreenshot failed:", e);
            return e.getMessage();
        }
    }


    *//**//**
     * Composes url to screenshot that will be shown in build log
     * In case of local build - result is screenshot local path
     * In case of jenkins build - points to jenkins build artifacts
     * <p>
     * NOTE: url will become valid only after build is complete and results are archived
     *
     * @param screenShotFile
     * @param fileName       name to give for screenshot.
     * @return url (or path for local machine) to saved screenshot
     *//**//*
    String getScreenshotUrl(File screenShotFile, String fileName) throws IOException {
        String url = null;
        if (isLocal()) {
            url = screenShotFile.getCanonicalPath();
        } else {
            url = System.getenv("BUILD_URL") + "/artifact/test_automation/" + getRelativeScreenshotPath(fileName);
        }

        return url;
    }

    *//**//**
     * Composes relative path to screenshot file
     *
     * @param
     * @return path
     *//**//*
    String getRelativeScreenshotPath(String fileName) {
        String path = "screenshots/" + getScreenShotsDir() +
                "/" + Thread.currentThread().getName().replaceAll("\\(|\\)", "") + "/" + fileName + ".png";
        return path;
    }*/

    public boolean waitForElementPresent(String locator, int secs) throws Throwable {
        boolean status = false;
        try {
            WebDriverWait wait = new WebDriverWait(Driver, secs);
            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator(locator)));
            status=true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return status;
        }

        return status;
    }
    public boolean waitForElementToBeClickable(String locator, int secs) throws Throwable {

        boolean flag=false;
        try {
            WebDriverWait wait = new WebDriverWait(Driver, secs);
            wait.until(ExpectedConditions.elementToBeClickable(byLocator(locator)));
           return flag=true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return flag;
        }

    }

    public void waitForElementToBeClickable(String locator) throws Throwable {
        try {
            WebDriverWait wait = new WebDriverWait(Driver, pauseS);
            wait.until(ExpectedConditions.elementToBeClickable(byLocator(locator)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Wait until element is invisible/not present on the page
     *
     * @param locator locator to element
     * @param timeOut time to wait
     */
    protected void waitForElementNotPresent(final String locator, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(Driver, timeOut);
            wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(byLocator(locator))));
        } catch (Exception e) {
            if (e.getCause() != null &&
                    !e.getCause().getClass().toString().contains("NoSuchElementException")) {
                //takeScreenshot();
                throw e;
            }
        }
    }


    /**
     * Soft wait for visibility of element with default timeout
     *
     * @param locator locator of element to wait for
     * @return true if element is present and visible / false otherwise
     */
    protected boolean waitForElementPresent(final String locator) throws Throwable {
        return waitForElementPresent(locator, pauseS);
    }

    /**
     * Wait until element is invisible/not present on the page with default timeout
     *
     * @param locator locator to element
     */
    protected void waitForElementNotPresent(final String locator) {
        waitForElementNotPresent(locator, pauseS);
    }


    /**
     * Wait for invisibility of specific object on page
     *
     * @param locator of object that we wait for invisibility
     */
    protected void waitForInvisibility(final String locator) {

        WebDriverWait wait = new WebDriverWait(Driver, pauseS);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator(locator)));
        } catch (Exception e) {
            //log.info("Try to wait little more (wait for invisibility)");
        }
    }

    /**
     * Verifies whether element is present and displayed
     *
     * @param locator locator for element to verify
     * @return true if present; false otherwise
     */
    protected boolean isElementPresent(final String locator) {
        try {
            return isElementPresent(getElement(locator, false));
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Binding to click the webElement
     *
     * @param we webElement to click
     */
    protected void click(final WebElement we) {
        try {
            //((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", we);
            we.click();
        } catch (Exception e) {
            //log.error("Failed to click:", e);
            //takeScreenshot();
            throw e;
        }
    }



    /**
     * Binding to click Xpath, CSS, Link, Partial link element
     *
     * @param locator locator of the element in format xpath=locator; css=locator  etc
     */
    protected void click(final String locator) {
        click(getElement(locator));
    }

    /**

     * Verifies whether element is displayed

     *

     * @param we webelement to verify

     * @return true if present; false otherwise

     */

    protected boolean isElementPresent(final WebElement we) {

        try {

            return we.isDisplayed();

        } catch (Exception e) {

            return false;

        }

    }

    public void type( final WebElement we,String testdata) throws Throwable {

        try {
            //((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", we);
            we.sendKeys(testdata);
        } catch (Exception e) {
            throw e;
        }
    }

    public void type( String locatorName,String testdata) throws Throwable {

        type(getElement(locatorName),testdata);
    }
    public void type( String locatorName,String testdata, boolean clear, boolean keyClear) throws Throwable {

        typeKeys(getElement(locatorName),testdata,clear,keyClear);
    }

    /**
     *
     * @param locatorName: webelement having multiple elements
     * @return list of webelements
     * @throws Throwable
     */
    public List<WebElement> getAllElements( String locatorName) throws Throwable {


        List<WebElement> elements = new ArrayList<WebElement>();
        try {
            elements = Driver.findElements(byLocator(locatorName));
            return elements;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }

    /**
     * Binding to check Checkbox
     *
     * @param we webElement of checkbox to check
     */
    protected void check(final WebElement we) {
        if (!we.isSelected()) {
            we.click();
        }
    }

    /**
     * Binding to check checkbox
     *
     * @param locator locator of checkbox to check
     */
    protected void check(final String locator) {
        check(getElement(locator));
    }

    /**
     * Binding to clear text field and enter text
     *
     * @param we       webElement to type to
     * @param value    value to type into the field
     * @param clear    true to clear the field first; false to enter text without clearing field
     * @param keyClear true to clean using keyboard shortcut; false without clean;
     * @return webElement with edited text
     */
    protected WebElement typeKeys(final WebElement we, final String value, final boolean clear, final boolean keyClear) {
        if (clear) we.clear();
        if (keyClear) {
            we.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            we.sendKeys(Keys.chord(Keys.DELETE));
        }
        we.sendKeys(value);
        return we;
    }

    public  String getVisibleText(final String locator) throws Throwable {
        String text = "";
        try {
            text = getElement(locator).getText();
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * Binding to select item in dropdown which needs to be clicked for edit mode.
     * Fills actual value and presses "TAB" to submit, otherwise value could be not saved
     *
     * @param clickE        webElement of the field to click
     * @param selectLocator locator of the dropdown
     * @param value         value to select
     */
    protected void clickToSelect(final WebElement clickE, final String selectLocator, final String value) {
        click(clickE);
        selectDropDown(getElement(selectLocator), value);
        getElement(selectLocator).sendKeys(Keys.TAB);
    }

    /**
     * Binding to select item in dropdown by value
     *
     * @param we     WebElement of the dropdown
     * @param option value to select in the dropdown
     */
    protected void selectDropDown(final WebElement we, final String option) {

        try {
            getSelect(we).selectByVisibleText(option);
        } catch (Exception e) {
            //takeScreenshot();
            throw e;
        }
    }
    protected Select getSelect(final WebElement we) {

        return new Select(we);

    }

    public void scrollElementIntoView(String locator){
        ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollElementIntoView(WebElement elementToScroll){
        ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
    }

    public  String timeStamp(){
		/*java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime()).toString();*/
        Date now = new Date();
        String time = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH).format(now);
        System.out.println(time);
        return time;
    }
}
