package org.devlab.ui.basetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.devlab.utility.Utility;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

public class BaseClass {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<String> browser_name = new ThreadLocal<String>();
	public static String jsnTestData = "";

	@BeforeMethod
	@Parameters({ "Browsername" })
	public void initDriver(String browsername) throws IOException {
//		String browsername = Utility.getpropertyFromPropertyfile("browsername");
		String executionbrowser = Utility.getpropertyFromPropertyfile("executionbrowser");

		browser_name.set(browsername);
		DesiredCapabilities cap = null;

		// launching browser bassed on the parameter value defined @test level
		if (browsername.equalsIgnoreCase("CHROME")) {
			cap = DesiredCapabilities.chrome();
		} else if (browsername.equalsIgnoreCase("FF")) {
			cap = DesiredCapabilities.firefox();
		} else if (browsername.equalsIgnoreCase("IE")) {
			cap = DesiredCapabilities.internetExplorer();
		}

		if (executionbrowser.equalsIgnoreCase("LOCAL")) {
			if (browsername.equalsIgnoreCase("CHROME")) {
				driver.set(new ChromeDriver());
			} else if (browsername.equalsIgnoreCase("FF")) {
				driver.set(new FirefoxDriver());
			}

		} else {
			try {

				driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap));

			} catch (

			MalformedURLException e) {
				Assert.fail("issue with hub url" + e.toString());
			}

		}

//		String appUrl=Utility.getTestDataFromXMLFile("Common/AppURL");

		// reading the test data from json file

		String appUrl = Utility.getTestdataFromJson("Common.AppURL");
		driver.get().get(appUrl);
	}

	@AfterMethod
	public void teardown(ITestResult itr) throws IOException {

		if (itr.getStatus() > 1) {
			File fle = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);

			Files.copy(fle, new File("Screenshot/" + browser_name.get() + "_" + itr.getName() + ".png"));

		}

		// close driver
		driver.get().quit();
	}

	@BeforeSuite
	public void beforeSuiteExc() {
		try {

			File fle = new File("TestData.json");
			BufferedReader br = new BufferedReader(new FileReader(fle));
			String line = "";

			while (line != null) {
				line = br.readLine();
				jsnTestData = jsnTestData + "\n" + line;
			}

		} catch (Exception e) {
			Assert.fail("Failed to read the content of testdata file" + e.toString());
		}

		File imageFolder = new File("Screenshot");

		imageFolder.mkdir();
	}
}
