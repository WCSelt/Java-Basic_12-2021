package homeworks.homework8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Person implements UserSurvey{
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String userName = "";
    final int[] personAnswers = new int[TestOption.values().length];
    private String result;

    Person() throws Exception {
        System.out.println("Как вас зовут?");
        while (userName.isBlank()) {
            this.userName = askPerson();
            if (userName.isBlank()) {
                System.err.println("Имя не должно быть пустым.");
                System.out.println("Пожалуйста, введите своё имя:");
            }
        }
    }

    void greet() {
        System.out.println("Здравствуйте, " + userName + "!");
    }

    @Override
    public String askPerson() throws Exception {
        return reader.readLine();
    }

    String getUserName() {
        return userName;
    }

    String getResult() {
        int countTrueAnswers = 0, countWrongAnswers = 0;
        for (int i = 0; i < personAnswers.length; i++) {
            if (personAnswers[i] == TestOption.values()[i].getTrueTestAnswer()) {
                countTrueAnswers++;
            } else {
                countWrongAnswers++;
            }
        }
        result = "правильных ответов: " + countTrueAnswers + "\nнеправильных ответов: " + countWrongAnswers;
        return countTrueAnswers > countWrongAnswers ?
                String.format("Поздравляем, %s! Вы прошли!%n%s", getUserName(), result)
                : String.format("Сожалеем, %s. Вы не прошли.%n%s", getUserName(), result);
    }


}