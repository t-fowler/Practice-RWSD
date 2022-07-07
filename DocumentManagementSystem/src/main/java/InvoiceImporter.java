package src.main.java;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static src.main.java.Attributes.*;

/**
 * An importer for invoice record files to the document management system.
 */
class InvoiceImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";
    private static final String AMOUNT_PREFIX = "Amount: ";

    /**
     * Creates a document with PATIENT, AMOUNT, and TYPE attributes 
     * from the given invoice file.
     * 
     * @param file The invoice file to be imported.
     * @throws IOException A text file read error.
     * @return A document created from the file.
     */
    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOICE");
        return new Document(attributes);
    }
}
