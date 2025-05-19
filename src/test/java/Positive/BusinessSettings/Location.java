package Positive.BusinessSettings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyUtils;
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

    @DataProvider(name = "locationSearchData")
    public Object[][] locationSearchData() {
        return new Object[][]{
                {"Location", PropertyUtils.getProperty("Location_Name"), 2},
                {"City", PropertyUtils.getProperty("Location_City"), 4},
                {"Email", PropertyUtils.getProperty("Location_Email"), 6},
                {"Address", PropertyUtils.getProperty("Location_Address"), 3}
        };
    }

    @Test(dataProvider = "locationSearchData", priority = 2)
    public void searchLocation (String criteriaType,String inputValue, int columnIndex) throws InterruptedException, AWTException {
        extentReportManager.startTest("Locations Functionality", "<b>Search Location Using " + criteriaType + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the location can search by " + criteriaType.toLowerCase() + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Business Settings " +
                "<br>Step 4- Click Locations " +
                "<br>Step 5 - Select '" + criteriaType + "' from 'Search By' dropdown" +
                "<br>Step 6 - Enter Search Input" +
                "<br>Step 7 - Click Search"
        );
        webSteps.passValue(criteriaType,"SearchBy_Dropdown");
        webSteps.type(inputValue,"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        String actualResult = webSteps.getTableCellText(1, columnIndex);
        Assert.assertEquals(actualResult.trim(), inputValue.trim(), "Search result mismatch for criteria: " + criteriaType);
    }

    @Test(priority = 3)
    public void editLocation() throws InterruptedException, AWTException {
        extentReportManager.startTest("Locations Functionality", "<b>Edit Location</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit a location</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Business Settings " +
                "<br>Step 4- Click Locations " +
                "<br>Step 3- Select a Location" +
                "<br>Step 4- Click Edit Action" +
                "<br>Step 5- Edit Details" +
                "<br>Step 6- Click 'Update' Button"
        );
        webSteps.passValue("Location","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Location_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action1");
        webSteps.type(webSteps.generateRandomLocationName(),"Location_Name");
        webSteps.type(webSteps.generateRandomLocationAddress(),"Location_Address");
        webSteps.type(webSteps.generateRandomLocationCity(),"Location_City");
        webSteps.type(webSteps.generateRandomLocationContactNo(),"Location_ContactNo");
        webSteps.type(webSteps.generateRandomLocationEmail(),"Location_Email");
        webSteps.click("UpdateButton");
        Assert.assertEquals("Location updated successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
