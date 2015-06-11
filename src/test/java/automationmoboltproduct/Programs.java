package automationmoboltproduct;

import org.testng.annotations.Test;

public class Programs {
  @Test (enabled = true) // print fibonacii series
  public void fibonacii() {
	  int a = 0; 
	  int b = 1;
	for (int i = 0; i < 10; i++) {
		System.out.println(a);
		a = a + b;
		b = a - b;
	}
	System.out.println("*************************");
  }
  @Test (enabled = true)
  public void reversestring(){
	  String original = "Welcome", reverse = "";
	  int length = original.length();
	  for (int i = original.length()-1; i >=0; i--) {
		reverse = reverse + original.charAt(i);
	}
	  System.out.println("The reversed string is:"+reverse);
	  System.out.println("*************************");
  }
  @Test (enabled = true)
  public void revstringbuffer(){
	  StringBuffer a = new StringBuffer("Santosh Kumar");
	  System.out.println(a.reverse());
	  System.out.println("*************************");
  }
  @Test (enabled = true)
  public void swap(){
	  int x =3;
	  int y =4;
	  x = x + y;
	  y = x - y;
	  x = x - y;
	  System.out.println("x:"+x+"y:"+y);
	  System.out.println("*************************");
  }
  @Test (enabled = true)
  public void sumoftennum(){
	  int n, sum=0;
	  for (n =1; n<=10; n++){
		  sum+=n;
	  }
	  System.out.println("Sum of first 10 numbers is:"+sum);
	  System.out.println("*************************");
  }
  @Test (enabled = true)
  public void sumofsquaresoftennum(){
	  int n, sum=0;
	  for(n=1; n<=10; n++){
		  sum+=n*n;
	  }
	  System.out.println("Sum of squares is:"+sum);
	  System.out.println("*************************");
  }
  @Test (enabled = true)
  public void sumofcubesoftennum(){
	  int n, sum=0;
	  for(n=1; n<=10; n++){
		  sum+=n*n*n;
	  }
	  System.out.println("Sum of cubes is:"+sum);
	  System.out.println("*************************");
  }
  @Test (enabled = true)
  public void factorial(){
	  int n = 5, i;
	 int fact = 1;
	  for(i = n; i >= 1 ; i--){
		  fact*=i;
	  }
	  System.out.println("Factorial is:"+fact);
	  System.out.println("*************************");
  }
  @Test (enabled = true)
  public void reversenum(){
	  int num = 235;
	  int reverse = 0;
	  int remainder;
	  
	  while(num!=0){
		  remainder = num%10;
		  reverse = reverse + remainder;
		  num = num/10;
	  }
	  if(reverse==num){
		  System.out.println("The given number is a palindrome");
	  }
	  else{
		  System.out.println("The given number is not a palindrome");
	  }
  }
  @Test (enabled=true)
  public void stringseperation(){
	  String str = "me1ss2a3ge4";
	  int stringlength = str.length();
	  String number = "";
	  String alpha = "";
	  
	  for (int i = 0; i < stringlength; i++) {
		System.out.print(str.charAt(i));
		char a = str.charAt(i);
		if (Character.isDigit(a)) {
		number = number + a;
		}
		else if (Character.isAlphabetic(a)) {
		alpha = alpha + a;	
		}
	}
	  
	  System.out.println("the num is:"+number);
	  System.out.println("the characters are:"+alpha);
	  System.out.println("*************************");
  }
  @Test (enabled = true)
  public void multiplicationtable(){
	  int num = 5;
	  System.out.println("The multiplication table is:");
	  for (int i = 0; i <= 10; i++) {
		System.out.println(num+""+"x"+i+ "=" +num*i);
	}
  }
  
}
