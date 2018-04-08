package com.ghosta3.ocr;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * Hello world!
 *
 */
public class OCRExample 
{
    public static void main( String[] args ) throws IOException
    {
    	System.setProperty("webdriver.chrome.driver", "D:\\Download\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        IFXSimpleCodeOCR.DEBUG = true;
        for (int i = 0; i < 50; i++) {
        	driver.get("http://192.168.0.51:47064/OpentrueAccount");
        	By by = By.id("VerifyCode");
        	IFXSimpleCodeOCR.doOCRWithWebDriver(driver, by);
		}
        
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }

	
}
