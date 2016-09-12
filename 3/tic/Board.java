// Board.java
// ENSF 409 - LAB 3 - Ex. C
// This file was originally written for ENGG 335 in fall 2001, and was
// adapted for ENSF 409 in 2014
//

/**
 * Provides a tic-tac-toe board and logic to fill, empty, and test if a player has won.
 * @author Originally written by Mahmood Moussavi, modified by Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Originally written in fall 2001, adapted in 2014, modified in 2016
 */
public class Board implements Constants {
    /**
     * Two-Dimensional char array to hold the values of each slot on the board
     */
	private char theBoard[][];

    /**
     * The total number of slots filled in on the board.
     */
	private int markCount;

    /**
     * Constructs a Board object without any spaces filled in.
     */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

    /**
     * Returns the value of a board slot at a given row and column.
     * @param row the row to retrieve the board slot from
     * @param col the column to retrieve the board slot from
     * @return the Character value of the board slot
     */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

    /**
     * Returns whether or not the board has values in all 9 slots.
     * @return True if all 9 slots are full, False otherwise
     */
	public boolean isFull() {
		return markCount == 9;
	}

    /**
     * Checks whether or not the letter X has won on the current board.
     * @return 0 if X has not won, 1 otherwise
     */
	public int xWins() {
		return checkWinner(LETTER_X);
	}

    /**
     * Checks whether or not the letter O has won on the current board.
     * @return 0 if O has not won, 1 otherwise
     */
	public int oWins() {
		return checkWinner(LETTER_O);
	}

    /**
     * Prints the board to stdout.
     */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

    /**
     * Sets the value of the board slot at a given row and column.
     * @param row the row to set the slot value
     * @param col the column to set the slot value
     * @param mark the Character to set the slot to
     */
	public void addMark(int row, int col, char mark) {
		theBoard[row][col] = mark;
		markCount++;
	}

    /**
     * Resets every value on the board to SPACE_CHAR.
     */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

    /**
     * Uses tic-tac-toe logic to determine if a specific player has won.
     * @param mark the player to check, either LETTER_X or LETTER_O
     * @return 0 if the player has lost, 1 otherwise
     */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}


		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

    /**
     * Print the board's column headers to stdout.
     */
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}

    /**
     * Adds a line to separate the board's rows.
     */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}

    /**
     * Adds spacing inside the board to correctly place the values of the slots.
     */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
