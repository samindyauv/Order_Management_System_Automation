package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
        webSteps.type("Amal Perera","AddUser_Name");
        //webSteps.select("AddUser_Role",5,1);
        webSteps.type("761234567","AddUser_ContactNo");
        webSteps.type("amal@gmail.com","AddUser_Email");
        webSteps.type("Amal@12345","AddUser_Password");
        webSteps.type("Amal@12345","AddUser_ConfirmPassword");
        webSteps.type("Kuliyapitiya","AddUser_Address");
        webSteps.click("AddUser_SaveButton");
        //Assert.assertEquals("User added successfully",webSteps.getText("AddUser_ToastMessage"), "Passed");
    }

    @Test(priority = 2)
    public void searchUserUsingName() throws InterruptedException, AWTException {
        extentReportManager.startTest("User Functionality", "<b>Search User Using Name</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can search a user using user's name</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Select User from 'Search By' dropdown " +
                "<br>Step 5- Type in Search" +
                "<br>Step 6- Click Search"
        );
        webSteps.select("SearchUser_SearchByDropdown",2,0);
        webSteps.type("Kasun Bandara","SearchUser_SearchBar");
        webSteps.click("SearchUser_SearchButton");
        Assert.assertEquals("Kasun Bandara",webSteps.getText("SearchUser_Result"), "Passed");
    }

    @Test(priority = 3)
    public void searchUserUsingRole() throws InterruptedException, AWTException {
        extentReportManager.startTest("User Functionality", "<b>Search User Using Role</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can search a user using user's role</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Select Role from 'Search By' dropdown " +
                "<br>Step 5- Type in Search" +
                "<br>Step 6- Click Search"
        );
        webSteps.select("SearchUser_SearchByDropdown",1,1);
        webSteps.type("Admin","SearchUser_SearchBar");
        webSteps.click("SearchUser_SearchButton");
        Assert.assertEquals("Admin",webSteps.getText("SearchUser_Result2"), "Passed");
    }

    @Test(priority = 4)
    public void searchUserUsingEmail() throws InterruptedException, AWTException {
        extentReportManager.startTest("User Functionality", "<b>Search User Using Email</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can search a user using user's email</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4- Select Email from 'Search By' dropdown " +
                "<br>Step 5- Type in Search" +
                "<br>Step 6- Click Search"
        );
        webSteps.select("SearchUser_SearchByDropdown",2,1);
        webSteps.type("kasun@gmail.com","SearchUser_SearchBar");
        webSteps.click("SearchUser_SearchButton");
        Assert.assertEquals("kasun@gmail.com",webSteps.getText("SearchUser_Result3"), "Passed");
    }
}
