package com.ghosta3.ocr;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class IFXSimpleCodeOCR {
	public static boolean DEBUG = false;
	
	public static String doOCRWithWebDriver(WebDriver driver, By by) throws IOException {		
		WebElement element = driver.findElement(by);
		byte[] byteArrOfCode = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.BYTES);
		ByteArrayInputStream bin = new ByteArrayInputStream(byteArrOfCode);
		BufferedImage image = ImageIO.read(bin);
		Point p = element.getLocation();
		Dimension s = element.getSize();
		return IFXSimpleCodeOCR.doOCR(image.getSubimage(p.x, p.y, s.width, s.height));
	}

	public static String doOCR(BufferedImage captchaImg) {
		try {
			BufferedImage image = convertToGray(captchaImg);
			ITesseract instance = new Tesseract(); 
            instance.setLanguage("eng");
            String result = instance.doOCR(image).trim().replaceAll(" ", "");
            if(DEBUG) {
				String fileName = "DEBUG-" + result + "-" + System.currentTimeMillis() + ".jpg";
				ImageIO.write(image, "jpg", new File(fileName));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage convertToGray(BufferedImage in) throws IOException {

        int w = in.getWidth();
        int h = in.getHeight();
        BufferedImage bufferedImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(in, 0,0, null);
        byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        int[][] dataIn2D = new int[h][w];


        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                dataIn2D[i][j] = data[i * w + j] & 0xFF;
            }
        }

        for (int i = 1; i < h - 1; i++) {
            for (int j = 1; j < w - 1; j++) {
                if (judge(dataIn2D, i, j) > 1300) {
                    data[i * w + j] = -1;
                }
            }
        }

        
        return bufferedImage;
    }

    private static int judge(int[][] dataIn2D, int x, int y) {
        int weight = dataIn2D[x - 1][y - 1] + dataIn2D[x - 1][y] + dataIn2D[x - 1][y + 1]
                + dataIn2D[x][y - 1] + dataIn2D[x][y + 1]
                + dataIn2D[x + 1][y - 0] + dataIn2D[x + 1][y] + dataIn2D[x + 1][y + 1];

        return weight;
    }
}
