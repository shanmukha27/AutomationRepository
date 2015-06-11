package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	public static WebDriver driver;
	public static void waitforElementVisibility(String ELE_CSS_SELECTOR){
		try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ELE_CSS_SELECTOR)));
		System.out.println("Element has been located: " + " " + ELE_CSS_SELECTOR);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void waitforElementClick(String ELE_CSS_SELECTOR){
		try{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ELE_CSS_SELECTOR)));
		System.out.println("Element has been located: " + " " + ELE_CSS_SELECTOR);
	    }
		catch(Exception ex){
			ex.printStackTrace();
		}
		}
	public static void verifyAndClick(WebDriver driver, String id) {
		try{
		By by = By.cssSelector(id);
		WebElement element = driver.findElement(by);
		element.click();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		}
	public static void verifyAndType(WebDriver driver, String id, String input){
		try{
			By by = By.cssSelector(id);
			WebElement element = driver.findElement(by);
			element.sendKeys(input);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
	}
}
