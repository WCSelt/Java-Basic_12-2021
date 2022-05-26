package finalproject.tests;

import finalproject.tests.unit.PrescriptionPriceTest;

public class AllTests {
    public void runTests () {
        new PrescriptionPriceTest().testNumberToWordConversion();
        new PrescriptionPriceTest().testCurrencyMorph();
        new PrescriptionPriceTest().testAvailabilityOtherCurrencies();
    }
}
