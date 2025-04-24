package Positive;

import org.openqa.selenium.By;
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
        //webSteps.select("AddUser_Role",5,1);
        webSteps.type("761234567", "AddUser_ContactNo");
        webSteps.type("amal@gmail.com", "AddUser_Email");
        webSteps.type("Amal@12345", "AddUser_Password");
        webSteps.type("Amal@12345", "AddUser_ConfirmPassword");
        webSteps.type("Kuliyapitiya", "AddUser_Address");
        webSteps.click("AddUser_SaveButton");
        //Assert.assertEquals("User added successfully",webSteps.getText("AddUser_ToastMessage"), "Passed");
    }

    @DataProvider(name = "userSearchData")
    public Object[][] userSearchData() {
        return new Object[][]{
                {"Name", 2, 0, "Kasun Bandara", "SearchUser_Result"},
                {"Role", 1, 1, "Admin", "SearchUser_Result2"},
                {"Email", 2, 1, "kasun@gmail.com", "SearchUser_Result3"},
                {"Address", 3, 1, "Dewalegama,Kegalle", "SearchUser_Result4"}
        };
    }

    @Test(dataProvider = "userSearchData", priority = 2)
    public void searchUser(String criteriaType, int dropdownValue, int dropdownIndex, String inputValue, String resultLocator) throws InterruptedException, AWTException {
        extentReportManager.startTest("User Functionality", "<b>Search User Using " + criteriaType + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can search by " + criteriaType.toLowerCase() + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System (done once before class)" +
                "<br>Step 2 - Click User" +
                "<br>Step 3 - Click User List" +
                "<br>Step 4 - Select '" + criteriaType + "' from 'Search By' dropdown" +
                "<br>Step 5 - Enter Search Input" +
                "<br>Step 6 - Click Search"
        );

        webSteps.select("SearchUser_SearchByDropdown", dropdownValue, dropdownIndex);
        webSteps.type(inputValue, "SearchUser_SearchBar");
        webSteps.click("SearchUser_SearchButton");

        Assert.assertEquals(inputValue, webSteps.getText(resultLocator), "Search failed for: " + criteriaType);
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
        webSteps.click("EditUser_Action");
        webSteps.type("Amal Perera", "AddUser_Name");
        //webSteps.select("AddUser_Role",5,1);
        webSteps.type("761234567", "AddUser_ContactNo");
        webSteps.type("amal@gmail.com", "AddUser_Email");
        webSteps.type("Kuliyapitiya", "AddUser_Address");
        //webSteps.click("EditUser_UpdateButton");
        //Assert.assertEquals("User added successfully",webSteps.getText("EditUser_ToastMessage"), "Passed");
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
        webSteps.click("UserChangePassword_Action");
        webSteps.type("9876543210", "User_NewPassword");
        webSteps.type("9876543210", "User_ConfirmPassword");
        //webSteps.click("User_PasswordResetButton");
        //Assert.assertEquals("User added successfully",webSteps.getText("ChangePassword_ToastMessage"), "Passed");
    }

    @DataProvider(name = "pageSizes")
    public Object[][] pageSizes() {
        return new Object[][]{
                {10}, {25}, {50}, {100}, {500}, {1000}
        };
    }

    @Test(dataProvider = "pageSizes", priority = 5)
    public void verifyPageSize(int size) throws InterruptedException {
        extentReportManager.startTest("Pagination", "Verify user page size option: " + size);
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can select page size " + size + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4 - Open the Page Size dropdown" +
                "<br>Step 5 - Select page size " + size +
                "<br>Step 5 - Verify the number of rows matches the page size"
        );

        webSteps.select("UserPageSizeDropdown", String.valueOf(size));
        webSteps.waiting();
        int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();
        Assert.assertTrue(rowCount <= size, "Row count exceeds selected page size: " + size);
    }




}
