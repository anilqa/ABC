package com.thomoscook.scripts;

import com.thomoscook.businesslogic.Reusables;
import com.thomoscook.objectrepository.OR_FlightDetails;
import com.thomoscook.objectrepository.OR_HomePage;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

import static com.automation.utilities.ExcelUtilsJxl.getExcelData;

/**
 * Created by E002465 on 06-06-2017.
 */
public class Scenario1 extends Reusables implements OR_HomePage,OR_FlightDetails{

    LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
    @Test
    public void scenario1() throws Throwable {
        logger=extent.startTest("Started Scenario1(One ward Journey)");
        testData=getExcelData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData","Scenario1");
        System.out.println("testData+++++++++++++++"+testData);
        alertAccept(true);
        from(testData.get("From"));
        to(testData.get("To"));
        waitForElementToBeClickable(oneWayTripBtn);
        oneWayTrip(testData.get("Month"),testData.get("Day"));
        selectPassengers("Adults",2);
        waitForElementToBeClickable(passContBtn);
        click(passContBtn);
        waitForElementToBeClickable(searchBtn,60);
        click(searchBtn);
        alertAccept(true);
        waitForElementToBeClickable(selectEconomyclass,60);
        click(selectEconomyclass);

        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);

        waitForElementToBeClickable(slctSeatBtn,60);
        click(slctSeatBtn);
        reserveSeatsForEveryone();
        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);

      /*  waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);*/
        addPriorityPackage();
        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);
        contactDetails(testData.get("Salutation"),testData.get("FirstName"),testData.get("SurName"),testData.get("AddressLine1"),testData.get("Post Code"),testData.get("City"),testData.get("Country"),testData.get("MobilePhone"),testData.get("Email"));
        passDetails(testData.get("A1Gender"),testData.get("A1Salutation"),testData.get("A1Firstname"),testData.get("A1Surname"),testData.get("A1DOB"),1);
        passDetails(testData.get("A2Gender"),testData.get("A2Salutation"),testData.get("A2Firstname"),testData.get("A2Surname"),testData.get("A2DOB"),2);
        waitForElementToBeClickable(contBtn);
        click(contBtn);
        waitForElementNotPresent(iconSippner);
        paymentDetails(testData.get("PaymentType"),testData.get("CardType"),testData.get("NameOnCard"),"5399999999999999",testData.get("ExpMonth"),testData.get("Year"),testData.get("CVV"));
        verifyBooking();
    }

}
