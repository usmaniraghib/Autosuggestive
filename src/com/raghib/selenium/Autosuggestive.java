package com.raghib.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Enter the letters BENG
// Verify if Airport option is displayed in the suggestive box

//Javascript DOM can extract hidden elements
// because selenium cannot identify hidden elements - (Ajax implementation)
// investigate the properties of object if it have any hidden text

public class Autosuggestive {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://ksrtc.in/oprs-web/guest/home.do?h=1");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys("BENG");
		Thread.sleep(2000);		

		// JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "return document.getElementById(\"fromPlaceName\").value;";
		String text = (String) js.executeScript(script);
		System.out.println(text);
		
		int i = 0;
		//while (!text.equalsIgnoreCase("BENGALURU INTATION AIPORT")) {
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
		driver.quit();
	}
}
