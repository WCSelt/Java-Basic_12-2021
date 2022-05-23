package homeworks.homework8;

class HomeWork {
    public static void main(String[] args) {
        try {
            Test test = new Test();
            System.out.println("Приступаем к тестированию.");
            test.beginTest();
            System.out.println("Тестирование завершено.");
            System.out.println(test.getQuestionnaire().getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
