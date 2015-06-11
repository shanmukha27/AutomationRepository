package automationmoboltproduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.indeed.automation.Libutils;
import com.indeed.automation.Utils;

public class Samplecode {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ExcelUtilities.getInputDataFromExcel("/Users/santoshkumar/Desktop/jobslistinfo.xls", 1);
		//WebDriver driver = new FirefoxDriver();
		try{
			Utils.driver.get("https://admin:AxkN!BjZx@cecentertainment-test.mobolt.com");
			Utils.driver.findElement(By.cssSelector(Repository.SEARCH_JOBS_CSS_SELECTOR)).click();
		Thread.sleep(3000);
		Utils.driver.findElement(By.cssSelector(Repository.SEARCH_CSS_SELECTOR)).click();;
		Thread.sleep(3000);
		Utils.driver.findElement(By.partialLinkText("Senior Assistant Restaurant Manager")).click();
		Thread.sleep(3000);
		Utils.driver.findElement(By.cssSelector(Repository.APPLY_BUTTON_CSS_SELECTOR)).click();;
		Thread.sleep(3000);
		Utils.driver.findElement(By.cssSelector(Repository.BUILD_CV_CSSSELECTOR)).click();
		Thread.sleep(3000);
		Libutils.extractQuestionFrmJSON();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
