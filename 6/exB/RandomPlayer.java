import java.io.BufferedReader;
import java.io.PrintWriter;
/**
 * Provides a tic tac toe robot that randomly chooses a space on every move.
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Mar 2016
 */
public class RandomPlayer extends Player {

    /**
     * Constructs a RandomPlayer object with the specified name, mark, and board.
     * @param name the name of the player
     * @param mark the mark of the player
     * @param board the game board
     * @param in the input stream
     * @param out the output stream
     */
    public RandomPlayer(String name, char mark, Board board, BufferedReader in, PrintWriter out) {
        super(name, mark, board, in, out);
    }

    /**
     * Starts a game of tic tac toe with this player as player X.
     * @throws IOException
     */
    protected void play() throws java.io.IOException {
        String winner;
        Player p = this;
        while (true) {
            if (board.isFull()) {
                winner = "Nobody";
                break;
            } else if (board.xWins() == 1) {
                winner = p.name;
                break;
            } else if (board.oWins() == 1) {
                winner = p.opponent.name();
                break;
            }
            board.display(p.sout);
            p.makeMove();
            p = p.opponent;
        }
        sout.printf("P \nP THE GAME IS OVER: %s is the winner!\n", winner);
        opponent.sout.printf("P \nP THE GAME IS OVER: %s is the winner!\n", winner);
        sout.println("Q");
        opponent.sout.println("Q");
        sout.close();
        sin.close();
        opponent.sout.close();
        opponent.sin.close();
        System.exit(0);
    }

    /**
     * Picks a random board slot and makes a move there.
     */
    protected void makeMove() {
        RandomGenerator rand = new RandomGenerator();
        int row, col;

        do {
            row = rand.discrete(0, 2);
            col = rand.discrete(0, 2);
        } while (board.getMark(row, col) != SPACE_CHAR);

        board.addMark(row, col, mark);
    }
}
