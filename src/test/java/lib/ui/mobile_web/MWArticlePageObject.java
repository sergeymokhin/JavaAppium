package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch";
                //"css:#page-actions li#.mw-ui-ca-watch-icon-mf-watched watch button";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched";
//                "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}


//css:#page-actions li#ca-watch";