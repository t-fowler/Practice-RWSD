package src.main.java;

import java.util.List;
import java.util.logging.*;
/**
 * An interface providing the functionality to parse a bank statement.
 *
 * @author Tyler Fowler
 */
public interface BankStatementParser
{
    /**
     * Extracts the pieces of data from a single transaction and creates the BankTransaction.
     * 
     * @param line A single transaction in the format of the implementation.
     */
    public BankTransaction parseFrom(final String line);
    
    /**
     * Parses each transaction in turn, returning a list of BankTransactions.
     * 
     * @param lines A list of strings to be parsed according to the format of the implementation.
     */
    public List<BankTransaction> parseLinesFrom(final List<String> lines);
}
