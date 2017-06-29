package com.thomoscook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_EssentialsBaggagePreference {
    String bookBaggage = "css=a[aria-label='Book baggage ffrom just ']";
    String addAdult = "xpath=//div[contains(text(),'Adult')]/parent::div/../div[contains(.,'Add')]";
    String selectBaggage = "xpath=//*[@aria-label='%s']";
}
