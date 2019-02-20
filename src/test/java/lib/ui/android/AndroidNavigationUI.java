package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "path://android.widget.FrameLayout[@content-desc='My lists']";
    }
    public AndroidNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
