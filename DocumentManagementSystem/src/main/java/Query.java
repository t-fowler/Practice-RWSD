package src.main.java;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toMap;

/**
 * Given a string formatted in attribute:value pairs, separated by commas, Queries determine
 * whether the attributes of a given Document contain the values specified in the string.
 * 
 * ex. "TYPE:prescription,DRUG:tylenol" will test for tylenol prescriptions.
 */
class Query implements Predicate<Document> {
    private final Map<String, String> clauses;

    /**
     * Parses a string and returns a Query with the correct attribute map to
     * compare with Documents.
     * 
     * @param query The query string formatted as described above.
     * @return A Query for testing documents against the given query string.
     */
    static Query parse(final String query) {
        return new Query(Arrays.stream(query.split(","))
              .map(str -> str.split(":"))
              .collect(toMap(x -> x[0], x -> x[1])));
    }

    /**
     * Constructor for Queries. Requires the attribute to value map that will be
     * checked against the Document. Use the parse() function to get a Query
     * that checks against a query strin.
     * 
     * @param clauses The attribute to value map to compare against Documents.
     */
    private Query(final Map<String, String> clauses) {
        this.clauses = clauses;
    }

    /**
     * Compares clauses against the Documents attribute map. If the attribtutes in the Document,
     * corresponding to the attributes in the Query, contain the string value from the query,
     * then the test is positive.
     * 
     * @param document The document to test agains the query.
     * @return True/false whether the document matches the query.
     */
    @Override
    public boolean test(final Document document) {
        return clauses.entrySet()
                      .stream()
                      .allMatch(entry -> {
                          final String documentValue = document.getAttribute(entry.getKey());
                          final String queryValue = entry.getValue();
                          return documentValue != null && documentValue.contains(queryValue);
                      });
    }
}
