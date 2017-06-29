package com.thomoscook.objectrepository;

import org.openqa.selenium.By;

/**
 * Created by E002465 on 05-06-2017.
 */
public class OR_EssentialsBundle1 {

    public static By getBundle=By.cssSelector("a[ng-class^='vm.bundleAdded']");
    public static By pssngSelectionChkBox=By.cssSelector("span[ng-bind='flight.label']");
    public static By bundleDoneBtn=By.cssSelector(".col-xs-10 .btn.ng-scope");
    public static By bundleCancelBtn=By.cssSelector(".col-xs-10 .btn-text");
    public static By bundleEditBtn=By.cssSelector("a[aria-label='Edit the bundle']");

}
