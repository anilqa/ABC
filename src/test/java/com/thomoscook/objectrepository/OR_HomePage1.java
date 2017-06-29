package com.thomoscook.objectrepository;

import org.openqa.selenium.By;

/**
 * Created by E002465 on 05-06-2017.
 */
public class OR_HomePage1 {

    public static By fromMenu=By.cssSelector("div[translate='common.from']");
    public static By fromTextBox=By.id("airportinput_id_origin");
    public static By fromDropDwn=By.cssSelector("#airportSelectOutOne-airportsList li:nth-child(1)");
    public static By toTextBox=By.id("airportinput_id_destination");
    public static By toDropDwn=By.cssSelector("#airportSelectInOne-airportsList li:nth-child(1)");
    public static By calOWTripBtn=By.cssSelector(".calendar-options li:nth-child(2)");
    public static By dOJBtn(int day){
        return By.cssSelector("button[aria-label^='"+day+" June available']");
    }
    public static By adultPlusBtn=By.cssSelector(".list.clearfix li:nth-child(1) .icon-plus");
    public static By youngPlusBtn=By.cssSelector(".list.clearfix li:nth-child(2) .icon-plus");
    public static By childPlusBtn=By.cssSelector(".list.clearfix li:nth-child(3) .icon-plus");
    public static By infantPlusBtn=By.cssSelector(".list.clearfix li:nth-child(4) .icon-plus");
    public static By adultMinusBtn=By.cssSelector(".list.clearfix li:nth-child(1) .icon-minus");
    public static By youngMinusBtn=By.cssSelector(".list.clearfix li:nth-child(2) .icon-minus");
    public static By childMinusBtn=By.cssSelector(".list.clearfix li:nth-child(3) .icon-minus");
    public static By infantMinusBtn=By.cssSelector(".list.clearfix li:nth-child(4) .icon-minus");
    public static By passContBtn=By.cssSelector(".btn.btn--plane.ng-binding");
    public static By searchBtn=By.id("search");
}
