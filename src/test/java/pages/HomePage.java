package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By places = By.cssSelector("[aria-label='Where are you going?']");

    public void selectPlace() {
        driver.findElement(this.places).click();
    }
}
