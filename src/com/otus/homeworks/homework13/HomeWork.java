package com.otus.homeworks.homework13;

class HomeWork {
    public static void main(String[] args) {
        try {
            Test test = new Test();
            System.out.println("Приступаем к тестированию.");
            test.beginTest();
            System.out.println("Тестирование завершено.");
            System.out.println(test.getQuestionnaire().getResult());
            test.getQuestionnaire().checkAgreementForDetailedResult();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}