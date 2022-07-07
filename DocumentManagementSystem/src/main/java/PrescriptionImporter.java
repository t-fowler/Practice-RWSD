package src.main.java;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static src.main.java.Attributes.*;

/**
 * An importer for prescription record files to the document management system.
 */
public class PrescriptionImporter implements Importer
{
    /**
     * Creates a document with PATIENT, DRUG, DATE, AMOUNT, CONDITION, 
     * and TYPE attributes from the given jpg file.
     * 
     * @param file The prescription file to be imported.
     * 
     * @throws IOException An text file read error.
     * 
     * @return A document created from the file.
     */
    @Override
    public Document importFile(final File file) throws IOException
    {
        final TextFile textFile = new TextFile(file);
        
        textFile.addLineSuffix(PATIENT + ": ", PATIENT);
        textFile.addLineSuffix(DRUG + ": ", DRUG);
        textFile.addLineSuffix(DATE + ": ", DATE);
        textFile.addLineSuffix(AMOUNT + ": ", AMOUNT);
        textFile.addLineSuffix(CONDITION + ": ", CONDITION);
        
        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "PRESCRIPTION");
        return new Document(attributes);
    }
}
