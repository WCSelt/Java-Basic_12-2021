package homeworks.homework16.tests;

import homeworks.homework16.tests.unit.DiceImplTest;
import homeworks.homework16.tests.unit.GameTest;

public class AllTests {
    public void runTests () {
        new GameTest().testGameWithBlankPlayerNames();
        new GameTest().testGameWithFakeDiceImpl();
        new DiceImplTest().testDiceImplValuesValidation();
        new GameTest().testGameWithNullPlayers();
    }
}
