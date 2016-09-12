import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Provides methods to gather input from stdin in order to play a game of tic tac toe.
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Mar 2016
 */
public class HumanPlayer extends Player {

    /**
     * Constructs a HumanPlayer object with the specified name, mark, and board.
     * @param name the Player's name
     * @param mark the Player's mark
     * @param board the Board to play the game on
     * @param in the input stream
     * @param out the output stream
     */
    public HumanPlayer(String name, char mark, Board board, BufferedReader in, PrintWriter out) {
        super(name, mark, board, in, out);
    }

    /**
     * Starts a game of tic tac toe with this player as player X.
     * @throws IOException
     */
    public void play() throws java.io.IOException {
        String winner;
        Player p = this;
        while (true) {
            if (board.isFull()) {
                winner = "Nobody";
                break;
            } else if (board.xWins() == 1) {
                winner = name;
                break;
            } else if (board.oWins() == 1) {
                winner = opponent.name();
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
     * Prompts the user via stdout to make a move on the tic tac toe Baord.
     * @throws IOException
     */
    public void makeMove() throws java.io.IOException {
        int row, col;
        Player p = this;

        while (true) {
            while (true) {
                String input = "";
                boolean success = true;
                do {
                    sout.printf("I %s, what row should your next %c be placed in?\n", p.name, p.mark);
                    input = sin.readLine();
                    try {
                        Integer.parseInt(input);
                        success = false;
                    } catch (NumberFormatException e) {
                    }
                } while (success);

                row = Integer.parseInt(input);
                if (row < 0 || row > 2)
                    sout.printf("P \nP Invalid row: %d, please try again.\n", row);
                else
                    break;
            }
            while (true) {
                String input = "";
                boolean success = true;
                do {
                    sout.printf("I %s, what column should your next %c be placed in?\n", p.name, p.mark);
                    input = sin.readLine();
                    try {
                        Integer.parseInt(input);
                        success = false;
                    } catch (NumberFormatException e) {
                    }
                } while (success);

                col = Integer.parseInt(input);
                if (col < 0 || col > 2)
                    sout.printf("P \nP Invalid row: %d, please try again.\n", col);
                else
                    break;
            }


            if (board.getMark(row, col) == SPACE_CHAR) {
                board.addMark(row, col, mark);
                break;
            } else {
                sout.printf("P \nP The coordinate (%d, %d) has already been used.\n", row, col);
            }
        }
    }
}
