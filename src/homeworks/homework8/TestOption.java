package homeworks.homework8;

enum TestOption {

    QUESTION_1("Какой из типов чисел с плавающей точкой занимает 4 байта?",
            new String[]{"double", "int", "float", "short"},
            3),
    QUESTION_2("Какое количество значений может принимать тип int?",
            new String[]{"2^16", "2^32", "от 0 до 65535", "от -214748368 до 214748367"},
            2),
    QUESTION_3("Какое объявление переменной является неправильным?",
            new String[]{"int i = 1;", "Integer i = 1_000_000_000;", "String s,a;", "d = 4.35"},
            4),
    QUESTION_4("Сколько примитивных типов данных существует в в Java?",
            new String[]{"8", "11", "5", "3"},
            1),
    QUESTION_5("Какой из предложенных целочисленных типов данных занимает наименьший объём памяти?",
            new String[]{"bite", "char", "float", "long"},
            4);

    private final String QUESTION;
    private final String[] QUESTION_OPTIONS;
    private final int TRUE_TEST_ANSWER;

    TestOption(String QUESTION, String[] QUESTION_OPTIONS, int true_test_answer) {
        this.QUESTION = QUESTION;
        this.QUESTION_OPTIONS = QUESTION_OPTIONS;
        this.TRUE_TEST_ANSWER = true_test_answer;
    }

    public String getQuestion() {
        return QUESTION;
    }

    public String[] getQuestionOptions() {
        return QUESTION_OPTIONS;
    }

    public int getTrueTestAnswer() {
        return TRUE_TEST_ANSWER;
    }
}
