package automationmoboltproduct;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jxl.Sheet;
import jxl.Workbook;

//import org.openqa.jetty.html.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ScenarioUtilities extends Utilities{

	public static void navigateTillApplyPage(String path_name, int sheet, int cellval) throws Exception{
		try{
		verifyAndClick(driver, SEARCH_JOBS_CSS_SELECTOR);
		waitforElementClick(SEARCH_CSS_SELECTOR);
		verifyAndClick(driver, SEARCH_CSS_SELECTOR);
		waitforElementVisibility(SORT_CSS_SELECTOR);
		selectAJobfromExcelSheet(path_name, sheet, cellval);
		waitforElementClick(APPLY_BUTTON_CSS_SELECTOR);
		verifyAndClick(driver, APPLY_BUTTON_CSS_SELECTOR);
		waitforElementClick(BUILD_CV_CSSSELECTOR);
		verifyAndClick(driver, BUILD_CV_CSSSELECTOR);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void applyActionsForWebElementsOnAPage(){
			getTitleofQuestionSet(driver, TITLE_CSS_SELECTOR);
			getCurrentURL();
			System.out.println(currentURL);
		/*	if (currentURL.contains(URL_HAS_SUBMIT_XPATH)) {
				verifyAndClick(driver, ADD_BUTTON_CSS_SELECTOR);
			}*/
			verifyAndType(driver, TEXTBOX_CHARACTERS_XPATH, "0"); //Entering text data into all the text boxes
			verifyAndType(driver, TEXTBOX_NUMBERS_XPATH, "9"); //Entering mobile number into Mobile Number fields
			verifyAndType(driver, TEXTBOX_SOCIALNUMBER_XPATH, "8"); //Entering Social number into the Social Number fields
			verifyAndType(driver, TEXTBOX_ZIP_XPATH, "5"); //Entering PIN Code into the ZIP fields
			verifyAndType(driver, TEXTBOX_EMAIL_XPATH, "0"); //Entering the email into the email address fields
			verifyAndType(driver, TEXTAREA_XPATH, "0");
			verifyAndSelectElement(driver, CHECKBOX_XPATH); //Choose a check box 
			verifyAndSelectElement(driver, RADIO_BUTTON_XPATH); // Choose a radio button
			verifyAndSelectValue(driver, DROPDOWN_XPATH, 1);
			verifyAndClick(driver, CONTINUE_CSS_SELECTOR); //Click on the Continue button
	}
	public static void loginIntoFunc(String cv_upload, String wait_ele, String path_name) throws Exception{
		try{
		FileInputStream fi = new FileInputStream(path_name);
		  Workbook w = Workbook.getWorkbook(fi);
		  Sheet s1 = w.getSheet(2);
		  verifyAndClick(driver, cv_upload);
		  waitforElementVisibility(wait_ele);
		  System.out.println("The user is going to login into: " +cv_upload);
		if (cv_upload == GOOGLEDRIVE_CV_CSS_SELECTOR){
			credential1 = s1.getCell(3, 1).getContents();
			credential2 = s1.getCell(3, 2).getContents();
			String resume_name = s1.getCell(3, 3).getContents();
			System.out.println("The credentials are:"+credential1 +"and"+credential2+"and resume name is: "+resume_name);
			signIn(driver, GOOGLEDRIVE_EMAIL_XPATH, GOOGLEDRIVE_PASSWORD_XPATH, 
					GOOGLEDRIVE_SIGNIN_CSS_SELECTOR, GOOGLEDRIVE_ALLOW_CSS_SELECTOR,credential1,credential2, resume_name);
		}
		else if (cv_upload == DROPBOX_CV_CSS_SELECTOR) {
			credential1 = s1.getCell(4, 1).getContents();
			credential2 = s1.getCell(4, 2).getContents();
			String resume_name = s1.getCell(4, 3).getContents();
			System.out.println("The credentials are:"+credential1 + "and" + credential2 );
			signIn(driver, DROPBOX_EMAIL_XPATH, DROPBOX_PASSWORD_XPATH, 
					DROPBOX_SIGNIN_CSS_SELECTOR, DROPBOX_ALLOW_CSS_SELECTOR, credential1, credential2, resume_name);
		}
		else if (cv_upload == AUTOCREATE_CV_CSS_SELECTOR) {
			verifyAndClick(driver, cv_upload);
		}
		else if (cv_upload == EMAIL_IT_LATER_CSS_SELECTOR) {
			verifyAndClick(driver, cv_upload);
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void extractandFillQuestionsinEveryPage(String cv_upload, String wait_ele, String path_name, String signup_type) throws Exception{
		for (int P = 0; P < 50; P++) {
			getTitleofQuestionSet(driver, TITLE_CSS_SELECTOR);
			getCurrentURL();
			if (currentURL.contains(URL_HAS_SUBMIT_XPATH)==false) {
				verifyAndClick(driver, ADD_BUTTON_CSS_SELECTOR);
				Thread.sleep(2000);
			}
			extractJavaScript(driver, JAVA_SCRIPT_XPATH);
			verifyAndType(driver, TEXTBOX_CHARACTERS_XPATH, "0"); //Entering text data into all the text boxes
			verifyAndType(driver, TEXTBOX_NUMBERS_XPATH, "9"); //Entering mobile number into Mobile Number fields
			verifyAndType(driver, TEXTBOX_SOCIALNUMBER_XPATH, "8"); //Entering Social number into the Social Number fields
			verifyAndType(driver, TEXTBOX_ZIP_XPATH, "5"); //Entering PIN Code into the ZIP fields
			verifyAndType(driver, TEXTBOX_EMAIL_XPATH, "softwaretesting652@gmail.com"); //Entering the email into the email address fields
			verifyAndType(driver, TEXTAREA_XPATH, "0");
			verifyAndSelectElement(driver, CHECKBOX_XPATH); //Choose a check box 
			verifyAndSelectElement(driver, RADIO_BUTTON_XPATH); // Choose a radio button
			verifyAndSelectValue(driver, DROPDOWN_XPATH, 1);
			verifyAndSelectValueXPATH(driver, LABEL_DROPDOWN_FROMDATE_XPATH, "2013");
			verifyAndSelectValueXPATH(driver, LABEL_DROPDOWN_TODATE_XPATH, "2015");
			loginIntoFunc(cv_upload, wait_ele, path_name);
			chooseSignUp(signup_type);
			verifyAndClick(driver, CONTINUE_CSS_SELECTOR); //Click on the Continue button
			verifyerrormessages();
			try{
			if(driver.findElement(By.xpath("//input[@id='feedback_Very_Easy' and @value = 'Very Easy']")).isDisplayed()){
				System.out.println("End of Application Flow");
				break;
			}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
	}
	public static void chooseSignUp(String signup_type){
		try{
		if (signup_type.contains("autocreate")) {
			autoGenerateMyAccount();
		}
		else if (signup_type.contains("create")) {
			createAnAccount();
		}
		else if (signup_type.contains("signin")) {
			signIn();
		}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void autoGenerateMyAccount(){
		if (driver.findElements(By.xpath(AUTO_CREATE_ACCOUNT_XPATH)).size()>0) {
			driver.findElement(By.xpath(AUTO_CREATE_ACCOUNT_XPATH)).click();
			//applyActionsForWebElementsOnAPage();
		}
	}
	public static void createAnAccount(){
		if(driver.findElement(By.xpath("//label/span/span/label[text() = 'Create my own account']")).isDisplayed()){ //Write Verify text function for this
			try{
			driver.findElement(By.xpath("//label/span/span/label[text() = 'Create my own account']")).click();
			//applyActionsForWebElementsOnAPage();
			verifyAndType(driver, TEXTBOX_PASSWORD_XPATH, "0");
			verifyAndSelectElement(driver, CHECKBOX_XPATH);
			verifyAndSelectValue(driver, DROPDOWN_XPATH, 2);
			verifyAndType(driver, TEXTBOX_CHARACTERS_XPATH, "0");
			}
			catch (Exception e){
				System.out.println("No textboxes are available");
			}
		}
	}
	public static void signIn() throws Exception{
		if (driver.findElements(By.xpath("//label/span/span/label[text() = 'Sign in']")).size()>0) {
			driver.findElement(By.xpath("//label/span/span/label[text() = 'Sign in']")).click();
			FileInputStream fi = new FileInputStream(path_name);
			  Workbook w = Workbook.getWorkbook(fi);
			  Sheet s1 = w.getSheet(2);
			  String username = s1.getCell(4, 1).getContents();
			  String password = s1.getCell(4, 2).getContents();
			  driver.findElement(By.xpath("//p[contains(text(),'Username') or contains(text(),'Email')]/../..//input")).sendKeys(username);
			  driver.findElement(By.xpath("//p[contains(text(),'Password')]/../..//input")).sendKeys(password);
		}
	
	}
	}
	

