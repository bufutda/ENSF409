import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Acts an an isolated client designed to interact with the TTTServer
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Mar 2016
 */
public class TTTClient {
    /**
     * The outgoing stream
     */
    private PrintWriter sOut;

    /**
     * The client socket
     */
    private Socket sock;

    /**
     * The incoming stream
     */
    private BufferedReader sIn;

    /**
     * A BufferedReader of stdin
     */
    private BufferedReader stdIn;

    /**
     * Constructs a TTTClient opject
     * @constructor
     */
    public TTTClient () {
        try {
            sock = new Socket("localhost", 8080);
            sIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            sOut = new PrintWriter(sock.getOutputStream(), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            sOut.println("R");
        } catch (IOException e) {
            System.err.println("Unable to connect to localhost:8080");
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * Logic for parsing server responses and instructions
     */
    public void parseServer () {
        String line = "";
        try {
            do {
                line = sIn.readLine();
                // System.out.println("server>> " + line);
                if (line != null) {
                    switch (line.substring(0, 1)) {
                        case "R": // READY
                            System.out.println("Connected to the server...");
                            break;
                        case "I": // INPUT
                            System.out.println(line.substring(2, line.length()));
                            sOut.println(stdIn.readLine());
                            break;
                        case "P": // PRINT
                            System.out.println(line.substring(2, line.length()));
                            break;
                        case "S": // Server full
                            System.out.println(line);
                            line = "QUIT";
                            break;
                        case "Q":
                            line = "QUIT";
                            break;
                    }
                } else {
                    System.out.println("The server disconnected you.");
                    line = "QUIT";
                }
            } while (line != "QUIT");
            sIn.close();
            sOut.close();
            stdIn.close();
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * Program entry point
     * @param argv the command line arguments
     */
    public static void main (String[] argv) {
        TTTClient cli = new TTTClient();
        cli.parseServer();
    }
}
