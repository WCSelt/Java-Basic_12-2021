package finalproject;

import finalproject.app.PrescriptionPrice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Currency;

public class FinalProject {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            PrescriptionPrice prescriptionPrice = new PrescriptionPrice(reader.readLine());
            System.out.println(prescriptionPrice.getNumberToString());
            System.out.println();
        } catch (NullPointerException e) {
            System.out.println("Пожалуйста, проверьте ввод");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}