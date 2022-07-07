package src.main.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static src.main.java.Attributes.PATH;
import static java.util.stream.Collectors.toList;

/**
 * A domain class for manipulating the contents of text files. Allows for different types
 * of text files to be used and groups all the relevant behaviours in this class. It is
 * not a subclass of Document because documents are not restricted to text files only; this
 * would violate the Liskov Substitution Principle.
 */
class TextFile {
    private final Map<String, String> attributes;
    private final List<String> lines;

    /**
     * Constructor for a text file.
     * 
     * @param file The text file to import
     */
    TextFile(final File file) throws IOException {
        attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());
        lines = Files.lines(file.toPath()).collect(toList());
    }

    /**
     * Returns the attributes associated with this text file.
     * 
     * @return The attribute to value mapping for the file.
     */
    Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     * Parses the file line by line starting from a given line number. Combines the lines
     * and adds them as an attribute. The user can define an end line so that cuts off the
     * parsing.
     * 
     * @param start The line number to start on.
     * @param isEnd A predicate where isEnd.test(line) determines whether line is the end line.
     * @param attributeName The name of the attribute for which the lines will be a value.
     * @return The line number of the final line added to the attribute.
     */
    int addLines(
        final int start,
        final Predicate<String> isEnd,
        final String attributeName) {

        final StringBuilder accumulator = new StringBuilder();
        int lineNumber;
        for (lineNumber = start; lineNumber < lines.size(); lineNumber++) {
            final String line = lines.get(lineNumber);
            if (isEnd.test(line)) {
                break;
            }

            accumulator.append(line);
            accumulator.append("\n");
        }
        attributes.put(attributeName, accumulator.toString().trim());
        return lineNumber;
    }

    /**
     * Adds what comes after the given prefix--the first time it appears in a text file--as a value
     * to an attribute with the given attribute name.
     * 
     * @param prefix The start of the line for which the suffix will be added as an attribute value.
     * @param attributeName The name of the attribute to add the suffix.
     */
    void addLineSuffix(final String prefix, final String attributeName) {
        for(final String line: lines) {
            if (line.startsWith(prefix)) {
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }
}
