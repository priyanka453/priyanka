package Casestudy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Casestudy.CaseStudy.Connect;

public class CaseStudy_1 {
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;

	@BeforeTest
	public void startReport()
	{
		driver=Connect.openBrowser("Chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Reports.html", true);
		extent.addSystemInfo("Host Name", "TestMe");
		extent.addSystemInfo("Environment", "Selenium Test");
		extent.addSystemInfo("User Name", "Priyanka");
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/PassedScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
		@Test(priority=1)
		public void signup()
		{
			logger=extent.startTest("SignUp passTest");
			driver.findElement(By.linkText("SignUp")).click();
			driver.findElement(By.name("userName")).sendKeys("priyanka123");
			driver.findElement(By.name("firstName")).sendKeys("Priyanka");
			driver.findElement(By.name("lastName")).sendKeys("Lodhe");
			driver.findElement(By.name("password")).sendKeys("Priyanka123");
			driver.findElement(By.name("confirmPassword")).sendKeys("Priyanka123");
			driver.findElement(By.xpath("//input[@name='gender' and @value='Female']")).click();
			driver.findElement(By.name("emailAddress")).sendKeys("priyanka123@gmail.com");
			driver.findElement(By.name("mobileNumber")).sendKeys("9999922222");
			driver.findElement(By.xpath("//*[@id=\"dob\"]")).sendKeys("08/04/2000");
			driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("CDC2,block2,Perangulatur");
			Select select = new Select(driver.findElement(By.name("securityQuestion")));
			select.selectByIndex(1);
			driver.findElement(By.name("answer")).sendKeys("Black");
			driver.findElement(By.name("Submit")).click();
			Assert.assertEquals(driver.getTitle(), "Login");
		}
       @Test(priority=2)
       public void signin()
       {
    	logger = extent.startTest("SignIn passTest");
   		//driver.findElement(By.linkText("SignIn")).click();
		driver.findElement(By.name("userName")).sendKeys("lalitha");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123");
		driver.findElement(By.name("Login")).click();
		Assert.assertEquals(driver.getTitle(), "Home");
       }
       @Test(priority=3)
       public void testcart()
       {
    	   logger=extent.startTest("Cart passTest");
    	   Actions action=new Actions(driver);
    	   action.moveToElement(driver.findElement(By.xpath("/html/body/header/div[2]/div/div/ul/li[2]/a/span"))).build().perform();
    	   driver.findElement(By.linkText("Electronics")).click();
    	   WebDriverWait wait=new WebDriverWait(driver,100);
    	   wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Head Phone"))).click();
    	   driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();//Add to cart
    	   driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();//cart1 link
    	   Assert.assertEquals(driver.getTitle(), "View Cart");   
    	   driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();//checkout button
    	   Assert.assertEquals(driver.getTitle(), "Cart Checkout");
    	   
    	    
       }
       @Test(priority=4)
       public void testPayment()
       {
    	   logger=extent.startTest("Payment Gateway passTest");
    	  //Assert.assertEquals(driver.getTitle(), "View Cart"); 
    	  driver.findElement(By.xpath("//input[@type='submit' and @value='Proceed to Pay']")).click();
    	  Assert.assertEquals(driver.getTitle(), "Redirecting to Payment Gateway");
    	  WebDriverWait wait=new WebDriverWait(driver,100);
    	  wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/h3"),"Net Banking"));
    	  driver.findElement(By.xpath("//*[@id=\"swit\"]/div[1]/div/label")).click();
    	  driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();
    	  driver.findElement(By.name("username")).sendKeys("123456");
    	  driver.findElement(By.name("password")).sendKeys("Pass@456");
    	  driver.findElement(By.xpath("//input[@type='submit' and @value='LOGIN']")).click();
    	  //driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
    	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input")).sendKeys("Trans@456");
    	  //*[@id="horizontalTab"]/div[2]/div/div/div/div/form/input
    	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[2]/input")).click();//paynow
    	  //Assert.assertEquals(driver.getTitle(), "Payment Gateway");
    	  Assert.assertEquals(driver.getTitle(), "Order Details");
    	 driver.findElement(By.xpath("/html/body/header/div/div/ul/b/a[2]")).click();
       }
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getThrowable());
			String screenshotPath = CaseStudy_1.getScreenshot(driver, result.getName());
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
	}	
		extent.endTest(logger);
	}

	@AfterTest
	public void endReport() {
		extent.close();
		driver.close();
	}

}
