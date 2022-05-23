package finalproject;

import finalproject.app.PrescriptionPrice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FinalProject {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            PrescriptionPrice prescriptionPrice = new PrescriptionPrice(reader.readLine()) ;
            System.out.println(prescriptionPrice.getNumberToString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
