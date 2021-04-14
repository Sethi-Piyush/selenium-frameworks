package org.selenium.iris.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.selenium.iris.utilities.UIElement;
import org.selenium.iris.utilities.UILocatorType;
import org.selenium.iris.utilities.UIType;

import static org.selenium.iris.driver.TestDriver.driver;

/**
 * Created by amura on 21/11/18.
 */
public class LoginPage  extends BasePage {

    UIElement login =  new UIElement(UIType.Button, UILocatorType.Xpath,"//div[@class='actions']//input[@value='Log in']");
    UIElement loginWithPassword =  new UIElement(UIType.Button, UILocatorType.Xpath,"//a[@class='toggle_signin_form d-block d-md-inline float-md-right pr-0' ]");
    UIElement userName =  new UIElement(UIType.Button, UILocatorType.ID,"user_login");
    UIElement password =  new UIElement(UIType.Button, UILocatorType.ID,"user_password");
    UIElement username2 =  new UIElement(UIType.Button, UILocatorType.ID,"user_login");
    UIElement password2 =  new UIElement(UIType.Button, UILocatorType.ID,"user_password");
    UIElement profileIcon =  new UIElement(UIType.Button, UILocatorType.Xpath,"//li//div//div/following::div//a[@class='nav-link dropdown-toggle']");
    UIElement profile =  new UIElement(UIType.Button, UILocatorType.Xpath,"//div//a[@class='nav-link dropdown-toggle']/following-sibling::div[@class='dropdown-menu dropdown-menu-right show']//a");


    public void lanuchTheURL(String arg0) {
        tDriver.myGet(arg0);
    }

    public void clickOnSignInButton() {
        JavascriptExecutor js = (JavascriptExecutor)(driver);
        js.executeScript("window.scrollBy(0,300)");
        tDriver.myClick(login);
    }

    public void enterAnd(String arg0, String arg1) {
        tDriver.myLogin(userName, arg0, password, arg1);
    }

    public void clickLoginWithPasswordInstead() {
        tDriver.myClick(loginWithPassword);
    }

    public void enterUsername(String arg0) {
        tDriver.myEntering(username2, arg0);
    }

    public void enterPassword(String arg0) {
        tDriver.myEntering(password2, arg0);
    }

    public void clickProfileIconAndSelectSignOut() {
        tDriver.mySignOut(profileIcon, profile);
    }

    public void clickProfileIconAndSelectProfile() {
        tDriver.myProfileEdit(profileIcon, profile);
    }
}

