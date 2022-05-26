package com.otus.homeworks.homework6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeWork {
    private final static String[] TEST_QUESTIONS = new String[]{
            "Какой из типов чисел с плавающей точкой занимает 4 байта?",
            "Какое количество значений может принимать тип int?",
            "Какое объявление переменной является неправильным?",
            "Сколько примитивных типов данных существует в в Java?",
            "Какой из предложенных целочисленных типов данных занимает наименьший объём памяти?"
    };
    private final static String[][] TEST_ANSWER_OPTIONS = new String[][]{
            {"double", "int", "float", "short"},
            {"2^16", "2^32", "от 0 до 65535", "от -214748368 до 214748367"},
            {"int i = 1;", "Integer i = 1_000_000_000;", "String s,a", "d = 4.35"},
            {"8", "11", "5", "3"},
            {"bite", "char", "float", "long"},
    };
    private final static int[] TEST_TRUE_ANSWERS = new int[]{3, 2, 4, 1, 4};
    private static int[] userAnswers = new int[5];
    private static String userName = "";
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            greeting();
            System.out.println("Вы готовы начать тестирование?\n" +
                    "Для начала тестирования введите 'да':");
            checkUserActivity();
            testingUser();
            System.out.println("Тест завершён.\n" + getTestResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void greeting() {
        System.out.println("Как вас зовут?");
        while (userName.isBlank()) {
            try {
                userName = reader.readLine();
                if (userName.isBlank()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.err.println("Поле имя не должно быть пустым.");
                System.out.println("Пожалуйста, введите своё имя:");
            }
        }
        System.out.println("Здравствуйте, " + userName + "!");
    }

    private static void checkUserActivity() throws IOException {
        boolean userActivity = false;
        while (!(userActivity)) {
            String userReady = reader.readLine();
            if (userReady.equalsIgnoreCase("да")) {
                userActivity = true;
            }
        }
    }

    private static void testingUser() {
        for (int i = 0; i < userAnswers.length; ) {
            System.out.println(TEST_QUESTIONS[i]);
            for (int j = 0; j < TEST_ANSWER_OPTIONS[i].length; j++) {
                System.out.println(String.format("%s) %s", j + 1, TEST_ANSWER_OPTIONS[i][j]));
            }
            try {
                userAnswers[i] = Integer.parseInt(reader.readLine());
                if (userAnswers[i] > TEST_ANSWER_OPTIONS[i].length || userAnswers[i] < 1) {
                    throw new IndexOutOfBoundsException("");
                }
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Пожалуйста, выберите ответ в диапазоне от 1 до 4 и введите его номер");
                continue;
            } catch (Exception e) {
                System.err.println("В качестве ответа принимаются только цифры в диапазоне от 1 до 4");
                continue;
            }
            i++;
        }
    }

    private static String getTestResult() {
        int trueAnswerCount = 0, falseAnswerCount = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] == TEST_TRUE_ANSWERS[i]) {
                trueAnswerCount++;
                continue;
            }
            falseAnswerCount++;
        }
        String testResult = "правильных ответов: " + trueAnswerCount + "\nнеправильных ответов: " + falseAnswerCount;
        return trueAnswerCount > falseAnswerCount ? String.format("Поздравляем, %s! Вы прошли!%n%s", userName, testResult)
                : String.format("Сожалеем, %s. Вы не прошли.%n%s", userName, testResult);
    }
}
