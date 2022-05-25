package finalproject.app;

class NumberToWordTemplate {
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
            {"тысяча", "тысячи", "тысяч", "1"},
            {"миллион", "миллиона", "миллионов", "0"},
            {"миллиард", "миллиарда", "миллиардов", "0"},
            {"триллион", "триллиона", "триллионов", "0"},
            {"квадрилион","квадрилиона","квадрилионов","0"},
    };

    private final String[] rubWithMorph = {"рубль", "рубля", "рублей", "0"};

    private final String[] pennyWithMorph = {"копейка", "копейки", "копеек", "1"};

    public String getFirstOrderNumbersMorph(int index1, int index2) {
        return firstOrderNumbersWithMorph[index1][index2];
    }

    public String getSecondTenNumbers(int index) {
        return secondTenNumbers[index];
    }

    public String getSecondOrderNumbers(int index) {
        return secondOrderNumbers[index];
    }

    public String getThirdOrderNumbers(int index) {
        return thirdOrderNumbers[index];
    }

    public String getOrdersMorph(int index1, int index2) {
        return ordersMorph[index1][index2];
    }

    public String getRubWithMorph(int index) {
        return rubWithMorph[index];
    }

    public String getPennyWithMorph(int index) {
        return pennyWithMorph[index];
    }
}
