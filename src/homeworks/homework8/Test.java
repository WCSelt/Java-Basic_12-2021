package homeworks.homework8;

class Test {
    private final Person person;
    private final TestOption[] testOption = TestOption.values();
    private final static String answerTemplateForError = "В качестве ответа принимаются только цифры в диапазоне от 1 до %s";
    private final static String ANSWER_TemplateForIndex = "Пожалуйста, выберите ответ в диапазоне от 1 до %s и введите его номер";

    Test(Person person) {
        this.person = person;
    }

    void beginTest() {
        for (int i = 0; i < testOption.length; i++) {
            System.out.println(getQuestionWithOptions(i));
            while (person.personAnswers[i] == 0) {
                try {
                    int personAnswer = Integer.parseInt(person.askPerson());
                    if (personAnswer > 0 && personAnswer <= testOption[i].getQuestionOptions().length) {
                        person.personAnswers[i] = personAnswer;
                    } else {
                        System.err.printf(ANSWER_TemplateForIndex,testOption[i].getQuestionOptions().length);
                    }
                } catch (Exception e) {
                    System.err.printf(answerTemplateForError+"\n",testOption[i].getQuestionOptions().length);
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
