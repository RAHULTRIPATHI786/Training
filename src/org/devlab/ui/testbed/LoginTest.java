package org.devlab.ui.testbed;

import org.devlab.ui.basetest.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

	@Test
	public void Login_with_with_set_of_valid_data() {

		driver.get().findElement(By.name("userName")).sendKeys("validuser");
		driver.get().findElement(By.name("password")).sendKeys("validpwd");

		driver.get().findElement(By.name("login")).click();

		// assume that navigating to sign on page makes authentication succesfull
		Assert.assertTrue(driver.get().getCurrentUrl().contains("meuryreservation"),
				"Failed to login into application with valid set of user : validuser,validpwd");

	}

	@Test
	public void Login_with_with_set_of_Invalid_data() {

		driver.get().findElement(By.name("userName")).sendKeys("Invaliduser");
		driver.get().findElement(By.name("password")).sendKeys("Invalidpwd");

		driver.get().findElement(By.name("login")).click();

		Assert.assertTrue(driver.get().getCurrentUrl().contains("mercuryreservation"),
				"Failed to login into application with valid set of user : Invaliduser,Invalidpwd");

	}
}
