package com.thomoscook.objectrepository;

import org.openqa.selenium.By;

/**
 * Created by E002465 on 05-06-2017.
 */
public class OR_EssentialsSeatPreference1 {


    public static By slctSeatBtn=By.cssSelector("tca-teaser[show-early-bird$='SEAT'] a[aria-label='Book seats from just ']");
    public static By edtSeatBtn=By.cssSelector("tca-teaser[show-early-bird$='SEAT'] a[aria-label='Edit selection']");
    public static By adult1=By.cssSelector("[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(1)");
    public static By adult2=By.cssSelector("[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(2)");
    public static By youth1=By.cssSelector("[ng-show='flight.isSsrAvailableForSegment']>li:nth-child(3)");

    public static By selectSeat(int row,String column){
    return By.cssSelector("[aria-label='Row , column "+column+", seat "+row+column+"']");
    }
    public static By seatSelectionSkipBtn=By.cssSelector("[aria-label='Skip']");
    public static By seatSelcDoneBtn=By.cssSelector(".col-xs-10>.btn.ng-scope");
    public static By seatSelcCancelBtn=By.cssSelector(".col-xs-10 .btn-text");
    public static By resSeatsForEvryOneBtn=By.className("list-item__extra.ng-binding.ng-scope");


}
