package org.selenium.iris.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.iris.config.Email;
import org.selenium.iris.config.ZipUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amura on 17/1/19.
 */
public class Hooks {

    public Hooks() throws IOException {
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("") + "/FailedTestsScreenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    @Before
    public void beforeRunning(Scenario scenario) {
        System.out.println("\nRunning scenario: " + scenario.getName());
    }

    @After
    public void afterRunning(Scenario scenario){
        System.out.println("\nScenario Status:" + scenario.getStatus());
    }

    @After
    public void reportMail(){
        ZipUtils.zip();
        try {
            Email.sendMail("piyush@amuratech.com ", "piyush@amuratech.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
