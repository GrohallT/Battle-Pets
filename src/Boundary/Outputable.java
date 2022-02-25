package Boundary;

/**
 * Base interface for all classes that display output.
 */
public interface Outputable extends AutoCloseable {
    /**
     * Displays some output to a stream.
     * @param message The text to display.
     */
    void writeOutput(String message);
}
