package org.selenium.iris.driver;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.iris.pages.ApartmentSelectorsPage;
import org.selenium.iris.pages.MakePaymentPage;
import org.selenium.iris.utilities.UIElement;
import org.selenium.iris.utilities.UILocatorType;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.openqa.selenium.logging.LogType.DRIVER;

/**
 * Created by amura on 21/11/18.
 */
public class TestDriver {
    public static TestDriver tDriver;
    public static WebDriver driver;
    public static RemoteWebDriver rDriver;

    private TestDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/amura/Downloads/Software/chromedriver");
//        System.setProperty("webdriver.gecko.driver", "/home/amuraqa/Desktop/Software/geckodriver");
//        ChromeDriver driver = new ChromeDriver();
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();    //------- To maximize the screen------/

        }
    }

    public static TestDriver getDriver() {
        if (tDriver == null) {
            tDriver = new TestDriver();
        }
        return tDriver;
    }

    private By myFindBy(UIElement uiElement) {
        By by = null;

        if (uiElement.getUiLocatorType() == UILocatorType.ID) {
            by = By.id(uiElement.getLocator());
        }
        if (uiElement.getUiLocatorType() == UILocatorType.Name) {
            by = By.name(uiElement.getLocator());
        }
        if (uiElement.getUiLocatorType() == UILocatorType.Xpath) {
            by = By.xpath(uiElement.getLocator());
        }
        if (uiElement.getUiLocatorType() == UILocatorType.LinkText) {
            by = By.linkText(uiElement.getLocator());
        }
        if (uiElement.getUiLocatorType() == UILocatorType.PartialLinkText) {
            by = By.partialLinkText(uiElement.getLocator());
        }
        if (uiElement.getUiLocatorType() == UILocatorType.TagName) {
            by = By.tagName(uiElement.getLocator());
        }
        if (uiElement.getUiLocatorType() == UILocatorType.ClassName) {
            by = By.className(uiElement.getLocator());
        }
        if (uiElement.getUiLocatorType() == UILocatorType.CSS) {
            by = By.cssSelector(uiElement.getLocator());
        }

        return by;
    }

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void myGet(String s) {
        driver.get(s);
    }

    public void myClick(UIElement uiElement) {
        driver.findElement(myFindBy(uiElement)).click();
    }

    public void mySearch(UIElement uiElement){
        WebElement element = driver.findElement(myFindBy(uiElement));
        if (element.isDisplayed()){
            element.click();
        }
        else {

        }
    }

    public void myLogin(UIElement uiElement, String arg0, UIElement uiElement2, String arg1) {
        // Get the size and find element
        int size = driver.findElements(myFindBy(uiElement)).size();
        driver.findElements(myFindBy(uiElement)).get(size - 1).clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElements(myFindBy(uiElement)).get(size - 1).sendKeys(arg0);
        driver.findElement(myFindBy(uiElement2)).sendKeys(arg1);
    }

    // Entering when field is not visible
    public void myEntering(UIElement uiElement, String arg0) {
        int size = driver.findElements(myFindBy(uiElement)).size();
        driver.findElements(myFindBy(uiElement)).get(size - 1).clear();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElements(myFindBy(uiElement)).get(size - 1).sendKeys(arg0);
    }

    public void myEnter(UIElement uiElement, String arg0) {
        driver.findElement(myFindBy(uiElement)).sendKeys(arg0 + generateRandomString());
    }

    public void myEnter_Email(UIElement uiElement, String arg0){
        driver.findElement(myFindBy(uiElement)).sendKeys(arg0+generateRandomString()+"@amuratech.com");
    }

    public void myEnter_Phone(UIElement uiElement, String arg0){
        driver.findElement(myFindBy(uiElement)).sendKeys(arg0+generateRandomNumber());
    }
    public void myEnter_ClearFields(UIElement uiElement, String arg0){
        driver.findElement(myFindBy(uiElement)).clear();
        driver.findElement(myFindBy(uiElement)).sendKeys(arg0);
    }

    // For generating random string
    public String generateRandomString() {
        String CHAR_LIST =
                "cdefghjlqeryuiopsdzcvmCDEFFGHJLQERYUIOPSDFZCVM1234567890";
        int RANDOM_STRING_LENGTH = 5;
        StringBuffer randStr = new StringBuffer();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    public String generateRandomNumber(){
      return RandomStringUtils.randomNumeric(5);

    }
    // For generating random number
    private int getRandomNumber() {
        String CHAR_LIST =
                "1234567890";
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    // For entering unique value all the time
    public void myEntering_Unique(UIElement uiElement){
        driver.findElement(myFindBy(uiElement)).sendKeys("Testing" + generateRandomString());
    }

    // Selecting the Title for 'Registering as Channel Partner'
    public void myTitle(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement element : list) {
            System.out.println("Values" + element.getAttribute("innerHTML"));
            if (element.getAttribute("innerHTML").contains("Brig.")) {
                element.click();
            }
        }
    }

    // Selecting Manager for 'Register as Channel Partner'
    public void myManager(UIElement uiElement, UIElement uiElement2) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        driver.findElement(myFindBy(uiElement)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement2)).click();
    }

    // Selecting Projects for 'Selecting as Channel Partner'
    public void myProjects(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement element : list) {
            System.out.println("Values" + element.getAttribute("innerHTML"));
            if (element.getAttribute("innerHTML").contains("Amura Platinum Towers")) {
                element.click();
            }
        }
    }

    // Selecting Account Type for 'Register as Channel Partner'
    public void myAccountType(UIElement uiElement, UIElement uiElement2) {
        Actions actions  = new Actions(driver);
        actions.moveToElement(driver.findElement(myFindBy(uiElement)));
        actions.click();
        actions.build().perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement element : list) {
            System.out.println("Values" + element.getAttribute("innerHTML"));
            if (element.getAttribute("innerHTML").contains("Savings")) {
                element.click();
            }
        }
    }

    // For selecting account type for KYC
    public void myAccountTypeKYC(UIElement uiElement, UIElement uiElement2) {
        Actions actions  = new Actions(driver);
        actions.moveToElement(driver.findElement(myFindBy(uiElement)));
        actions.click();
        actions.build().perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement element : list) {
            System.out.println("Values" + element.getAttribute("innerHTML"));
            if (element.getAttribute("innerHTML").contains("Savings")) {
                element.click();
            }
        }
    }

    // Selecting Country for 'Register as Channel Partner'
    public void myCountry(UIElement uiElement, UIElement uiElement2) {
        WebElement element = driver.findElement(myFindBy(uiElement));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        for (WebElement element2 : list) {
            System.out.println("Values" + element.getAttribute("innerHTML"));
            if (element2.getAttribute("innerHTML").contains("India")) {
                element2.click();
            }
        }
    }



    // Selecting State for 'Register as Channel Partner'
    public void myState(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        for (WebElement element : list) {
            System.out.println("Values" + element.getAttribute("innerHTML"));
            if (element.getAttribute("innerHTML").contains("Maharashtra")) {
                element.click();
            }
        }
    }

    // For printing error message
    public void myErrorMessage(UIElement uiElement) {
        String element = driver.findElement(myFindBy(uiElement)).getAttribute("innerHTML");
        System.out.println("Error messages are-" + element);
    }

    // For printing success message
    public void mySuccessMessage(UIElement uiElement) {
        String element = driver.findElement(myFindBy(uiElement)).getText();
        System.out.println("Success message-" + element);
    }

    // Selecting Beds for 'Apartment Selector'
    public void myBeds(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        for (WebElement element : list) {
            System.out.println("Beds-" + element.getAttribute("innerHTML"));
            if (element.getAttribute("innerHTML").contains("1.5 BHK starting at")) {
                element.click();
            }

        }
    }

    // Selecting Agreement Value for 'Apartment Selector'
    public void myAgreementValue(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        for (WebElement element : list) {
            System.out.println("Agreement Value-" + element.getAttribute("innerHTML"));
            if (element.getAttribute("innerHTML").contains("₹45,12,098 - ₹55,14,397")) {
                element.click();
            }

        }
    }

    // Selecting All Inclusive Price for 'Apartment Selector'
    public void myAllInclusivePrice(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        for (WebElement element : list) {
            if (element.getAttribute("innerHTML").contains("₹55,91,936 - ₹69,05,079")) {
                element.click();
            }

        }
    }

    // Selecting Project for 'Apartment Selector'
    public void myProject(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        List<WebElement> list = driver.findElements(myFindBy(uiElement2));
        for (int i = 0; i <= 2; i++) {
            try {
                for (WebElement element : list) {
                    System.out.println("Project-" + element.getAttribute("innerHTML"));
                    if (element.getAttribute("innerHTML").contains("Amura Crown")) {
                        element.click();
                    }
                }
            }
            catch (Exception e)  {
                System.out.println(e.getMessage());
            }
        }
    }

    // Entering Amount for 'Make Payment'
    public void myAmountPayment(UIElement uiElement, String arg0) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).clear();
        driver.findElement(myFindBy(uiElement)).sendKeys(arg0);
    }

    public void myPaymentMethod(UIElement uiElement) {
//        driver.navigate().refresh();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().frame(0);
        driver.findElement(myFindBy(uiElement)).click();
    }


    // For clicking Success or Failure of payment
    public void myTransactionStatus(UIElement uiElement) {
        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            System.out.println(winHandle);
            driver.switchTo().window(winHandle);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Perform the actions on new window
        driver.findElement(myFindBy(uiElement)).click();
    }

    public void myTransactionStatusMessage(UIElement uiElement) {
        for (String winHandle : driver.getWindowHandles()) {
            System.out.println(winHandle);
            driver.switchTo().window(winHandle);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(driver.findElement(myFindBy(uiElement)).getText());
    }

    public void myPreferencesKYC(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0; i<=2; i++ ) {
            try {
                List<WebElement> list = driver.findElements(myFindBy(uiElement2));
                for (WebElement element : list) {
                    System.out.println("Preferences-" + element.getAttribute("innerHTML"));
                    if (element.getAttribute("innerHTML").contains("6 BHK")) {
                        element.click();
                    }
                }
            }
            catch   (Exception e){
                System.out.println(e.getMessage());
            }
        }

        }

    public void myNRIKYC(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,600)");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(myFindBy(uiElement))).click();
        actions.build().perform();
        driver.findElement(myFindBy(uiElement2)).click();
    }


    public void myPreferredFloorsKYC(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Preferences-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("5")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myCorporateKYC(UIElement uiElement, UIElement uiElement2) {
            driver.findElement(myFindBy(uiElement)).click();
            driver.findElement(myFindBy(uiElement2)).click();
    }

    public void myExistingCustomerKYC(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        driver.findElement(myFindBy(uiElement2)).click();
    }

    public void myApartmentSelect(UIElement uiElement, UIElement uiElement2) {
        List<WebElement> list = driver.findElements(myFindBy(uiElement));
        for (WebElement element:list
             ) {System.out.println(element.getAttribute("innerHTML"));
            if (element.isEnabled()){
                element.click();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JavascriptExecutor js =  (JavascriptExecutor)driver;
            js.executeScript("window.scrollBy(0,-300)");
            System.out.println("Unit No.-" + driver.findElement(myFindBy(uiElement2)).getText());
    }
    }

    public void myPrimaryUserKYC(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement2)).click();
    }

    public void myScheme(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Primary KYC User-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Default scheme")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myTowerSelect(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,-400)");
        System.out.println("Tower name-" + driver.findElement(myFindBy(uiElement2)).getText());

    }

    public void myTowerSelect_Peninsula(UIElement uiElement, UIElement uiElement2) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,700)");
        driver.findElement(myFindBy(uiElement)).click();
        System.out.println("Tower name-" + driver.findElement(myFindBy(uiElement2)).getText());

    }

    String paymentElement;
    // For getting payment details when the apartment is booked
    public void myPaymentDetails(UIElement uiElement) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,900)");
        System.out.println("Payment Details" + driver.findElement(myFindBy(uiElement)).getText());
        paymentElement = driver.findElement(myFindBy(uiElement)).getText();
        System.out.println(paymentElement);

    }


    public void myAddNewManagementUser(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Add new user" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("New Management User")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void myAddNewEmployeeUser(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Add new user" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("New Customer")) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myProjectTowerSchemes(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Tower-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("T044")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myApartmentSchemes(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Apartment-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("1506")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myUserRoleSchemes(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User Role- " + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Customer")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void myUserSchemes(UIElement uiElement,  String arg0, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement2)).click();
    }


    public void myAppliedBySchemes(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Can be applied by-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Superadmin")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myActionsEdit_UserRole(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Actions" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Edit")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myRoleEdit_UserRoleManagement(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User roles" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Management User")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



    public void myActionEdit_Schemes(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User roles" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Edit")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void myStatusChange_Schemes(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(myFindBy(uiElement)));
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Status-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Approved")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myRoleEdit_UserRoleEmployee(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Status-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Employee")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myUpdateKYC(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Update List-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Update KYCs")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myUpdatePayment(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Update List-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Pay Remaining Amount")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myKYCC0Applicants(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Co-applicants" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Mr. Sanket Redkar")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    String relatedApartment ;
    public void mySwapRequest(UIElement uiElement, UIElement uiElement2) {
        relatedApartment = driver.findElement(By.xpath("//div//label[contains(text(),'Name')]/following-sibling::div[1]")).getText();
        System.out.println("Related Apartment-" + relatedApartment);

        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Update Booking-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Swap Request")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // For Swaping Request
    public void myRelatedApartment(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement2)).click();
    }

    // For Unit to be Swapped with
    public void myAternativeProjectUnit(UIElement uiElement, UIElement uiElement2) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (WebElement element : list) {
                System.out.println("Alternative Units-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("2.0 BHK")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }


    public void myCancellationRequest(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Related Apartments" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Cancellation Request")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        driver.navigate().refresh();
    }

    public void myDirectPayment(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Co-applicants" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Add Direct Payment")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myModeOfPayment(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Co-applicants" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Cheque")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void myUserSearch(UIElement uiElement, UIElement uiElement2, UIElement uiElement3) {
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User Status-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Confirmed")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement3)).click();
    }

    // For adding booking by an admin
    public void myActionsAddBooking_admin(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Apartment-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Choose Apartment")) {
                    element.click();
                }
                else(element.getAttribute("innerHTML")).contains("Edit");
                {
                    element.click();
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myApartmentSelect_admin(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Apartment-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("042")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myPaymentStatus_admin(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions actions  = new Actions(driver);
        actions.moveToElement(driver.findElement(myFindBy(uiElement)));
        actions.click();
        actions.build().perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Apartment-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Clearance Pending")) {
                    element.click();
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myActions_AddKYC(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Actions- " + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Add KYC Details")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myKYCProjectUnitIds(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Project Unit Ids- " + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("02")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myRole_Administrator(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User roles" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Administrator")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myTimeZone_Administrator(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User roles" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Mumbai")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myAddNewAdministratorUser(UIElement uiElement, UIElement  uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User roles" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Administrator")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myAddNewCRMUser(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Roles- " + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("CRM User")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myAddNewSalesHeadUser(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User roles-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("New Sales Head")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void myAddNewChannelPartnerUser(UIElement uiElement, UIElement uiElement2) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("User roles" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Channel Partner")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myActiveUser_PartnerRegistration(UIElement uiElement, UIElement uiElement2, UIElement uiElement3) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Status-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Inactive")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        driver.findElement(myFindBy(uiElement3)).click();
    }

    public void myEdit_PartnerRegistration(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Actions-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Edit")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myStatusChange_PartnerRegistration(UIElement status_partnerRegistration, UIElement statusDropdown) {

    }

    public void mySearchReceipt(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).sendKeys(paymentElement);
        driver.findElement(myFindBy(uiElement2)).click();
    }

    public void mySignOut( UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Profile-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Sign out")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    // For getting all the swap requests
    public void mySwapRequestFilter(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Requests-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Swap")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // For getting swap requests which are in pending status
    public void myPendingStatusFilter(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Status-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Pending")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        myApply();
    }

    public void myActionsEdit_SwapRequest(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        for (int i = 0; i <= 2; i++) {
            try {
                List<WebElement> list = driver.findElements(myFindBy(uiElement2));
                for (WebElement element : list) {
                    System.out.println("Actions-" + element.getAttribute("innerHTML"));
                    if (element.getAttribute("innerHTML").contains("Edit")) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        element.click();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void myApply(){
        driver.findElement(By.xpath("//input[@value='Apply']")).click();
    }

    public void myStatusResolved_SwapRequests(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Status-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Resolved")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    String relatedApartmentResolved;
    String relatedApartmentName ;
    public void myResolvedStatus_Filter(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Status-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Resolved")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        myApply();
        relatedApartmentResolved = driver.findElement(By.xpath("//div[contains(text(),'Related Apartment')]")).getText();
        relatedApartmentName = relatedApartmentResolved.substring(19);
        System.out.println("Related Apartment-" + relatedApartmentName);;
        Assert.assertEquals(relatedApartment, relatedApartmentName);
    }


    public void myField_Negotiation(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavascriptExecutor js = (JavascriptExecutor)(driver);
        js.executeScript("window.scrollBy(0,400)");
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Fields-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Agreement Price")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myProfileEdit(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Profile-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Profile")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void myAddNewSalesUser(UIElement uiElement, UIElement uiElement2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Users-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("New Sales User")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myRoleFilter_customer(UIElement uiElement, UIElement uiElement2, UIElement uiElement3) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Roles-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Customer")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myEditClient_LoginMethod(UIElement uiElement, UIElement uiElement2) {
        WebElement element2 = driver.findElement(myFindBy(uiElement));
        JavascriptExecutor js = (JavascriptExecutor)driver ;
        js.executeScript("window.scrollBy(0,600)");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Login Methods-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Phone Based")) {
                    element.click();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void myEditClient_EnableDirectMethod(UIElement uiElement) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> list = driver.findElements(myFindBy(uiElement));
        for (WebElement element : list
                ) {
            System.out.println(element.getAttribute("innerHTML"));
            if (element.isEnabled()) {
                element.click();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void myEditClient_PaymentGateway(UIElement uiElement, UIElement uiElement2) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(myFindBy(uiElement)).click();
        try {
            List<WebElement> list = driver.findElements(myFindBy(uiElement2));
            for (WebElement element : list) {
                System.out.println("Payment Gateways-" + element.getAttribute("innerHTML"));
                if (element.getAttribute("innerHTML").contains("Razorpay Payment Gateway")) {
                    element.click();
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void myEditClient_AllowCompanyUser(UIElement uiElement) {
        List<WebElement> list = driver.findElements(myFindBy(uiElement));
        for (WebElement element:list
                ) {
            System.out.println(element.getAttribute("innerHTML"));
            if (element.isEnabled()){
                element.click();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    }

    public void myEditClient_AllowMultipleUser(UIElement uiElement) {
        List<WebElement> list = driver.findElements(myFindBy(uiElement));
        for (WebElement element:list
                ) {
            System.out.println(element.getAttribute("innerHTML"));
            if (element.isEnabled()){
                element.click();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    }



    public void myEditClient_EnableChannelPartner(UIElement uiElement) {
        List<WebElement> list = driver.findElements(myFindBy(uiElement));
        for (WebElement element:list
                ) {
            System.out.println(element.getAttribute("innerHTML"));
            if (element.isEnabled()){
                element.click();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    }

    public void myEditClient_EmailDomain(UIElement uiElement, String arg0) {
        driver.findElement(myFindBy(uiElement)).sendKeys(arg0);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);
        actions.perform();
    }

    public void myAllowedBookings(){
        driver.findElement(By.xpath("")).click();
        List<WebElement> list = driver.findElements(By.xpath(""));
        for (WebElement element : list) {
            System.out.println("Bookings-" + element.getAttribute("innerHTML"));
            if (element.getAttribute("innerHTML").contains("100")) {
                element.click();
            }
        }
    }
    public void myRoleFilter_channelPartner(UIElement uiElement, UIElement uiElement2) {
        driver.findElement(myFindBy(uiElement)).click();
        for (int i = 0; i <= 2; i++) {
            try {
                List<WebElement> list = driver.findElements(myFindBy(uiElement2));
                for (WebElement element : list) {
                    System.out.println("Roles-" + element.getAttribute("innerHTML"));
                    if (element.getAttribute("innerHTML").contains("Channel Partner")) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        element.click();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void myStartDate_futureSlot(UIElement uiElement){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement(myFindBy(uiElement));
        element.click();
        element.clear();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        Date dt = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(dt);
        cl.add(Calendar.DAY_OF_YEAR, 1);
        dt = cl.getTime();
        String str = df.format(dt);
        element.sendKeys(str);
    }

    public void myEnableSlotGeneration_slot(UIElement uiElement) {
        if(!driver.findElement(myFindBy(uiElement)).isSelected()){
            driver.findElement(myFindBy(uiElement)).click();
        }
    }
}
