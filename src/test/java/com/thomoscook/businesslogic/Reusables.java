package com.thomoscook.businesslogic;

import com.automation.accelerators.ActionEngine;
import com.thomoscook.objectrepository.*;
import com.relevantcodes.extentreports.LogStatus;
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by E002465 on 06-06-2017.
 */
public class Reusables extends ActionEngine implements OR_HomePage,OR_PassengerDetails,OR_ContactDetails,OR_PaymentDetails,OR_FlightDetails,OR_EssentialsBaggagePreference,OR_ExtrasSportsEqpAndCargo,OR_BookingConfPage,OR_EssentialsSeatPreference,OR_AddPriorityPackage {

    Actions act;
    Select opt;

    public void from(String departure) throws Throwable {
        waitForElementPresent(depMenu,60);
        click(depMenu);
        waitForElementPresent(deparAirPorttHeader);
        if((getVisibleText(deparAirPorttHeader).contains("Your departure airport"))) {
            waitForElementPresent(depTextBox, 60);
            type(depTextBox, departure);
            waitForElementPresent(depDropDwn);
            for (WebElement ele : getAllElements(depDropDwn)) {
                if (ele.getText().contains(departure)) {
                    ele.click();
                }
            }
        }
        logger.log(LogStatus.PASS,"entered Departure city : "+departure);

    }

    public void to(String destination) throws Throwable {

        waitForElementPresent(arrvlTextBox, 60);
        type(arrvlTextBox, destination);
        waitForElementPresent(arrvlDropDwn);
        for (WebElement ele : getAllElements(arrvlDropDwn)) {
            if (ele.getText().contains(destination)) {
                ele.click();
            }
        }
        logger.log(LogStatus.PASS,"entered Destination city : "+destination);
    }

    public void passDetails(String gender,String salutation,String firstName,String surName,String dOB,int passNum) throws Throwable {
        String d_MaleRadioButton=String.format(d_MaleRadioBtn,passNum);
        String d_SalutationRadioButton_MR=String.format(d_SalutationRadioBtn_MR,passNum);
        String d_FirstNameTextBox=String.format(d_FirstNameTxtBox,passNum);
        //String d_MiddleNameTextBox=String.format(d_MiddleNameTxtBox,num);
        String d_SurNameTextBox=String.format(d_SurNameTxtBox,passNum);
        String d_DOBTextBox=String.format(d_DOBTxtBox,passNum);
        click(d_MaleRadioButton);
        click(d_SalutationRadioButton_MR);
        type(d_FirstNameTextBox,firstName);
        type(d_SurNameTextBox,surName);
        type(d_DOBTextBox,dOB);
        logger.log(LogStatus.PASS,"entered Adult passenger "+passNum+" details");
    }

    public void youngOrChildOrInfantDetails(String gender,String firstName,String surName,String dOB,int passNum) throws Throwable {
        String d_MaleRadioButton=String.format(d_MaleRadioBtn,passNum);
        String d_FirstNameTextBox=String.format(d_FirstNameTxtBox,passNum);
        //String d_MiddleNameTextBox=String.format(d_MiddleNameTxtBox,num);
        String d_SurNameTextBox=String.format(d_SurNameTxtBox,passNum);
        String d_DOBTextBox=String.format(d_DOBTxtBox,passNum);
        click(d_MaleRadioButton);
        type(d_FirstNameTextBox,firstName);
        type(d_SurNameTextBox,surName);
        type(d_DOBTextBox,dOB);
        logger.log(LogStatus.PASS,"entered passenger "+passNum+" details");
    }

    public void contactDetails(String salutation,String firstName,String surName,String address1,String postCode,String city,String country,String phoneNum,String email) throws Throwable {

        String SalutationRadioButton=String.format(salutationRadioBtn,salutation.toUpperCase());
        waitForElementToBeClickable(SalutationRadioButton);
        click(SalutationRadioButton);
        type(firstNameTxtBox,firstName);
        type(surNameTxtBox,surName);
        type(address1TxtBox,address1);
        type(postTxtBox,postCode);
        type(cityTxtBox,city);
        type(countryTxtBox,country,true,false);
        List<WebElement> listOfCountries=getAllElements(countryDropDown);
        for(WebElement we:listOfCountries){
            //Adding java script scroll to view might require
            if(we.getText().equalsIgnoreCase(country)){
                click(we);
            }
        }
        type(phnNumTxtBox,phoneNum);
        type(emailTxtBox,email);
        check(promoOfferDecChkBox);
        logger.log(LogStatus.PASS,"entered Contact details");
    }

