package src.test.java;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import src.main.java.*;
import static src.main.java.Attributes.*;

/**
 * Test class for the document management system.
 */
public class DocumentManagementSystemTest
{
    private static final String RESOURCES =
        "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String PRESCRIPTION = RESOURCES + "patient.prescription";
    private static final String JOE_BLOGGS = "Joe Bloggs";


    private DocumentManagementSystem system = new DocumentManagementSystem();

    /**
     * Test for importing a file.
     */
    @Test
    public void shouldImportFile() throws Exception
    {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, LETTER);
    }

    /**
     * Test for properly importing a letter file.
     */
    @Test
    public void shouldImportLetterAttributes() throws Exception
    {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, ADDRESS,
            "123 Fake Street\n" +
                "Westminster\n" +
                "London\n" +
                "United Kingdom");
        assertAttributeEquals(document, BODY,
            "We are writing to you to confirm the re-scheduling of your appointment\n" +
            "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertTypeIs("LETTER", document);
    }

    /**
     * Test for properly importing a report file.
     */
    @Test
    public void shouldImportReportAttributes() throws Exception
    {
        system.importFile(REPORT);

        assertIsReport(onlyDocument());
    }

    /**
     * test for properly importing an image file.
     */
    @Test
    public void shouldImportImageAttributes() throws Exception
    {
        system.importFile(XRAY);

        final Document document = onlyDocument();

        assertAttributeEquals(document, WIDTH, "320");
        assertAttributeEquals(document, HEIGHT, "179");
        assertTypeIs("IMAGE", document);
    }

    /**
     * Test for properly importing an invoice file.
     */
    @Test
    public void shouldImportInvoiceAttributes() throws Exception
    {
        system.importFile(INVOICE);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, AMOUNT, "$100");
        assertTypeIs("INVOICE", document);
    }
    
    /**
     * Test for properly importing a prescription file.
     */
    @Test
    public void shouldImportPrescriptionAttributes() throws Exception
    {
        system.importFile(PRESCRIPTION);
        
        final Document document = onlyDocument();
        
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, DATE, "April 14th, 2020");
        assertAttributeEquals(document, DRUG, "Pain Killer");
        assertAttributeEquals(document, AMOUNT, "5mgs");
        assertAttributeEquals(document, CONDITION, "No more than twice per day.");
        assertTypeIs("PRESCRIPTION", document);
    }

    /**
     * Test for querying the attributes of a file.
     */
    @Test
    public void shouldBeAbleToSearchFilesByAttributes() throws Exception
    {
        system.importFile(LETTER);
        system.importFile(REPORT);
        system.importFile(XRAY);

        final List<Document> documents = system.search("patient:Joe,body:Diet Coke");
        assertThat(documents, hasSize(1));

        assertIsReport(documents.get(0));
    }

    /**
     * Test that the system throws an error on a missing file.
     */
    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() throws Exception
    {
        system.importFile("gobbledygook.txt");
    }

    /**
     * Test that the system throws an error on an unsupported file type.
     */
    @Test(expected = UnknownFileTypeException.class)
    public void shouldNotImportUnknownFile() throws Exception
    {
        system.importFile(RESOURCES + "unknown.txt");
    }

    /**
     * Checks that reports are correctly identified.
     */
    private void assertIsReport(final Document document)
    {
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, BODY,
            "On 5th January 2017 I examined Joe's teeth.\n" +
                "We discussed his switch from drinking Coke to Diet Coke.\n" +
                "No new problems were noted with his teeth.");
        assertTypeIs("REPORT", document);
    }

    /**
     * Compares an attribute of a document against an expected value. Throws an error
     * if they do not have the same value.
     */
    private void assertAttributeEquals(
        final Document document,
        final String attributeName,
        final String expectedValue)
    {
        assertEquals(
            "Document has the wrong value for " + attributeName,
            expectedValue,
            document.getAttribute(attributeName));
    }

    /**
     * Checks whether the TYPE of documents are correct.
     */
    private void assertTypeIs(final String type, final Document document)
    {
        assertAttributeEquals(document, TYPE, type);
    }

    /**
     * Throws an error if the system contains for than one document. Otherwise, returns the
     * only document.
     * 
     * @return The only document in the system.
     */
    private Document onlyDocument()
    {
        final List<Document> documents = system.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }
}
