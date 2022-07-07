package src.main.java;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static src.main.java.Attributes.BODY;
import static src.main.java.Attributes.PATIENT;
import static src.main.java.Attributes.TYPE;

/**
 * An importer for report files to the document management system.
 */
class ReportImporter implements Importer {
    private static final String NAME_PREFIX = "Patient: ";

    /**
     * Creates a document with PATIENT, BODY and TYPE attributes 
     * from the given invoice file.
     * 
     * @param file The report file to be imported.
     * @throws IOException A text file read error.
     * @return A document created from the file.
     */
    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLines(2, line -> false, BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "REPORT");
        return new Document(attributes);
    }
}
