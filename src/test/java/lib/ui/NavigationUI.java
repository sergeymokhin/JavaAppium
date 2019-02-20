package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click " +
                    "open navigation button", 5);
            //KOSTYL
            this.waitForElementPresent(
                    MY_LISTS_LINK,
                    "Cannot wait for MY_LISTS_LINK",
                    5);

        } else {
            System.out.println("Method openNavigation() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyLists()
    {
        System.out.println(MY_LISTS_LINK + " +!+!+!+!+!+!+!+");
        if (Platform.getInstance().isMw()) {

            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Can not find navigation button to My List",
                    5
            );
        } else {
            this.waitForElementPresent(
                    MY_LISTS_LINK,
                    "Canot wait for MY_LISTS_LINK",
                    10
            );

            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Can not find navigation button to My List",
                    5
            );
        }
    }
}
