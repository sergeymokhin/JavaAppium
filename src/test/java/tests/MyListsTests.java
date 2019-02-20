package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String
        login = "Qaban",
        password = "Qwerty123";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            System.out.println("AUTH SUCCESS");

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }

        System.out.println("!!!!!!!!!!!");
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        System.out.println("Im INSIDE");


        NavigationUI.clickMyLists();
        System.out.println("I'm clicked it!!!!");

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        System.out.println("+++++++++++++++++++");

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

//        MyListsPageObject.closeLoginToSyncPopup();
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticles() // Ex.5 Тест: сохранение двух статей
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToNewList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Swift");
        SearchPageObject.clickByArticleWithSubstring("General-purpose, multi-paradigm, " +
                "compiled programming language");

        ArticlePageObject.waitForTitleElement();
        String second_article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addNewArticleToExistingList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        ArticlePageObject.openMyListByListName(name_of_folder);
        ArticlePageObject.swipeElementToLeftToDelete(first_article_title);
        ArticlePageObject.makeSureArticleWasDeleted(first_article_title);
        ArticlePageObject.makeSureArticlePresentByTitle(second_article_title);
        ArticlePageObject.clickOnUndeletedArticle(second_article_title);
        String second_article_title_after_deleting_the_first_one = ArticlePageObject.getArticleTitle();

        assertTrue("Article tittle does not match after deletion from reading list",
                second_article_title.contentEquals(second_article_title_after_deleting_the_first_one));
    }

}
