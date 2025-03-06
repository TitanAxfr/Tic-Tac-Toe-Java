public class Player {
    private char currentPlayer;

    public Player() {
        currentPlayer = 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void resetPlayer() {
        currentPlayer = 'X';
    }
}
