import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server for a game of Tic Tac Toe
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Mar, 2016
 */
public class TTTServer implements Constants {
    /**
     * The server socket
     */
    private ServerSocket sSock;

    /**
     * A ServerConnection for player 1
     */
    protected ServerConnection p1 = null;

    /**
     * A ServerConnection for player 2
     */
    protected ServerConnection p2 = null;

    /**
     * Thread wrapper for each connection
     * @author Mitchell Sawatzky
     * @since Mar, 2016
     * @version 1.0
     */
    private class ServerConnection implements Runnable {
        /**
         * The output stream
         */
        private PrintWriter sOut;

        /**
         * The input stream
         */
        private BufferedReader sIn;

        /**
         * The client socket
         */
        private Socket sock;

        /**
         * Whether or not this is player 1
         */
        private boolean playerOne;

        /**
         * Constructs a ServerConnection ovject with a socket
         * @constructor
         * @param s the client socket
         * @param pOne if this connection is player 1
         * @throws IOException
         */
        private ServerConnection (Socket s, boolean pOne) throws IOException {
            sock = s;
            sIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            sOut = new PrintWriter(s.getOutputStream(), true);
            playerOne = pOne;
        }

        /**
         * Thread entry point
         */
        public void run () {
            try {
                String line = "";
                do {
                    try {
                        line = sIn.readLine();
                        if (line == null) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println((playerOne ? "p1" : "p2") + " disconnected...");
                        sIn.close();
                        sOut.close();
                        if (playerOne) {
                            p1 = null;
                        } else {
                            p2 = null;
                        }
                    }
                    // System.out.println((playerOne ? "p1" : "p2" ) + ">> " + line);
                    sOut.println("R");
                    if (playerOne) {
                            sOut.println("P Waiting for player 2...");
                    } else {
                        Game theGame = new Game();
                        try {
                            theGame.start(p1.sIn, p1.sOut, sIn, sOut);
                        } catch (IOException e) {
                            sIn.close();
                            sOut.close();
                            p1.sIn.close();
                            p1.sOut.close();
                            p1 = null;
                            p2 = null;
                        }
                        break;
                    }
                } while (line == "R");
            } catch (IOException e) {
                System.err.println("Error closing connection");
                System.err.println(e.getStackTrace());
            }
        }
    }

    /**
     * Constructs a TTTServer object
     * @constructor
     */
    public TTTServer () {
        try {
            sSock = new ServerSocket(8080);
            System.out.println("Server started");
        } catch (IOException e) {
            System.err.println("Could not start server on localhost:8080");
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * Program entry point
     * @param argv the command line arguments
     */
    public static void main (String[] argv) {
        TTTServer server = new TTTServer();
        server.listen();
    }

    /**
     * Listen for new connections to the server
     */
    public void listen () {
        System.out.println("Listening on localhost:8080");
        while (true) {
            try {
                Socket s = sSock.accept();
                if (p1 == null) {
                    p1 = new ServerConnection(s, true);
                    System.out.println("Player 1 connected");
                    Thread t = new Thread(p1);
                    t.start();
                } else if (p2 == null) {
                    p2 = new ServerConnection(s, false);
                    System.out.println("Player 2 connected");
                    Thread t = new Thread(p2);
                    t.start();
                } else {
                    try {
                        PrintWriter reject = new PrintWriter(s.getOutputStream(), true);
                        reject.println("Sorry, this server is full.");
                        s.close();
                        reject.close();
                        System.out.println("Rejected a player");
                    } catch (IOException e) {
                        System.err.println("Error rejecting connection");
                        System.err.println(e.getStackTrace());
                    }
                }
            } catch (IOException e) {
                System.err.println("Error establishing new client");
                System.err.println(e.getStackTrace());
            }
        }
    }

}
