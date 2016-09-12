//Player.java
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Provides a container to hold a Player's name and preferred mark (X or O), as well as logic prototypes.
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Mar 5, 2016
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
     * The input stream
     */
    protected BufferedReader sin;

    /**
     * The output stream
     */
    protected PrintWriter sout;

    /**
     * Constructs a Player Object with a given name, mark, and Board.
     * @param name the Player's name
     * @param mark the Player's mark, either 'X' or 'O'
     * @param b the Board to play the game on
     * @param in the input stream
     * @param out the output stream
     */
    public Player(String name, char mark, Board b, BufferedReader in, PrintWriter out) {
        this.name = name;
        this.mark = mark;
        this.board = b;
        sin = in;
        sout = out;
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
     * @throws IOException
     */
    abstract protected void play() throws java.io.IOException;

    /**
     * Prompt the user to place their mark on a given board slot retrieved through stdin.
     * @Throws IOException
     */
    abstract protected void makeMove() throws java.io.IOException;
}
