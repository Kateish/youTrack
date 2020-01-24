package com.jetbrains.youtrack.utils;

import com.jetbrains.youtrack.base.Base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class testHelper {
    public WebDriver driver;
    private String sTestCaseName;
    private int iTestCaseRow;

    @Before
    public void beforeMethod() throws Exception {

        // Getting the Test Case name, as it will going to use in so many places
        // The main use is to get the TestCase row from the Test Data Excel sheet
        // The below method will refine your test case name, exactly the name use have used
        sTestCaseName = ExcelUtils.getTestCaseName(this.toString());
        System.out.println("Test case runs: " + sTestCaseName);

        // Setting up the Test Data Excel file using Constants variables
        ExcelUtils.setExcelFile("testdata/TestData.xlsx", "Sheet1");
        // Fetching the Test Case row number from the Test Data Sheet
        // This row number will be feed to so many functions, to get the relevant data from the Test Data sheet

        iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.Col_TestCaseName);
        System.out.println("Row: " + iTestCaseRow);

        // Launching the browser, this will take the Browser Type from Test Data Sheet
        driver = ExcelUtils.OpenBrowser(iTestCaseRow);

        // Initializing the Base Class for Selenium driver

        new Base(driver);

    }

    @After
    public void after() {
        // Printing beautiful logs to end the test case
        Log.endTestCase(sTestCaseName);
        // Closing the opened driver
        driver.close();
    }
}
