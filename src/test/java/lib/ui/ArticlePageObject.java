package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        EXISTING_LIST_NAME_TPL;


    private static String getExistingListName(String name_of_folder)
    {
        return EXISTING_LIST_NAME_TPL.replace("{LIST_NAME}", name_of_folder);
    }

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find " +
                "article title on page", 10);
    }

    public String  getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    50
            );
        } else {
            this.scrollWebPageTilllementNotVisible(
                    FOOTER_ELEMENT,
                     "Cannot find the end of article",
                    40
            );
        }
    }

    public void addArticleToNewList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find button to open article options",
                5
        );

        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find add to reading list in article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not click option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Can not find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'OK' button",
                5
        );
    }

    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option " +
                "to add article to reading list", 10);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    5
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved after removing it" +
                            "from this list before"
            );
        }
    }

    public void openMyListByListName(String name_of_folder)
    {
        String existing_list_xpath = getExistingListName(name_of_folder);

        this.waitForElementPresent(existing_list_xpath,
                "Cannot find my previously created list", 5
        );

        this.waitForElementAndClick(
                existing_list_xpath,
                "Cannot click existing list name",
                5
        );
    }

    public void addNewArticleToExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find button to open article options",
                5
        );

        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not find add to reading list in article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Can not click option to add article to reading list",
                5
        );

        this.openMyListByListName(name_of_folder);
    }

    public void swipeElementToLeftToDelete(String first_article_title)
    {
        String existing_list_xpath = getExistingListName(first_article_title);
        this.swipeElementToLeft(
                existing_list_xpath,
                "Cannot find saved article to delete"
        );
    }

    public void makeSureArticleWasDeleted(String first_article_title)
    {
        String existing_list_xpath = getExistingListName(first_article_title);
        this.waitForElementNotPresent(
                existing_list_xpath,
                "Cannot delete saved article",
                5
        );
    }

    public void makeSureArticlePresentByTitle(String second_article_title)
    {
        String existing_list_xpath = getExistingListName(second_article_title);
        this.waitForElementPresent(
                existing_list_xpath,
                "Cannot find non deleted article",
                5
        );
    }

    public void clickOnUndeletedArticle(String second_article_title) //aka open article
    {
        String existing_list_xpath = getExistingListName(second_article_title);
        this.waitForElementAndClick(existing_list_xpath,
                "Can not click on non deleted article",
                5
                );
    }

    public void closeArticle()
    {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Can not close article, can not find X link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform " +
                    Platform.getInstance().getPlatformVar());
        }
    }

    public String assertElementPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements == 0) {
            Assert.fail("An element '" + locator + "' is absent.");
        }
        return error_message;
    }

    public void assertElementNotPresent()
    {
        this.assertElementPresent(
                TITLE,
                "Element is Present, but we did not expected it"
        );
    }


}
