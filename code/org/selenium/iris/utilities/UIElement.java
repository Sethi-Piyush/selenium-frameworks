package org.selenium.iris.utilities;

/**
 * Created by amuraqa on 18/7/17.
 */
public class UIElement {
    private UIType uiType;
    private UILocatorType uiLocatorType;
    private String locator;

    public UIElement(UIType uiType, UILocatorType uiLocatorType, String locator) {
        this.uiType = uiType;
        this.uiLocatorType = uiLocatorType;
        this.locator = locator;
    }

    public UIType getUiType() {
        return uiType;
    }

    public UILocatorType getUiLocatorType() {
        return uiLocatorType;
    }

    public String getLocator() {
        return locator;
    }
}
