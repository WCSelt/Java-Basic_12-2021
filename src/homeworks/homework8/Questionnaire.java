package homeworks.homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Questionnaire implements UserSurvey {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Person person = new Person();
    final int[] personAnswers = new int[TestOption.values().length];
    private String result;

    Questionnaire() throws IOException {
        System.out.println("Как вас зовут?");
        while (person.getUserName().isBlank()) {
            person.setUserName(askPerson());
            if (person.getUserName().isBlank()) {
                System.err.println("Имя не должно быть пустым.");
                System.out.println("Пожалуйста, введите своё имя:");
            }
        }
        greetPerson();
    }

    @Override
    public String askPerson() throws IOException {
        return reader.readLine();
    }

    void greetPerson() {
        System.out.println("Здравствуйте, " + person.getUserName() + "!");
    }

    int getPersonAnswer (int index){
        return personAnswers[index];
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
                String.format("Поздравляем, %s! Вы прошли!%n%s", person.getUserName(), result)
                : String.format("Сожалеем, %s. Вы не прошли.%n%s", person.getUserName(), result);
    }
}
