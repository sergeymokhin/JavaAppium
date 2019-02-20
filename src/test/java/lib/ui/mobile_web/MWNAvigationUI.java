package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNAvigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "css:a[data-event-name='watchlist']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
    }

    public MWNAvigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}

//<a href="/w/index.php?title=Special:Watchlist&amp;watchlistview=a-z" class="mw-ui-icon mw-ui-icon-before mw-ui-icon-minerva-watchlist " data-event-name="watchlist">Watchlist</a>