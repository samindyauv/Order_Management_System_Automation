package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

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

    public static void type(String text, String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement inputField = driver.findElement(xpath);

        inputField.click();

        String existingValue = inputField.getAttribute("value");
        if (existingValue != null && !existingValue.isEmpty()) {
            inputField.clear();
            inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }

        inputField.sendKeys(text);
        waiting();
    }

    public static void click(String locator) throws InterruptedException {
        By xpath = constructElement(findElementRepo(locator));
        WebElement button =  driver.findElement(xpath);
        button.click();
        waiting();
    }

    public String getText(String locator) {
        By xpath = constructElement(findElementRepo(locator));
        return driver.findElement(xpath).getText();
    }

    public static void waiting() throws InterruptedException {
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

    public void selectFromDropdown(){
        WebElement dropdown = driver.findElement(By.xpath("//div[@class='overflow-y-auto max-h-60']"));
        List<WebElement> childDivs = dropdown.findElements(By.xpath("./div"));

        Random random = new Random();
        int randomIndex = random.nextInt(childDivs.size());

        childDivs.get(randomIndex).click();

//        WebElement parentDiv = driver.findElement(By.xpath("//div[@class='overflow-y-auto max-h-60']"));
//        List<WebElement> childDivs = parentDiv.findElements(By.tagName("div"));
//
//        for (WebElement child : childDivs) {
//            System.out.println(child.getText());
//        }
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

    public String generateRandomUserName() {
        String randomUserName = "User_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("User_Name", randomUserName);
        return randomUserName;
    }

    public String generateRandomUserAddress() {
        String randomUserAddress = "Address_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("User_Address", randomUserAddress);
        return randomUserAddress;
    }

    public static String generateRandomUserEmail() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        String randomEmail = "user" + randomNum + "@example.com";
        PropertyUtils.setProperty("User_Email", randomEmail);
        return randomEmail;
    }

    public static String generateRandomContactNumber() {
        int contactNumber = ThreadLocalRandom.current().nextInt(100_000_000, 1_000_000_000);
        String contact = String.valueOf(contactNumber);
        PropertyUtils.setProperty("User_ContactNo", contact);
        return contact;
    }

    public String generateRandomRoleName() {
        String randomRoleName = "Role_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Role_Name", randomRoleName);
        return randomRoleName;
    }

    public String generateRandomCustomerName() {
        String randomCustomerName = "Customer_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Customer_Name", randomCustomerName);
        return randomCustomerName;
    }

    public static String generateRandomCustomerNumber() {
        int contactNumber = ThreadLocalRandom.current().nextInt(100_000_000, 1_000_000_000);
        String contact = String.valueOf(contactNumber);
        PropertyUtils.setProperty("Customer_ContactNo", contact);
        return contact;
    }

    public String generateRandomCustomerAddress() {
        String randomCustomerName = "Address_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Customer_Address", randomCustomerName);
        return randomCustomerName;
    }

    public static String generateRandomCustomerEmail() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        String randomEmail = "customer" + randomNum + "@example.com";
        PropertyUtils.setProperty("Customer_Email", randomEmail);
        return randomEmail;
    }

    public String generateRandomCityName() {
        String randomCustomerName = "City_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("City_Name", randomCustomerName);
        return randomCustomerName;
    }

    public static String generateRandomCityPostalCode() {
        int postalCode = ThreadLocalRandom.current().nextInt(10000, 100000); // Ensures 5 digits
        String code = String.valueOf(postalCode);
        PropertyUtils.setProperty("City_PostalCode", code);
        return code;
    }

    public String generateRandomCategoryName() {
        String randomCategoryName = "Category_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Category_Name", randomCategoryName);
        return randomCategoryName;
    }

    public String generateRandomBrandName() {
        String randomBrandName = "Brand_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Brand_Name", randomBrandName);
        return randomBrandName;
    }

    public String generateRandomUnitName() {
        String randomUnitName = "Unit_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Unit_Name", randomUnitName);
        return randomUnitName;
    }

    public String generateRandomUnitShortName() {
        String randomUnitShortName = "UT_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Short_Name", randomUnitShortName);
        return randomUnitShortName;
    }

    public String generateRandomProductName() {
        String randomProductName = "Product_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Product_Name",randomProductName);
        return randomProductName;

    }

    public String generateRandomLocationName() {
        String randomLocationName = "Location_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Location_Name",randomLocationName);
        return randomLocationName;
    }

    public String generateRandomLocationAddress() {
        String randomLocationAddress = "Address_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Location_Address",randomLocationAddress);
        return randomLocationAddress;
    }

    public String generateRandomLocationCity() {
        String randomLocationCity = "City_" + ThreadLocalRandom.current().nextInt(0, 100);
        PropertyUtils.setProperty("Location_City",randomLocationCity);
        return randomLocationCity;
    }

    public static String generateRandomLocationContactNo() {
        int randomLocationContactNo = ThreadLocalRandom.current().nextInt(100_000_000, 1_000_000_000);
        String contact = String.valueOf(randomLocationContactNo);
        PropertyUtils.setProperty("Location_ContactNo", contact);
        return contact;
    }

    public static String generateRandomLocationEmail() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100);
        String randomEmail = "location" + randomNum + "@example.com";
        PropertyUtils.setProperty("Location_Email", randomEmail);
        return randomEmail;
    }

    public String getTableCellText(int row, int col) {
        String xpath = "//tr[" + row + "]/td[" + col + "]";
        WebElement tableCell = driver.findElement(By.xpath(xpath));
        return tableCell.getText().trim();
    }

    public void passValue(String text, String locator){
        By xpath = constructElement(findElementRepo(locator));
        driver.findElement(xpath).sendKeys(text);
    }

    public void searchFromDropdown(String propertyValue, String locator) throws InterruptedException {
        String name = PropertyUtils.getProperty(propertyValue);
        click(locator);
        type(name,"DropdownSearch");
        WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + name + "')]"));
        element.click();
        waiting();
    }

}
