package homeworks.homework13;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Questionnaire implements UserSurvey {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Person person = new Person();
    final int[] personAnswers = new int[TestOption.values().length];
    private String result;

    Questionnaire(){
        System.out.println("Как вас зовут?");
        while (person.getUserName().isBlank()) {
            try {
                person.setUserName(askPerson());
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    void checkAgreementForDetailedResult() throws Exception {
        try {
            while (true) {
                System.out.println("Желаете получить подробный результат? (да/нет)");
                String personAnswer = askPerson();
                if (personAnswer.equalsIgnoreCase("да")){
                    getDetailedResult();
                    throw new IOException();
                } else if (personAnswer.equalsIgnoreCase("нет")) {
                    throw new IOException();
                }
                System.err.println("Пожалуйста, примите решение.");
            }
        } catch (IOException e) {
            reader.close();
            System.out.println("Всего доброго.");
            System.exit(0);
        } catch (Exception e){
            throw new Exception("Что-то произошло чуть выше глубин кода", e);
        }
    }

    private void getDetailedResult() throws Exception {
        System.out.println("Пожалуйста, укажите директорию, в которой хотите разместить файл");
        try {
            Path path = Paths.get(askPerson());
            if (Files.exists(path)) {
                try {

                    File file = new File(path+"TestResult_"+getDate());
                }
            }
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy_\"at\"_HH:mm:ss").format(new Date());
    }
}