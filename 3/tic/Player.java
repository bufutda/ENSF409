//Player.java

import java.util.Scanner;

/**
 * Provides a container to hold a Player's name and preferred mark (X or O), as well as logic to make moves.
 * @author Mitchell Sawatzky and Connor Newman
 * @version 1.0
 * @since Feb 5, 2016
 */
public class Player implements Constants {
    /**
     * The name of the player.
     */
    private String name;

    /**
     * The player's mark, either 'X' or 'O'.
     */
    private char mark;

    /**
     * The player's opponent.
     */
    private Player opp;

    /**
     * The Board to play the game on.
     */
    private Board b;

    /**
     * Constructs a Player Object with a given name, mark, and Board.
     * @param name the Player's name
     * @param mark the Player's mark, either 'X' or 'O'
     * @param b the Board to play the game on
     */
    public Player(String name, char mark, Board b) {
        this.name = name;
        this.mark = mark;
        this.b = b;
    }

    /**
     * Getter function for the Player's name.
     * @return the String name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the opponent of a given Player to another Player.
     * @param opp the Player opponent
     */
    public void setOpponent(Player opp) {
        this.opp = opp;
    }

    /**
     * Initiate a game of tic-tac-toe with the opponent player.
     */
    public void play() {
        String winner;
        Player p = this;
        while (true) {
            if (b.isFull()) {
                winner = "Nobody";
                break;
            } else if (b.xWins() == 1) {
                winner = this.name;
                break;
            } else if (b.oWins() == 1) {
                winner = this.opp.getName();
                break;
            }
            p.makeMove();
            b.display();
            p = p.opp;
        }
        System.out.printf("\nTHE GAME IS OVER: %s is the winner!\n", winner);
    }

    /**
     * Prompt the user to place their mark on a given board slot retrieved through stdin.
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

            if (b.getMark(row, col) == SPACE_CHAR) {
                b.addMark(row, col, mark);
                break;
            } else {
                System.out.printf("\nThe coordinate (%d, %d) has already been used.\n", row, col);
            }
        }
    }
}
