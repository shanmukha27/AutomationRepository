package automationmoboltproduct;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static String path_name;
	public static String url_client;
	public static String signup_name;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		url_client = args[0];
		System.out.println("The entered URL is:"+url_client);
		path_name = args[1];
		System.out.println("The entered path of the input excel file is:"+"  "+path_name);
		signup_name = args[2];
		Result result = JUnitCore.runClasses(BuildCV.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());

	}

}
