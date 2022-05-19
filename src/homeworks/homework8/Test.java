package homeworks.homework8;

class Test {
    private final Questionnaire questionnaire;
    private final TestOption[] testOption = TestOption.values();
    private final static String ANSWER_TEMPLATE_FOR_PARSING_ERROR = "В качестве ответа принимаются только цифры в диапазоне от 1 до %s";
    private final static String ANSWER_TEMPLATE_FOR_INDEX_ERROR = "Пожалуйста, выберите ответ в диапазоне от 1 до %s и введите его номер";

    Test() {
        this.questionnaire = new Questionnaire();
    }

    void beginTest() {
        for (int i = 0; i < testOption.length; i++) {
            System.out.println(getQuestionWithOptions(i));
            while (questionnaire.getPersonAnswer(i) == 0) {
                try {
                    int personAnswer = Integer.parseInt(questionnaire.askPerson());
                    if (personAnswer > 0 && personAnswer <= testOption[i].getQuestionOptions().length) {
                        questionnaire.personAnswers[i] = personAnswer;
                    } else {
                        System.err.printf(ANSWER_TEMPLATE_FOR_INDEX_ERROR+"\n",testOption[i].getQuestionOptions().length);
                    }
                } catch (Exception e) {
                    System.err.printf(ANSWER_TEMPLATE_FOR_PARSING_ERROR+"\n",testOption[i].getQuestionOptions().length);
                }
            }
        }
    }

    private String getQuestionWithOptions(int i) {
        StringBuilder questionWithOptions =
                new StringBuilder(String.format("%s. %s%n", i + 1, testOption[i].getQuestion()));
        for (int j = 0; j < testOption[i].getQuestionOptions().length; j++) {
            questionWithOptions.append(String.format("%n%s) %s",
                    j + 1, testOption[i].getQuestionOptions()[j]));
        }
        return questionWithOptions.toString();
    }
}
