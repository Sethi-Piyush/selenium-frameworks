package org.selenium.iris.pages;

import org.selenium.iris.driver.TestDriver;

/**
 * Created by amura on 21/11/18.
 */
public class BasePage {

    TestDriver tDriver;

    public BasePage(){
        if(tDriver == null){
            tDriver = TestDriver.getDriver();
        }
    }



}
