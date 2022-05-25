package finalproject.app;

import java.util.ArrayList;
import java.util.Collections;
import java.math.BigDecimal;

public class PrescriptionPrice {
    private final NumberToWordTemplate numberToWordTemplate = new NumberToWordTemplate();
    private final CurrencyTypeMorphTemplate[] currencyTypeMorphTemplate = CurrencyTypeMorphTemplate.values();
    private final BigDecimal amount;

    public PrescriptionPrice(long number) {
        String numberString = String.valueOf(number);
        if (!numberString.contains("."))
            numberString += ".0";
        this.amount = new BigDecimal(numberString);
    }

    public PrescriptionPrice(double number) {
        String numberString = String.valueOf(number);
        if (!numberString.contains("."))
            numberString += ".0";
        this.amount = new BigDecimal(numberString);
    }

    public PrescriptionPrice(String number) throws Exception {
        if (!number.contains("."))
            number += ".0";
        this.amount = new BigDecimal(number);
    }

    public String getAmountAsString() {
        return amount.toString();
    }

    public String getNumberToString() {
        return numberToString(false);
    }

    public String numberToString(boolean checkPenny) {
        long currency = amount.longValue();
        String[] numberSegments = amount.toString().split("\\.");
        long penny = Long.parseLong(numberSegments[1]);

        if (numberSegments[1].charAt(0) != '0' && penny < 10) {
                penny *= 10;
        }

        String pennyString = String.valueOf(penny);
        if (pennyString.length() == 1) {
            pennyString = "0" + pennyString;
        }

        ArrayList<Long> segments = splitIntoSegments(currency);
        StringBuilder result = new StringBuilder();
        
        if (currency == 0) {
            result = new StringBuilder("ноль " + morph(0, numberToWordTemplate.getRubWithMorph(0),
                    numberToWordTemplate.getRubWithMorph(1),
                    numberToWordTemplate.getRubWithMorph(2)));
            if (checkPenny) {
                return result.toString();
            } else {
                return result + " " + penny + " " + morph(penny, numberToWordTemplate.getPennyWithMorph(0),
                        numberToWordTemplate.getPennyWithMorph(1), numberToWordTemplate.getPennyWithMorph(2));
            }
        }

        int thousandthsCount = segments.size();
        for (Long segment : segments) {
            int kind = Integer.parseInt(numberToWordTemplate.getOrdersMorph(thousandthsCount, 3));
            int currentSegment = Integer.parseInt(segment.toString());
            String segmentString = String.valueOf(currentSegment);

            if (segmentString.length() == 1) {
                segmentString = "00" + segmentString;
            }
            if (segmentString.length() == 2) {
                segmentString = "0" + segmentString;
            }

            int hundredsValue = Integer.parseInt(segmentString.substring(0, 1));
            int tensValue = Integer.parseInt(segmentString.substring(1, 2));
            int unitValue = Integer.parseInt(segmentString.substring(2, 3));
            int secondTenValue = Integer.parseInt(segmentString.substring(1, 3));

            if (currentSegment > 99) {
                result.append(numberToWordTemplate.getThirdOrderNumbers(hundredsValue)).append(" ");
            }
            if (secondTenValue > 20) {
                result.append(numberToWordTemplate.getSecondOrderNumbers(tensValue)).append(" ");

                result.append(numberToWordTemplate.getFirstOrderNumbersMorph(kind, unitValue)).append(" ");
            } else {
                if (secondTenValue > 9) {
                    result.append(numberToWordTemplate.getSecondTenNumbers(secondTenValue - 9)).append(" ");
                } else {
                    result.append(numberToWordTemplate.getFirstOrderNumbersMorph(kind, unitValue)).append(" ");
                }
            }

            if (thousandthsCount == 1) {
                result.append(morph(currentSegment, numberToWordTemplate.getRubWithMorph(0),
                        numberToWordTemplate.getRubWithMorph(1),
                        numberToWordTemplate.getRubWithMorph(2))).append(" ");
                thousandthsCount--;
            } else {
                result.append(morph(currentSegment, numberToWordTemplate.getOrdersMorph(thousandthsCount - 2, 0),
                        numberToWordTemplate.getOrdersMorph(thousandthsCount - 2, 1),
                        numberToWordTemplate.getOrdersMorph(thousandthsCount - 2, 2))).append(" ");
                thousandthsCount--;
            }
        }

        if (checkPenny) {
            result = new StringBuilder(result.toString().replaceAll(" {2,}", " "));
        } else {
            result.append(pennyString).append(" ").append(morph(penny, numberToWordTemplate.getPennyWithMorph(0),
                    numberToWordTemplate.getPennyWithMorph(1), numberToWordTemplate.getPennyWithMorph(2)));
            result = new StringBuilder(result.toString().replaceAll(" {2,} ", " "));
        }
        return result.toString();
    }
    
    ArrayList<Long> splitIntoSegments(long currency) {
        ArrayList<Long> segments = new ArrayList<>();
        while (currency > 999) {
            long seg = currency / 1000;
            segments.add(currency - (seg * 1000));
            currency = seg;
        }
        segments.add(currency);
        Collections.reverse(segments);
        return segments;
    }
    
    public static String morph(long n, String nominative, String accusative, String genitive) {
        n = Math.abs(n) % 100;
        long n1 = n % 10;
        if (n > 10 && n < 20) return genitive;
        if (n1 > 1 && n1 < 5) return accusative;
        if (n1 == 1) return nominative;
        return genitive;
    }
}