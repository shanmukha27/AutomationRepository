package automationmoboltproduct;

import org.testng.TestNG;

public class Test {
	public static String path_name;
	public static String url_client;
	public static String signup_name;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getStackTrace());
			url_client = args[0];
			System.out.println("The entered URL is:"+url_client);
			path_name = args[1];
			System.out.println("The entered path of the input excel file is:"+"  "+path_name);
			signup_name = args[2];
			
			TestNG testng = new TestNG();
	        Class[] classes = new Class[]{BuildCVEmailitLater.class};
	        testng.setTestClasses(classes);
	        testng.run();
			

	}

}
