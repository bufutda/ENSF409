//Player.java

/**
 * Provides a container to hold a Player's name and preferred mark (X or O), as well as logic prototypes.
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Feb 5, 2016
 */
abstract class Player implements Constants {
    /**
     * The name of the player.
     */
    protected String name;

    /**
     * The player's mark, either 'X' or 'O'.
     */
    protected char mark;

    /**
     * The player's opponent.
     */
    protected Player opponent;

    /**
     * The Board to play the game on.
     */
    protected Board board;

    /**
     * Constructs a Player Object with a given name, mark, and Board.
     * @param name the Player's name
     * @param mark the Player's mark, either 'X' or 'O'
     * @param b the Board to play the game on
     */
    public Player(String name, char mark, Board b) {
        this.name = name;
        this.mark = mark;
        this.board = b;
    }

    /**
     * Getter function for the Player's name.
     * @return the String name of the Player
     */
    protected String name() {
        return name;
    }

    /**
     * Getter function for the Player's mark
     * @return the char mark of the Player
     */
    protected char mark() {
        return mark;
    }
    /**
     * Sets the opponent of a given Player to another Player.
     * @param opp the Player opponent
     */
    protected void setOpponent(Player other) {
        this.opponent = other;
    }

    /**
     * Initiate a game of tic-tac-toe with the opponent player.
     */
    abstract protected void play();

    /**
     * Prompt the user to place their mark on a given board slot retrieved through stdin.
     */
    abstract protected void makeMove();
}
