package Boundary;

/**
 * Base interface for all classes that parse input.
 */
public interface Inputable extends AutoCloseable {
    /**
     * Reads some input from a stream.
     * @return The String of text parsed from a stream.
     */
    String readInput();
}
