package automationmoboltproduct;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationCode extends ScenarioUtilities{
	public final static String SEARCH_JOBS_CSS_SELECTOR = "span.ui-btn-text";
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.get("https://admin:n32BuW41k@barchesterjobs-test.mobolt.com");
		//driver.get("https://admin:qc5t22Zs@hbcjobs.mobolt.com");
		Thread.sleep(4000);
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		System.out.println("Entered the Test Loop");
		LOGGER.info("The page has been loaded and the user is on the Home Page.....!!!");
		verifyAndClick(driver, SEARCH_JOBS_CSS_SELECTOR);
		waitforElementClick(SEARCH_CSS_SELECTOR);
		verifyAndClick(driver, SEARCH_CSS_SELECTOR);
		waitforElementClick(SORT_CSS_SELECTOR);
		driver.findElement(By.partialLinkText("Tennyson Wharf | Lincoln, ENG, GB")).click();
		waitforElementClick(APPLY_BUTTON_CSS_SELECTOR);
		verifyAndClick(driver, APPLY_BUTTON_CSS_SELECTOR);
		waitforElementClick(SIGN_IN_CSS_SELECTOR);
		verifyAndClick(driver, BUILD_CV_CSSSELECTOR);
		try{
		//	getTitleofQuestionSet(driver, TITLE_CSS_SELECTOR);
		  //  verifyAndClick(driver, ADD_BUTTON_CSS_SELECTOR);
			verifyAndType(driver, TEXTBOX_CHARACTERS_XPATH, "0"); //Entering text data into all the text boxes
			verifyAndType(driver, TEXTBOX_NUMBERS_XPATH, "9"); //Entering mobile number into Mobile Number fields
			verifyAndType(driver, TEXTBOX_SOCIALNUMBER_XPATH, "8"); //Entering Social number into the Social Number fields
			verifyAndType(driver, TEXTBOX_ZIP_XPATH, "5"); //Entering PIN Code into the ZIP fields
			verifyAndType(driver, TEXTBOX_EMAIL_XPATH, "softwaretesting652@gmail.com"); //Entering the email into the email address fields
			verifyAndSelectElement(driver, CHECKBOX_XPATH); //Choose a check box 
			verifyAndSelectElement(driver, RADIO_BUTTON_XPATH); // Choose a radio button
			getLabelNames(driver, LABEL_TEXTBOXES_XPATH);
			verifyAndClick(driver, CONTINUE_CSS_SELECTOR); //Click on the Continue button
		
		}
		catch(Exception e){
			e.printStackTrace();     
		}
		
	}

}
