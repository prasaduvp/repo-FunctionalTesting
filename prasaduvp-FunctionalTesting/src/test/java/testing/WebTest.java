package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebTest {

	public static void main(String[] args) throws IOException, InterruptedException{

		//Intialize ChromeDriver
		WebDriver driver = new ChromeDriver();

		//Maximizing the diver window
		driver.manage().window().maximize();

		//Navigate to the url
		driver.get("https://demo.dealsdray.com/");

		//Login Credentials and submit
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Click on the order
		driver.findElement(By.xpath("//span[text()='Order']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//Click on the order
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='sidenavHoverShow MuiBox-root css-i9zxpg'])[2]"))).click();
		//click on the bulk orders
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add Bulk Orders']"))).click();
		//import the file from the device to the script
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mui-7']"))).sendKeys("C:\\Users\\vara prasad\\Downloads\\demo-data.xlsx");
		//Click on import 
		driver.findElement(By.xpath("//button[text()='Import']")).click();
		//Click on the validate button to check whether the data is sucessfully imported or not
		driver.findElement(By.xpath("//button[text()='Validate Data']")).click();
		Thread.sleep(2000);
		//This is way switch into alert box and clicks ok but it's actally working so unable to take screenshot at this time
		driver.switchTo().alert().accept();

		//This is the code to take screenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./src/test/Prexo_Final_output_screenshot.png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Close the browser
		driver.quit();

	}
}
