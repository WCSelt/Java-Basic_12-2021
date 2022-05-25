package finalproject.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomerRequestImpl implements CustomerRequest,UserSurvey{
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private CurrencyMorphTemplate[] currencyMorphTemplate = CurrencyMorphTemplate.values();
    private PrescriptionPrice prescriptionPrice;
    private String result;

    @Override
    public String askCustomer() throws IOException {
        return reader.readLine();
    }

    @Override
    public String getResult() {
        try {
            String currencyName;
            boolean checkCurrencyAvailability = false;



            while(!checkCurrencyAvailability){
                try {
                    System.out.println("Введите число");
                    String number = askCustomer();
                    prescriptionPrice = new PrescriptionPrice(number);

                    System.out.println("Выберите одну из предложенных валют: ");
                    currencyName = askCustomer();
                    for (CurrencyMorphTemplate availableCurrency : currencyMorphTemplate) {
                        if (currencyName.equalsIgnoreCase(availableCurrency.name())){
                            prescriptionPrice.getNumberToWordTemplate().setMorph(availableCurrency.getCurrencyMorph(),
                                    availableCurrency.getPennyMorph());
                            System.out.println(availableCurrency.name());
                            System.out.println(prescriptionPrice.getNumberToString());

                        }
                    }

                } catch (IOException e){

                }


            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
