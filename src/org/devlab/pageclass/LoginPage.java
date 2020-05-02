package org.devlab.pageclass;

import org.devlab.ui.basetest.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class LoginPage extends BaseClass {

	// enter provided user name into username text box
	public static void enterUserName(String username) {
		try {
			driver.get().findElement(By.name("userName")).sendKeys(username);
		} catch (StaleElementReferenceException e) {
			driver.get().findElement(By.name("userName")).sendKeys(username);
		}
	}

	// enter provided password into password text box
	public static void enterPassword(String pwd) {
		driver.get().findElement(By.name("password")).sendKeys(pwd);
	}

	// Click on Signin button
	public static void clickOnSigninButton() {
		driver.get().findElement(By.name("login")).click();
	}

	// login

	public static boolean login(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickOnSigninButton();

		if (driver.get().getCurrentUrl().contains("mercurysignon")) {
			return true;
		} else
			return false;

	}

}
