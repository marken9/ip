package painter.parser;

import painter.exception.PainterException;

public class Parser {
    public String getCommandWord(String input) throws PainterException {
        String trimmed = input.strip();
        if (trimmed.isEmpty()) {
            throw new PainterException("Empty Command");
        }
        return trimmed.split(" ")[0];
    }

    public String[] getArgs(String input) {
        return input.strip().split(" ");
    }


}
