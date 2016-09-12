import java.util.Scanner;

/**
 * Provides methods to gather input from stdin in order to play a game of tic tac toe.
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Feb 2016
 */
public class HumanPlayer extends Player {

    /**
     * Constructs a HumanPlayer object with the specified name, mark, and board.
     * @param name the Player's name
     * @param mark the Player's mark
     * @param board the Board to play the game on
     */
    public HumanPlayer(String name, char mark, Board board) {
        super(name, mark, board);
    }

    /**
     * Starts a game of tic tac toe with this player as player X.
     */
    public void play() {
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
            p.makeMove();
            board.display();
            p = p.opponent;
        }
        System.out.printf("\nTHE GAME IS OVER: %s is the winner!\n", winner);
    }

    /**
     * Prompts the user via stdout to make a move on the tic tac toe Baord.
     */
    public void makeMove() {
        int row, col;
        Player p = this;

        while (true) {
            while (true) {
                System.out.printf("%s, what row should your next %c be placed in? ", p.name, p.mark);
                Scanner input = new Scanner(System.in);
                row = input.nextInt();
                if (row < 0 || row > 2)
                    System.out.printf("\nInvalid row: %d, please try again.\n", row);
                else
                    break;
            }

            while (true) {
                System.out.printf("%s, what column should your next %c be placed in? ", name, mark);
                Scanner input = new Scanner(System.in);
                col = input.nextInt();
                if (col < 0 || col > 2)
                    System.out.printf("\nInvalid column: %d, please try again.\n", col);
                else
                    break;
            }

            if (board.getMark(row, col) == SPACE_CHAR) {
                board.addMark(row, col, mark);
                break;
            } else {
                System.out.printf("\nThe coordinate (%d, %d) has already been used.\n", row, col);
            }
        }
    }
}
