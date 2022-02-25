package Boundary;

/**
 * Singleton manager class for all I/O manipulation throughout the program.
 */
public class IOManager {
    private static IOManager instance;

    private Inputable inputStream;
    private Outputable outputStream;

    /**
     * Constructs a new IOManager.
     */
    private IOManager() {
        this.inputStream = new ConsoleReader();
        this.outputStream = new ConsoleWriter();
    }

    /**
     * Returns the single instance of the IOManager class. Implemented as the
     * lazy Singleton.
     * @return
     */
    public static IOManager getInstance() {
        if (instance == null)
            instance = new IOManager();

        return instance;
    }

    /**
     * Closes the Inputable stream.
     */
    public void closeInputStream() {
        try {
            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Closes the Outputable stream.
     */
    public void closeOutputStream() {
        try {
            outputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Inputable getInputStream() { return inputStream; }

    public Outputable getOutputStream() { return outputStream; }
}
