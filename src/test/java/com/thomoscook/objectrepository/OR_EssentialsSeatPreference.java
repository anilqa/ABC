package com.thomoscook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_EssentialsSeatPreference {
    String slctSeatBtn="css=tca-teaser[show-early-bird$='SEAT'] a[aria-label='Book seats from just ']";
    String edtSeatBtn="css=tca-teaser[show-early-bird$='SEAT'] a[aria-label='Edit selection']";
    String adult1="css=[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(1)";
    String adult2="css=[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(2)";
    String youth1="css=[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(3)";
    /*String selectSeat=(int row,String column){
        return By.cssSelector("[aria-label='Row , column 3, seat A3']");
    }*/
    String seatSelectionSkipBtn="css=[aria-label='Skip']";
    String seatSelcDoneBtn="css=.col-xs-10>.btn.ng-scope";
    String seatSelcCancelBtn="css=.col-xs-10 .btn-text";
    String resSeatsForEvryOneBtn="classname=list-item__extra.ng-binding.ng-scope";
    String availableSeats="className=seat-v2--standard";
    String totalNoOfPassengers="css=[ng-show='flight.isSsrAvailableForSegment']>li";

}
