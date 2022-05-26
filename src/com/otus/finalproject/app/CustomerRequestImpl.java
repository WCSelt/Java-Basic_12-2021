package com.otus.finalproject.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CustomerRequestImpl implements CustomerRequest, UserSurvey {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private CurrencyMorphTemplate[] currencyMorphTemplate = CurrencyMorphTemplate.values();
    private PrescriptionPrice prescriptionPrice;
    private String result;
    private boolean checkCurrencyAvailability = false;
    private String currencyName;

    @Override
    public String askCustomer() throws IOException {
        return reader.readLine();
    }

    @Override
    public String getResult() throws Exception {
        try {
            while (!checkCurrencyAvailability) {
                try {
                    System.out.println("Введите число или \"exit\":");
                    String number = askCustomer();
//                    validationInputNumber(number);

                    if (number.equalsIgnoreCase("exit")) {
                        checkCurrencyAvailability = true;
                        break;
                    }

                    prescriptionPrice = new PrescriptionPrice(number);

                    System.out.println("Выберите одну из предложенных валют: " + Arrays.toString(currencyMorphTemplate));

                    currencyName = askCustomer();
//                    validationInputCurrency(currencyName);
                    boolean checkCurrencySearch = false;
                    for (CurrencyMorphTemplate availableCurrency : currencyMorphTemplate) {
                        if (currencyName.equalsIgnoreCase(availableCurrency.name())) {
                            prescriptionPrice.getNumberToWordTemplate().setMorph(availableCurrency.getCurrencyMorph(),
                                    availableCurrency.getPennyMorph());
                            checkCurrencySearch = true;
                        }
                    }
                    if (currencyName.isEmpty() || currencyName.isBlank()) {
                        throw new NumberFormatException("Поле для ввода числа не должно быть пустым");
                    }

                    if (!checkCurrencySearch) {
                        throw new IOException("В данный момент такую валюту мы не обслуживаем");
                    }

                    System.out.println(prescriptionPrice.getNumberToString());
                } catch (NumberFormatException | IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new Exception("Что-то произошло при попытке получить результат", e);
        }
        return result;
    }

    void validationInputNumber(String number) throws NumberFormatException {
        if (number.isEmpty() || number.isBlank()) {
            throw new NumberFormatException("Поле для ввода числа не должно быть пустым");
        }

    }

//    void validationInputCurrency(String currency) throws IOException {
//        boolean checkCurrencySearch = false;
//        for (CurrencyMorphTemplate availableCurrency : currencyMorphTemplate) {
//            if (currency.equalsIgnoreCase(availableCurrency.name())) {
//                prescriptionPrice.getNumberToWordTemplate().setMorph(availableCurrency.getCurrencyMorph(),
//                        availableCurrency.getPennyMorph());
//            }
//        }
//        if (currencyName.isEmpty() || currencyName.isBlank()) {
//            throw new NumberFormatException("Поле для ввода числа не должно быть пустым");
//        }
//
//        if (!checkCurrencySearch) {
//            throw new IOException("В данный момент такую валюту мы не обслуживаем");
//        }
//    }
}
