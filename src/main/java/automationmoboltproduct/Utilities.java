package automationmoboltproduct;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import junit.framework.Assert;
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

public class Utilities extends ExcelUtilities {
	int i;
	public static List<WebElement> textboxes;
	public static List<WebElement> labelnames;
	public static String lblname;
	public static String first_string[];
	public static String number;
	public static int l;
	public static String textbox_value;
	public static Workbook w;
	public Sheet s1;
	public static WritableWorkbook wo;
	public static WritableSheet ws;
	public static String credential1;
	public static String credential2;
	public static String wait_ele;
	public static String java_script;
	
	public static final Logger LOGGER = Logger.getAnonymousLogger();
	    public static void openUrlandWaitForPageToLoad(WebDriver driver, String url){
	    	LOGGER.info("Opening the URL" + url + " ");
	    	driver.get(url);
	    	waitforElementVisibility(SEARCH_JOBS_CSS_SELECTOR);
	    	LOGGER.info("User is on the home page: ");
	    }
	    //This function will extract java script in each and every page
	    public static void extractJavaScript(WebDriver driver, String id){
	    	By by = By.xpath(id);
			WebElement script = driver.findElement(by);
			java_script = script.getAttribute(ATTRIBUTE_JAVASCRIPT);
			System.out.println("The java script for this page is :" +java_script);
	    }
	    //This function will ask the script to wait until the respective element is visible - Explicit Wait
		public static void waitforElementVisibility(String ELE_CSS_SELECTOR){
			try{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ELE_CSS_SELECTOR)));
			System.out.println("Element has been located: " + " " + ELE_CSS_SELECTOR);
			LOGGER.info("Wait until the element is found:" +ELE_CSS_SELECTOR);
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
			LOGGER.info("Wait until the element is found:" +ELE_CSS_SELECTOR);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		}
		public static void verifyTextPresent(WebDriver driver, String id){
			try{
				By by = By.xpath(id);
				List<WebElement> text_present = driver.findElements(by);
				System.out.println("the text is: "+text_present);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	// Clicks the button for the CSS locator
		public static void verifyAndClick(WebDriver driver, String id) {
			try{
			By by = By.cssSelector(id);
			WebElement element = driver.findElement(by);
			element.click();
			LOGGER.info("Clicks on the button: "+id);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			}
		//Extracts all TEXTBOXES that accepts characters, numbers, email etc and will input data into them
		public static void verifyAndType(WebDriver driver, String id, String input){
			By by = By.xpath(id);
			 textboxes = driver.findElements(by);
			for (int i = 0; i < textboxes.size(); i++) {
				String textbox = textboxes.get(i).getAttribute("id");
				String textbox_value = textboxes.get(i).getAttribute("value");
				String placeholder = textboxes.get(i).getAttribute("placeholder");
				LOGGER.info("The available textboxes are:" +textbox+"-"+textbox_value);
			}
			for (int l = 0; l < textboxes.size(); l++) {
				String textbox_value = textboxes.get(l).getAttribute("value");
				if (textbox_value.equalsIgnoreCase("")) {
					try{
						textboxes.get(l).clear();
						if (id.contains(TEXTBOX_CHARACTERS_XPATH)) {
							LOGGER.info("The textbox "+textboxes.get(l)+" accepts characters since it's type is text");
							generateRandomString();
							break;
						}
						else if (id.contains(TEXTBOX_NUMBERS_XPATH)) {
							LOGGER.info("The textbox "+textboxes.get(l)+" accepts number since it's type is num or tel");
							generateRandomNumbers(input);
							break;
						}
						else if (id.contains(TEXTBOX_SOCIALNUMBER_XPATH)) {
							LOGGER.info("The textbox "+textboxes.get(l)+" accepts number since it's type is num or tel");
							generateRandomNumbers(input);
							break;
						}
						else if (id.contains(TEXTBOX_ZIP_XPATH)) {
							LOGGER.info("The textbox "+textboxes.get(l)+" accepts number since it's type is num or tel");
							generateRandomNumbers(input);
							break;
						}
						else if (id.contains(TEXTBOX_EMAIL_XPATH)) {
							LOGGER.info("The textbox "+textboxes.get(l)+" accepts number since it's type is num or tel");
							textboxes.get(l).sendKeys(input);
					    	LOGGER.info("A value:"+input+"has been entered into the text box"+textboxes.get(l)); 
					    	break;
						}
						else if (id.contains(TEXTAREA_XPATH)) {
							//generateRandomString();
							List<WebElement> textarea = driver.findElements(By.xpath(id));
							for (int i = 0; i < textarea.size(); i++) {
								textarea.get(i).sendKeys(sample.get(i));
							}
							break;
						}
						else if (id.contains(TEXTBOX_PASSWORD_XPATH)) {
							textboxes.get(l).sendKeys("Pass@w0rd!");
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		//Generates strings depending upon the text boxes size available on a page
				public static void generateRandomString(){
					char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
					StringBuilder sb = new StringBuilder();
					Random random = new Random();
					String first_string[] = {"Software", "Tester", "Testing", "Testapplication", "ApplicationTester"};
					for (int j = 0, i=0; j < textboxes.size(); j++, i++) {
					    	 char c = chars[random.nextInt(chars.length)];
							    sb.append(c);
							    String space = " ";
							    int sub = first_string.length-1;
					    	String output = first_string[i]+space+sb.toString();
					    	LOGGER.info(output);
					    	textboxes.get(j).sendKeys(output);
					    	List<WebElement> str = driver.findElements(By.xpath(LABEL_TEXTBOXES_XPATH));
					    	LOGGER.info("A value:"+output+" has been entered into the text box"+" .. "+str.get(j).getText()+" with id as: "+textboxes.get(j).getAttribute("id")); 
					    	
					    	System.out.println("i value is:"+i);
					    	if (j>textboxes.size()) {
								break;
							}
					    	if (i==sub) {
								LOGGER.info("The number of test strings have been exceeded");
								i = 0;
							}
					    	else{
					    		LOGGER.info("Test Strings are not exceeded");
					    	}
						}
				}
				//Generates numbers depending upon the "count" given
				public static void generateRandomNumbers(String count){
					int count_value = Integer.parseInt(count);
					for (int k = 0; k < textboxes.size(); k++) {
					String randomNumbers = RandomStringUtils.randomNumeric(count_value);
					String number = 9+randomNumbers;
					LOGGER.info("The generated number for the length: "+count_value+" is:"+number);
					textboxes.get(k).sendKeys(number);
					List<WebElement> str = driver.findElements(By.xpath(LABEL_TEXTBOXES_XPATH));
			    	LOGGER.info("A value:"+number+" has been entered into the text box"+" .. "+str.get(k).getText()+" with id as: "+textboxes.get(k).getAttribute("id"));
			    	if (k>textboxes.size()) {
						break;
					}
					}
				}
		public static void getLabelNames(WebDriver driver, String id){
			By by = By.xpath(id);
			 labelnames = driver.findElements(by);
			 for (int i = 0; i < labelnames.size(); i++) {
					String tb = labelnames.get(i).getAttribute("id");
					String tb_val = labelnames.get(i).getAttribute("value");
					String ph = labelnames.get(i).getAttribute("placeholder");
					
					lblname = labelnames.get(i).getText();
					System.out.println("The label names are:"+lblname);
					LOGGER.info(lblname +" : "+ tb_val +" ..: " +ph);
			 }
		}
		//Selects all checkboxes and radio buttons
		public static void verifyAndSelectElement(WebDriver driver, String id){
			try{
			By by = By.xpath(id);
			List<WebElement> element = driver.findElements(by);
			for (int i = 0; i < element.size(); i++) {
				String element_click = element.get(i).getAttribute("id");
			}
			for (int i= 0; i<element.size(); i++){	
				try{
				element.get(i).click();
				}
				catch(Exception ex){
					System.out.println("Hidden elements");
					ex.printStackTrace();
				}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		public static void verifyAndSelectValue(WebDriver driver, String id, int index){
			try{
			By by = By.cssSelector(id);
			List<WebElement> element = driver.findElements(by);
			if (element.size()>0) {
				for (int i = 0; i < element.size(); i++){
					String dropdown = element.get(i).getAttribute("id");			
					System.out.println(dropdown);			
				}
				for (int i = 0; i < element.size(); i++){			
					try{
					new Select(driver.findElement(By.id(element.get(i).getAttribute("id")))).selectByIndex(index);	
					}
					catch(Exception e){
						System.out.println("Hidden dropdown");
						e.printStackTrace();
					}
				}
			}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		public static void verifyAndSelectValueXPATH(WebDriver driver, String id, String value){
			By by = By.xpath(id);
			List<WebElement> element = driver.findElements(by);
			
			if (element.size()>0) {
				for (int i = 0; i < element.size(); i++){			
					try{
						System.out.println(element);
					new Select(driver.findElement(By.xpath(id))).selectByValue(value);;	
					}
					catch(Exception e){
						System.out.println("Hidden dropdown");
						e.printStackTrace();
					}
				}
				}
			}
		
		public static void getTitleofQuestionSet(WebDriver driver, String id){
			try{
			By by = By.xpath(id);
			List<WebElement> title = driver.findElements(by);
			for (int i = 0; i < title.size(); i++){
				String title_questionset = title.get(i).getText();		
				LOGGER.info(title_questionset);			
			}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	//Maximizes the window
		public static void maximizeWindow(WebDriver driver){
			LOGGER.info("Wait until the window is loaded");
			driver.manage().window().maximize();
			LOGGER.info("The window is maximized....!!!");
			driver.manage().deleteAllCookies();
		}
		public static void getCurrentURL(){
			currentURL = driver.getCurrentUrl();
			System.out.println(currentURL);
		}
		public static void getCompletedstatusANDApplicationIDFromURL(){
			try{
				currentURL = driver.getCurrentUrl();
				LOGGER.info(currentURL);
				String URL[] = currentURL.split("&status=");//Split URL into 2 parts with "&status=" as split
				LOGGER.info(URL[1]);
				urlapplicationid = URL[0];
				urlcompletedstatus = URL[1]; //Get the second part into the String
				}
				catch (Exception e){
					System.out.println("No need to get the element");
				}
		}
		public static void verifyerrormessages(){
			try{
			int numeric_val = 0;
			while ((driver.getCurrentUrl().contains(URL_CONTAINS_SUBMIT)) && (driver.findElements(By.xpath(ERROR_VALIDATION_XPATH)).get(0).findElements(By.xpath(PARENT_ELEMENT_XPATH)).size()>0)) {
			List<WebElement> error_elems = driver.findElements(By.xpath(ERROR_VALIDATION_XPATH));			
				int no_of_errors = error_elems.size();				
					for (int each_error = 0; driver.findElements(By.xpath(ERROR_VALIDATION_XPATH)).size() > 0; each_error++) {
						// displaying the error texts in the page
						System.out.println(each_error+ ". "+ driver.findElements(By.xpath(ERROR_VALIDATION_XPATH)).get(0).getText());
						List<WebElement> we = driver.findElements(By.xpath(ERROR_VALIDATION_XPATH)).get(0).findElements(By.xpath(PARENT_ELEMENT_XPATH));
						System.out.println("No. of siblings text fields Found are : "+ we.size());
						String text_id = we.get(0).getAttribute("id");
						String text_xpath = "//input[@id='" + text_id + "']";
						driver.findElement(By.xpath(text_xpath)).clear();
						tryDiffNumericEntries(text_xpath, driver.findElements(By.xpath(ERROR_VALIDATION_XPATH)).size());
						}
						//}
					if (driver.getCurrentUrl().contains(URL_CONTAINS_SUBMIT)) {
					driver.findElement(By.cssSelector(CONTINUE_CSS_SELECTOR)).click();
					}
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		public static void tryDiffNumericEntries(String text_xpath, int no_of_errors){
			//for(int i=0; i<NUMERICS.length; i++){
			for(int i=0; i<data_trial_error.size(); i++){
				System.out.println("The size of Trial and error is:"+data_trial_error.size());
			driver.findElement(By.xpath(text_xpath)).clear();
			//verifyAndType(driver, text_xpath, NUMERICS[i]);
			//verifyAndType(driver, text_xpath, data_trial_error.get(i));
			System.out.println("The value that is going to be entered is:" +data_trial_error.get(i));
			driver.findElement(By.xpath(text_xpath)).sendKeys(data_trial_error.get(i));
			verifyAndClick(driver, CONTINUE_CSS_SELECTOR);
			
			

			if( driver.findElements(By.xpath(ERROR_VALIDATION_XPATH)).size()< no_of_errors)
				break;
			}
		}
		public static void signIn(WebDriver driver, String email, String password, 
				String signIn, String access, String credential1, String credential2, String resume_name){
			try{ 
			driver.findElement(By.xpath(email)).sendKeys(credential1);
			driver.findElement(By.xpath(password)).sendKeys(credential2);
			verifyAndClick(driver, signIn);
			Thread.sleep(8000);
			verifyAndClick(driver, access);
			System.out.println(resume_name);
			driver.findElement(By.linkText(resume_name)).click();
			waitforElementVisibility(CONTINUE_CSS_SELECTOR);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		public void fileInputStream(String path_name, int sheet) throws Exception{
			FileInputStream fi = new FileInputStream(path_name);
			  Workbook w = Workbook.getWorkbook(fi);
			  Sheet s1 = w.getSheet(0);
		}
		public void fileOutpuStream(String path_name) throws Exception{
			FileOutputStream fo = new FileOutputStream(path_name);
			 WritableWorkbook wo = Workbook.createWorkbook(fo);
			 WritableSheet ws = wo.createSheet("ApplicationID", 0);
			 Label ti = new Label(0, 0, "ListofID's");
				ws.addCell(ti);
				Label lb = new Label(0, 1, urlapplicationid);		
				ws.addCell(lb);	
				wo.write();
				wo.close();
		}
		public static void selectAJobfromExcelSheet(String path_name, int sheet, int cellval) throws Exception{
			FileInputStream fi = new FileInputStream(path_name);
			  Workbook w = Workbook.getWorkbook(fi);
			  Sheet s1 = w.getSheet(sheet);
			System.out.println("Job:"+s1.getCell(1, cellval).getContents());
			for (int scroll = 0; scroll < 50; scroll++) {
				try{
	    		if (driver.findElements(By.partialLinkText(s1.getCell(1, cellval).getContents())).size()>0) {
	    		driver.findElement(By.partialLinkText(s1.getCell(1, cellval).getContents())).click();	
	    		waitforElementVisibility(APPLY_BUTTON_CSS_SELECTOR);
	    		break;
				}else{			
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("window.scrollBy(0,500)", "");
				      Thread.sleep(3000);
				}
				}
				catch(Exception e){
					e.printStackTrace();
				}
	    	}
		}
}
