package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line= "Linking Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amountOfSearchResults = SearchPageObject.getAmountOfFindArticles();

        assertTrue(
                "We found too few results",
                amountOfSearchResults > 0
        );

    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "qwessdgcf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }

    @Test
    public void testCancelOfSearch() // Ex.3 Тест: отмена поиска
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Swift";
        SearchPageObject.typeSearchLine(search_line);
        int amountOfSearchResultsBeforeWeCancelSearch = SearchPageObject.getAmountOfFindArticles();

        assertTrue(
                "We found too few results",
                amountOfSearchResultsBeforeWeCancelSearch > 0
        );

        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertSearchInputIsPresentAndEmpty();
    }

    @Test
    public void testSearchResultsContainsMultipleMatching() // Ex.4 Тест: проверка слов в поиске
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Nirvana";
        SearchPageObject.assertSearchResultsContainsMultipleMatchingOfSearchedText(search_line);

    }

//    @Test
//    public void testTest()
//    {
//        SearchPageObject SearchPageObject = new SearchPageObject(driver);
//
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Java");
//        String element = "//*[@resource-id='org.wikipedia:id/page_list_item_container'";
//        String title2 = "@resource-id='org.wikipedia:id/page_list_item_title'";
//
//        String LOCATOR = element + "[descendant::" + title2 + "[@text='Java']]";
//        String LOCATOR = "//*[@resource-id='org.wikipedia:id/page_list_item_container'][[descendant::" +
//                "[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java']";



////book[descendant::title[@lang='it']]
//        Это позволяет вам выбрать все элементы book,
//        содержащие дочерний элемент title (независимо от того, насколько глубоко он вложен),
//        содержащий значение атрибута языка, равное 'it'.
//
//        @resource-id=‘org.wikipedia:id/page_list_item_container’
//
//        book = org.wikipedia:id/page_list_item_container
//        Title = org.wikipedia:id/page_list_item_title
//        атрибут Java
////        '//*[@id="I"]//E'

//        String LOCATOR = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//[2]";
////                +
////                "//*[@resource-id='org.wikipedia:id/page_list_item_description][@text='Island of Indonesia']";
//        System.out.println(LOCATOR);
//
//        SearchPageObject.waitForElementAndClick(
//                By.xpath(LOCATOR),
//                "PZD",
//                7
//                );
//        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
//
//        String title = ArticlePageObject.waitForTitleElement().getAttribute("text");
//
//        System.out.println(title);
//
//
//        String LOCATOR_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
//                "//*[@resource-id='{TITLE}' and //*[@resource-id='{DESCRIPTION}]";
//
//        String LOCATOR_TEST = "//*[@resource-id='org.wikipedia:id/page_list_item_title']*//[@text='{TITLE}']" +
//                " and //*[@resource-id='org.wikipedia:id/page_list_item_description]//*[@text='{DESCRIPTION}']";

//        String LOCATOR = "//*[@resource-id='org.wikipedia:id/page_list_item_title']*//[@text='Java']" +
//                " and //*[@resource-id='org.wikipedia:id/page_list_item_description]//*[@text='Island of Indonesia']";


//*[@text='{DESCRIPTION}'

    }

    //Java
    //Island of Indonesia

//org.wikipedia:id/page_list_item_title /[@text='Island of Indonesia'] [@text='Java']
//org.wikipedia:id/page_list_item_description
// resource-id

//}
// SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']" +
//                "//*[@text='{SUBSTRING}']",
/*
В одном из занятий четвертого урока упоминается о методе темплейтов.
Там рассказано, как работать с локаторами, которые зависят от подстроки SUBSTRING.
В примере из теста у нас всего одна подстрока.
Но подобные локаторы можно строить с любым количеством подстрок.

В приложении Wikipedia результатом поиска является набор ссылок на статьи,
и каждая ссылка содержит как заголовок статьи, так и краткое описание.
Например, для запроса “Java” одним из результатов выдачи будет “Java (Programming language)”
и описание “Object-oriented programming language”.


Задача:

Подобрать локатор, который находит результат поиска одновременно по заголовку и описанию
(если заголовок или описание отличается - элемент не находится).

Добавить соответствующий метод в секцию TEMPLATES METHODS класса SearchPageObject.
 */
