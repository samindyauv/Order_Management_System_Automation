package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;
import java.awt.*;
import java.io.IOException;

public class User extends baseTest {
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickUser");
        webSteps.click("ClickUserList");
    }

    @Test(priority = 1)
    public void addUser() throws InterruptedException, AWTException {
        extentReportManager.startTest("User Functionality", "<b>Add User</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a user</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Click 'Add New User' Button" +
                "<br>Step 5- Fill Primary Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewUserButton");
        webSteps.type("Amal Perera", "AddUser_Name");
        webSteps.click("AddUser_Role");
        webSteps.selectFromDropdown();
        webSteps.type("761234567", "AddUser_ContactNo");
        webSteps.type("amal@gmail.com", "AddUser_Email");
        webSteps.type("Amal@12345", "AddUser_Password");
        webSteps.type("Amal@12345", "AddUser_ConfirmPassword");
        webSteps.type("Kuliyapitiya", "AddUser_Address");
        webSteps.click("SaveButton");
        Assert.assertEquals("User created successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @DataProvider(name = "userSearchData")
    public Object[][] userSearchData() {
        return new Object[][]{
                // criteriaType,inputValue, expectedColumnIndex
                {"Name","Kasun Bandara", 1},
                {"Role", "Admin", 2},
                {"Email","kasun@gmail.com", 4},
                {"Address","Dewalegama,Kegalle", 5}
        };
    }

    @Test(dataProvider = "userSearchData", priority = 2)
    public void searchUser(String criteriaType,String inputValue, int columnIndex) throws InterruptedException, AWTException {
        extentReportManager.startTest("User Functionality", "<b>Search User Using " + criteriaType + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can search by " + criteriaType.toLowerCase() + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System" +
                "<br>Step 2 - Click User" +
                "<br>Step 3 - Click User List" +
                "<br>Step 4 - Select '" + criteriaType + "' from 'Search By' dropdown" +
                "<br>Step 5 - Enter Search Input" +
                "<br>Step 6 - Click Search"
        );

        webSteps.passValue(criteriaType,"SearchBy_Dropdown");
        webSteps.type(inputValue,"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");

        String actualResult = webSteps.getTableCellText(1, columnIndex);
        Assert.assertEquals(actualResult.trim(), inputValue.trim(), "Search result mismatch for criteria: " + criteriaType);
    }

    @Test(priority = 3)
    public void editUser() throws InterruptedException, AWTException {
        extentReportManager.startTest("User Functionality", "<b>Edit User</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit a user</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Select a User" +
                "<br>Step 5- Click Edit Action" +
                "<br>Step 6- Edit Details" +
                "<br>Step 7- Click 'Update' Button"
        );
        webSteps.click("Action1");
        webSteps.type("Amal Perera", "AddUser_Name");
        webSteps.click("AddUser_Role");
        webSteps.selectFromDropdown();
        //webSteps.type("761234567", "AddUser_ContactNo");
        webSteps.type("amal@gmail.com", "AddUser_Email");
        webSteps.type("Kuliyapitiya", "AddUser_Address");
        //webSteps.click("EditUser_UpdateButton");
        //Assert.assertEquals("User added successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @Test(priority = 4)
    public void changeUserPassword() throws InterruptedException, AWTException {
        extentReportManager.startTest("User Functionality", "<b>Change User Password</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC04: Verify that the user can successfully change the user password</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Click 'Password Change' Action" +
                "<br>Step 6- Enter New Password and Confirm Password" +
                "<br>Step 7- Click 'Reset' Button"
        );
        webSteps.click("Action2");
        webSteps.type("Sami@@1234", "User_NewPassword");
        webSteps.type("Sami@@1234", "User_ConfirmPassword");
        webSteps.click("ResetButton");
        Assert.assertEquals("Password changed successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
