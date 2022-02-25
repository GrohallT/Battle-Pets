package Control;

/**
 * Utility class to make parsing and verifying input more consistent.
 */
public class InputUtils {
    /**
     * Determines if an input String is any of the given characters. The input
     * String is expected to be a character, otherwise the test will automatically
     * fail.
     * @param text The input String entered by a user.
     * @param chars All potential characters this String is expected to be.
     * @return True if any of the desired characters are matched, false otherwise.
     */
    public static boolean isCharacters(String text, char... chars) {
        // Fail if the text isn't a character
        if (text.length() != 1)
            return false;

        // Check if the text matches any of the given characters
        for (char c : chars) {
            if (text.toUpperCase().charAt(0) == c) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines if an input String is within a given range (inclusive). The
     * input String is expected to be parsed as a double, otherwise the test will
     * fail automatically.
     * @param text The input String entered by the user.
     * @param low The lower threshold of the range.
     * @param high The upper threshold of the range.
     * @return True if the text is within the range specified, false otherwise.
     */
    public static boolean isDoubleInRange(String text, double low, double high) {
        if (isDouble(text)) {
            double number = Double.parseDouble(text);
            return number >= low && number <= high;
        }

        return false;
    }

    /**
     * Determines whether a String is able to be parsed into a double.
     * Source code retrieved from: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
     * @param text The input String entered by the user.
     * @return A boolean indicating whether or not the text can be parsed to an int.
     */
    public static boolean isDouble(String text) {
        return text.length() <= 9 && text.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Determines if an input String is above a specified double. The input
     * String is expected to be parsed as a double, otherwise the test will
     * fail automatically.
     * @param text The input String entered by the user.
     * @param above The double to test if the text is above.
     * @return True if the text is above the threshold, false otherwise.
     */
    public static boolean isDoubleAndAbove(String text, double above) {
        if (isDouble(text))
            return Double.parseDouble(text) > above;
        return false;
    }

    /**
     * Determines if an input String is below a specified double. The input
     * String is expected to be parsed as an double, otherwise the test will
     * fail automatically.
     * @param text The input String entered by the user.
     * @param below The double to test if the text is below.
     * @return True if the text is below the threshold, false otherwise.
     */
    public static boolean isDoubleAndBelow(String text, double below) {
        if (isInteger(text))
            return Double.parseDouble(text) < below;
        return false;
    }

    /**
     * Determines if an input String is within a given range (inclusive). The
     * input String is expected to be parsed as an int, otherwise the test will
     * fail automatically.
     * @param text The input String entered by the user.
     * @param low The lower threshold of the range.
     * @param high The upper threshold of the range.
     * @return True if the text is within the range specified, false otherwise.
     */
    public static boolean isIntegerInRange(String text, int low, int high) {
        if (isInteger(text)) {
            int number = Integer.parseInt(text);
            return number >= low && number <= high;
        }

        return false;
    }

    /**
     * Determines whether a String is able to be parsed into an int or double.
     * Source code retrieved from: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
     * @param text The input String entered by the user.
     * @return A boolean indicating whether or not the text can be parsed to an int.
     */
    public static boolean isInteger(String text) {
        return text.length() <= 9 && text.matches("-?\\d+");
    }

    /**
     * Determines if an input String is above a specified int. The input
     * String is expected to be parsed as an int, otherwise the test will
     * fail automatically.
     * @param text The input String entered by the user.
     * @param above The int to test if the text is above.
     * @return True if the text is above the threshold, false otherwise.
     */
    public static boolean isIntegerAndAbove(String text, int above) {
        if (isInteger(text))
            return Integer.parseInt(text) > above;
        return false;
    }

    /**
     * Determines if an input String is below a specified int. The input
     * String is expected to be parsed as an int, otherwise the test will
     * fail automatically.
     * @param text The input String entered by the user.
     * @param below The int to test if the text is below.
     * @return True if the text is below the threshold, false otherwise.
     */
    public static boolean isIntegerAndBelow(String text, int below) {
        if (isInteger(text))
            return Integer.parseInt(text) < below;
        return false;
    }
}