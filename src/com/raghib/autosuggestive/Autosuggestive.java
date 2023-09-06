package com.raghib.autosuggestive;

/**
Enter the letters BENG
Verify if Airport option is displayed in the suggestive box

Javascript DOM can extract hidden elements
because selenium cannot identify hidden elements - (Ajax implementation)
investigate the properties of object if it have any hidden text
 */

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import com.raghib.selenium.BaseClass;

public class Autosuggestive extends BaseClass {

	public static WebDriver driver;
	public static String browserName = "chrome";
	public static String browserVersion = "116";
	
	public static String url = "https://ksrtc.in/oprs-web/guest/home.do?h=1";
	
	public static void main(String[] args) throws InterruptedException {
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(url);

		driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys("BENG");
		Thread.sleep(2000);		

		// JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "return document.getElementById(\"fromPlaceName\").value;";
		String text = (String) js.executeScript(script);
		System.out.println(text);
		
		int i = 0;
		while (!text.equalsIgnoreCase("BENGALURU INTERNATION AIRPORT")) {
			i++;
			driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys(Keys.DOWN);

			text = (String) js.executeScript(script);
			System.out.println(text);
			if (i > 10) {
				break;
			}
		}

		if (i > 10) {
			System.out.println("Element not found");
		} else {
			System.out.println("Element  found");
		}
		Thread.sleep(10000);
		//BaseClass.quitDriver();
	}
}
