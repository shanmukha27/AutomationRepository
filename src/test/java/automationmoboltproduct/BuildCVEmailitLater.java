package automationmoboltproduct;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class BuildCVEmailitLater extends ScenarioUtilities{
  @Test
  public void f() throws Exception {
	  getInputDataFromExcel(path_name, 1); // "/Users/santoshkumar/Documents/jobslistinfo.xls"
		navigateTillApplyPage(path_name, 0, 0);
		extractandFillQuestionsinEveryPage(EMAIL_IT_LATER_CSS_SELECTOR, "0", path_name, signup_name);	
  }
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
		//openUrlandWaitForPageToLoad(driver, "https://admin:n32BuW41k@barchesterjobs-test.mobolt.com");
		openUrlandWaitForPageToLoad(driver, url_client);
		maximizeWindow(driver);
  }
  @AfterTest
  public void afterTest() {
  }

}
