package com.indeed.automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationmoboltproduct.ExcelUtilities;
import automationmoboltproduct.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Libutils {
	public static WebDriver driver;
	static List<WebElement> textboxes;
	static String xpath;
	static String tagname, java_script;
	static String id, name, visible, label, category, options, validations,
			knockout, indices_val, req_indices_val,index, compulsory;
	static String length, length_json, email_string, textarea_string,
			questions_arr, maximum;
	static WebElement element, cvupload;
	static Map<String, Object> validatonMap;
	static Map<String, Object> questionMap;
	static Map<String, Object> knockout_ans;
	static Map<String, Object> required_ans;
	static ObjectMapper mapper;
	static int index_ctr;
	static int knock_answer, required_answer;

	@SuppressWarnings("unchecked")
	public static void extractJavaScript(WebDriver driver, String id){
    	By by = By.xpath(id);
		WebElement script = driver.findElement(by);
		java_script = script.getAttribute(Repository.ATTRIBUTE_JAVASCRIPT);
		System.out.println("The java script for this page is :" +java_script);
    }
	@SuppressWarnings("unused")
	// Prepare Questions from the JSON File
	public static void generateQuestions(String questions_arr)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> elementsMap = new HashMap<String, Object>(); 
		mapper = new ObjectMapper();
		elementsMap = mapper.readValue(questions_arr,
				new TypeReference<HashMap<String, Object>>() {
				});
		@SuppressWarnings("unchecked")
		List<Object> questions = (List<Object>) elementsMap.get("questions");
		// Get all the list of questions
		System.out.println(questions);
		
		// test
		System.out.println("No. of questions in the page are : " +questions.size());
		
		loop1 : for (Object question : questions) { 
			// Navigate into each and every question and performing actions
			
			System.out.println(question);
			@SuppressWarnings("unchecked")
			Map<String, Object> questionMap = (Map<String, Object>) question;
			
			System.out.println("Trying to answer for the question label :" +questionMap.get("label"));
			
			id = questionMap.get("id").toString();
			// Extract id from JSON
			
			visible = questionMap.get("visible").toString();
			// Extract visible

			label = questionMap.get("label").toString();
			// Extract label from JSON

			category = questionMap.get("category").toString();
			// Extract Category/type of webelement from JSON

			// if (category.equals("label"))
			validations = (String) questionMap.get("validations");
			System.out.println(questionMap.get("options"));
			// Extracting options of a question
			options = questionMap.get("options").toString();
			System.out.println(category);
			System.out.println("\n The id of the question: " + label + " is "
					+ id + "and category is:" + category);
			// Extract validations from JSON for each and every question
			if (visible.contains("0") || visible.contains("-1")) {
				continue;
			}
			getVaidationsFrmQuestions();
			getLengthFrmValidations();
			getOptionsFrmQuestions();
			selectWebelement();
		}
	}
	@SuppressWarnings("unchecked")
	public static void getVaidationsFrmQuestions() throws JsonParseException,
			JsonMappingException, IOException {
		validatonMap = new HashMap<String, Object>();
		validatonMap = mapper.readValue(validations,
				new TypeReference<HashMap<String, Object>>() {
				});
		// Extract Knockout from the validations
		// Extract compulsory from validations
		compulsory = validatonMap.get("compulsory").toString();
		System.out.println(compulsory);
		//Extracting knockout into an object
		Object knockout_val = validatonMap.get("knockout"); 
		Object required_val = validatonMap.get("required");
		@SuppressWarnings("unchecked")
		Map<String, Object> knockout_ans = (Map<String, Object>) knockout_val;
		Map<String, Object> required_ans = (Map<String, Object>) required_val;
		//Extracting indices value into a string
		if ((knockout_ans != null && !knockout_ans.isEmpty())
				|| (required_ans != null && !required_ans.isEmpty())) { 
			try {
				//Indices value extracted
				indices_val = knockout_ans.get("indices").toString()
						.replaceAll("\\[", "").replaceAll("\\]", "").toString();
				System.out.println(indices_val);
				knock_answer = Integer.parseInt(indices_val);
				knock_answer--;
				if (knock_answer <= 0) {
					//If knock_answer is '1' then it will be incremented twice
					knock_answer = knock_answer + 2;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				req_indices_val = required_ans.get("indices").toString()
						.replaceAll("\\[", "").replaceAll("\\]", "").toString();
				required_answer = Integer.parseInt(req_indices_val);
				System.out.println("The value of required is:"
						+ required_answer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void xpathForInputtag(String id, String category,
			int knockout_ans, int required_ans) {
		try {
			if (category.contains("text") || category.contains("tel")
					|| category.equalsIgnoreCase("num")
					|| category.equalsIgnoreCase("number")
					|| category.contains("Email")
					|| category.contains("password")
					|| category.contains("radio") || category.contains("check")) {
				String tagname = "input";
				System.out.println("The tagname has been generated as: "
						+ tagname);
				if (category.equalsIgnoreCase("radio")
						|| category.equalsIgnoreCase("check")) {
					if (knockout_ans != 0) {
						xpath = "//" + tagname + "[contains(@id,'" + id
								+ "') and (@value='" + knock_answer + "')]";
						System.out.println(xpath);
					} else if (required_ans != 0) {
						xpath = "//" + tagname + "[contains(@id,'" + id
								+ "') and (@value='" + required_answer + "')]";
						System.out.println(xpath);
					}
					// }
					else if (required_ans == 0 || knockout_ans == 0) {
						xpath = "//" + tagname + "[contains(@id,'" + id
								+ "') and (@value='" + index + "')]";
						System.out.println(xpath);
					}
				}// to rem
				else if (category.contains("text") || category.contains("tel")
						|| category.contains("num")
						|| category.contains("number")
						|| category.contains("Email")) {
					xpath = "//" + tagname + "[contains(@id,'" + id + "')]";
					System.out.println(xpath);
				}
			}
		}//
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void xpathForOthertags(String id, String category,
			int knockout_ans, int required_ans) {
		try {
			if (category.contains("select") || category.contains("textarea")) {
				tagname = category;
				System.out.println("The tagname has been generated as: "
						+ tagname);
				xpath = "//" + tagname + "[contains(@id,'" + id + "')]";
				System.out.println(xpath);
			} else if (category.equals("label")) {
				xpath = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void xpathGenerator(String id, String category,
			int knockout_ans, int required_ans) {
		if (category.contains("text") || category.equalsIgnoreCase("tel")
				|| category.equalsIgnoreCase("num")
				|| category.equalsIgnoreCase("number")
				|| category.equalsIgnoreCase("Email")
				|| category.equals("password")
				|| category.equalsIgnoreCase("radio")
				|| category.equalsIgnoreCase("check")) {
			String tagname = "input";
			System.out.println("The tagname has been generated as: " + tagname);
			if (category.equalsIgnoreCase("radio")
					|| category.equalsIgnoreCase("check")) {
				if (knockout_ans != 0) {
					xpath = "//" + tagname + "[contains(@id,'" + id
							+ "') and (@value='" + knock_answer + "')]";
					System.out.println(xpath);
				} else if (required_ans != 0) {
					xpath = "//" + tagname + "[contains(@id,'" + id
							+ "') and (@value='" + required_answer + "')]";
					System.out.println(xpath);
				}
				// }
				else if (required_ans == 0 || knockout_ans == 0) {
					xpath = "//" + tagname + "[contains(@id,'" + id
							+ "') and (@value='" + index + "')]";
					System.out.println(xpath);
				}
			}// to rem
		} else {
			tagname = category;
			System.out.println("The tagname has been generated as: " + tagname);
			xpath = "//" + tagname + "[contains(@id,'" + id + "')]";
			System.out.println(xpath);
		}
	}
	public static void getLengthFrmValidations() throws JsonParseException,
			JsonMappingException, IOException {
		if (category.contains("text") || category.equalsIgnoreCase("tel")
				|| category.equalsIgnoreCase("num")
				|| category.equalsIgnoreCase("number")
				|| category.equalsIgnoreCase("Email")
				|| category.equalsIgnoreCase("Password")) {
			//Extracting length from validations
			System.out.println(validatonMap.get("length")); 
			String length = validatonMap.get("length").toString();
			String max[] = length.split("max=");
			String max_val[] = max[1].split("}]");
			if (max_val[0] != null && !max_val[0].isEmpty()) {
				maximum = max_val[0];
				/*length_json = "{" + "\"length\":" + length + "}";
				System.out.println(length_json);
				// Mapping length into a Map
				Map<String, Object> inner_ln_map = new HashMap<String, Object>();
				ObjectMapper map = new ObjectMapper();
				inner_ln_map = map.readValue(length_json,
						new TypeReference<HashMap<String, Object>>() {
						});
				// Getting all the options into a List
				@SuppressWarnings("unchecked")
				List<Object> length_v = (List<Object>) inner_ln_map
						.get("length");
				for (Object lng : length_v) {
					@SuppressWarnings("unchecked")
					Map<String, Object> lengthMap = (Map<String, Object>) lng;
					try {
						System.out.println(lengthMap.get("max"));
						max = (int)lengthMap.get("max"); //tostring()
					} catch (Exception e) {
						e.printStackTrace();
					}
				}*/
				//"\"First Name\""
			} else if (label.contains("\"Number\"")
					|| label.contains("\"Primary Phone\"") || label.contains("\"Mobile\"") || label.equalsIgnoreCase("\"Cell\"")) {
				maximum = "10";
			} else if (label.equalsIgnoreCase("\"Zip\"")
					|| label.equalsIgnoreCase("\"Postal Code\"")
					|| label.equalsIgnoreCase("\"Area Code\"")
					|| label.contains("\"Code\"")) {
				maximum = "9";
			} else if (label.equalsIgnoreCase("\"SSN\"")
					|| label.equalsIgnoreCase("\"Social Security Number\"")) {
				maximum = "10";
			}
		}
	}
	public static void getOptionsFrmQuestions() throws JsonParseException,
			JsonMappingException, IOException {
		String options_json = "{" + "\"options\":" + options + "}";
		System.out.println(options_json);
		// Mapping options into a Map
		Map<String, Object> inner_opt_map = new HashMap<String, Object>();
		ObjectMapper map = new ObjectMapper();
		inner_opt_map = map.readValue(options_json,
				new TypeReference<HashMap<String, Object>>() {
				});
		// Getting all the options into a List
		@SuppressWarnings("unchecked")
		List<Object> option_s = (List<Object>) inner_opt_map.get("options");
		loop2 : for (Object option : option_s) {
			@SuppressWarnings("unchecked")
			Map<String, Object> optionMap = (Map<String, Object>) option;
			if(optionMap!=null)
			try {
				if(index!=null)
				System.out.println(optionMap.get("index"));
				index = optionMap.get("index").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void selectWebelement() {
		System.out.println(category);
		xpathForInputtag(id, category, knock_answer, required_answer);
		xpathForOthertags(id, category, knock_answer, required_answer);
		selectAWebElement(driver, category, xpath, "google");
		System.out.println("Completed writing the answer for category question & its xapth :" +category +xpath);
	}
	public static void selectAWebElement(WebDriver driver, String category,
			String xpath, String attachment_type) {
		if (xpath != null) {
			try {
				By by = By.xpath(xpath);
				element = driver.findElement(by);
			} catch (Exception e) {
				e.printStackTrace();
			}
			switch (category) {
				case "text" :
					generateRandomString();
					break;
				case "tel" :
				case "num" :
					generateRandomNumbers(maximum);
					break;
				case "email" :
					verifyAndTypeEmailFrmExcel();
					break;
				case "password" :
					// Selects the password field and inputs
					break;
				case "radio" :
				case "check" :
					verifyAndClick();
					break;
				case "textarea" :
					verifyAndTypeTextArea();
					break;
				case "select" :
					verifyAndSelect();
					break;
				case "label" :
					break;
				case "file" :
					name = questionMap.get("name").toString();
					uploadAttachments(attachment_type);
					break;
			}
		}
	}
	public static void generateRandomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		char c = chars[random.nextInt(chars.length)];
		sb.append(c);
		int i = 0;
		String sample = ExcelUtilities.sample.get(i);
		String output = sample + sb.toString();
		System.out.println(output);
		element.sendKeys(output);
	}
	public static void generateRandomNumbers(String count) {
		int count_value = Integer.parseInt(count);
		String randomNumbers = RandomStringUtils.randomNumeric(count_value);
		String number = 9 + randomNumbers;
		System.out.println(number);
		element.sendKeys(number);
	}
	public static void verifyAndTypeEmailFrmExcel() {
		email_string = ExcelUtilities.email_address.get(0).toString();
		element.sendKeys(email_string);
	}
	public static void verifyAndClick() {
		// new Select(element).selectByValue(index); // I have used dropdown
		// syntax to select an answer {For verification purpose only}
		element.click();
	}
	public static void verifyAndSelect() {
		String v1 = Integer.toString(knock_answer);
		String v2 = Integer.toString(required_answer);
		System.out.println(v1);
		System.out.println(v2);
		if (v1 != null) {
			// String value = ""+knock_answer;
			String value = Integer.toString(knock_answer);
			System.out.println(value);
			new Select(element).selectByValue(v1);
		} else if (v2 != null) {
			// String value = ""+required_answer;
			String value1 = Integer.toString(required_answer);
			System.out.println(value1);
			new Select(element).selectByValue(v2);
		} else if (knockout_ans == null && required_ans == null) {
			new Select(element).selectByValue(index);
		}
	}
	public static void verifyAndTypeTextArea() {
		textarea_string = ExcelUtilities.sample.get(0).toString();
		element.sendKeys(textarea_string);
	}
	public static void uploadAttachments(String attachment_type) {
		if (name.contains("resume") || name.contains("additional")) {
			xpath = "//input[contains(@id,'" + attachment_type + "')]";
			System.out.println(xpath);
			By by = By.xpath(xpath);
			cvupload = driver.findElement(by);
			if (attachment_type == "google") {
				cvupload.click();
				// Pending Work
			}
		}
	}
	public static void verifyAndClick(WebDriver driver, String id) {
		try {
			By by = By.cssSelector(id);
			WebElement element = driver.findElement(by);
			element.click();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void getJSONFrmJS() throws JsonParseException, JsonMappingException, IOException{
		String JSON2 = "", input;
		String JSON = "";
		char[] json2_char;
		char[] json_final_char;
		String fileData = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/script")).getAttribute(
				"innerHTML");
		input = fileData;
		int matches_val = StringUtils.countMatches(input, "\"questions\":");
		System.out.println("The number of ques are: "+matches_val);
		for(int i =0; i<matches_val; i++){
		//System.out.println(fileData);
		JSON2 = input.split("\"questions\":")[1].split("\"answers\":")[0];
		System.out.println(JSON2);
		System.out.println(input);
		input = input.replaceFirst("\"questions\":", "");
		System.out.println(input);
		// removing '[' from the starting index of the string 
		 json2_char = JSON2.trim().toCharArray();
		 System.out.println(json2_char[0]);
		 if(json2_char[0] == '[')
		 json2_char[0] = ' ';
		 JSON2 = String.valueOf(json2_char);
		// removing ']' from the ending index of the string 
		 json2_char  = JSON2.toCharArray();
		 int count = JSON2.length() - 2;
		 System.out.println(json2_char[count]);
		 if(json2_char[count] == ']')
			 json2_char[count] = ' ';
		 JSON2 = String.valueOf(json2_char).trim();
		System.out.println("REMOVED FIRST INDEX VALUE :" +JSON2);
		// Adding all the individual questions (json2 here) to JSON string
		JSON = JSON + JSON2;
		}
		System.out.println("output: \n" +JSON);
		json_final_char = JSON.toCharArray();
		int count = JSON.length() - 1;
		System.out.println(json_final_char[count]);
		if (json_final_char[count] == ',') {
			json_final_char[count] = ' ';
			System.out.println(JSON);
		}
		JSON = String.valueOf(json_final_char).trim();
		String final_json = "{"+"\"questions\":"+"["+JSON+"]"+"}"; 
		System.out.println("The Final Question Set is:"+final_json);
		generateQuestions(final_json);
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
}
