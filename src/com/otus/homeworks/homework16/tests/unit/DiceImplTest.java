package com.otus.homeworks.homework16.tests.unit;

import com.otus.homeworks.homework16.app.game.DiceImpl;
import com.otus.homeworks.homework16.tests.assertions.Assertions;
import org.junit.Test;

public class DiceImplTest {
    @Test
    public void testDiceImplValuesValidation() {
        String scenario = "Тест диапазона генерируемых значений";
        try {
            Throwable actual = null;
            try {
                for (int i = 0; i < 100; i++) {
                    int number = new DiceImpl().roll();
                    if (number<1 || number>6) {
                        throw new Throwable("Value is out of range");
                    }
                }
            } catch (Throwable e) {
                actual = e;
            }

            if (actual != null) {
                throw new Throwable("Given code does not throw any Exception");
            } else {
                Assertions.assertEquals(Exception.class, actual.getClass());
            }
            System.out.printf("\"%s\": passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\": fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }
}
