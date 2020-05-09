package org.devlab.ui.customelistener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryCustomClass implements IRetryAnalyzer {

	int maxTry = 2;
	int cntr = 0;

	@Override
	public boolean retry(ITestResult result) {

		if (cntr < maxTry) {
			cntr = cntr + 1;
			return true;
		}

		return false;
	}

}
