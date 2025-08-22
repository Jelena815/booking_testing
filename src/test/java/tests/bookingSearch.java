package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePagePF;
import java.io.IOException;

public class bookingSearch extends BaseTest{
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) throws Exception {
        init(browser);
    }


    @AfterMethod
    @Parameters({"quit"})
    public void tearDown(String quit) throws IOException {
        if(quit.equalsIgnoreCase("YES")) {
            reportScreenshot("successClick","Successful to click");
            quit();
        }
    }

    @Test
    @Parameters({"env"})
    public void test1(String env) throws Exception {
        openApp(env);
        HomePagePF homePagePF = new HomePagePF(driver);
        homePagePF.searchPlace("Kopaonik", "2025-09-01", "2025-09-05", 2,0,2);
        String numOfResults = homePagePF.numberOfResults();
        Assert.assertTrue(numOfResults.contains("Kopaonik: 733 properties found."));
    }

    @Test
    @Parameters({"env"})
    public void test2(String env) throws Exception {
        openApp(env);
        HomePagePF homePagePF = new HomePagePF(driver);
        int numberOfItems = homePagePF.verifyMenuItems();
        Assert.assertEquals(numberOfItems, 6);
        homePagePF.checkCarouselButton();
        String color = homePagePF.checkColorOfTheText();
        Assert.assertEquals(color, "rgba(26, 26, 26, 1)");
        homePagePF.clickImagePlace();
        String numOfResults = homePagePF.numberOfResults();
        Assert.assertTrue(numOfResults.contains("Vrnjačka Banja: 53 properties found."));
        //Assert.assertEquals(numOfResults, "Search results updated. Vrnjačka Banja: 56 properties found. Sorted by: Our top picks.");
        homePagePF.setBudget(15000, 20000);
        numOfResults = homePagePF.numberOfResults();
        Assert.assertTrue(numOfResults.contains("Vrnjačka Banja: 12 properties found."));
        //Assert.assertEquals(numOfResults, "Search results updated. Vrnjačka Banja: 12 properties found. Applied filters: RSD 15,000 – RSD 20,000 (per night). Sorted by: Our top picks");
        homePagePF.sortByLowPrice();
        homePagePF.clickCheapestProperty();
        homePagePF.switchTab();
        Assert.assertTrue(homePagePF.selectOneRoom());
        //homePagePF.clickSortByLowPrice(4,0,2);
    }



}
