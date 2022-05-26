package com.otus.finalproject.app;

public enum CurrencyMorphTemplate {
    RUB(new String[]{"рубль", "рубля", "рублей", "0"},new String[]{"копейка", "копейки", "копеек", "1"}),
    USD(new String[]{"доллар","доллара","долларов","0"},new String[]{"цент","цента","центов","0"}),
    EUR(new String[]{"евро","евро","евро","0"},new String[]{"евроцент","евроцента","евроцентов","0"});
    //Валюту добавлять сюда

    private final String[] currencyMorph;
    private final String[] pennyMorph ;

    CurrencyMorphTemplate(String[] currencyMorph, String[] pennyMorph) {
        this.currencyMorph = currencyMorph;
        this.pennyMorph = pennyMorph;
    }

    public String[] getCurrencyMorph(){
        return currencyMorph;
    }

    public String[] getPennyMorph(){
        return pennyMorph;
    }
}
