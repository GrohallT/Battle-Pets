package Boundary;

import java.io.PrintStream;

/**
 * Writes output to the Console.
 */
public class ConsoleWriter implements Outputable {

    private PrintStream outputStream;

    /**
     * Constructs a new ConsoleWriter.
     */
    public ConsoleWriter() {
        outputStream = System.out;
    }

    /**
     * Writes a new line of output the Console.
     * @param message The text to output.
     */
    @Override
    public void writeOutput(String message) {
        outputStream.println(message);
    }

    /**
     * Closes the stream.
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        outputStream.close();
    }
}
