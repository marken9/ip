package painter.parser;

import painter.Painter;
import painter.exception.PainterException;

public class Parser {

    public void validateRawInput(String line) throws PainterException {
        if (line == null || line.isBlank()) {
            throw new PainterException("Empty command");
        }
        if (line.contains(";")) {
            throw new PainterException("Input not allowed to contain ';'");
        }
    }

    public String getCommandWord(String input) throws PainterException {
        validateRawInput(input);
        String trimmed = input.strip();
        return trimmed.split(" ")[0];
    }

    public String[] getArgs(String input) throws PainterException {
        validateRawInput(input);
        return input.strip().split(" ");
    }

}
