package src.main.java;

import java.io.File;
import java.io.IOException;

/**
 * An interface for file importing functions of different file types. Using this aligns with the
 * Open/Closed principle because new import functions can be added without modifying the code
 * using the functionality.
 */
@FunctionalInterface
interface Importer {
    /**
     * Creates a Document out of the given file by assigning all relevant
     * attributes.
     * 
     * @param file The file to be imported.
     * @throws IOException Indicates a failed import.
     */
    Document importFile(File file) throws IOException;
}