    public void paymentDetails(String paymentType,String cardType,String nameOnCard,String cardNumber,String month,String year,String cVV) throws Throwable {

        String cardTypeButton=String.format(cardTypeBtn,cardType);
        if(paymentType.equalsIgnoreCase("Credit card")||paymentType.contains("Credit")){
            waitForElementToBeClickable(creditCardRadioBtn);
            click(creditCardRadioBtn);
            waitForElementToBeClickable(cardTypeButton);
            click(cardTypeButton);
            enterCardDetails(paymentType,cardType,nameOnCard,cardNumber,month,year,cVV);
        }
        else if(paymentType.equalsIgnoreCase("Debit card")||paymentType.contains("Debit")){
            waitForElementToBeClickable(debitCardRadioBtn);
            click(debitCardRadioBtn);
            waitForElementToBeClickable(cardTypeButton);
            click(cardTypeButton);
            enterCardDetails(paymentType,cardType,nameOnCard,cardNumber,month,year,cVV);
        }
    }

    public void enterCardDetails(String paymentType,String cardType,String nameOnCard,String cardNumber,String month,String year,String cVV) throws Throwable {
        String cardTypeButton=String.format(cardTypeBtn,cardType);
        String expMonth=String.format(monthLink,month);
        String expYear=String.format(yearLink,year);

        waitForElementToBeClickable(acceptTCChkBox);
        click(acceptTCChkBox);
        waitForElementPresent(cardHolderNameTxtBox);
        type(cardHolderNameTxtBox,nameOnCard);
        waitForElementPresent(iframe,30);
        Driver.switchTo().frame(getElement(iframe));
        waitForElementPresent(cardNumberTxtBox,60);
        scrollElementIntoView(cardNumberTxtBox);
        type(cardNumberTxtBox,cardNumber);
        click(monthDropDwnBtn);
        waitForElementToBeClickable(expMonth);
        click(expMonth);
        waitForElementToBeClickable(yearDropDwnBtn);
        click(yearDropDwnBtn);
        waitForElementToBeClickable(expYear);
        click(expYear);
        type(cVVTxtBox,cVV);
        Driver.switchTo().parentFrame();
        click(payByBtn);
        logger.log(LogStatus.PASS,"entered Payment details");
    }
    public void oneWayTrip( String month,String date) throws Throwable {

        waitForElementPresent(calHeader);
        if (getVisibleText(calHeader).contains("Select day of departure")) {
            waitForElementToBeClickable(oneWayTripBtn);
            click(oneWayTripBtn);
            selectMonthandDayOfJourney(month, date);
        }
        logger.log(LogStatus.PASS,"selected oneway trip with DOJ:"+date+" "+month);

    }

    public void roundTrip(String month,String date,String return_Month,String return_Date) throws Throwable {

        if (getVisibleText(calHeader).contains("Select day of departure")) {
            selectMonthandDayOfJourney(month, date);
            logger.log(LogStatus.PASS,"selected trip with DOJ:"+date+" "+month);
        }

        if(getVisibleText(calHeader).contains("Select return flight")) {
            selectMonthandDayOfJourney(return_Month, return_Date);
            logger.log(LogStatus.PASS,"selected return trip with DOJ:"+return_Date+" "+return_Month);
        }

    }

