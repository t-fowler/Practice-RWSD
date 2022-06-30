package src.main.java;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
/**
 * Reads the given file, the format of which must be consistent with what is expected by the given parser.
 * Summarizes the results of the queries made by the BankStatementProcessor.
 *
 * @author Tyler Fowler
 */
public class BankStatementAnalyzer
{
    private static final String RESOURCES = "src/main/resources/";
    
    /**
     * Uses the parser parameter with the data from the given file, passing the list of transactions to the BankStatementProcessor.
     * Then collects the results from the queries performed by the processor and prints them to stdout.
     */
    public SummaryStatistics analyze(final String filename, final BankStatementParser parser) throws java.io.IOException
    {   
        BankStatementProcessor statement 
            = new BankStatementProcessor(
            parser.parseLinesFrom(
            Files.readAllLines(
            Paths.get(RESOURCES + filename))));
        
        double sum = statement.calculateTotalProfit();
        double max = statement.maxTransaction().getAmount();
        double min = statement.minTransaction().getAmount();
        double avg = sum / statement.numberOfTransactions();
        
        return  new SummaryStatistics(sum, max, min, avg);
    }
}
