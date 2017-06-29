package com.thomoscook.objectrepository;

/**
 * Created by E002465 on 06-06-2017.
 */
public interface OR_ExtrasSportsEqpAndCargo {

    String bookSportsEqpBtn="xpath=//a[text()=' Book sports equipment ']";
    String outboundSportsEqiAddBtn="xpath=(//h3[contains(.,'Outbound flight')])[2]/ancestor::div[@ng-repeat='flight in vm.summary']/descendant::div[text()='%s ']/../following-sibling::div[text()=' Add ']";//Adult 1
    String inboundSportsEqiAddBtn="xpath=(//h3[contains(.,'Inbound flight')])[2]/ancestor::div[@ng-repeat='flight in vm.summary']/descendant::div[text()='%s ']/../following-sibling::div[text()=' Add ']";//Child 1
    String sportsEqpBtn="css=[aria-label^='%s']";//Cricket
    String sameDetailsForReturnFlightChkBox="css=.ng-scope.summary-leg label>input";
}