    public void selectMonthandDayOfJourney(String month,String date) throws Throwable {
        String currentMonth = getVisibleText(departuremonthText);
        String journeydate=String.format(selectday,date);
        System.out.println("The current month is :" + currentMonth);
        while (!currentMonth.contains(month)) {
            waitForElementPresent(calNextBtn);
            click(calNextBtn);
            currentMonth = getVisibleText(departuremonthText);
            System.out.println("The current month is :" + currentMonth);
        }
        waitForElementToBeClickable(journeydate);
       try {
           click(journeydate);
       }catch (StaleElementReferenceException e){
           System.out.println("==Stale error==");
           click(journeydate);
       }
    }


    public void selectAdults(int reqpass) throws Throwable {
        int Passengers;
        waitForElementPresent(defaultAdults, 60);
        String defaultPassg = getVisibleText(defaultAdults);
        int defaultPass = Integer.parseInt(defaultPassg);
        System.out.println("default Adults passengers: " + defaultPass);
        if (reqpass >= defaultPass) {
            Passengers = reqpass - defaultPass;
            for (int i = 0; i < Passengers; i++) {
                click(adultPlusBtn);
            }
        } else if (reqpass < defaultPass) {
            Passengers = defaultPass - reqpass;
            for (int i = 0; i < Passengers; i++) {
                click(adultMinusBtn);
            }
        }
        logger.log(LogStatus.PASS,"selected "+reqpass+" Adults");

    }

