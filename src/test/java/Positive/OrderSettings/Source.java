package Positive.OrderSettings;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyUtils;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Source extends baseTest {

    String sourceName;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickSettings");
        webSteps.click("ClickOrderSettings");
        webSteps.click("Sources");
    }

    @Test(priority = 1)
    public void addSource() throws InterruptedException, AWTException {
        extentReportManager.startTest("Source Functionality", "<b>Add Source</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully add a source</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Settings " +
                "<br>Step 3- Click Order Settings " +
                "<br>Step 4- Click Sources " +
                "<br>Step 5- Click 'Add New Source' Button" +
                "<br>Step 6- Fill Details" +
                "<br>Step 7- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewSource");
        sourceName = webSteps.generateRandomSourceName();
        webSteps.type(sourceName,"Source_Name");
        webSteps.click("Source_Icon");
        webSteps.click("OtherIcon");
        webSteps.type("Testing Source","Source_Remark");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Source created successfully",webSteps.getText("ToastMessage"));
        PropertyUtils.setProperty("Source_Name", sourceName);
    }

    @Test(priority = 2)
    public void editSource() throws InterruptedException, AWTException {
        extentReportManager.startTest("Source Functionality", "<b>Edit Source</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully edit a source</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Settings" +
                "<br>Step 3- Click Order Settings" +
                "<br>Step 4- Click Sources" +
                "<br>Step 5- Search the source to be edited" +
                "<br>Step 6- Click Edit Action" +
                "<br>Step 7- Modify the necessary fields" +
                "<br>Step 8- Click 'Update' Button"
        );
        webSteps.passValue("Source Name","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Source_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action1");
        sourceName = webSteps.generateRandomSourceName();
        webSteps.type(sourceName, "Source_Name");
        webSteps.click("Source_Icon");
        webSteps.click("OtherIcon");
        webSteps.type("Updated Source", "Source_Remark");
        webSteps.click("UpdateButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Source updated successfully", webSteps.getText("ToastMessage"));
        PropertyUtils.setProperty("Source_Name", sourceName);
    }

    @DataProvider(name = "sourceSearchData")
    public Object[][] sourceSearchData() {
        return new Object[][]{
                {"Source Name", PropertyUtils.getProperty("Source_Name"), 1}
        };
    }

    @Test(dataProvider = "sourceSearchData", priority = 3)
    public void searchSource(String criteriaType, String inputValue, int columnIndex) throws InterruptedException, AWTException {
        extentReportManager.startTest("Source Functionality", "<b>Search Source Using " + criteriaType + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the source can be searched by " + criteriaType.toLowerCase() + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System" +
                "<br>Step 2 - Click Settings" +
                "<br>Step 3 - Click Order Settings" +
                "<br>Step 4 - Click Sources" +
                "<br>Step 5 - Select '" + criteriaType + "' from 'Search By' dropdown" +
                "<br>Step 6 - Enter Search Input" +
                "<br>Step 7 - Click Search"
        );
        webSteps.passValue(criteriaType, "SearchBy_Dropdown");
        webSteps.type(inputValue, "SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        String actualResult = webSteps.getTableCellText(1, columnIndex);
        Assert.assertEquals(actualResult.trim(), inputValue.trim(), "Search result mismatch for criteria: " + criteriaType);
    }

    @Test(priority = 4)
    public void deactivateSource() throws InterruptedException, AWTException {
        extentReportManager.startTest("Source Functionality", "<b>Deactivate Source</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully deactivate a source</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System" +
                "<br>Step 2 - Click Settings" +
                "<br>Step 3 - Click Order Settings" +
                "<br>Step 4 - Click Sources" +
                "<br>Step 5 - Search the Source" +
                "<br>Step 6 - Click Deactivate Action" +
                "<br>Step 7 - Confirm Deactivation & Click 'Deactivate' Button"
        );
        webSteps.passValue("Source Name", "SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Source_Name"), "SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action2");
        webSteps.click("DeactivateButton");
        Assert.assertEquals("Source active status toggled successfully", webSteps.getText("ToastMessage"));
    }

    @Test(priority = 5)
    public void activateSource() throws InterruptedException, AWTException {
        extentReportManager.startTest("Source Functionality", "<b>Activate Source</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully activate a source</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System" +
                "<br>Step 2 - Click Settings" +
                "<br>Step 3 - Click Order Settings" +
                "<br>Step 4 - Click Sources" +
                "<br>Step 5 - Search the Source" +
                "<br>Step 6 - Click Activate Action" +
                "<br>Step 7 - Confirm Activation & Click 'Activate' Button"
        );
        webSteps.passValue("Source Name", "SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Source_Name"), "SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action2");
        webSteps.click("ActivateButton");
        Assert.assertEquals("Source active status toggled successfully", webSteps.getText("ToastMessage"));
    }
}
