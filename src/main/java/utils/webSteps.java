package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import static dataProviders.repositoryFileReader.constructElement;
import static dataProviders.repositoryFileReader.findElementRepo;

public class webSteps {
    protected static WebDriver driver;
    private final String businessName;
    private final String emailAddress;
    private final String password;


    public webSteps(WebDriver driver) {
        webSteps.driver = driver;

        Properties properties = propertyLoader.loadProperties("src/main/resources/dataset.properties");
        this.businessName = properties.getProperty("businessName");
        this.emailAddress = properties.getProperty("emailAddress");
        this.password = properties.getProperty("password");
    }

    public void login() throws InterruptedException {
        waiting();
        type(businessName, "Login_BusinessNameField");
        type(emailAddress, "Login_EmailAddressField");
        type(password, "Login_PasswordField");
        click("LoginButton");
        waiting();
    }

    public void type(String text, String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement inputField = driver.findElement(xpath);
        inputField.clear();
        inputField.sendKeys(text);
        waiting();
    }


    public void click(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement button =  driver.findElement(xpath);
        button.click();
        waiting();
    }

    public String getText(String locator) {
        By xpath = constructElement(findElementRepo(locator));
        return driver.findElement(xpath).getText();
    }

    public void waiting() throws InterruptedException {
        Thread.sleep(2000);
    }

    public void scrollToElement(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
        waiting();

    }

    public void select(String locator, int loop, int bool) throws InterruptedException, AWTException {
        By xpath = constructElement(findElementRepo(locator));
        click(locator);

        Robot robot = new Robot();

        if(bool==1){
            for (int i=1;i<=loop; i++){
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);

                Thread.sleep(100);
            }

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


        }else if(bool==0){
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } else {
            System.out.println("The boolean value is invalid");
        }

        waiting();
    }

    public void implicitWait(String locator){
        By xpath = constructElement(findElementRepo(locator));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {

            wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
        } catch (TimeoutException e) {
            System.out.println("Element not found after login: " + e.getMessage());

        }
    }
}
