package TestCases;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test1 {
	
	
	WebDriver driver;
	String url = "https://www.w3schools.com/tags/tag_select.asp";
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "G:\\INFOSYS Lectures & Codes\\Stream Training\\JAR Files\\CD.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@AfterClass 
	public void tearDown() {
		driver.close();
		driver.quit();
	} 
	
	@Test 
	public void test() throws InterruptedException {
		String actualTitle = driver.getTitle();
		System.out.println("The title of main window is: ----->" + actualTitle);
		driver.findElement(By.linkText("Try it Yourself »")).click();
		
		Thread.sleep(2000);
		
		String parentWin = driver.getWindowHandle();
		Set<String>windows = driver.getWindowHandles();
		
		for (String win2 : windows) {
			driver.switchTo().window(win2);
			String title = driver.getTitle();
			if(title.equals("W3Schools Tryit Editor"));
			break;
		}
		
		Thread.sleep(2000);
		
		driver.switchTo().frame("iframeResult");
		
		WebElement cars = driver.findElement(By.id("cars"));
		Select s1 = new Select(cars);
		WebElement listOfAttri = s1.getFirstSelectedOption();
		System.out.println("The default value is: " + listOfAttri);
		
		
		List<WebElement>options = s1.getOptions();
		for (WebElement v1 : options) {
			System.out.println(v1.getAttribute("value"));
		}
		
		driver.switchTo().window(parentWin);
		driver.switchTo().defaultContent();
		
		String title = driver.getTitle();
		Assert.assertEquals(actualTitle, title);
		
	}
}
