package com.otus.homeworks.homework16.tests.unit.fake;

import com.otus.homeworks.homework16.app.game.GameWinnerPrinter;
import com.otus.homeworks.homework16.app.game.Player;

public class FakeGameWinnerConsolePrinter implements GameWinnerPrinter {
    Player player;
    @Override
    public void printWinner(Player winner) {
        this.player = winner;
        System.out.printf("Победитель: %s%n", winner.getName());
    }
    public Player getPlayer(){
        return player;
    }
}
