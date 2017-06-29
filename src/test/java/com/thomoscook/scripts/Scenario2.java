package com.thomoscook.scripts;


import com.thomoscook.businesslogic.Reusables;
import com.thomoscook.objectrepository.OR_EssentialsBaggagePreference;
import com.thomoscook.objectrepository.OR_FlightDetails;
import com.thomoscook.objectrepository.OR_HomePage;
import org.testng.annotations.Test;
import java.util.LinkedHashMap;
import static com.automation.utilities.ExcelUtilsJxl.getExcelData;

/**
 * Created by E001227 on 09-06-2017.
 */
public class Scenario2 extends Reusables implements OR_HomePage,OR_FlightDetails,OR_EssentialsBaggagePreference {

    LinkedHashMap<String,String> testData= new LinkedHashMap<String,String>();
    @Test
    public void scenario2() throws Throwable {
        logger=extent.startTest("Started Scenario2 (One ward Journey)");
        testData=getExcelData(System.getProperty("user.dir")+"\\TestData\\TestData.xls","TCTestData","Scenario2");
        System.out.println("testData+++++++++++++++"+testData);
        alertAccept(true);
        from(testData.get("From"));
        to(testData.get("To"));
        waitForElementToBeClickable(oneWayTripBtn);
        oneWayTrip(testData.get("Month"),testData.get("Day"));
        selectPassengers("Adults",1);
        selectPassengers("Infants",1);
        waitForElementToBeClickable(passContBtn);
        click(passContBtn);
        waitForElementToBeClickable(searchBtn,60);
        click(searchBtn);
        alertAccept(true);
        waitForElementToBeClickable(selectEconomyclass,60);
        click(selectEconomyclass);
        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);
        waitForElementToBeClickable(bookBaggage,60);
        click(bookBaggage);
        waitForElementToBeClickable(addAdult);
        click(addAdult);
        selectBaggage(testData.get("Baggage"));
        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);
        Thread.sleep(4000);
        waitForElementToBeClickable(popUpContinueBtn);
        click(popUpContinueBtn);
        Thread.sleep(2000);
        waitForElementNotPresent(iconSippner);
        waitForElementToBeClickable(continueBtn_Gen);
        click(continueBtn_Gen);
        waitForElementNotPresent(iconSippner);
        contactDetails(testData.get("Salutation"),testData.get("FirstName"),testData.get("SurName"),testData.get("AddressLine1"),testData.get("Post Code"),testData.get("City"),testData.get("Country"),testData.get("MobilePhone"),testData.get("Email"));
        passDetails(testData.get("A1Gender"),testData.get("A1Salutation"),testData.get("A1Firstname"),testData.get("A1Surname"),testData.get("A1DOB"),1);
        youngOrChildOrInfantDetails(testData.get("I1Gender"),testData.get("I1Firstname"),testData.get("I1Surname"),testData.get("I1DOB"),2);
        waitForElementToBeClickable(contBtn);
        click(contBtn);
        waitForElementNotPresent(iconSippner);
        paymentDetails(testData.get("PaymentType"),testData.get("CardType"),testData.get("NameOnCard"),"4111111111111111",testData.get("ExpMonth"),testData.get("Year"),testData.get("CVV"));
        verifyBooking();
    }
}
