package src.main.java;

import java.util.Map;

/**
 * A domain class for storing and managing documents of different types. Documents maintain
 * data in the form of attribute/value pairs. The use of a domain class is intended to 
 * generalize the types of documents that can be managed without resorting to a hierarchical
 * interhitance relationship.
 */
public class Document {
    private final Map<String, String> attributes; // Maps attributes to the documents value associated with the attribute.

    /**
     * Constructor for a Document object.
     * 
     * @param attributes The attribute to value mapping imported from the document file.
     */
    Document(final Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Returns the value associated to the given attribute for the document.
     * 
     * @param attributeName The attribute for which to return a value.
     * @return The value associated with the given attribute.
     */
    public String getAttribute(final String attributeName) {
        return attributes.get(attributeName);
    }
}
