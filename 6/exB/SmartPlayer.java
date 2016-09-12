import java.io.BufferedReader;
import java.io.PrintWriter;
/**
 * Provides a tic tac toe robot that first checks it it can win, and then checks whether or not it can block the opponent from winning.
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Mar 2016
 */
public class SmartPlayer extends BlockingPlayer {

    /**
     * Constructs a SmartPlayer object with the specified name, mark, and board.
     * @param name the Player's name
     * @param mark the Player's mark
     * @param board the board to play the game on
     * @param in the input stream
     * @param out the output stream
     */
    public SmartPlayer(String name, char mark, Board board, BufferedReader in, PrintWriter out) {
        super(name, mark, board, in, out);
    }

    /**
     * First checks whether it can win the game, and then falls back to BlockingPlayer's logic to block the opponent.
     */
    protected void makeMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((board.getMark(i, j) == SPACE_CHAR) && testForWinning(i, j)) {
                    board.addMark(i, j, mark);
                    return;
                }
            }
        }
        super.makeMove();
    }

    /**
     * Decides whether placing a mark in the specified row and column will win the getName
     * @param row the row to place the mark in
     * @param col the column to place the mark in
     * @return true if placing the mark wins the game, false otherwise
     */
    public boolean testForWinning(int row, int col) {
        boolean res = true;

        // row
        for (int i = 0; i < 3; i++) {
            if ((i != col) && (board.getMark(row, i) != mark)) {
                res = false;
                break;
            }
        }
        if (res)
            return true;

        // col
        res = true;
        for (int i = 0; i < 3; i++) {
            if ((i != row) && (board.getMark(i, col) != mark)) {
                res = false;
                break;
            }
        }
        if (res)
            return true;

        // can't be diagonal
        if ((row + col) % 2 != 0)
            return false;

        // diagonal
        switch (row) {
            case 0:
                if (col != 0 && board.getMark(2, 0) == mark && board.getMark(1, 1) == mark)
                    return true;
                else if (board.getMark(2, 2) == mark && board.getMark(1, 1) == mark)
                    return true;
                break;
            case 1:
                if ((board.getMark(0, 0) == mark && board.getMark(2, 2) == mark) ||
                    (board.getMark(0, 2) == mark && board.getMark(2, 0) == mark))
                    return true;
                break;
            case 2:
                if (col != 0 && board.getMark(0, 0) == mark && board.getMark(1, 1) == mark)
                    return true;
                else if (board.getMark(0, 2) == mark && board.getMark(1, 1) == mark)
                    return true;
                break;
        }
        return false;
    }
}