    public void SelectYoungAdults(int reqpass) throws Throwable {
        int pass;
        waitForElementPresent(defaultYoungAdults, 60);
        String defaultPassg = getVisibleText(defaultYoungAdults);
        int defaultPass = Integer.parseInt(defaultPassg);
        System.out.println("default YoungAdults passengers: " + defaultPass);
        Thread.sleep(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                click(youngPlusBtn);
            }
        }
        logger.log(LogStatus.PASS,"selected "+reqpass+" YoungAdults");

    }
    public void SelectChildren(int reqpass) throws Throwable {
        int pass;
        waitForElementPresent(defaultChildren, 60);
        String defaultPassg = getVisibleText(defaultChildren);
        int defaultPass = Integer.parseInt(defaultPassg);
        System.out.println("default Children passengers: " + defaultPass);
        Thread.sleep(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                click(childPlusBtn);
            }
        }
        logger.log(LogStatus.PASS,"selected "+reqpass+" Children");
    }
    public void SelectInfants(int reqpass) throws Throwable {
        int pass;
        waitForElementPresent(defaultInfants, 60);
        String defaultPassg = getVisibleText(defaultInfants);
        int defaultPass = Integer.parseInt(defaultPassg);
        System.out.println("default Infants passengers: " + defaultPass);
        Thread.sleep(3000);
        if (reqpass >= defaultPass) {
            pass = reqpass - defaultPass;
            for (int i = 0; i < pass; i++) {
                click(infantPlusBtn);
            }
        }
        logger.log(LogStatus.PASS,"selected "+reqpass+" Infants");
    }

    public void selectPassengers(String PassengerType, int reqpass ) throws Throwable {
        switch (PassengerType) {
            case "Adults":
                selectAdults(reqpass);
                break;
            case "YoungAdults":
                SelectYoungAdults(reqpass);
                break;
            case "Children":
                SelectChildren(reqpass);
                break;
            case "Infants":
                SelectInfants(reqpass);
                break;

            default:
                SelectYoungAdults(reqpass);
        }

    }
    public void reserveSeatsForEveryone() throws Throwable {

        int count=1;
        waitForElementToBeClickable(reserveSeatsForEveryone);
        List<WebElement> passCount=getAllElements(totalNoOfPassengers);
        click(reserveSeatsForEveryone);
        for (WebElement ele:getAllElements(availableSeats)) {
            if(count>passCount.size()){
                break;
            }
            scrollElementIntoView(ele);
            click(ele);
            count++;
        }
        waitForElementToBeClickable(Done,30);
        click(Done);
        Thread.sleep(3000);
        logger.log(LogStatus.PASS,"reserved seats for every one "+passCount.size());

    }

    public void selectBaggage(String baggage) throws Throwable {
        String baggaeType=String.format(selectBaggage,baggage);
        waitForElementToBeClickable(baggaeType);
        click(baggaeType);
        waitForElementToBeClickable(Done);
        click(Done);
        logger.log(LogStatus.PASS,"selected baggage "+baggage);
    }

    public void selectOutboundClass(String selectOutboundClass) throws Throwable {
        String outBoundClass=String.format(outBoundCls,selectOutboundClass);
        waitForElementToBeClickable(outBoundClass);
        click(outBoundClass);
        logger.log(LogStatus.PASS,"selected outbound class "+selectOutboundClass);
    }

    public void selectInboundClass(String selectInboundClass) throws Throwable {
        String inBoundClass=String.format(inBoundCls,selectInboundClass);
        waitForElementToBeClickable(inBoundClass,60);
        click(inBoundClass);
        logger.log(LogStatus.PASS,"selected inbound class "+selectInboundClass);
    }

    public void outboundSportsEquipment(String passenger,String equipment) throws Throwable {
        String pass=String.format(outboundSportsEqiAddBtn,passenger);
        String equip=String.format(sportsEqpBtn,equipment);
        waitForElementToBeClickable(pass);
        Thread.sleep(2000);
        click(pass);
        waitForElementToBeClickable(equip);
        click(equip);
        logger.log(LogStatus.PASS,"selected outbound sports eqipment "+equipment+" for"+passenger);
    }

    public void inboundSportsEquipment(String passenger,String equipment) throws Throwable {
        String pass=String.format(inboundSportsEqiAddBtn,passenger);
        String equip=String.format(sportsEqpBtn,equipment);
        waitForElementToBeClickable(pass);
        click(pass);
        waitForElementToBeClickable(equip);
        click(equip);
        logger.log(LogStatus.PASS,"selected inbound sports eqipment "+equipment+" for"+passenger);
    }

    public void verifyBookingDetails() throws Throwable {
        Assert.assertTrue(Driver.getCurrentUrl().contains("flight/confirmation"));
        String bookingNumber=getVisibleText(bookingNum);
        String bookingTotalAmount=getVisibleText(bookingTotal);
        int noOfPass=getAllElements(noOfPassengers).size();
        System.out.println("Total Booking amount is: "+bookingTotalAmount);
        System.out.println("Booking number is: "+bookingNumber);
        logger.log(LogStatus.PASS,"Hurrehhh Booking is successful");
    }

    public void overViewOfBooking() throws Throwable {

        ArrayList<String> addedEssentials=new ArrayList<>();
        String includedServDetails;
        if(getVisibleText(includedSevicesHeader).contains("Included services")) {
            includedServDetails=getVisibleText(includedSevices);
        }

        if(getVisibleText(essentilasHeader).contains("Essentials")) {
            for (WebElement ele : getAllElements(essentilas)) {
                addedEssentials.add(ele.getText());
            }
        }

        if(getVisibleText(moreExtrasHeader).contains("More extras")) {
            //more extras code goes here
        }
    }
    //can add click on Reserve seats in feature
    public void popUpHandler() throws Throwable {
        if(isElementPresent(popUpWindow)){
            waitForElementToBeClickable(popUpContinueBtn);
            click(popUpContinueBtn);
        }
        else
            System.out.println("---------------------");
    }

    public void alertAccept(boolean accept) throws InterruptedException {
        Thread.sleep(2000);
        try
        {
            Alert alert = Driver.switchTo().alert();
            if(accept)
                alert.accept();
            else
                alert.dismiss();
        }
        catch (NoAlertPresentException Ex)
        {
            System.out.println("===No Alert present=====");
        }


    }

    public void addPriorityPackage () throws Throwable {
        waitForElementToBeClickable(addPriorityPackageBtn);
        click(addPriorityPackageBtn);
        waitForElementPresent(checkPass);
        click(checkPass);
        waitForElementToBeClickable(Done);
        click(Done);
        Thread.sleep(2000);
        logger.log(LogStatus.PASS,"selected Priority pacakge");
    }

    public boolean verifyBooking() throws Throwable {
        waitForElementPresent(bookingconfirmMessage,60);
        if (isElementPresent(bookingconfirmMessage) && isElementPresent(bookingOverview)) {

            return true;
        }else {

            return false;

        }
    }

}
