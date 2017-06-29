package com.thomoscook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_BookingConfPage {
    String thanksHeadre="css=.image--caption h2";
    String bookingNum="css=[class*='summary-overlap'] div:nth-child(1)>h2";
    String bookingTotal="css=[class*='summary-overlap'] div:nth-child(2)>h2";
    String noOfPassengers="css=h3[class*='avatar--summary']";
    String includedSevices="xpath=(//div[@class='title-strikethrough'])[2]/../descendant::span[@class='ng-binding ng-scope']";
    String includedSevicesHeader="xpath=(//div[@class='title-strikethrough'])[2]//span";
    String essentilas="xpath=(//div[@class='title-strikethrough'])[3]/../descendant::span[@class='ng-binding ng-scope']";
    String essentilasHeader="xpath=(//div[@class='title-strikethrough'])[3]//span";
    String moreExtras="xpath=(//div[@class='title-strikethrough'])[4]/../descendant::span[@class='ng-binding ng-scope']";
    String moreExtrasHeader="xpath=(//div[@class='title-strikethrough'])[4]//span";
    String itinerarySectionTitle="css=confirmation-itinerary h2";
    String itineraryDetails="css=confirmation-flight-segment>div";
    //https://www-stage.thomascookairlines.com/tca/tcauk/en/flight/confirmation
}
