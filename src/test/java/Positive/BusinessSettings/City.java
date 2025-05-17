package Positive.BusinessSettings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class City extends baseTest {

    String cityName;
    String postalCode;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickSettings");
        webSteps.click("ClickBusinessSettings");
        webSteps.click("Cities");
    }

    @Test(priority = 1)
    public void addCity() throws InterruptedException, AWTException {
        extentReportManager.startTest("Cities Functionality", "<b>Add City</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a city</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Business Settings " +
                "<br>Step 4- Click Cities " +
                "<br>Step 5- Click 'Add New City' Button" +
                "<br>Step 6- Fill Details" +
                "<br>Step 7- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewCity");
        webSteps.type(webSteps.generateRandomCityName(),"City_Name");
        webSteps.type(webSteps.generateRandomCityPostalCode(),"City_PostalCode");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("City created successfully",webSteps.getText("ToastMessage"));
    }
}
