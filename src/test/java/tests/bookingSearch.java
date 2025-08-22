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
//
//    @Test
//    @Parameters({"env"})
//    public void searchPlace(String env) throws Exception {
//        openApp(env);
//        HomePagePF homePagePF = new HomePagePF(driver);
//        homePagePF.searchPlace("Kopaonik", "2025-09-01", "2025-09-05", 2,0,2);
//        Assert.assertEquals(driver.findElement(By.cssSelector("[aria-label='Search results updated. Kopaonik: 728 properties found. Sorted by: Our top picks.']")).getText(), "Kopaonik: 728 properties found");
//    }

//    @Test
//    @Parameters({"env"})
//    public void verifyNumberOfMenuItems(String env) throws Exception {
//        openApp(env);
//        HomePagePF homePagePF = new HomePagePF(driver);
//        homePagePF.verifyMenuItems();
//    }

//    @Test
//    @Parameters({"env"})
//    public void verifyCarouselButtonForRightAndLeft(String env) throws Exception {
//        openApp(env);
//        HomePagePF homePagePF = new HomePagePF(driver);
//        homePagePF.checkCarouselButton();
//    }

//    @Test
//    @Parameters({"env"})
//    public void verifyColorOfTheText(String env) throws Exception {
//        openApp(env);
//        HomePagePF homePagePF = new HomePagePF(driver);
//        WebElement getColorOfText = driver.findElement(By.cssSelector(".f546354b44"));
//        homePagePF.verifyColor(getColorOfText);
//    }

//    @Test
//    @Parameters({"env"})
//    public void clickImageCheck(String env) throws Exception {
//        openApp(env);
//        HomePagePF homePagePF = new HomePagePF(driver);
//        homePagePF.clickImagePlace();
//        Assert.assertEquals(driver.findElement(By.cssSelector("[aria-label='Search results updated. Vrnjačka Banja: 98 properties found. Sorted by: Our top picks.']")).getText(), "Vrnjačka Banja: 98 properties found");
//    }

//    @Test
//    @Parameters({"env"})
//    public void selectPriceForPlace(String env) throws Exception {
//        openApp(env);
//        HomePagePF homePagePF = new HomePagePF(driver);
//        homePagePF.clickImagePlaceWithAdults(4,0,2,15000, 20000);
//        Assert.assertEquals(driver.findElement(By.cssSelector("[aria-label='Search results updated. Vrnjačka Banja: 37 properties found. Sorted by: Our top picks.']")).getText(), "Vrnjačka Banja: 37 properties found");
//
//    }


    @Test
    @Parameters({"env"})
    public void chooseTheCheapestProperty(String env) throws Exception {
        openApp(env);
        HomePagePF homePagePF = new HomePagePF(driver);
        homePagePF.clickSortByLowPrice();
    }


}
