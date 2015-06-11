package com.indeed.automation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class TestJSONRead {
	
	public static void main(String[] args) throws IOException {
		
		String JSON2 = "", input;
		String JSON = "";
		char[] json2_char;
		char[] json_final_char;
		
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("/Users/santoshkumar/Desktop/testjsonfile.json");
		String fileData = IOUtils.toString(fis);
		input = fileData;
		int matches_val = StringUtils.countMatches(input, "\"questions\":");
		System.out.println("The number of ques are: "+matches_val);
		for(int i =0; i<matches_val; i++){
		//System.out.println(fileData);
		JSON2 = input.split("\"questions\":")[1].split("\"answers\":")[0];
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(JSON2);
		System.out.println(input);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
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
		
		
	}
}
