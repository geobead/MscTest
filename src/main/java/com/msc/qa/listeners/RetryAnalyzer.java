package com.msc.qa.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	int maxRetries = 2;
	int retryCounter = 0;
	
	@Override
	public boolean retry(ITestResult result) {
		if(retryCounter<maxRetries) {
			retryCounter++;
			return true;
		}
		return false;
	}

}
