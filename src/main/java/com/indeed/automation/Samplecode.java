package com.indeed.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import automationmoboltproduct.ExcelUtilities;
import automationmoboltproduct.Repository;

import com.indeed.automation.Libutils;
import com.indeed.automation.Utils;

public class Samplecode extends Libutils {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ExcelUtilities.getInputDataFromExcel("/Users/santoshkumar/Desktop/jobslistinfo.xls", 1);
		 Libutils.driver = new FirefoxDriver();
		try{
			Libutils.driver.get("https://admin:AxkN!BjZx@cecentertainment-test.mobolt.com");
		verifyAndClick(driver, Repository.SEARCH_JOBS_CSS_SELECTOR);
			//Libutils.driver.findElement(By.cssSelector(Repository.SEARCH_JOBS_CSS_SELECTOR)).click();
		Thread.sleep(3000);
		verifyAndClick(driver, Repository.SEARCH_CSS_SELECTOR);
		//Libutils.driver.findElement(By.cssSelector(Repository.SEARCH_CSS_SELECTOR)).click();;
		Thread.sleep(3000);
		Libutils.driver.findElement(By.partialLinkText("Senior Assistant Restaurant Manager")).click();
		Thread.sleep(3000);
		verifyAndClick(driver, Repository.APPLY_BUTTON_CSS_SELECTOR);
		//Libutils.driver.findElement(By.cssSelector(Repository.APPLY_BUTTON_CSS_SELECTOR)).click();;
		Thread.sleep(3000);
		verifyAndClick(driver, Repository.BUILD_CV_CSSSELECTOR);
		//Libutils.driver.findElement(By.cssSelector(Repository.BUILD_CV_CSSSELECTOR)).click();
		//Thread.sleep(3000);
		for(int i = 0; i<20; i++){
//		extractQuestionFrmJSON();
		getJSONFrmJS();
		driver.findElement(By.cssSelector("#application-continue-button")).click();
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
