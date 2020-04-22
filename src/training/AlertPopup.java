package training;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertPopup {

	public static void main(String[] args) throws InterruptedException, IOException {

		// example to handle java script alert window
		jsAlert();
		jsAlertText();
		jsConfirmationAlertAccept();
		jsConfirmationAlertDismiss();
		jsPromptAlert();

	}

	public static void jsAlert() {
		System.setProperty("webdriver.chrome.driver", "driverslib//chromedriver.exe");

		// creating instance of driver
		WebDriver driver = new ChromeDriver();

		// configure implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// launch application
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");

		// click on button
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		// accept alert
		driver.switchTo().alert().accept();

	}

	public static void jsConfirmationAlertAccept() {

		System.setProperty("webdriver.chrome.driver", "driverslib//chromedriver.exe");

		// creating instance of driver
		WebDriver driver = new ChromeDriver();

		// configure implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// launch application
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");

		// click on button
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// accept alert
		driver.switchTo().alert().accept();

	}

	public static void jsConfirmationAlertDismiss() {

		System.setProperty("webdriver.chrome.driver", "driverslib//chromedriver.exe");

		// creating instance of driver
		WebDriver driver = new ChromeDriver();

		// configure implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// launch application
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");

		// click on button
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// click on cancel button
		driver.switchTo().alert().dismiss();

	}

	public static void jsPromptAlert() {

		System.setProperty("webdriver.chrome.driver", "driverslib//chromedriver.exe");

		// creating instance of driver
		WebDriver driver = new ChromeDriver();

		// configure implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// launch application
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");

		// click on button
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		// click on cancel button
		Alert alrt = driver.switchTo().alert();
		alrt.sendKeys("Hello");

		alrt.accept();

	}

	public static void jsAlertText() {

		System.setProperty("webdriver.chrome.driver", "driverslib//chromedriver.exe");

		// creating instance of driver
		WebDriver driver = new ChromeDriver();

		// configure implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// launch application
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");

		// click on button
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		System.out.println(driver.switchTo().alert().getText());
	}

}
