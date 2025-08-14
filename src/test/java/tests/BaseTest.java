package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import seleniumCore.DriverManager;
import seleniumCore.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {
    DriverManager driverManager;
    WebDriver driver;

    public void init(String browser) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getDriver();
    }

    public void quit(){
        driverManager.quitDriver();
    }

    public void openApp(String env) throws Exception {
        env = env.toUpperCase();
        switch (env){
            case "LOCAL":{
                driver.get("127.0.0.1");
            }
            break;
            case "PROD":{
                driver.get("https://www.booking.com/");
            }
            break;
            default: throw new Exception("Enviroment: "+ env + " is not supported!");
        }
    }

    //        hvata screenshot i snima
    public void takeScreenshot(String fileName) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("results/screenshots/"+ fileName+".png"));

    }

    //        uzima screenshot, ubaci, otvori i omoguci da se cita
    public void reportScreenshot(String fileName, String desc) throws IOException {
        takeScreenshot(fileName);
        Path content = Paths.get("results/screenshots/"+ fileName+".png");
        try(InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(desc, is);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

}
