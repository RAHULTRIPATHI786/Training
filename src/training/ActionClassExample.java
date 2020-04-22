package training;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionClassExample {

	public static void main(String[] args) throws InterruptedException, IOException {

		// Example1: DragAndDrop operation
		dragandDrop();

		// Example 2: clickandhold opeartion

		clickandhold();

		// Example :3 Mouse hover action
		mousehoveraction();
	}

	public static void clickandhold() throws InterruptedException {

		// setting path of chromedriver
		System.setProperty("webdriver.chrome.driver", "driverslib//chromedriver.exe");

		// creating instance of driver
		WebDriver driver = new ChromeDriver();

		// configure implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// launch application
		driver.get("https://jqueryui.com/droppable/");

		// switch to frame

		driver.switchTo().frame(0);

		// element to be dragged
		WebElement elm1 = driver.findElement(By.id("draggable"));

		// Target element
		WebElement elm2 = driver.findElement(By.id("droppable"));

		// new object of Actions Class

		Actions action = new Actions(driver);

		// Click and hold operation

		action.clickAndHold(elm1).moveToElement(elm2).release().build().perform();

		// little wait time to see the operation happening
		Thread.sleep(1000);

		// quit the browser
		driver.quit();
	}

	public static void dragandDrop() throws InterruptedException {
		// setting path of chromedriver
		System.setProperty("webdriver.chrome.driver", "driverslib//chromedriver.exe");

		// creating instance of driver
		WebDriver driver = new ChromeDriver();

		// configure implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// launch application
		driver.get("https://jqueryui.com/droppable/");

		// switch to frame

		driver.switchTo().frame(0);

		// element to be dragged
		WebElement elm1 = driver.findElement(By.id("draggable"));

		// Target element
		WebElement elm2 = driver.findElement(By.id("droppable"));

		// new object of Actions Class

		Actions action = new Actions(driver);

		// drag and drop operation

		action.dragAndDrop(elm1, elm2).build().perform();

		// little wait time to see the operation happening

		Thread.sleep(1000);

		// quit the browser
		driver.quit();

	}

	public static void mousehoveraction() throws InterruptedException {

		// setting path of chromedriver
		System.setProperty("webdriver.chrome.driver", "driverslib//chromedriver.exe");

		// creating instance of driver
		WebDriver driver = new ChromeDriver();

		// configure implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// launch application
		driver.get("https://www.sc.com/in/");

		// element to be dragged
		WebElement elm1 = driver.findElement(By.xpath("//button[@title='Login']"));

		// new object of Actions Class

		Actions action = new Actions(driver);

		// mouse hover action

		action.moveToElement(elm1).build().perform();

		// little wait time to see the operation happening

		Thread.sleep(1000);

		// quit the browser
		driver.quit();
	}

}
