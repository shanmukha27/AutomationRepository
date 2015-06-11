package automationmoboltproduct;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Repository extends TestRunner{
	public static WebDriver driver;
	public String testdata;
	public static String urlcompletedstatus;
	public static String urlapplicationid;
	public static String currentURL;
	public final static String TITLE_CSS_SELECTOR = "//div[contains(@class, 'question_set')]/div/div/h3";
	public final static String SEARCH_JOBS_CSS_SELECTOR = "span.ui-btn-text"; //"a[data-role='button']"
	public final static String SEARCH_CSS_SELECTOR = "#search-continue-button > span.ui-btn-inner";
	public final static String APPLY_BUTTON_CSS_SELECTOR = "a#apply";//"//a[@id='apply']";//"apply";
	public final static String APPLY_FACEBOOK_CSS_SELECTOR = "a[data-social='facebook']";
	public final static String APPLY_LINKEDIN_CSS_SELECTOR = "a[data-social='linkedin']";	
	public final static String APPLY_INDEED_CSS_SELECTOR = "a[data-social='indeed']";
	public final static String BUILD_CV_CSSSELECTOR = "a[data-social='manual']";
	public final static String SIGN_IN_CSS_SELECTOR = "a.apply_signin";//"//div[@id='main']/div[2]/div/a/span/span";
	public final static String SORT_CSS_SELECTOR = "#sort";
	public final static String CONTINUE_CSS_SELECTOR = "#application-continue-button";
	public final static String ADD_BUTTON_CSS_SELECTOR = "label[data-icon='plus'][for='add']";
	public final static String ACCESS_GOOGLEDRIVE_CSS_SELECTOR = "button#submit_approve_access";//"submit_approve_access";
	public final static String ACCESS_DROPBOX_CSS_SELECTOR =  "button[name='allow_access'][type='submit']";//"//button[@name='allow_access']";
	public final static String GOOGLEDRIVE_CV_CSS_SELECTOR = "input[type='radio'][value='google']";//"//input[@type='radio' and @value='google'or contains(@id='google')]";
	public final static String AUTOCREATE_CV_CSS_SELECTOR = "input[type='radio'][value='auto']";//"//input[@type='radio' and @value='auto'or contains(@id='auto')]";
	public final static String DROPBOX_CV_CSS_SELECTOR = "input[type='radio'][value='dropbox']";//"//input[@type='radio' and @value='dropbox'or contains(@id='dropbox')]";
	public final static String PASTE_CV_CSS_SELECTOR = "input[type='radio'][value='paste']";//"//input[@type='radio' and @value='paste'or contains(@id='paste')]";
	public final static String EMAIL_IT_LATER_CSS_SELECTOR = "input[type='radio'][value='email']";
	public final static String GOOGLEDRIVE_EMAIL_XPATH = "//input[@name='Email']";
	public final static String GOOGLEDRIVE_PASSWORD_XPATH = "//input[@name='Passwd']";
	public final static String GOOGLEDRIVE_SIGNIN_CSS_SELECTOR = "input#signIn";
	public final static String GOOGLEDRIVE_ALLOW_CSS_SELECTOR = "button#submit_approve_access";
	public final static String DROPBOX_EMAIL_XPATH = "//input[@name='login_email']";
	public final static String DROPBOX_PASSWORD_XPATH = "//input[@name='login_password']";
	public final static String DROPBOX_SIGNIN_CSS_SELECTOR = "button[type='submit']";
	public final static String DROPBOX_ALLOW_CSS_SELECTOR = "button[name='allow_access']";
	public final static String FACEBOOK_EMAIL_CSS_SELECTOR = "input#email";
	public final static String FACEBOOK_PASSWORD_CSS_SELECTOR = "input#pass";
	public final static String FACEBOOK_SIGNIN_CSS_SELECTOR = "input[type='submit']";
	public final static String INDEED_EMAIL_CSS_SELECTOR = "input#signin_email";
	public final static String INDEED_PASSWORD_CSS_SELECTOR = "input#signin_password";
	public final static String INDEED_SIGNIN_CSS_SELECTOR = "input[type='submit']";
	public final static String LINKEDIN_EMAIL_CSS_SELECTOR = "input[placeholder='Email']";
	public final static String LINKEDIN_PASSWORD_CSS_SELECTOR = "input[placeholder='Password']";
	public final static String LINKEDIN_SIGNIN_CSS_SELECTOR = "input[type='submit']";
	public final static String TEXTBOX_CHARACTERS_XPATH = "//input[@type='text']";
	public final static String TEXTBOX_NUMBERS_XPATH = "//input[@type = 'text' and contains(@placeholder,'Number') or "
	  		+ "contains(@placeholder,'Phone') or contains(@placeholder, 'Salary') or contains(@type, 'tel') or contains(@type, 'number') or contains(@type, 'num')]";
	public final static String TEXTBOX_EMAIL_XPATH = "//input[@type='email']";
	public final static String TEXTBOX_ZIP_XPATH = "//input[@type = 'text' and contains(@placeholder,'Zip')]";
	public final static String TEXTBOX_SOCIALNUMBER_XPATH = "//input[@type = 'password' or contains(@placeholder,'Security Number') "
			+ "or contains(@placeholder,'SSN')]";
	public final static String TEXTBOX_PASSWORD_XPATH = "//input[@type='password']";
	public final static String RADIO_BUTTON_XPATH = "//input[@type='radio' and @value='1']";
	public final static String CHECKBOX_XPATH = "//input[@type='checkbox' and @value='1']";
	public final static String DROPDOWN_XPATH = "select";
	public final static String TEXTAREA_XPATH = "//textarea";
	public final static String CREATE_MY_OWN_ACCOUNT_XPATH = "//label/span/span/label[text() = 'Create my own account']";
	public final static String URL_CONTAINS_SUBMIT = "/apply/form/submit";
	public final static String LABEL_XPATH_TEXTBOX_CHAR = "//input[@type='text']/../..//p";
	public final static String LABEL_XPATH_TEXTBOX_PHNUM = "//input[@type='number' or @type = 'tel' or @type = 'text']/../..//p[contains(text(),'phone') "
			+ "or contains(text(),'Phone') or contains(text(),'Cell') or contains(text,'Mobile')]";
	public final static String LABEL_XPATH_TEXTBOX_PASS = "//input[@type='password']/../..//p[contains(text(),'Password')or contains(text(),'Re-type new password')]";
	public final static String LABEL_XPATH_TEXTBOX_EMAIL = "//input[@type='email' or @type='text']/../..//p[contains(text(),'Email')]";
	public final static String LABEL_TEXTBOXES_XPATH = "//input[@type='text' or @type='email' or @type='number' or @type='password']/../..//p";
	public final static String LABEL_DROPDOWN_FROMDATE_XPATH = "//select[contains(@id,'year')]/../../../../..//p[contains(text(),'From') or contains(text(), 'Start')]";
	public final static String LABEL_DROPDOWN_TODATE_XPATH = "//select[contains(@id,'year')]/../../../../..//p[contains(text(),'To') or contains(text(),'End')]";
	public final static String ERROR_VALIDATION_XPATH = "//div[contains(@class,'error')]";
	public final static String PARENT_ELEMENT_XPATH = "../following-sibling::div//input";
	public final static String URL_HAS_SUBMIT_XPATH = "/apply/form/submit";
	public final static String AUTO_CREATE_ACCOUNT_XPATH = "//label[text() = 'Auto-generate my account and send me the login credentials']";
	public final static String JAVA_SCRIPT_XPATH = "/html/body/div[1]/div[3]/script";
	public final static String ATTRIBUTE_JAVASCRIPT = "innerHTML";
	//private static Logger Log = Logger.getLogger(Testcode.class.getName());
	
}
