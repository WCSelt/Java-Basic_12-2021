package com.otus.homeworks.homework13;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Questionnaire implements UserSurvey {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Person person = new Person();
    final int[] personAnswers = new int[TestOption.values().length];
    private String result;
    private final List<String> listWithTemplateForDetailedResult = new ArrayList<>();

    Questionnaire() {
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

    int getPersonAnswer(int index) {
        return personAnswers[index];
    }

    String getResult() {
        int countTrueAnswers = 0, countWrongAnswers = 0;
        listWithTemplateForDetailedResult.add("Имя тестируемого: " + person.getUserName() + "\n");
        listWithTemplateForDetailedResult.add("Дата: " + getDate() + "\n");
        for (int i = 0; i < personAnswers.length; i++) {
            if (personAnswers[i] == TestOption.values()[i].getTrueTestAnswer()) {
                addTestOptionToList(TestOption.values()[i].getQuestion(),
                        TestOption.values()[i].getQuestionOptions()[personAnswers[i] - 1],
                        TestOption.values()[i].getQuestionOptions()[TestOption.values()[i].getTrueTestAnswer() - 1]);
                countTrueAnswers++;
            } else {
                addTestOptionToList(TestOption.values()[i].getQuestion(),
                        TestOption.values()[i].getQuestionOptions()[personAnswers[i] - 1],
                        TestOption.values()[i].getQuestionOptions()[TestOption.values()[i].getTrueTestAnswer() - 1]);
                countWrongAnswers++;
            }
        }
        result = "правильных ответов: " + countTrueAnswers + "\nнеправильных ответов: " + countWrongAnswers;
        listWithTemplateForDetailedResult.add(result);
        return countTrueAnswers > countWrongAnswers ?
                String.format("Поздравляем, %s! Вы прошли!%n%s", person.getUserName(), result)
                : String.format("Сожалеем, %s. Вы не прошли.%n%s", person.getUserName(), result);
    }

    void checkAgreementForDetailedResult() throws Exception {
        try {
            while (true) {
                System.out.println("Желаете получить подробный результат? (да/нет)");
                String personAnswer = askPerson();
                if (personAnswer.equalsIgnoreCase("да")) {
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
        } catch (Exception e) {
            throw new Exception("Что-то произошло в методе checkAgreementForDetailedResult()", e);
        }
    }

    private void getDetailedResult() throws Exception {
        System.out.println("Пожалуйста, укажите директорию, в которой хотите разместить файл");
        try {
            while (true) {
                String pathName = askPerson();
                if (pathName.equalsIgnoreCase("exit")) {
                    throw new IOException("была команда exit");
                }
                Path path = Paths.get(pathName);
                if (Files.exists(path)) {
                    File file = new File(path.toFile(), "TestResult_" + getDate() + ".txt");
                    try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
                        for (int i = 0; i < listWithTemplateForDetailedResult.size(); i++) {
                            writer.write(listWithTemplateForDetailedResult.get(i));
                            writer.newLine();
                        }
                    } catch (Exception e) {
                        throw new Exception("Что-то случилось во время записи", e);
                    }
                    throw new IOException();
                } else {
                    System.err.println("Проверьте, пожалуйста, указанную вами директорию либо введите \"exit\"" +
                            " для выхода");
                }
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_'at'_HH:mm:ss");
        return dateFormat.format(new Date());
    }

    private void addTestOptionToList(String question, String personAnswer, String testAnswer) {
        listWithTemplateForDetailedResult.add(question + ": \nВаш ответ: " + personAnswer + "  Правильный ответ: " + testAnswer + "\n");
    }
}