package src.main.java;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static src.main.java.Attributes.*;

/**
 * Write a description of class PrescriptionImporter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PrescriptionImporter implements Importer
{
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
