package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Contacts extends baseTest{
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickContacts");
    }

    @Test(priority = 1)
    public void addContact() throws InterruptedException, AWTException {
        extentReportManager.startTest("Contacts Functionality", "<b>Add Customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Contacts " +
                "<br>Step 3- Click 'Add New Customer' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewCustomer");
        webSteps.type("Amal Perera","Contacts_ContactName");
        webSteps.type("UID12345","Contacts_ReferenceID");
        webSteps.type("0761234567","Contacts_ContactNo1");
        webSteps.type("0767654321","Contacts_ContactNo2");
        webSteps.type("Kuliyapitiya, North Western Province","Contacts_ShippingAddress");
        webSteps.select("Contacts_City",10,1);
        webSteps.type("contact@gmail.com","Contacts_Email");
        //webSteps.click("AddContact_SaveButton");
        //Assert.assertEquals("Contact created successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @Test(priority = 2)
    public void editContact() throws InterruptedException, AWTException {
        extentReportManager.startTest("Contacts Functionality", "<b>Edit Customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully edit a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Contacts " +
                "<br>Step 3- Select a Customer" +
                "<br>Step 4- Click Edit Action" +
                "<br>Step 5- Edit Details" +
                "<br>Step 6- Click 'Update' Button"
        );
        webSteps.click("EditContacts_Action");
        webSteps.type("Amal Perera","Contacts_ContactName");
        webSteps.type("UID12345","Contacts_ReferenceID");
        webSteps.type("0761234567","Contacts_ContactNo1");
        webSteps.type("0767654321","Contacts_ContactNo2");
        webSteps.type("Kuliyapitiya, North Western Province","Contacts_ShippingAddress");
        webSteps.select("Contacts_City",10,1);
        webSteps.type("contact@gmail.com","Contacts_Email");
        //webSteps.click("EditCustomer_UpdateButton");
        //Assert.assertEquals("Contact created successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
