/**
 * Provides a tic tac toe robot Player that attempts to block every move it's opponent makes.
 * @version 1.0
 * @author Mitchell Sawatzky and Connor Newman
 * @since Feb 2016
 */
public class BlockingPlayer extends RandomPlayer {

    /**
     * Constructs a BlockingPlayer object with the specified name, mark, and board.
     * @param name the Player's name
     * @param mark the Player's mark
     * @param board the Player's board
     */
    public BlockingPlayer(String name, char mark, Board board) {
        super (name, mark, board);
    }

    /**
     * Detects whether or not the opponent is about to win, and blocks it if necesarry, otherwise it makes a random move.
     */
    protected void makeMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((board.getMark(i, j) == SPACE_CHAR) && testForBlocking(i, j)) {
                    board.addMark(i, j, mark);
                    return;
                }
            }
        }
        super.makeMove();
    }

    /**
     * Tests wether or not the specified board space would win the game for the opponent if they played there on the next turn.
     * @param row the row of the board to test
     * @param col the column of the board to test
     * @return true if the space needs to be blocked, false otherwise
     */
    protected boolean testForBlocking(int row, int col) {
        char oM = opponent.mark();
        boolean res = true;

        // row
        for (int i = 0; i < 3; i++) {
            if ((i != col) && (board.getMark(row, i) != oM)) {
                res = false;
                break;
            }
        }
        if (res)
            return true;

        // col
        res = true;
        for (int i = 0; i < 3; i++) {
            if ((i != row) && (board.getMark(i, col) != oM)) {
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
                if (col != 0 && board.getMark(2, 0) == oM && board.getMark(1, 1) == oM)
                    return true;
                else if (board.getMark(2, 2) == oM && board.getMark(1, 1) == oM)
                    return true;
                break;
            case 1:
                if ((board.getMark(0, 0) == oM && board.getMark(2, 2) == oM) ||
                    (board.getMark(0, 2) == oM && board.getMark(2, 0) == oM))
                    return true;
                break;
            case 2:
                if (col != 0 && board.getMark(0, 0) == oM && board.getMark(1, 1) == oM)
                    return true;
                else if (board.getMark(0, 2) == oM && board.getMark(1, 1) == oM)
                    return true;
                break;
        }
        return false;
    }
}
