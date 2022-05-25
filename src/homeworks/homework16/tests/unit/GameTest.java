package homeworks.homework16.tests.unit;

import homeworks.homework16.app.game.*;
import homeworks.homework16.tests.assertions.Assertions;
import homeworks.homework16.tests.unit.fake.FakeDiceImpl;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameTest {
    @Test
    public void testGameWithBlankPlayerNames() {
        String scenario = "Тест с пустыми значениями name";
        try {
            Exception actual = null;
            try {
                new Game(new DiceImpl(), new GameWinnerConsolePrinter()).playGame(new Player(""), new Player(""));
            } catch (Exception e) {
                actual = e;
            }

            if (actual == null) {
                throw new Exception("Given code does not throw any Exception");
            } else {
                Assertions.assertEquals(Exception.class, actual.getClass());
            }
            System.out.printf("\"%s\": passed %n", scenario);
        } catch (Exception e) {
            System.err.printf("\"%s\": fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    @Test
    public void testGameWithNullPlayers() {
        String scenario = "Тест с null вместо игроков";
        try {
            Throwable actual = null;
            try {
                new Game(new DiceImpl(), new GameWinnerConsolePrinter()).playGame(null, null);
            } catch (Throwable e) {
                actual = e;
            }

            if (actual == null) {
                throw new Throwable("Given code does not throw any Exception");
            } else {
                Assertions.assertEquals(NullPointerException.class, actual.getClass());
            }
            System.out.printf("\"%s\": passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\": fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    @Test
    public void testGameWithFakeDiceImpl() {
        String scenario = "Поведение при одинаковых значениях Dice";
        try {
            //Перенаправляем поток вывода и записываем в actual
            String actual;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);
            new Game(new FakeDiceImpl(), new GameWinnerConsolePrinter()).playGame(new Player("Вася"), new Player("Игорь"));
            System.out.flush();
            System.setOut(old);
            actual = baos.toString();

            if (actual != null && actual.contains("Победитель")) {
                throw new Throwable("Given code does not realize function \"draw\"");
            }
            System.out.printf("\"%s\": passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\": fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

//    @Test
//    public void testGameWithFakeDiceImpl()
}