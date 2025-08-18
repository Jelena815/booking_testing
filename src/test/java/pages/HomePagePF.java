package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
    WebElement numOfAdultChildrenRoom;

    @FindBy(css = "[data-testid='header-logo']")
    WebElement homeButton;


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

//    public void selectAdultsChildrenRooms(String adult, String children, String room){
//        click(numOfAdultChildrenRoom);
//        boolean polje = String.(driver.findElement(By.cssSelector("[class='e484bb5b7a']")));
//        boolean adultText = String.valueOf(driver.findElement(By.xpath("//label[text()='Adults']")));
//        boolean childrenText = String.valueOf(driver.findElement(By.xpath("//label[text()='Children']")));
//        boolean roomText = String.valueOf(driver.findElement(By.xpath("////label[text()='Rooms']")));
//
//        if(polje && adultText){}
//    }

public void selectAdultsChildrenRooms(int targetAdultsValue, int targetChildrenValue, int targetRoomsValue) throws InterruptedException {
    Thread.sleep(200);
    click(numOfAdultChildrenRoom);
    List<WebElement> listElements =  driver.findElements(By.cssSelector(".e484bb5b7a"));  //ceo deo

    int currentAdultsValue = Integer.parseInt(listElements.get(0).findElement(By.cssSelector(".e32aa465fd")).getText());
    List<WebElement> adultsPlusMinus = listElements.get(0).findElements(By.cssSelector("[type='button']"));
    if(targetAdultsValue > currentAdultsValue){
        for (int i = 0; i < targetAdultsValue - currentAdultsValue; i++){
            //+
            click(adultsPlusMinus.get(1));
        }
    }else if(targetAdultsValue < currentAdultsValue)
    {
        for (int i = 0; i < currentAdultsValue - targetAdultsValue; i++){
            //-
            click(adultsPlusMinus.get(0));
        }
    }

    int currentChildrenValue = Integer.parseInt(listElements.get(1).findElement(By.cssSelector(".e32aa465fd")).getText());
    List<WebElement> childrenPlusMinus = listElements.get(1).findElements(By.cssSelector("[type='button']"));
    if(targetChildrenValue > currentChildrenValue){
        for (int i = 0; i < targetChildrenValue - currentChildrenValue; i++){
            //+
            click(childrenPlusMinus.get(1));
        }
    }else if(targetChildrenValue < currentChildrenValue)
    {
        for (int i = 0; i < currentChildrenValue - targetChildrenValue; i++){
            //-
            click(childrenPlusMinus.get(0));
        }
    }

    int currentRoomsValue = Integer.parseInt(listElements.get(2).findElement(By.cssSelector(".e32aa465fd")).getText());
    List<WebElement> roomsPlusMinus = listElements.get(2).findElements(By.cssSelector("[type='button']"));
    if(targetRoomsValue > currentRoomsValue){
        for (int i = 0; i < targetRoomsValue - currentRoomsValue; i++){
            //+
            click(roomsPlusMinus.get(1));
        }
    }else if(targetRoomsValue < currentRoomsValue)
    {
        for (int i = 0; i < currentRoomsValue - targetRoomsValue; i++){
            //-
            click(roomsPlusMinus.get(0));
        }
    }
}

    //        - Slanje upita
    public void clickOnSearchButton(){
        searchButton.click();
    }

//    public void goToHomePage() throws InterruptedException {
//        Thread.sleep(200);
//        homeButton.click();
//    }

    public void scrollDown() throws InterruptedException {
        Thread.sleep(7000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,1150)");
        Thread.sleep(7000);

    }

    public void searchPlace(String place, String dateOn, String dateOff,int targetAdultsValue, int targetChildrenValue, int targetRoomsValue) throws Exception {
        selectPlaces(place);
        selectDate(dateOn, dateOff);
        //selectDateOff(dateOff);
//        selectAdultsChildrenRooms();
        selectAdultsChildrenRooms(targetAdultsValue,targetChildrenValue,targetRoomsValue);
        clickOnSearchButton();
    }

    public void verifyMenuItems() throws InterruptedException {
//        goToHomePage();
        scrollDown();
    }
}
