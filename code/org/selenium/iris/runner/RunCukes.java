package org.selenium.iris.runner;

/**
 * Created by amuraqa on 26/10/17.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false,
        strict = false,
        features = {"resources/features/"},
        tags = {"@smoke"},
        glue = {"org.selenium.iris.steps"},
        format = {"html:target/output-reports/apartment-booking/html", "json:target/output-reports/cucumber-extent-report/junitrunner.json"},
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/output-reports/cucumber-extent-report/apartment-booking_peninsula.html"}
)
public class    RunCukes {

}
