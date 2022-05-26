package com.otus.finalproject.tests.unit;

import com.otus.finalproject.app.*;
import com.otus.finalproject.tests.assertions.Assertions;
import com.otus.finalproject.app.PrescriptionPrice;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionPriceTest {
    CurrencyMorphTemplate[] currencyTypes = CurrencyMorphTemplate.values();
    CurrencyMorphTemplate currencyTypeTemplate = CurrencyMorphTemplate.RUB;
    PrescriptionPrice price;

    @Test
    public void testNumberToWordConversion() {
        String scenario = "Тест на корректность преобразования чисел в слова";
        try {
            List<String> inputTemplates = new ArrayList<>(List.of(new String[]{"0", "1", "999"}));
            List<String> expectedNumberResults = new ArrayList<>(List.of(new String[]{"ноль", "один", "девятьсот девяносто девять"}));
            List<String> actualResults = new ArrayList<>();

            for (String inputTemplate : inputTemplates) {
                price = new PrescriptionPrice(inputTemplate);
                price.getNumberToWordTemplate().setMorph(currencyTypeTemplate.getCurrencyMorph(),
                        currencyTypeTemplate.getPennyMorph());
                actualResults.add((price.getNumberToString()));
            }

            for (int i = 0; i < actualResults.size(); i++) {
                Assertions.assertContains(expectedNumberResults.get(i), actualResults.get(i));
            }

            System.out.printf("%s: passed\n", scenario);
        } catch (Exception e) {
            System.err.printf("%s: failed   Reason: %s", scenario, e);
            e.printStackTrace();
        }
    }

    @Test
    public void testCurrencyMorph() {
        String scenario = "Тест на корректность склонения валюты";
        try {
            List<String> inputTemplates = new ArrayList<>(List.of(new String[]{"1", "2", "5"}));
            List<String> expectedMorphResults = new ArrayList<>(List.of(new String[]{"рубль", "рубля", "рублей"}));
            List<String> actualResults = new ArrayList<>();

            for (String inputTemplate : inputTemplates) {
                price = new PrescriptionPrice(inputTemplate);
                price.getNumberToWordTemplate().setMorph(currencyTypeTemplate.getCurrencyMorph(),
                        currencyTypeTemplate.getPennyMorph());
                actualResults.add((price.getNumberToString()));
            }

            for (int i = 0; i < actualResults.size(); i++) {
                Assertions.assertContains(expectedMorphResults.get(i), actualResults.get(i));
            }

            System.out.printf("%s: passed.\n", scenario);
        } catch (Exception e) {
            System.err.printf("%s: failed   Reason: %s", scenario, e);
            e.printStackTrace();
        }
    }

    @Test
    public void testAvailabilityOtherCurrencies() {
        String scenario = "Тест на наличие других валют";
        try {
            String inputNumberTemplate = "1";
            List<String> currencyInputTemplates = new ArrayList<>(List.of(new String[]{"rub", "usd", "eur"}));
            List<String> expectedMorphResults = new ArrayList<>(List.of(new String[]{"рубль", "доллар", "евро"}));
            List<String> actualResults = new ArrayList<>();

            for (int i = 0; i < currencyInputTemplates.size(); i++) {
                String currencyInputTemplate = currencyInputTemplates.get(i);
                for (CurrencyMorphTemplate currencyType : currencyTypes) {
                    if (currencyInputTemplate.equalsIgnoreCase(currencyType.name())) {
                        price = new PrescriptionPrice(inputNumberTemplate);
                        price.getNumberToWordTemplate().setMorph(currencyType.getCurrencyMorph(),
                                currencyType.getPennyMorph());
                        actualResults.add((price.getNumberToString()));
                    }
                }
            }

            for (int i = 0; i < actualResults.size(); i++) {
                Assertions.assertContains(expectedMorphResults.get(i), actualResults.get(i));
            }

            System.out.printf("%s: passed.\n", scenario);
        } catch (Exception e) {
            System.err.printf("%s: failed   Reason: %s", scenario, e);
            e.printStackTrace();
        }
    }
}