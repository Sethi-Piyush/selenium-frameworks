package org.selenium.iris.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.selenium.iris.pages.LoginPage;
import org.selenium.iris.utilities.UIElement;

import static org.selenium.iris.driver.TestDriver.driver;

/**
 * Created by amura on 21/11/18.
 */
public class LoginSteps {

    LoginPage dp = new LoginPage();

    public LoginSteps(){
//        dp = new LoginPage();
    }

    @Given("^Launch the URL  \"([^\"]*)\"$")
    public void launchTheURL(String arg0) {
       dp.lanuchTheURL(arg0);
    }

    @When("^Enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void enterAnd(String arg0, String arg1) {
       dp.enterAnd(arg0, arg1);
    }

    @Then("^Click on 'Sign in' button$")
    public void clickOnSignInButton(){
        dp.clickOnSignInButton();
    }

    @When("^Click 'Login with password instead'$")
    public void clickLoginWithPasswordInstead(){
        dp.clickLoginWithPasswordInstead();
    }


    @And("^Enter Username \"([^\"]*)\"$")
    public void enterUsername(String arg0) {
        dp.enterUsername(arg0);
    }

    @And("^Enter Password \"([^\"]*)\"$")
    public void enterPassword(String arg0) {
        dp.enterPassword(arg0);
    }

    @When("^Click Profile icon and select Sign out$")
    public void clickProfileIconAndSelectSignOut() {
        dp.clickProfileIconAndSelectSignOut();
    }

    @When("^Click Profile icon and select Profile$")
    public void clickProfileIconAndSelectProfile() {
        dp.clickProfileIconAndSelectProfile();
    }
}
