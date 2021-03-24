package reusableLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Generalactions {

	// Variable to define driver
	private WebDriver driver;

	// Variable to store wait time
	static final int waittime = 30;

	// wait variable
	private WebDriverWait wait;

	public int count = 0;

	public WebDriver initiaiteChromedriver() throws Exception {
		try {
			if (count == 0) {

				System.setProperty("webdriver.chrome.driver", "src/main/java/chromedriver.exe");

				ChromeOptions options = new ChromeOptions();
				// WebDriverManager.chromedriver().setup();
				// options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("--lang=en-au");
				options.addArguments("allow-file-access-from-files");
				options.addArguments("use-fake-device-for-media-stream");
				options.addArguments("use-fake-ui-for-media-stream");
				// driver = (WebDriver) new ChromeDriver(options);

				driver = new ChromeDriver(options);
				// driver.manage().deleteAllCookies();
				driver.manage().window().maximize();

				count += 1;
			}
			return driver;
		} catch (Exception e) {
			throw e;
		}
	}

	public void setwebdriverwait() {
		wait = new WebDriverWait(driver, waittime);
	}

	public void closebrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
		}
	}

	public void refreshbrowser() throws Exception {
		driver.navigate().refresh();
	}

	public void refreshcurrentURl() {
		String url = driver.getCurrentUrl();
		driver.get(url);
	}

	public void openURL(String url) throws Exception {
		try {
			driver.get(url);
			// locateelement_withwait(locator);
		} catch (Exception e) {
			throw e;
		}
	}

	public WebElement locateelement_withwait(By locator) throws Exception {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			throw e;
		}
	}

	public WebElement locateelement_withcustomwait(By locator, int wait_time) throws Exception {
		try {
			WebDriverWait customwait = new WebDriverWait(driver, wait_time);
			return customwait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean checkifelementexist(By locator, int wait_time) {
		try {
			WebDriverWait customwait = new WebDriverWait(driver, wait_time);
			customwait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void sendkeys(By locator, String value) throws Exception {
		try {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(value);
		} catch (Exception e) {
			throw e;
		}
	}

	/*****
	 * JAVA SCRIPT METHODS
	 * 
	 * 
	 ****/
	public void javascriptSendkeys(String account) throws InterruptedException {
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('AccountNumber').setAttribute('value', '" + account + "')");
	}

	public void javascriptClick(By locator) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		WebElement el = driver.findElement(locator);
		JavascriptExecutor exec = (JavascriptExecutor) driver;
		exec.executeScript("arguments[0].click()", el);
	}

	public void click(By locator) throws Exception {
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, waittime);
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean checkIfButtonEnables(By locator) throws InterruptedException {
		Thread.sleep(1000);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			WebElement button = driver.findElement(locator);
			return button.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public String getattribute(By locator, String attribute_name) throws InterruptedException {
		Thread.sleep(1000);
		String value = null;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			value = driver.findElement(locator).getAttribute(attribute_name);
			return value;
		} catch (Exception e) {

			return value;
		}
	}

	public String getbackgroundcolor(By locator) throws InterruptedException {
		Thread.sleep(1000);
		String colorvalue = null;
		try {
			colorvalue = driver.findElement(locator).getCssValue("background-color");
			// value = driver.findElement(locator).getAttribute(attribute_name);
			return colorvalue;
		} catch (Exception e) {

			return colorvalue;
		}
	}

	public String getelementtext(By locator, int wait_time) {
		try {
			Thread.sleep(1000);
			WebDriverWait customwait = new WebDriverWait(driver, wait_time);
			customwait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return driver.findElement(locator).getText();

		} catch (Exception e) {
			return "Unable to capture value";
		}
	}

	public void tabclick() throws AWTException {

		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).build().perform();

	}

	public void enter() throws AWTException {

		Actions act = new Actions(driver);

		act.sendKeys(Keys.RETURN).build().perform();
	}

}
