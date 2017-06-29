package com.thomoscook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_FlightDetails {

    String avblFlightsBtn="css=vacancy-flight[class='ng-isolate-scope']";
    String fltEcnmyBtn="className=fare-v3";
    String cntBtn="className=btn.btn--large";
    String outBoundCls="xpath=//h2[text()=' Outbound flight ']/ancestor::div[@class='container-fluid']/descendant::h3[text()='%s']/ancestor::li[@class='fare-wrap-v3 list-unstyled ng-scope']/descendant::div[@class='fare-v3__select']";//Economy Class Premium Class
    String inBoundCls="xpath=(//h2[text()=' Inbound flight ']/ancestor::div[@class='container-fluid']/descendant::h3[text()='%s']/ancestor::li[@class='fare-wrap-v3 list-unstyled ng-scope']/descendant::div[@class='fare-v3__select'])[3]";//Economy Class Premium Class
}
