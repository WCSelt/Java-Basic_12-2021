package com.otus.finalproject;

import com.otus.finalproject.app.CustomerRequest;
import com.otus.finalproject.app.CustomerRequestImpl;
import com.otus.finalproject.tests.AllTests;

public class FinalProject {
    public static void main(String[] args) {
        try {
            CustomerRequest customerRequest = new CustomerRequestImpl();
            customerRequest.getResult();
            new AllTests().runTests();
        } catch (NullPointerException e) {
            System.err.println("Кажется что-то пошло не так"+e);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}