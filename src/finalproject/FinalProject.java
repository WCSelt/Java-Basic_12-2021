package finalproject;

import finalproject.app.CustomerRequest;
import finalproject.app.CustomerRequestImpl;
import finalproject.tests.AllTests;

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