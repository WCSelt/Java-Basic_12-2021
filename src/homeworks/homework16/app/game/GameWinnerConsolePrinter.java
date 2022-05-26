package homeworks.homework16.app.game;

public class GameWinnerConsolePrinter implements GameWinnerPrinter {
    @Override
    public void printWinner(Player winner) {
        System.out.printf("Победитель: %s%n", winner.getName());
    }
}
