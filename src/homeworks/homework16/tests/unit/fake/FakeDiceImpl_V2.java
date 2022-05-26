package homeworks.homework16.tests.unit.fake;

import homeworks.homework16.app.game.Dice;

public class FakeDiceImpl_V2 implements Dice {
    boolean switcher;
    public int roll() {
        if (!switcher){
            switcher = !switcher;
            return 4;
        } else {
            switcher = !switcher;
            return 3;
        }
    }
}
