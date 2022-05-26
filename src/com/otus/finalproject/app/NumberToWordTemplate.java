package com.otus.finalproject.app;

public class NumberToWordTemplate {
    private String[] currencyMorph;
    private String[] pennyMorph;
    private final String[][] firstOrderNumbersWithMorph = {
            {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
    };

    private final String[] secondTenNumbers = {"", "десять", "одиннадцать", "двенадцать", "тринадцать",
            "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать", "двадцать"};

    private final String[] secondOrderNumbers = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
            "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};

    private final String[] thirdOrderNumbers = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот", "девятьсот"};

    private final String[][] ordersMorph = {
            pennyMorph,
            currencyMorph,
            {"тысяча", "тысячи", "тысяч", "1"},
            {"миллион", "миллиона", "миллионов", "0"},
            {"миллиард", "миллиарда", "миллиардов", "0"},
            {"триллион", "триллиона", "триллионов", "0"},
            {"квадрилион","квадрилиона","квадрилионов","0"},
    };

    public void setMorph(String[] currencyMorph,String[] pennyMorph) {
        this.currencyMorph = currencyMorph;
        this.pennyMorph = pennyMorph;
    }

    String getCurrencyMorph(int index) {
        return currencyMorph[index];
    }

    String getPennyMorph(int index) {
        return pennyMorph[index];
    }

    String getFirstOrderNumbersMorph(int index1, int index2) {
        return firstOrderNumbersWithMorph[index1][index2];
    }

    String getSecondTenNumbers(int index) {
        return secondTenNumbers[index];
    }

    String getSecondOrderNumbers(int index) {
        return secondOrderNumbers[index];
    }

    String getThirdOrderNumbers(int index) {
        return thirdOrderNumbers[index];
    }

    String getOrdersMorph(int index1, int index2) {
        if (index1 == 0){
            return getPennyMorph(index2);
        }
        if (index1 == 1){
            return getCurrencyMorph(index2);
        }
        return ordersMorph[index1][index2];
    }
}
