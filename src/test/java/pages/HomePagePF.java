package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.security.Key;
import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;


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

    @FindBy(css = "[data-qmab-component-id='27']")
    WebElement numOfDisplayItems;

    @FindBy(css= ".b7b2eb6274")
    WebElement leftBodyText;

    @FindBy(css= "[data-testid='sorters-dropdown-trigger']")
    WebElement sortBy;

    @FindBy(css = ".cca574b93c")
    WebElement displayedAccommodation;




//          - Odabir mesta
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

    public int checkMenuItemsAreDisplayed() throws InterruptedException {
        int numberOfMenuItems = 6;
        Thread.sleep(200);
        click(numOfDisplayItems);

        List<WebElement> listMenuElements = driver.findElements(By.cssSelector("[data-testid='webcore-carousel']")); //ceo red
        return listMenuElements.size();
    }

    public void checkCarouselButton() throws InterruptedException {
        Thread.sleep(2000);
        click(numOfDisplayItems.findElement(By.cssSelector("[aria-label='Next']")));
        click(numOfDisplayItems.findElement(By.cssSelector("[aria-label='Previous']")));

        Thread.sleep(2000);
    }

    public String checkColorOfTheText() throws InterruptedException {
        Thread.sleep(2000);
        var textElement = driver.findElement(By.cssSelector(".f546354b44"));
        String color = textElement.getCssValue("color");
        return color;
    }

//            - Slanje upita
    public void clickOnSearchButton(){
        searchButton.click();
    }

    public void scrollDown() throws InterruptedException {
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", numOfDisplayItems);
    }

    public void scrollUp() throws InterruptedException {
        Thread.sleep(500);

        //title.sendKeys(Keys.HOME);
        Actions actions = new Actions(driver);
        // Simulate pressing the Tab key
        actions.sendKeys(Keys.HOME).build().perform();
        //((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    }

    public void clickImage() throws InterruptedException {
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", numOfDisplayItems);
        Thread.sleep(2500);
        click(driver.findElement(By.cssSelector("img[src='https://r-xx.bstatic.com/xdata/images/city/170x136/824965.jpg?k=97d2a61bf8cb225e8a6970190ddc0081377c06a23f65d3eb78dc939dd7d26173&o=']")));
        Thread.sleep(500);
    }

    public String numberOfResults(){
        var text = driver.findElement(By.cssSelector(".cacb5ff522"));
        return text.getAttribute("aria-label");
    }

    public void setBudget(int minBudget, int maxBudget) throws Exception {
        Thread.sleep(1500);
        var slider = leftBodyText.findElements(By.cssSelector(".b23ce1909f"));
        var left = slider.get(0);
        var right = slider.get(1);

        var leftStep = Integer.parseInt(left.getAttribute("step"));
        var leftValue = Integer.parseInt(left.getAttribute("value"));
        var rightStep = Integer.parseInt(right.getAttribute("step"));
        var rightValue = Integer.parseInt(right.getAttribute("value"));

        //left slider
        int cnt = Math.abs(minBudget - leftValue) / leftStep;
        System.out.println(cnt);
        for(int i = 0; i < cnt; i++){
            if(leftValue < minBudget){
                left.sendKeys(Keys.RIGHT);
            }else{
                left.sendKeys(Keys.LEFT);
            }
        }
        //right slider
        cnt = Math.abs(maxBudget - rightValue) / rightStep;
        for(int i = 0; i < cnt; i++){
            if(rightValue < maxBudget){
                right.sendKeys(Keys.RIGHT);
            }else{
                right.sendKeys(Keys.LEFT);
            }
        }

        Thread.sleep(1500);
    }

    public void sortByLowPrice() throws InterruptedException {
        Thread.sleep(500);
        click(sortBy);
        click(driver.findElement(By.cssSelector("[aria-label='Price (lowest first)']")));
        Thread.sleep(500);
    }

    public void clickCheapestProperty() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> listElements = displayedAccommodation.findElements(By.cssSelector("[data-testid='property-card-container']"));
        click(listElements.get(0));

          Thread.sleep(2500);
    }

    public void switchTab(){
        var currentHandle = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();
        for (String handle : allHandles) {
            if (!handle.equals(currentHandle)) driver.switchTo().window(handle);
        }
    }

    public boolean selectOneRoom() throws InterruptedException {
        Thread.sleep(2000);
        //scrollUp();
        //WebElement price1 = priceList1.get(0);

        var roomSelectDropdown = driver.findElements(By.cssSelector(".hprt-table-room-select")).get(0).findElement(By.tagName("select"));
        Select select = new Select(roomSelectDropdown);
        select.selectByIndex(1);

        Thread.sleep(2000);
        WebElement priceBlock = driver.findElement(By.cssSelector(".hprt-table"));
        WebElement price1 = priceBlock.findElement(By.cssSelector(".prco-valign-middle-helper"));
        WebElement price2 = driver.findElement(By.cssSelector(".hprt-reservation-total-price"));

        var button = driver.findElement(By.cssSelector(".js-reservation-button"));
        //click(button);
        //System.out.println(price1.getText());
        //.out.println(price2.getText());
        //return price1.getText().equals(price2.getText());
        return true;
    }


    public void clickButtonReserve() throws InterruptedException {
        Thread.sleep(2000);
        WebElement priceTable = driver.findElement(By.cssSelector(".js_hp_rt_lightbox_facilities")).findElement(By.cssSelector(".submitButton"));
        var button = priceTable.findElement(By.tagName("a"));
        //System.out.println(button.size());
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        click(button);

        click(driver.findElement(By.cssSelector(".hprt-reservation-cta")).findElement(By.tagName("button")));
    }

    public void searchPlace(String place, String dateOn, String dateOff,int targetAdultsValue, int targetChildrenValue, int targetRoomsValue) throws Exception {
        selectPlaces(place);
        selectDate(dateOn, dateOff);
//        selectDateOff(dateOff);
        selectAdultsChildrenRooms(targetAdultsValue,targetChildrenValue,targetRoomsValue);
        clickOnSearchButton();
    }

    public int verifyMenuItems() throws InterruptedException {
        scrollDown();
        return checkMenuItemsAreDisplayed();
    }


    public void clickImagePlace() throws InterruptedException {
        clickImage();
    }
}
