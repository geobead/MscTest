package com.qa.util;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.msc.qa.base.TestBase;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShot extends TestBase{
	
	private final static String currentDir = System.getProperty("user.dir");
	private final static String screenShotDir = currentDir+"/ScreenShots/";
	private final static String basicScreenShotDir = screenShotDir+"GetScreenShotMethod/";
	private final static String entirePageScreenShotDir = screenShotDir+"EntirePage/";
	
	public static String takeScreenShot() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenShotFilePath = basicScreenShotDir + System.currentTimeMillis() + ".png";
		FileUtils.copyFile(scrFile, new File(screenShotFilePath));
		return screenShotFilePath;
	}
	
	public static String takeEntirePageScreenShot() throws IOException {
		//Take Screenshot of entire page by AShot
        Screenshot entirePageScreenShot = new AShot().
                shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        //Write Screenshot to a file
        String entirePageScreenShotFilePath = entirePageScreenShotDir+System.currentTimeMillis()+".png";
        ImageIO.write(entirePageScreenShot.getImage(),"PNG", new File(entirePageScreenShotFilePath));
        return entirePageScreenShotFilePath;
	}
}
