package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
}
