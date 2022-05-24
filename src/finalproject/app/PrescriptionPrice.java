package finalproject.app;

import java.util.ArrayList;
import java.util.Collections;
import java.math.BigDecimal;

public class PrescriptionPrice {
    private final CurrencyType currencyType = new CurrencyType();
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

    public PrescriptionPrice(String number) throws Exception{
        if (!number.contains("."))
            number += ".0";
        this.amount = new BigDecimal(number);
    }

    public String getAmountAsString() {
        return amount.toString();
    }

    /**
     * <br/>     * Вернуть сумму прописью, с точностью до копеек<br/>
     */
    public String getNumberToString() {
        return numberToString(false);
    }

    /**
     * <br/>     * Выводим сумму прописью<br/>     * @param checkPenny boolean флаг - показывать копейки или нет<br/>     * @return String Сумма прописью<br/>
     */
    public String numberToString(boolean checkPenny) {
        String[][] forms = {
                {"копейка", "копейки", "копеек", "1"},
                {"рубль", "рубля", "рублей", "0"},
                {"тысяча", "тысячи", "тысяч", "1"},
                {"миллион", "миллиона", "миллионов", "0"},
                {"миллиард", "миллиарда", "миллиардов", "0"},
                {"триллион", "триллиона", "триллионов", "0"},
                {"квадрилион","квадрилиона","квадрилионов","0"},
                // можно добавлять дальше секстиллионы и т.д.
        };
        // получаем отдельно рубли и копейки
        long currency = amount.longValue();
        String[] moi = amount.toString().split("\\.");
        long penny = Long.valueOf(moi[1]);
        if (!moi[1].substring(0, 1).equals("0")) {// начинается не с нуля
            if (penny < 10)
                penny *= 10;
        }
        String pennyString = String.valueOf(penny);
        if (pennyString.length() == 1)
            pennyString = "0" + pennyString;
        long currency_tmp = currency;
        // Разбиватель суммы на сегменты по 3 цифры с конца
        ArrayList<Long> segments = new ArrayList();
        while (currency_tmp > 999) {
            long seg = currency_tmp / 1000;
            segments.add(currency_tmp - (seg * 1000));
            currency_tmp = seg;
        }
        segments.add(currency_tmp);
        Collections.reverse(segments);
        // Анализируем сегменты
        String o = "";
        if (currency == 0) {// если Ноль
            o = "ноль " + morph(0, forms[1][0], forms[1][1], forms[1][2]);
            if (checkPenny)
                return o;
            else
                return o + " " + penny + " " + morph(penny, currencyType.getPennyWithMorph(0),
                        currencyType.getPennyWithMorph(1), currencyType.getPennyWithMorph(2));
        }
        // Больше нуля
        int lev = segments.size();
        for (int i = 0; i < segments.size(); i++) {// перебираем сегменты
            int brandi = (int) Integer.valueOf(forms[lev][3].toString());// определяем род
            int ri = (int) Integer.valueOf(segments.get(i).toString());// текущий сегмент
            if (ri == 0 && lev > 1) {// если сегмент ==0 И не последний уровень(там Units)
                lev--;
                continue;
            }
            String rs = String.valueOf(ri); // число в строку
            // нормализация
            if (rs.length() == 1) {
                rs = "00" + rs;// два нулика в префикс?
            }
            if (rs.length() == 2) {
                rs = "0" + rs; // или лучше один?
            }
            // получаем циферки для анализа
            int r1 = (int) Integer.valueOf(rs.substring(0, 1)); //первая цифра
            int r2 = (int) Integer.valueOf(rs.substring(1, 2)); //вторая
            int r3 = (int) Integer.valueOf(rs.substring(2, 3)); //третья
            int r22 = (int) Integer.valueOf(rs.substring(1, 3)); //вторая и третья
            // Супер-нано-анализатор циферок
            if (ri > 99) o += currencyType.getThirdOrderNumbers(r1) + " "; // Сотни
            if (r22 > 20) {// >20
                o += currencyType.getSecondOrderNumbers(r2) + " ";

                o += currencyType.getFirstOrderNumbersWithMorph(brandi,r3) + " ";
            } else { // <=20
                if (r22 > 9) {
                    o += currencyType.getSecondTenNumbers(r22 - 9) + " "; // 10-20
                }
                else {
                    o += currencyType.getFirstOrderNumbersWithMorph(brandi,r3) + " "; // 0-9
                }
            }
            // Единицы измерения (рубли...)
            o += morph(ri, forms[lev][0], forms[lev][1], forms[lev][2]) + " ";
            lev--;
        }
        // Копейки в цифровом виде
        if (checkPenny) {
            o = o.replaceAll(" {2,}", " ");
        } else {
            o = o + "" + pennyString + " " + morph(penny, currencyType.getPennyWithMorph(0),
                    currencyType.getPennyWithMorph(1), currencyType.getPennyWithMorph(2));
            o = o.replaceAll(" {2,}", " ");
        }
        return o;
    }

    /**
     * <br/>     * Склоняем словоформу<br/>     * @param n Long количество объектов<br/>     * @param f1 String вариант словоформы для одного объекта<br/>     * @param f2 String вариант словоформы для двух объектов<br/>     * @param f5 String вариант словоформы для пяти объектов<br/>     * @return String правильный вариант словоформы для указанного количества объектов<br/>
     */
    public static String morph(long n, String f1, String f2, String f5) {
        n = Math.abs(n) % 100;
        long n1 = n % 10;
        if (n > 10 && n < 20) return f5;
        if (n1 > 1 && n1 < 5) return f2;
        if (n1 == 1) return f1;
        return f5;
    }


}