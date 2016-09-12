/**
 * Provides a tic tac toe robot that randomly chooses a space on every move.
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Feb 2016
 */
public class RandomPlayer extends Player {

    /**
     * Constructs a RandomPlayer object with the specified name, mark, and board.
     */
    public RandomPlayer(String name, char mark, Board board) {
        super(name, mark, board);
    }

    /**
     * Starts a game of tic tac toe with this player as player X.
     */
    protected void play() {
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
            p.makeMove();
            board.display();
            p = p.opponent;
        }
        System.out.printf("\nTHE GAME IS OVER: %s is the winner!\n", winner);
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
