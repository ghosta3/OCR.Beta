package com.ghosta3.ocr;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegRunner {

	private static final String PHONE_NO = "13585652145";

	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Download\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
    	driver.get("http://192.168.0.51:47064/OpentrueAccount");
    	By by = By.id("VerifyCode");
    	String verCode = IFXSimpleCodeOCR.doOCRWithWebDriver(driver, by);
    	
    	WebElement element = driver.findElement(By.id("userAccount"));
    	element.sendKeys("userAccount");
    	
    	element = driver.findElement(By.id("nickName"));
    	element.sendKeys("nickName");
    	
    	element = driver.findElement(By.cssSelector("#app > div.ng-scope > form > div.container > div > div.bg-white-only.openContent > div > div:nth-child(3) > div > div.col-sm-3.twoRight > select"));
    	new Select(element).selectByVisibleText("先生");
    	
    	element = driver.findElement(By.id("password"));
    	element.sendKeys("password");
    	
    	element = driver.findElement(By.id("confirmPassword"));
    	element.sendKeys("password");
    	
    	element = driver.findElement(By.id("code"));
    	element.sendKeys(verCode);
    	
    	element = driver.findElement(By.id("phone"));
    	element.sendKeys(PHONE_NO);
    	
    	
    	
    	//Click to send phone ver code
    	element = driver.findElement(By.cssSelector("#app > div.ng-scope > form > div.container > div > div.bg-white-only.openContent > div > div:nth-child(8) > div > div.col-sm-3.twoRight.halfWidth > button"));
    	element.click();
    	
    	Thread.sleep(5000);
        
    	IFXPhoneVerCodeGetter ifx = IFXPhoneVerCodeGetter.getInstance();
		String phoneVerCode = ifx.getPhoneVerCode(PHONE_NO);
    	element = driver.findElement(By.id("verCode"));
    	element.sendKeys(phoneVerCode);
    	
    	
    	
    	element = driver.findElement(By.cssSelector("#app > div.ng-scope > form > div.m-t-md.submitButton > button"));
    	element.click();
    	
	}

}
