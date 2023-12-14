import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class extent 
{
	WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;
	
	@BeforeTest
	public void launchBrowser()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		report = new ExtentReports("..\\ExtentReport\\extent_report\\report.html", true);
		test=report.startTest("extent report demo");
		driver.get("https://www.google.com/");
	}

	@AfterTest
	public void teardown()
	{
		driver.close();
		report.endTest(test);
		report.flush();
	}
	@Test(priority = 1)
	public void validatetitle()
	{
		     test.log(LogStatus.INFO, "Test case validate title is started");
		String title=driver.getTitle();
		System.out.println("title is " + title);
		     test.log(LogStatus.PASS , title);
	}
	
	@Test(priority = 2)
	public void searching()
	{
		     test.log(LogStatus.INFO, "Test case searching is started");
		WebElement search=driver.findElement(By.id("APjFqb"));
		search.click();
		     test.log(LogStatus.WARNING, "warning status display");
		search.sendKeys("learning extent reports");
		String title=driver.getTitle();
		System.out.println("title is " + title);
		     test.log(LogStatus.PASS , title);
		
		
	}
	

	public static String capturescreenshot(WebDriver driver)
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		 File srcFile= ts.getScreenshotAs(OutputType.FILE);
		 File destFile = new File(".\\screenshots\\"+System.currentTimeMillis()+".png");
		 String path=destFile.getAbsolutePath();
		 try {
		 Files.copy(srcFile, destFile);
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 return path;
	}
	
	
	@Test(priority = 4)
	public void failtest()
	{
		     test.log(LogStatus.FAIL,test.addScreenCapture(capturescreenshot(driver))+ "Test case fail");
	}
	
	@Test(priority = 3)
	public void skiptest()
	{
		     test.log(LogStatus.SKIP, "Test case skip");
	}
	
		     
		     
	
	
}
