package automationmoboltproduct;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import jxl.Sheet;
import jxl.Workbook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BuildCV extends ScenarioUtilities{

	@Before
	public void setUp() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "/Users/santoshkumar/Desktop/chromedriver");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		openUrlandWaitForPageToLoad(driver, "https://vodafonedejobs.mobolt.com/en/job/list");
		//openUrlandWaitForPageToLoad(driver, url_client);
		maximizeWindow(driver);
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void test() throws Exception {
		//fail("Not yet implemented");
		//getInputDataFromExcel(path_name, 1); // "/Users/santoshkumar/Documents/jobslistinfo.xls"
		getInputDataFromExcel("/Users/santoshkumar/Desktop/jobslistinfo.xls", 1);
		//navigateTillApplyPage(path_name, 0, 0);
		navigateTillApplyPage("/Users/santoshkumar/Desktop/jobslistinfo.xls", 0, 0);
		extractandFillQuestionsinEveryPage(DROPBOX_CV_CSS_SELECTOR, DROPBOX_SIGNIN_CSS_SELECTOR, "/Users/santoshkumar/Desktop/jobslistinfo.xls", "autocreate");
		//extractandFillQuestionsinEveryPage(DROPBOX_CV_CSS_SELECTOR, DROPBOX_SIGNIN_CSS_SELECTOR, path_name, signup_name);
	}
}
