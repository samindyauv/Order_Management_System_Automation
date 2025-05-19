package Positive.BusinessSettings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Location extends baseTest {

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickSettings");
        webSteps.click("ClickBusinessSettings");
        webSteps.click("Locations");
    }

    @Test(priority = 1)
    public void addLocation() throws InterruptedException, AWTException {
        extentReportManager.startTest("Locations Functionality", "<b>Add Location</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a location</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Business Settings " +
                "<br>Step 4- Click Locations " +
                "<br>Step 5- Click 'Add New Location' Button" +
                "<br>Step 6- Fill Details" +
                "<br>Step 7- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewLocation");
        webSteps.type(webSteps.generateRandomLocationName(),"Location_Name");
        webSteps.type(webSteps.generateRandomLocationAddress(),"Location_Address");
        webSteps.type(webSteps.generateRandomLocationCity(),"Location_City");
        webSteps.type(webSteps.generateRandomLocationContactNo(),"Location_ContactNo");
        webSteps.type(webSteps.generateRandomLocationEmail(),"Location_Email");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Location created successfully",webSteps.getText("ToastMessage"));
    }

}
