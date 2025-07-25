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

    String locationName;
    String locationAddress;
    String locationCity;
    String locationContactNo;
    String locationEmail;

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
        locationName = webSteps.generateRandomLocationName();
        webSteps.type(locationName,"Location_Name");
        locationAddress = webSteps.generateRandomLocationAddress();
        webSteps.type(locationAddress,"Location_Address");
        locationCity = webSteps.generateRandomLocationCity();
        webSteps.type(locationCity,"Location_City");
        locationContactNo = webSteps.generateRandomLocationContactNo();
        webSteps.type(locationAddress,"Location_ContactNo");
        locationEmail = webSteps.generateRandomLocationEmail();
        webSteps.type(locationEmail,"Location_Email");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Location created successfully",webSteps.getText("ToastMessage"));
        PropertyUtils.setProperty("Location_Name", locationName);
        PropertyUtils.setProperty("Location_Address", locationAddress);
        PropertyUtils.setProperty("Location_City", locationCity);
        PropertyUtils.setProperty("Location_ContactNo", locationContactNo);
        PropertyUtils.setProperty("Location_Email", locationEmail);
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
        locationName = webSteps.generateRandomLocationName();
        webSteps.type(locationName,"Location_Name");
        locationAddress = webSteps.generateRandomLocationAddress();
        webSteps.type(locationAddress,"Location_Address");
        locationCity = webSteps.generateRandomLocationCity();
        webSteps.type(locationCity,"Location_City");
        locationContactNo = webSteps.generateRandomLocationContactNo();
        webSteps.type(locationAddress,"Location_ContactNo");
        locationEmail = webSteps.generateRandomLocationEmail();
        webSteps.type(locationEmail,"Location_Email");
        webSteps.click("UpdateButton");
        Assert.assertEquals("Location updated successfully",webSteps.getText("ToastMessage"));
    }
}
