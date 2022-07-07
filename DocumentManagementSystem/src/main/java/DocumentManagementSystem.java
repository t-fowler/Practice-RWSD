package src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

/**
 * This document management system is found in _Real World Software Development_ and
 * was intended to induce learning concepts such as importing multiple file types,
 * the Liskov Substitution Principle, and object oriented program design (via inheritance).
 */
public class DocumentManagementSystem {
    private final List<Document> documents = new ArrayList<>();
    private final Map<String, Importer> extensionToImporter = new HashMap<>();  // Links file extention to the associated importer.

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
        extensionToImporter.put("prescription", new PrescriptionImporter());
        extensionToImporter.put("invoice", new InvoiceImporter());
    }

    /**
     * Attempts to open the file at the given path, throwing an IOException when this is not possible.
     * After opening, importFile calls the proper importer function and adds the document to the
     * management system.
     * 
     * @param path The path to the file.
     * @throws FileNotFoundException The file given by the path does not exist.
     * @throws UnknownFileTypeException The file given by path either is not a supported file type, or the path is incorrect.
     */
    public void importFile(final String path) throws IOException {
        final File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

        final int separatorIndex = path.lastIndexOf('.');
        if (separatorIndex != -1) {
            if (separatorIndex == path.length()) {
                throw new UnknownFileTypeException("No extension found For file: " + path);
            }
            final String extension = path.substring(separatorIndex + 1);
            final Importer importer = extensionToImporter.get(extension);
            if (importer == null) {
                throw new UnknownFileTypeException("For file: " + path);
            }

            final Document document = importer.importFile(file);
            documents.add(document);
        } else {
            throw new UnknownFileTypeException("No extension found For file: " + path);
        }
    }

    /**
     * Returns a list of all the documents in the management system.
     * 
     * @return The list of documents.
     */
    public List<Document> contents() {
        return documents;
    }

    /**
     * Finds all the documents that match the provided query string. Queries are formatted such that
     * constraints are separated by commas and consist of pairs of attributes and values separated by
     * colons. So, "patient:Joe Bloggs,body:Diet coke" would search for documents concerning Joe Bloggs
     * with "Diet coke" in the contents.
     * 
     * @param query The query string should be formatted by the rules given above.
     * @return A list of documents that satisfy the constraints of the query.
     */
    public List<Document> search(final String query) {
        return documents.stream()
                        .filter(Query.parse(query))
                        .collect(Collectors.toList());
    }
}
