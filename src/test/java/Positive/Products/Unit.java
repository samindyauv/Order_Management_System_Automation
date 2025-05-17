package Positive.Products;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyUtils;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Unit extends baseTest {

    String unitName;
    String shortName;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickProducts");
        webSteps.click("ClickUnits");
    }

    @Test(priority = 1)
    public void addUnit() throws InterruptedException, AWTException {
        extentReportManager.startTest("Units Functionality", "<b>Add Unit</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add an unit</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products " +
                "<br>Step 3- Click Units " +
                "<br>Step 4- Click 'Add New Unit' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewUnit");
        webSteps.type(webSteps.generateRandomUnitName(),"Unit_Name");
        webSteps.type(webSteps.generateRandomUnitShortName(),"Short_Name");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Unit created successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @DataProvider(name = "searchUnit")
    public Object[][] searchUnit() {
        return new Object[][] {
                { "Unit Name",1, PropertyUtils.getProperty("Unit_Name")},
                { "Short Name",2, PropertyUtils.getProperty("Short_Name")}
        };
    }
    @Test(priority = 2, dataProvider = "searchUnit")
    public void searchUnit(String searchBy,int tableColumnIndex, String searchInput) throws InterruptedException, AWTException {
        extentReportManager.startTest("Units Functionality", "<b>Search Unit</b>");
        extentReportManager.testSteps(
                "<b><font color='blue'>Test Case : </font>TC02: Verify that the user can successfully search an unit</b> " +
                        "<br><b><font color='blue'>Test Steps : </font></b>" +
                        "<br>Step 1 - Login to the System" +
                        "<br>Step 2 - Click Products" +
                        "<br>Step 3 - Click Units" +
                        "<br>Step 4 - Select '" + searchBy + "' from 'Search By' dropdown" +
                        "<br>Step 5 - Enter Search Input" +
                        "<br>Step 6 - Click Search"
        );
        webSteps.passValue(searchBy,"SearchBy_Dropdown");
        webSteps.type(searchInput, "SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        String actualResult = webSteps.getTableCellText(1, tableColumnIndex);
        Assert.assertEquals(actualResult, searchInput, "Search result does not match input value.");
    }

    @Test(priority = 3)
    public void editUnit() throws InterruptedException, AWTException {
        extentReportManager.startTest("Units Functionality", "<b>Edit Unit</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit an unit</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products " +
                "<br>Step 3- Click Units " +
                "<br>Step 4 - Search the unit name that needs to be edited" +
                "<br>Step 5 - Click Edit Action" +
                "<br>Step 6 - Make the necessary changes" +
                "<br>Step 7 - Click Update"
        );
        webSteps.passValue("Short Name","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Short_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action1");
        webSteps.type(webSteps.generateRandomUnitName(),"Unit_Name");
        webSteps.type(webSteps.generateRandomUnitShortName(),"Short_Name");
        webSteps.click("AllowDecimalCheckBox");
        webSteps.click("UpdateButton");
        Assert.assertEquals("Unit updated successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
