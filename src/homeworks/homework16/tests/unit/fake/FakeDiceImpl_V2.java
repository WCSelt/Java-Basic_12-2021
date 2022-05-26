package homeworks.homework16.tests.unit.fake;

import homeworks.homework16.app.game.Dice;

public class FakeDiceImpl_V2 implements Dice {
    boolean switcher;
    public int roll() {
        switcher = !switcher;
        return switcher ? 4 : 3;
    }
}
