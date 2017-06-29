package com.thomoscook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_HomePage {

    String depMenu = "css=div[translate='common.from']";
    String depTextBox = "id=airportinput_id_origin";
    String depDropDwn = "css=#airportSelectOutOne-airportsList div>h3";
    String arrvlTextBox = "id=airportinput_id_destination";
    String arrvlDropDwn = "css=#airportSelectInOne-airportsList div>h3";
    String oneWayTripBtn = "css=.calendar-options li:nth-child(2)";
    String roundTrip = "css=.btn.btn--warm-grey.ng-scope.active";
    String calHeader="id=MODAL_TITLE_DATES";
    String deparAirPorttHeader="css=[name='ORIGIN'] #MODAL_TITLE_AIRPORT";
    String destiAirPorttHeader="css=[name='DESTINATION'] #MODAL_TITLE_AIRPORT";

    //String dOJBtn="css=button[aria-label^='%s %s available']";//first s day second s month
    String dOJBtn = "css=button[aria-label^='22 September available']";//first s day second s month
    String calNextBtn = "css=[aria-label='next month']";
    String calPrvtBtn = "css=[aria-label='previous month']";
    String adultPlusBtn = "css=.list.clearfix li:nth-child(1) .icon-plus";
    String youngPlusBtn = "css=.list.clearfix li:nth-child(2) .icon-plus";
    String childPlusBtn = "css=.list.clearfix li:nth-child(3) .icon-plus";
    String infantPlusBtn = "css=.list.clearfix li:nth-child(4) .icon-plus";
    String adultMinusBtn = "css=.list.clearfix li:nth-child(1) .icon-minus";
    String youngMinusBtn = "css=.list.clearfix li:nth-child(2) .icon-minus";
    String childMinusBtn = "css=.list.clearfix li:nth-child(3) .icon-minus";
    String infantMinusBtn = "css=.list.clearfix li:nth-child(4) .icon-minus";
    String passContBtn = "css=.btn.btn--plane.ng-binding";
    String searchBtn = "id=search";

    String departuremonthText = "css=.btn.btn-default.btn-sm.uib-title";
    String selectday = "xpath=//button[contains(@id,'datepicker')]/span[1][contains(text(),'%s')]";
    String defaultAdults = "css=.list.clearfix>li:nth-child(1) [class='list-item-passenger__number ng-binding']";
    String defaultYoungAdults = "css=.list.clearfix>li:nth-child(2) [class='list-item-passenger__number ng-binding']";
    String defaultChildren = "css=.list.clearfix>li:nth-child(3) [class='list-item-passenger__number ng-binding']";
    String defaultInfants = "css=.list.clearfix>li:nth-child(4) [class='list-item-passenger__number ng-binding']";

    String selectEconomyclass ="className=fare-v3";
    String continueBtn_Gen= "css=.btn.btn--large";
    String selectYourSeat ="css=[aria-label='Book seats from just']";
    String reserveSeatsForEveryone = "xpath=//div[contains(text(),'Reserve seats for everyone')]";
    String noPass = "css=.list-item.list-item-avatar.ng-scope";
    String selectStandardSeat1 = "xpath=//td[1]/span[@class='seat-v2--standard']";
    String selectStandardSeat2 = "xpath=//td[2]/span[@class='seat-v2--standard']";
    String Done ="xpath=//a[contains(text(),'Done')]";
    String iconSippner="className=icon-animated-spinner";
    String popUpWindow="css=.overlay__inner.-dropshadow";
    String popUpContinueBtn="css=.btn-text.ng-binding";
    String popUpResSeatsBtn="css=.btn.cst-extras-session-button";



}