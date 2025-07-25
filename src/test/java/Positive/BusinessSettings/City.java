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
        extentReportManager.startTest("City Functionality", "<b>Add City</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully add a city</b>");
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
        cityName = webSteps.generateRandomCityName();
        webSteps.type(cityName,"City_Name");
        postalCode = webSteps.generateRandomCityPostalCode();
        webSteps.type(postalCode,"City_PostalCode");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");

        String toastMessage = webSteps.getText("ToastMessage").trim();
        System.out.println("Toast Message: >" + toastMessage + "<");
        try {
            Assert.assertEquals("City created successfully", toastMessage);
        } catch (AssertionError e) {
            Assert.fail("Expected 'City created successfully' but got: " + toastMessage);
        }
        PropertyUtils.setProperty("City_Name", cityName);
        PropertyUtils.setProperty("City_PostalCode", postalCode);
    }


    @Test(priority = 2)
    public void editCity() throws InterruptedException, AWTException {
        extentReportManager.startTest("City Functionality", "<b>Edit City</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully edit a city</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Business Settings " +
                "<br>Step 4- Click Cities " +
                "<br>Step 5- Select a City" +
                "<br>Step 6- Click Edit Action" +
                "<br>Step 7- Edit Details" +
                "<br>Step 8- Click 'Update' Button"
        );
        webSteps.passValue("City Name","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("City_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action1");
        cityName = webSteps.generateRandomCityName();
        webSteps.type(cityName,"City_Name");
        postalCode = webSteps.generateRandomCityPostalCode();
        webSteps.type(postalCode,"City_PostalCode");
        PropertyUtils.setProperty("cityID",webSteps.getValue("Save_cityID"));
        webSteps.click("UpdateButton");
        Assert.assertEquals("City updated successfully",webSteps.getText("ToastMessage"));
        PropertyUtils.setProperty("City_Name", cityName);
        PropertyUtils.setProperty("City_PostalCode", postalCode);
    }

    @DataProvider(name = "citySearchData")
    public Object[][] citySearchData() {
        return new Object[][]{
                {"City Name", PropertyUtils.getProperty("City_Name"), 2},
                {"City ID", PropertyUtils.getProperty("cityID"), 1},
                {"Postal Code", PropertyUtils.getProperty("City_PostalCode"), 3}
        };
    }

    @Test(dataProvider = "citySearchData", priority = 3)
    public void searchCity (String criteriaType,String inputValue, int columnIndex) throws InterruptedException, AWTException {
        extentReportManager.startTest("City Functionality", "<b>Search City Using " + criteriaType + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the city can search by " + criteriaType.toLowerCase() + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Business Settings " +
                "<br>Step 4- Click Cities " +
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


    @Test(priority = 4)
    public void deactivateCity() throws InterruptedException, AWTException {
        extentReportManager.startTest("City Functionality", "<b>Deactivate City</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully deactivate a city</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Business Settings " +
                "<br>Step 4- Click Cities " +
                "<br>Step 5- Select a City" +
                "<br>Step 6- Click Deactivate Action" +
                "<br>Step 7- Confirm Deactivation & Click 'Deactivate' Button"
        );
        webSteps.passValue("City Name","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("City_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action2");
        webSteps.click("DeactivateButton");
        Assert.assertEquals("City active status toggled successfully",webSteps.getText("ToastMessage"));
    }

    @Test(priority = 5)
    public void activateCity() throws InterruptedException, AWTException {
        extentReportManager.startTest("City Functionality", "<b>Activate City</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully activate a city</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Business Settings " +
                "<br>Step 4- Click Cities " +
                "<br>Step 5- Select a City" +
                "<br>Step 6- Click Activate Action" +
                "<br>Step 7- Confirm Activation & Click 'Activate' Button"
        );
        webSteps.passValue("City Name","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("City_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action2");
        webSteps.click("ActivateButton");
        Assert.assertEquals("City active status toggled successfully",webSteps.getText("ToastMessage"));
    }
}
