package painter.parser;

import painter.exception.PainterException;

/**
 * Responsible for parsing user input into command words
 * and arguments.
 */
public class Parser {
    /**
     * Check the raw input for unallowed elements.
     * ';' is not allowed because it is used as the separator when saving to file
     *
     * @param line One line of input from the user.
     * @throws PainterException If the input contains unallowed characters
     */
    public void validateRawInput(String line) throws PainterException {
        if (line == null || line.isBlank()) {
            throw new PainterException("Empty command");
        }
        if (line.contains(";")) {
            throw new PainterException("Input not allowed to contain ';'");
        }
    }

    /**
     * Extracts the command word from user input.
     *
     * @param input Raw user input.
     * @return The command keyword.
     * @throws PainterException If the input is invalid.
     */
    public String getCommandWord(String input) throws PainterException {
        validateRawInput(input);
        String trimmed = input.strip();
        return trimmed.split(" ")[0];
    }

    /**
     * Splits the user input into individual strings.
     *
     * @param input Raw user input.
     * @return Array of strings.
     */
    public String[] getArgs(String input) throws PainterException {
        validateRawInput(input);
        return input.strip().split(" ");
    }

}
