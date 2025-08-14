package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePagePF extends BasePage {

    public HomePagePF(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Where are you going?']")
    WebElement places;

    @FindBy(css = "[type='submit']")
    WebElement searchButton;

    @FindBy(css ="[data-testid='searchbox-dates-container']")
    WebElement dateSearch;

    @FindBy(css ="[data-testid='occupancy-config']")
    WebElement numOfPeopleAndRooms;


    //       - Odabir mesta
    public void selectPlaces(String place) throws Exception {
        Thread.sleep(200);
        click(places);
        driver.findElement(By.cssSelector("[value]")).sendKeys("Kopaonik");
    }

    public void selectDate(String dateOnCheck, String dateOffCheck)throws Exception {
//        Thread.sleep(200);
        WebDriverWait webDriverWait1 = new WebDriverWait(driver, Duration.ofSeconds(5));  //cekaj da odabere
        webDriverWait1.until(ExpectedConditions.elementToBeClickable(dateSearch)); // cekaj da se ispune uslovi, da bude klikabilan
        click(dateSearch);
        click(driver.findElement(By.cssSelector("[data-date=\""+dateOnCheck+"\"]")),"Date on : "+dateOnCheck);
        click(driver.findElement(By.cssSelector("[data-date=\""+dateOffCheck+"\"]")),"Date off : "+dateOffCheck);
    }

//    public void selectPeopleAndRooms(String people, String room)throws Exception {
////        Thread.sleep(200);
//        WebDriverWait webDriverWait1 = new WebDriverWait(driver, Duration.ofSeconds(5));  //cekaj da odabere
//        webDriverWait1.until(ExpectedConditions.elementToBeClickable(numOfPeopleAndRooms)); // cekaj da se ispune uslovi, da bude klikabilan
//        click(numOfPeopleAndRooms);
//        click(driver.findElement(By.cssSelector("span=\""+people+"\"")),"People check : "+people);
//        click(driver.findElement(By.cssSelector("[data-date=\""+dateOffCheck+"\"]")),"Date off : "+dateOffCheck);
//    }

    //        - Slanje upita
    public void clickOnSearchButton(){
        searchButton.click();
    }

    public void searchPlace(String place, String dateOn, String dateOff) throws Exception {
        selectPlaces(place);
        selectDate(dateOn, dateOff);
//        selectDateOff(dateOff);
        clickOnSearchButton();
    }
}
