package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyUtils;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Role extends baseTest {
    String role;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickUser");
        webSteps.click("ClickRoles");
    }

    @Test(priority = 1)
    public void addRole() throws InterruptedException, AWTException {
        extentReportManager.startTest("Roles Functionality", "<b>Add Role</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a role</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click Roles " +
                "<br>Step 3- Click 'Add New Role' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewRoleButton");
        webSteps.type(webSteps.generateRandomRoleName(),"Role_Name");
        webSteps.click("AddPermissionButton");
        webSteps.click("UserAllPermissionCheckbox");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Role created successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @Test(priority = 2)
    public void searchRole() throws InterruptedException, AWTException {
        extentReportManager.startTest("Roles Functionality", "<b>Search Role</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC02: Verify that the user can successfully search a role</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click Roles " +
                "<br>Step 4 - Select Role from 'Search By' dropdown" +
                "<br>Step 4 - Enter Search Input" +
                "<br>Step 5 - Click Search"
        );
        webSteps.passValue("Role","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Role_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        String actualResult = webSteps.getTableCellText(1, 1);
        Assert.assertEquals(actualResult, PropertyUtils.getProperty("Role_Name"), "Search result does not match input value.");
    }

    @Test(priority = 3)
    public void editRole() throws InterruptedException, AWTException {
        extentReportManager.startTest("Roles Functionality", "<b>Edit Role</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit a role</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User" +
                "<br>Step 3- Click Roles" +
                "<br>Step 4 - Search the role name that needs to be edited" +
                "<br>Step 5 - Click Edit Action" +
                "<br>Step 6 - Make the necessary changes" +
                "<br>Step 7 - Click Update"
        );
        webSteps.passValue("Role","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Role_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action1");
        webSteps.type(webSteps.generateRandomRoleName(),"Role_Name");
        webSteps.click("UpdateButton");
        Assert.assertEquals("Role updated successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
