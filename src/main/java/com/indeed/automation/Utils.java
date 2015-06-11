package com.indeed.automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Locale.Category;
import java.util.logging.Logger;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import automationmoboltproduct.ExcelUtilities;
import automationmoboltproduct.Repository;

public class Utils {
	public static WebDriver driver; 
	 static List<WebElement> textboxes;
	 static String xpath;
	 static String tagname;
	 static String id, visible, label, category, options, validations, knockout;

	public static final Logger LOGGER = Logger.getAnonymousLogger();
		public static void initateDriverObject(){ //This function is used to intiate the driver object
			driver = new FirefoxDriver();
		}
		public static void openUrlandWaitForPageToLoad(WebDriver driver, String url){
	    	LOGGER.info("Opening the URL" + url + " ");
	    	driver.get(url);
	    	waitforElementVisibility(Repository.SEARCH_JOBS_CSS_SELECTOR);//SEARCH_JOBS_CSS_SELECTOR
	    	LOGGER.info("User is on the home page: ");
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
								if (id.contains(Repository.TEXTBOX_CHARACTERS_XPATH)) {
									LOGGER.info("The textbox "+textboxes.get(l)+" accepts characters since it's type is text");
									generateRandomString();
									break;
								}
								else if (id.contains(Repository.TEXTBOX_NUMBERS_XPATH)) {
									LOGGER.info("The textbox "+textboxes.get(l)+" accepts number since it's type is num or tel");
									generateRandomNumbers(input);
									break;
								}
								else if (id.contains(Repository.TEXTBOX_SOCIALNUMBER_XPATH)) {
									LOGGER.info("The textbox "+textboxes.get(l)+" accepts number since it's type is num or tel");
									generateRandomNumbers(input);
									break;
								}
								else if (id.contains(Repository.TEXTBOX_ZIP_XPATH)) {
									LOGGER.info("The textbox "+textboxes.get(l)+" accepts number since it's type is num or tel");
									generateRandomNumbers(input);
									break;
								}
								else if (id.contains(Repository.TEXTBOX_EMAIL_XPATH)) {
									LOGGER.info("The textbox "+textboxes.get(l)+" accepts number since it's type is num or tel");
									textboxes.get(l).sendKeys(input);
							    	LOGGER.info("A value:"+input+"has been entered into the text box"+textboxes.get(l)); 
							    	break;
								}
								else if (id.contains(Repository.TEXTAREA_XPATH)) {
									//generateRandomString();
									List<WebElement> textarea = driver.findElements(By.xpath(id));
									for (int i = 0; i < textarea.size(); i++) {
										textarea.get(i).sendKeys(ExcelUtilities.sample.get(i));
									}
									break;
								}
								else if (id.contains(Repository.TEXTBOX_PASSWORD_XPATH)) {
									textboxes.get(l).sendKeys("Pass@w0rd!");
								}
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
				//Generates strings depending upon the text boxes size(i.e. number) available on a page
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
					    	List<WebElement> str = driver.findElements(By.xpath(Repository.LABEL_TEXTBOXES_XPATH));
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
					List<WebElement> str = driver.findElements(By.xpath(Repository.LABEL_TEXTBOXES_XPATH));
			    	LOGGER.info("A value:"+number+" has been entered into the text box"+" .. "+str.get(k).getText()+" with id as: "+textboxes.get(k).getAttribute("id"));
			    	if (k>textboxes.size()) {
						break;
					}
					}
				}
				public static void generateQuestionSet() throws JsonParseException, JsonMappingException, IOException{
					String java_script = Utils.driver.findElement(By.xpath("/html/body/div[1]/div[3]/script")).getAttribute("innerHTML");
					System.out.println(java_script);
					// "curr_count":1,
					String get_questions[] = java_script.split("\"curr_count\":1,");
					System.out.println(get_questions[0]);
					System.out.println(get_questions[1]);
					String filter_ques[] = get_questions[1].split(",\"answers\":");
					System.out.println(filter_ques[0]);
					String questions_arr = "{"+filter_ques[0]+"}";
					System.out.println(questions_arr);
					Map<String,Object> elementsMap = new HashMap<String, Object>(); //Extracting all the elements from JSON into an Hash Map
					ObjectMapper mapper = new ObjectMapper();
					elementsMap = mapper.readValue(questions_arr, new TypeReference<HashMap<String,Object>>() {});
					@SuppressWarnings("unchecked")
					List<Object> questions = (List<Object>)elementsMap.get("questions"); //Get all the list of questions
					for (Object question : questions) { //Navigate into each and every question and performing actions
						@SuppressWarnings("unchecked")
						Map<String,Object> questionMap = (Map<String, Object>) question;
						String id = questionMap.get("id").toString();
						String visible = questionMap.get("visible").toString();
						String label = questionMap.get("label").toString();
						String category = questionMap.get("category").toString();
						System.out.println(category);
						System.out.println("\n The id of the question: " +label+ " is "+ id);
						
						//String validations = (String) questionMap.get("validations");
					}
				}
				public static void xpathGenerator(String id, String category){
					if (category.contains("text")||category.contains("radio")||category.contains("check")){
						String tagname = "input";
						System.out.println("The tagname has been generated as: "+tagname);
						xpath = "//" + tagname + "[contains(@id,'" + id + "')]";
						System.out.println(xpath);
					}
					else{
						tagname = category;
						System.out.println("The tagname has been generated as: "+tagname);
						xpath = "//" + tagname + "[contains(@id,'" + id + "')]";
						System.out.println(xpath);
					}
				}
				public static void generateAnswers(String id, String category){
					if (category.contains("text")) {
						//Utils.verifyAndType(driver, id, input);
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
					Repository.currentURL = driver.getCurrentUrl();
					System.out.println(Repository.currentURL);
				}
				public static void getCompletedstatusANDApplicationIDFromURL(){
					try{
						Repository.currentURL = driver.getCurrentUrl();
						LOGGER.info(Repository.currentURL);
						String URL[] = Repository.currentURL.split("&status=");//Split URL into 2 parts with "&status=" as split
						LOGGER.info(URL[1]);
						Repository.urlapplicationid = URL[0];
						Repository.urlcompletedstatus = URL[1]; //Get the second part into the String
						}
						catch (Exception e){
							System.out.println("No need to get the element");
						}
				}
				public static void verifyerrormessages(){
					try{
					int numeric_val = 0;
					while ((driver.getCurrentUrl().contains(Repository.URL_CONTAINS_SUBMIT)) && (driver.findElements(By.xpath(Repository.ERROR_VALIDATION_XPATH)).get(0).findElements(By.xpath(Repository.PARENT_ELEMENT_XPATH)).size()>0)) {
					List<WebElement> error_elems = driver.findElements(By.xpath(Repository.ERROR_VALIDATION_XPATH));			
						int no_of_errors = error_elems.size();				
							for (int each_error = 0; driver.findElements(By.xpath(Repository.ERROR_VALIDATION_XPATH)).size() > 0; each_error++) {
								// displaying the error texts in the page
								System.out.println(each_error+ ". "+ driver.findElements(By.xpath(Repository.ERROR_VALIDATION_XPATH)).get(0).getText());
								List<WebElement> we = driver.findElements(By.xpath(Repository.ERROR_VALIDATION_XPATH)).get(0).findElements(By.xpath(Repository.PARENT_ELEMENT_XPATH));
								System.out.println("No. of siblings text fields Found are : "+ we.size());
								String text_id = we.get(0).getAttribute("id");
								String text_xpath = "//input[@id='" + text_id + "']";
								driver.findElement(By.xpath(text_xpath)).clear();
								tryDiffNumericEntries(text_xpath, driver.findElements(By.xpath(Repository.ERROR_VALIDATION_XPATH)).size());
								}
								//}
							if (driver.getCurrentUrl().contains(Repository.URL_CONTAINS_SUBMIT)) {
							driver.findElement(By.cssSelector(Repository.CONTINUE_CSS_SELECTOR)).click();
							}
						}
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
				public static void tryDiffNumericEntries(String text_xpath, int no_of_errors){
					//for(int i=0; i<NUMERICS.length; i++){
					for(int i=0; i<ExcelUtilities.data_trial_error.size(); i++){
						System.out.println("The size of Trial and error is:"+ExcelUtilities.data_trial_error.size());
					driver.findElement(By.xpath(text_xpath)).clear();
					//verifyAndType(driver, text_xpath, NUMERICS[i]);
					//verifyAndType(driver, text_xpath, data_trial_error.get(i));
					System.out.println("The value that is going to be entered is:" +ExcelUtilities.data_trial_error.get(i));
					driver.findElement(By.xpath(text_xpath)).sendKeys(ExcelUtilities.data_trial_error.get(i));
					verifyAndClick(driver, Repository.CONTINUE_CSS_SELECTOR);
					
					

					if( driver.findElements(By.xpath(Repository.ERROR_VALIDATION_XPATH)).size()< no_of_errors)
						break;
					}
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
			    		waitforElementVisibility(Repository.APPLY_BUTTON_CSS_SELECTOR);
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
