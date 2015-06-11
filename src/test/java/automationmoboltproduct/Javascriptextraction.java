package automationmoboltproduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Javascriptextraction {
	public WebDriver driver;
  @Test
  public void f() throws Exception {
	  driver.findElement(By.partialLinkText("Opening Coordinator")).click();
	  driver.findElement(By.id("apply")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.cssSelector("a[data-social='manual']")).click();
	  Thread.sleep(3000);
	  String str = driver.findElement(By.xpath("/html/body/div[1]/div[3]/script")).getAttribute("innerHTML");
	  System.out.println(str);
	  driver.close();
  }
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.get("https://admin:AxkN!BjZx@cecentertainment-test.mobolt.com/en/job/list");
	  driver.manage().window().maximize();
  }

  @AfterTest
  public void afterTest() {
  }

}
