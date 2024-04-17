/*Suggested site: https://ishahomes.com    

Description:

Launch the application in the specified browser
Handle any popups and the live chat that may appear
Maximize the browser and Navigate to the “Completed Projects” page by clicking on “Completed Projects” menu link.
It displays all the projects completed by Isha’s homes company.
Scroll down and count total number of completed projects and print it to console.
Display the names of first five completed projects to the console
Scroll down and click on “Enquire Now” button
Verify if “Contact Info” text is displayed on the page
Read and display the email address for contact to console
Capture the Screenshot of the result

Close the Browser*/

package project;
import java.io .File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MiniProject {
	
	WebDriver driver;
 
    @Parameters("browser")
    @BeforeTest
    public void initialize(String browserName) throws InterruptedException {
    	if(browserName.equalsIgnoreCase("chrome")) { 
    		WebDriverManager.chromedriver().setup();
    		driver=new ChromeDriver();
    		System.out.println("Chrome is launched");
    		String URL="https://ishahomes.com";
    		driver.get(URL);
    	}
    	else if(browserName.equalsIgnoreCase("edge")) {
    		WebDriverManager.edgedriver().setup();
    		driver=new EdgeDriver();
    		System.out.println("Edge is Launched");
    		String URL="https://ishahomes.com";
    		driver.get(URL);
    	}
    } 
    
    @Test(priority=1)
	public void maximize() throws InterruptedException {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	}  

    
	@Test(priority=2)
	public void popups() throws InterruptedException {
	    WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement close=mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id("livchat_close")));
		close.click();
		WebElement chat=mywait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.close-indicator")));
		chat.click();
	}

	@Test(priority=3)
	public void completedProjects() throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement completedproj = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='navbar d-flex align-items-center']//*[contains(text(),'Completed Projects')]")));
		completedproj.click();
		WebElement chat=mywait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.close-indicator")));
		chat.click();
	}
	
	@Test(priority=4)
	public void totalcompletedProjects() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		List<WebElement> elements = driver.findElements(By.xpath("//*[@class='isha_block']//following-sibling::img")); 
		int visibleCount =0;
		for(WebElement ele : elements) {
			if(ele.isDisplayed()) {
				visibleCount++;
			}
		}
		int TotalCount=visibleCount;
		System.out.println("Total number of completed projects is "+TotalCount);
	}
	
	@Test(priority=5)
	public void five() throws IOException, InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement scroll=driver.findElement(By.cssSelector("a[href='#boosted-tab-0']"));		
		js.executeScript("arguments[0].scrollIntoView();",scroll);
		
		
		System.out.println("Names of first five completed projects");
		WebElement first=driver.findElement(By.linkText("Isha Avni"));
		String FirstName=first.getText();
		System.out.println("First project   --- "+FirstName);
		WebElement second=driver.findElement(By.linkText("Isha Ishtabhumi"));
		String SecondName=second.getText();
		System.out.println("Second project  --- "+SecondName);
		WebElement third=driver.findElement(By.linkText("Isha Lakefront"));
		String ThirdName=third.getText();
		System.out.println("Third project   --- "+ThirdName);
		WebElement fourth=driver.findElement(By.linkText("Isha Santhosham"));
		String FourthName=fourth.getText();
		System.out.println("Fourth project  --- "+FourthName);
		WebElement fifth=driver.findElement(By.linkText("Isha Anandham"));
		String FifthName=fifth.getText();
		System.out.println("Fifth project   --- "+FifthName);
	}
	
	@Test(priority=6)
	public void contact() throws InterruptedException, IOException {
		Thread.sleep(3000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				
		WebElement contact=driver.findElement(By.linkText("Contact Us"));
		contact.click();
		
		WebElement scroll=driver.findElement(By.xpath("//*[@id=\"main-wrap\"]/div/section[2]/div/div/div/div"));		
		js.executeScript("arguments[0].scrollIntoView();",scroll);	
		
	
		WebElement em=driver.findElement(By.linkText("marketing@ishahomes.com"));		
		String email=em.getText();
		System.out.println("email address for contact--- "+email);
	}	
	@Test(priority = 7)
	 public void ScreenShot() throws IOException, InterruptedException {
		Thread.sleep(3000);
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File("C:\\Users\\2318294\\eclipse-workspace\\MiniProjectTestNG\\Screenshots\\first.png");
		FileUtils.copyFile(src, trg);
	}	
	@Test(priority = 0)
	public void negative() {
		WebElement negative=driver.findElement(By.id("wpforms-21384-field_2"));
		negative.sendKeys("dfsjfbdjkfsdj");
	}	
	@AfterTest
	public void End() {
		driver.quit();
	}		
} 
