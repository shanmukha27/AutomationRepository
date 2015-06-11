package automationmoboltproduct;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtilities extends Repository{
	public static ArrayList<String> sample;
	public static ArrayList<String> num;
	public static ArrayList<String> email_address;
	public static ArrayList<String> mobile_num;
	public static ArrayList<String> data_trial_error;
	public static String str;
	public static int k;
	public int ca;

	public static void getInputDataFromExcel(String file_path, int sheet) throws Exception{
		int j =0;
		FileInputStream fi = new FileInputStream(file_path);
		 Workbook wb = Workbook.getWorkbook(fi);
		 Sheet ws = wb.getSheet(sheet);
		 sample = new ArrayList<String>();
		 num = new ArrayList<String>();
		 email_address = new ArrayList<String>();
		 mobile_num = new ArrayList<String>();
		 
		 try{
		 for (int columns = 0; columns < ws.getColumns(); columns++) {    //columns=1
		 for (int rows = 0; rows < ws.getRows(); rows++) {	
			 str = ws.getCell(columns, rows).getContents();
			 System.out.println("The input is:"+str);
			 System.out.println(str.length());
			 
			 if ((str.contains("@")==true)&&(str.contains(".")==true)) { //Getting all the Email Address into Email Address Array
				System.out.println("Given input is an email address:"+str);
				for (int k =0; k <= sample.size();k++) {
					email_address.add(str);
					System.out.println("The array value is:"+email_address.get(k));
					break;
					 }
			}
			 else if (str.matches("[0-9]+")==true&&str.length()!=10) { //Getting all the numbers into Number Array
				System.out.println("Given input is a number:"+str);
				 for (int k =0; k <= sample.size();k++) {
					 num.add(str);
				System.out.println("The array value is:"+num.get(k));
				break;
				 }	
			}	
			 else if ((str.matches("[0-9]+")== true)&&str.length()==10){ //Getting mobile numbers into Mobile Number Array
				 System.out.println("Given input is a mobile number:"+str);
				 for (int k =0; k <= sample.size();k++) {
					 mobile_num.add(str);
				 System.out.println("The array value is:"+mobile_num.get(k));
				 break;
				 }
			 }
			 else if (str.matches("[a-zA-Z]+")==true) { //Getting all the strings into Characters Array
					System.out.println("Given input is a String:"+str);
				 for (int k =0; k <= sample.size();k++) {
					 sample.add(str);
				System.out.println("The array value is:"+sample.get(k));
				break;
				 }				
			}
			 else if ((str.matches("[a-zA-Z0-9]+")==true)) {
				System.out.println("Given input consists of characters and numbers"+str);
			}
			 else if (str.matches("[a-zA-Z0-9]*")!=true) {
				System.out.println("Given input consists of characters, numbers and special characters:"+str);
			}
		}
			 }
		 for (int k =0; k <= sample.size(); k++) {	 //Display Text Array
			 try{
			System.out.println("The Text data array is:"+sample.get(k));
			 }
			 catch(Exception e){
				 System.out.println("End of text array");
			 }
		}
		 for (int k = 0; k <= num.size(); k++) { //Display Numbers array
			try{
				System.out.println("The Number data array is:"+num.get(k));
			}
			catch(Exception e){
				System.out.println("End of numbers array");
			}
		}
		 for (int k = 0; k < mobile_num.size(); k++) { //Display Phone Numbers array
			 try{
					System.out.println("The Phone number array is:"+mobile_num.get(k));
				}
				catch(Exception e){
					System.out.println("End of mobile num");
				}
		}
		 for (int k = 0; k <= email_address.size(); k++) { //Display Email Address array
				try{
					System.out.println("The Email Address array is:"+email_address.get(k));
				}
				catch(Exception e){
					System.out.println("End of email address");
				}
			}
		 for (int k = 0; k < data_trial_error.size(); k++) {
			 try{
					System.out.println("The Trial and Error Data array is:"+data_trial_error.get(k));
				}
				catch(Exception e){
					System.out.println("End of Trial and Error data array");
				}
		}
		 }
		 catch(Exception e){
			 System.out.println("End of data");
		 }
		 
		 data_trial_error = new ArrayList<String>();
		 data_trial_error.addAll(sample);
		 data_trial_error.addAll(num);
		 data_trial_error.addAll(mobile_num);
		 data_trial_error.addAll(email_address);
		 
		 for(int a = 0; a < data_trial_error.size(); a++){
			 try{
					System.out.println("The Trial and Error array is:"+data_trial_error.get(a));
				}
				catch(Exception e){
					System.out.println("End of email address");
				}
		 }
		 }
	
}
