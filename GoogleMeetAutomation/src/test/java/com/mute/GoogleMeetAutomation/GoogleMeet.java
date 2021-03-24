package com.mute.GoogleMeetAutomation;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import reusableLibrary.Generalactions;

public class GoogleMeet {
	WebDriver driver;
	int count = 0;
	Generalactions webdriveractions;

	@BeforeSuite
	public void beforetest() throws Exception {
		webdriveractions = new Generalactions();
		webdriveractions.initiaiteChromedriver();

	}

	@Test(priority = 0)
	public void ValidateMicIs_Muted() throws Exception {
		try {
			loadgooglemeet(webdriveractions);
			System.out.println("Microphone is  unmuted");
			String clickbtn = "//*[@id='ow3']/div[1]/div/div[9]/div[3]/div[9]/div[2]/div[1]/div/div/div/span/span";
			webdriveractions.click(By.xpath(clickbtn));
			String value = webdriveractions.getattribute(By.xpath("(//div[@class='sUZ4id'])[1]"), "data-is-muted");
			String color = webdriveractions.getbackgroundcolor(By.xpath(clickbtn));
			System.out.println("Color in muted button " + color);
			boolean blnResult = Boolean.parseBoolean(value);
			Assert.assertFalse(blnResult);
			System.out.println("Microphone is muted now");
		} catch (Exception e) {

			System.out.println("Inside Catch block");
		}

	}

	@Test(priority = 1)
	public void ValidateMicIs_UnMuted() throws Exception {
		try {
			/*
			 * loadgooglemeet(webdriveractions);
			 * System.out.println("On the Video call screen...");
			 */
			String clickbtn = "(//*[@id='ow3']/div[1]//div//div[9]//div[3]//div[9]//div[2]//div[1]//div//div//div//span//span)[1]";
			webdriveractions.click(By.xpath(clickbtn));
			// click join now
			// webdriveractions.click(By.xpath("//span[text()='Join now']"));
			String value = webdriveractions.getattribute(
					By.xpath("//div[@role='button'][@data-tooltip='Turn off microphone (ctrl + d)']"), "data-is-muted");
			String color = webdriveractions.getbackgroundcolor(By.xpath(clickbtn));
			System.out.println("Color in unmuted button:" + color);
			boolean blnResult = Boolean.parseBoolean(value);
			Assert.assertTrue(blnResult);
			System.out.println("Microphone is unmuted now");
		} catch (Exception e) {

			System.out.println("Inside Catch block");
		}

	}

	public void loadgooglemeet(Generalactions webdriveractions) throws Exception {
		if (count == 0) {
			webdriveractions.setwebdriverwait();
			webdriveractions.openURL("https://meet.google.com");
			Thread.sleep(7000);
			count++;
		}

		try {
			// webdriveractions.checkifelementexist(By.xpath("(//span[text()='Start a
			// meeting'])[2]"), 10);
			Thread.sleep(5000);
			webdriveractions.javascriptClick(
					By.xpath("(//button[@class='glue-button glue-button--high-emphasis  glue-button--icon '])[1]"));

			webdriveractions.sendkeys(By.xpath("//input[@type='email']"), "classplussachit@gmail.com");
			webdriveractions.click(By.xpath("//button[@jsname='LgbsSe']"));
			webdriveractions.sendkeys(By.xpath("//input[@type='password']"), "sachit123");
			webdriveractions.click(By.xpath("//button[@jsname='LgbsSe']"));
			// webdriveractions.click(By.xpath("//span[text()='Confirm']"));

			/*
			 * webdriveractions.click(By.xpath("//span[text()='New meeting']"));
			 * webdriveractions.checkifelementexist(By.xpath("//span[text()='New meeting']"
			 * ), 10); webdriveractions.click(By.xpath("//span[text()='New meeting']"));
			 * webdriveractions.click(By.xpath("//span[text()='Start an instant meeting']"))
			 * ;
			 */
			Thread.sleep(3000);
			/*
			 * webdriveractions.tabclick(); webdriveractions.tabclick();
			 * webdriveractions.enter(); webdriveractions.tabclick();
			 * webdriveractions.tabclick(); webdriveractions.enter();
			 */
		} catch (NoSuchElementException e) {
			System.out.println("Element not found");
		}

	}
@AfterTest
public void flush()
{
	driver.close();
}
}
