//Referee.java

/**
 * Mediates and controls a game of Tic Tac Toe.
 * Begins the game by printing the board, and then asks Player X to choose
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Mar 5, 2016
 */
public class Referee {
    /**
     * Player X of the game.
     */
    private Player x;

    /**
     * Player O of the game.
     */
    private Player o;

    /**
     * The board to play on.
     */
    private Board b;

    /**
     * Construct a Referee object from Players and a Board.
     * @param board the Board for the referee to control
     * @param xPlayer the player with the mark 'X'
     * @param oPlayer the player with the mark 'O'
     */
    public Referee(Board board, Player xPlayer, Player oPlayer) {
        this.b = board;
        this.x = xPlayer;
        this.o = oPlayer;
    }

    /**
     * Initiate a game with Player X as the starting player.
     * @throws IOException
     */
    public void runTheGame() throws java.io.IOException {
        x.setOpponent(o);
        o.setOpponent(x);
        x.play();

        x.sout.println("\033[1mGame ended ...\033[0m");
        o.sout.println("\033[1mGame ended ...\033[0m");
    }
}
