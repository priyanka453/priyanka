package Casestudy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Casestudy.CaseStudy.Connect;




public class TestMeLogin 
{
	WebDriver driver;
	@BeforeTest
	public void beforeTest()
	{
		driver=Connect.openBrowser("Ie");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
	}
	@Test
	public void Login()
	{
		driver.findElement(By.linkText("SignIn")).click();
		driver.findElement(By.name("userName")).sendKeys("lalitha");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Password123");
		driver.findElement(By.name("Login")).click();
		Assert.assertEquals(driver.getTitle(), "Home");
		
	}
	@AfterTest
	public void afterTest()
	{
		driver.close();
	}
	
}
