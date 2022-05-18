package homeworks.homework8;

class HomeWork {
    public static void main(String[] args) {
        Person person;
        try {
            person = new Person();
            person.greet();
            System.out.println("Приступаем к тестированию:");
            Test test = new Test(person);
            test.beginTest();
            System.out.println("Тестирование завершено.");
            System.out.println(person.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
