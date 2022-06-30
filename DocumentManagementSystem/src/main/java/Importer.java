package src.main.java;


import java.io.File;
import java.io.IOException;

// tag::importer[]
@FunctionalInterface
interface Importer {
    Document importFile(File file) throws IOException;
}
// end::importer[]
