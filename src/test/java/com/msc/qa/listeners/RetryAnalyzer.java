package com.msc.qa.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	private int maxRetries = 2;
	private int retryCounter = 0;
	
	@Override
	public boolean retry(ITestResult result) {
		if(!result.isSuccess()) {}
		if(retryCounter<maxRetries) {
			retryCounter++;
			return true; 			//Tells TestNG to re-run the test
		}
		return false;
	}

}
