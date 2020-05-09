package org.devlab.ui.basetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

public class BaseClass {
	public static Set<String> browserSet = new LinkedHashSet<String>();
	public static HashMap<String, HashMap<String, String>> executioninfo = new HashMap<String, HashMap<String, String>>();

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<String> browser_name = new ThreadLocal<String>();
	public static String jsnTestData = "";

	@BeforeMethod
	@Parameters({ "Browsername" })
	public void initDriver(String browsername) throws IOException {

		synchronized (browsername) {
			// updating linkedhashset object with browser being used in test
			browserSet.add(browsername);
		}

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

		HashMap<String, String> brswerStatus = new HashMap<String, String>();

		String status;
		if (itr.getStatus() > 1) {
			status = "FAIL";
			brswerStatus.put(browser_name.get(), "FAIL");

			File fle = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);

			Files.copy(fle, new File("Screenshot/" + browser_name.get() + "_" + itr.getName() + ".png"));

		} else {
			status = "PASS";
			brswerStatus.put(browser_name.get(), "PASS");
		}

		if (executioninfo.get(itr.getName()) != null) {

			executioninfo.get(itr.getName()).put(browser_name.get(), status);
		} else {
			executioninfo.put(itr.getName(), brswerStatus);
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

	@AfterSuite
	public void afterSuiteMethod() throws IOException {

		// code to create result file in html file fomrat post execution
		File exechtmlReport = new File("Report.html");

		if (exechtmlReport.exists()) {
			exechtmlReport.delete();
		}
		exechtmlReport.createNewFile();

		PrintWriter prw = new PrintWriter(exechtmlReport);

		String str = "<html> <style> table {border-collapse: collapse;	} td, th {	 border: 1px solid black;  text-align: center;	padding: 8px;} tr {	 background-color: #D2FADB;}</style><body>	<table>";
		str = str + "<tr><th> TestCaseName</th>";
		// update the table with dynamic value

		for (String browsers : browserSet) {
			str = str + "<th>" + browsers + "</th>";
		}
		str = str + "</tr>";
		for (String testcasename : executioninfo.keySet()) {
			str = str + "<tr><td>" + testcasename + "</td>";

			for (String browsers : browserSet) {
				if (executioninfo.get(testcasename).get(browsers) != null) {
					if (executioninfo.get(testcasename).get(browsers).equalsIgnoreCase("PASS")) {
						str = str + "<td style=\"color:green\">" + executioninfo.get(testcasename).get(browsers)
								+ "</td>";
					} else if (executioninfo.get(testcasename).get(browsers).equalsIgnoreCase("FAIL")) {
						str = str + "<td ><a target =\"_blank\" href = \""+"Screenshot/"+browsers+"_"+testcasename+".png" +"\" style=\"color:red\">" + executioninfo.get(testcasename).get(browsers)
								+ "</a></td>";
					}
				} else {
					str = str + "<td style=\"color:yello\">" + "N/A" + "</td>";
				}

			}

			str = str + "</tr>";

		}

		str = str + "</table></body></html>";
		prw.write(str);
//		prw.write("</table></html>");

		prw.close();

	}
}
