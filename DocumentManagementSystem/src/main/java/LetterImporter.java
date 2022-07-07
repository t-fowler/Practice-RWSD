package src.main.java;


import java.io.File;
import java.io.IOException;
import java.util.Map;

import static src.main.java.Attributes.*;

/**
 * An importer for text letters to the document management system.
 */
class LetterImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";

    /**
     * Creates a document with PATIENT, ADDRESS, BODY, and TYPE attributes 
     * from the given invoice file.
     * 
     * @param file The letter to be imported.
     * 
     * @throws IOException A text file read error.
     * 
     * @return A document created from the file.
     */
    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);

        final int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards,"), BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "LETTER");
        return new Document(attributes);
    }
}
