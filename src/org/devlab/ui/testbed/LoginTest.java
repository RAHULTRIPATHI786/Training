package org.devlab.ui.testbed;

import org.devlab.pageclass.LoginPage;
import org.devlab.ui.basetest.BaseClass;
import org.devlab.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners({org.devlab.ui.customelistener.TestCaseListener.class,org.devlab.ui.customelistener.SuiteListenerClass.class})
public class LoginTest extends BaseClass {

	@Test(enabled = true)
	public void Login_with_with_set_of_valid_data() {

		// reading the testdata from xml file
//		String username=Utility.getTestDataFromXMLFile("TestCases/Login_with_with_set_of_valid_data/username");
//		String pwd=Utility.getTestDataFromXMLFile("TestCases/Login_with_with_set_of_valid_data/pwd");		

		// reading the testdata from json file
		String username = Utility.getTestdataFromJson("TestCases.Login_with_with_set_of_valid_data.username");
		String pwd = Utility.getTestdataFromJson("TestCases.Login_with_with_set_of_valid_data.pwd");

		
	
		Assert.assertTrue(LoginPage.login(username, pwd),
				"Failed to login into application with valid set of user :" + username + ":" + pwd);

		// assume that navigating to sign on page makes authentication succesfull
//		Assert.assertTrue(driver.get().getCurrentUrl().contains("mercurysignon.php"),
//				"Failed to login into application with valid set of user : validuser,validpwd");

	}

	@Test(enabled = true )
	public void Login_with_with_set_of_Invalid_data() {

		// reading the data from xml file
//		String username=Utility.getTestDataFromXMLFile("TestCases/Login_with_with_set_of_Invalid_data/username");
//		String pwd=Utility.getTestDataFromXMLFile("TestCases/Login_with_with_set_of_Invalid_data/pwd");	

		// reading the data from json file
		String username = Utility.getTestdataFromJson("TestCases.Login_with_with_set_of_Invalid_data.username");
		String pwd = Utility.getTestdataFromJson("TestCases.Login_with_with_set_of_Invalid_data.pwd");

		Assert.assertFalse(LoginPage.login(username, pwd),
				"Failed to login into application with valid set of user :" + username + ":" + pwd);

	}

//	@Test
//	public void Login_with_with_set_of_Multiple_data() {
//
//		String userset=Utility.getTestDataFromXMLFile("TestCases/Login_with_with_set_of_Multiple_data/userCredentialSet");
//		
//		System.out.println(userset);
//		String[] tmparr = userset.split(",");
//		SoftAssert sftAssert= new SoftAssert();
//		
//		for (int i=0;i<tmparr.length;i++) {
//	
//			String[] tmpArr1 = tmparr[i].split(":");
//			
//			String username=tmpArr1[0];
//			String pwssword=tmpArr1[1];
//			driver.get().findElement(By.name("userName")).clear();
//			driver.get().findElement(By.name("password")).clear();
//		driver.get().findElement(By.name("userName")).sendKeys(username);
//		driver.get().findElement(By.name("password")).sendKeys(pwssword);
////
//driver.get().findElement(By.name("login")).click();
//
//sftAssert.assertTrue(driver.get().getCurrentUrl().contains("mercuryreservation"),
//		"Failed to login into application with valid set of user : Invaliduser,Invalidpwd");
////		Assert.assertTrue(driver.get().getCurrentUrl().contains("mercuryreservation"),
////				"Failed to login into application with valid set of user : Invaliduser,Invalidpwd");
//		}
//		
//		
//		sftAssert.assertAll();
//
//	}

}
