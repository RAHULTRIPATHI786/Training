package org.devlab.ui.testbed;

import org.devlab.ui.basetest.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseClass {

	@Test(enabled = true)
	public void register_user_with_valid_setof_data() {

		// click on register link
		driver.get().findElement(By.linkText("REGISTER")).click();

		// enter user name
		driver.get().findElement(By.id("email")).sendKeys("Automationuser");

		// enter password
		driver.get().findElement(By.name("password")).sendKeys("password");

		// enter confirm password
		driver.get().findElement(By.name("confirmPassword")).sendKeys("password");

		// click on submit button
		driver.get().findElement(By.name("register")).click();

		// assert statement

		Assert.assertTrue(driver.get().getCurrentUrl().contains("create_account_success"), "unable to register user");

		

	}

}
