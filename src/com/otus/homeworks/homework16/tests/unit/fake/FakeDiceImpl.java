package com.otus.homeworks.homework16.tests.unit.fake;

import com.otus.homeworks.homework16.app.game.Dice;

public class FakeDiceImpl implements Dice {
    @Override
    public int roll() {
        return 3;
    }
}
