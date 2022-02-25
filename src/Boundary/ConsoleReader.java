package Boundary;

import java.util.Scanner;

/**
 * Reads the next segment of input from the Console.
 */
public class ConsoleReader implements Inputable {
    private Scanner scanner;

    /**
     * Constructs a new ConsoleReader.
     */
    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next segment of input from the Console.
     * @return
     */
    @Override
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the stream.
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        scanner.close();
    }
}