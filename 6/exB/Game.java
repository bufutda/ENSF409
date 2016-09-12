//Game.java
import java.io.*;

/**
 * @author Started by: M. Moussavi
 * Completed by: Mitchell Sawatzky and Connor Newman
 * Asks the user to select a player type, creates the player, creates the board,
 * assigns a referee to the game, then initiates the game.
 */
public class Game implements Constants {

	/**
	 * The board
	 */
	private Board theBoard;

	/**
	 * The referee
	 */
	private Referee theRef;

	/**
	 * creates a board for the game
	 */
    public Game( ) {
        theBoard  = new Board();

	}

    /**
     * calls the referee method runTheGame
     * @param r refers to the appointed referee for the game
     * @throws IOException
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }

	/**
	 * Creates the specified type of player indicated by the user.
	 *
	 * @param name player's name
	 * @param mark player's mark (X or O)
	 * @param board refers to the game board
	 * @param sin refers to an input stream
	 * @param sout refers to an output stream
	 * @return a newly created player
	 * @throws IOException
	 */
	static public Player  create_player(String name, char mark, Board board,
			BufferedReader sin, PrintWriter sout) throws IOException {
		// Get the player type.
		final int NUMBER_OF_TYPES = 4;
		sout.println("P \nP What type of player is " + name + "?\nP ");
		sout.println("P   1: human\nP " + "  2: Random Player\nP "
		+ "  3: Blocking Player\nP " + "  4: Smart Player\nP ");
		sout.println("I Please enter a number in the range 1-" + NUMBER_OF_TYPES + ": ");
		int player_type = 0;

		String input;
		input = sin.readLine();
		if (input == null || input.length() == 0) {
			player_type = -1;
		} else {
			player_type = Integer.parseInt(input);
		}
		while (player_type < 1 || player_type > NUMBER_OF_TYPES) {
			sout.println("P Please try again.\nP ");
			sout.println("I Enter a number in the range 1-" +NUMBER_OF_TYPES + ": ");
			input = sin.readLine();
			if (input == null || input.length() == 0) {
				player_type = -1;
			} else {
				player_type = Integer.parseInt(input);
			}
		}

		// Create a specific type of Player
		Player result = null;
		switch(player_type) {
			case 1:
				result = new HumanPlayer(name, mark, board, sin, sout);
				break;
			case 2:
				result = new RandomPlayer(name, mark, board, sin, sout);
				break;
			case 3:
				result = new BlockingPlayer(name, mark, board, sin, sout);
				break;
			case 4:
				result = new SmartPlayer(name, mark, board, sin, sout);
				break;
			default:
				System.out.print ( "\nDefault case in switch should not be reached.\n"
				+ "  Program terminated.\n");
				System.exit(0);
		}
		return result;
	}

	/**
	 * Starts a new game
	 * @param p1sin the input stream for player 1
	 * @param p1sout the output stream for player 1
	 * @param p2sin the input stream for player 2
	 * @param p2sout the output stream for player 2
	 * @throws IOException
	 */
	public void start (BufferedReader p1sin, PrintWriter p1sout, BufferedReader p2sin, PrintWriter p2sout) throws IOException {
		Player xPlayer, oPlayer;
		p1sout.println("I Please enter the name of the \'X\' player.");
		String name = p1sin.readLine();
		while (name == null && name.length() != 0) {
			p1sout.println("I Please try again: ");
			name = p1sin.readLine();
		}
		xPlayer = Game.create_player(name, LETTER_X, theBoard, p1sin, p1sout);
		p2sout.println("I Please enter the name of the \'O\' player.");
		name = p2sin.readLine();
		while (name == null && name.length() != 0) {
			p2sout.println("I Please try again: ");
			name = p2sin.readLine();
		}
		oPlayer = Game.create_player(name, LETTER_O, theBoard, p2sin, p2sout);

		appointReferee(new Referee(theBoard, xPlayer, oPlayer));
	}
}
