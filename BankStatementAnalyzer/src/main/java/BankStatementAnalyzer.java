package src.main.java;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
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
    
    private void collectSummary(BankStatementProcessor processor)
    {
        System.out.println("The total for all transactions is "
            + processor.calculateTotalProfit());
        
        System.out.println("The total for transactions in January is "
            + processor.calculateTotalInMonth(Month.JANUARY));
        
        System.out.println("The total salary received is "
            + processor.calculateTotalForCategory("Salary"));
        
        System.out.println("The number of transactions in January is "
            + processor.countTransactionsInMonth(Month.JANUARY));
        
        List<BankTransaction> top10Expenditures = processor.topTenExpenditures();
        String highestExpenseCategory = processor.highestExpenseCategory();
            
        if (top10Expenditures.isEmpty()) {
            System.out.println("There are no expenditures on this statement.");
        } else {
            System.out.println("The top ten expenditures are "
                + processor.topTenExpenditures());
        }
        
        if (highestExpenseCategory == null) {
            System.out.println("There are no expenditures on this statement.");
        } else {
            System.out.println("The highest expense category is "
                + processor.highestExpenseCategory());
        }
        
        
        
    }
    
    /**
     * Uses the parser parameter with the data from the given file, passing the list of transactions to the BankStatementProcessor.
     * Then collects the results from the queries performed by the processor and prints them to stdout.
     */
    public void analyze(final String filename, final BankStatementParser parser) throws java.io.IOException
    {   
        collectSummary(new BankStatementProcessor(
                        parser.parseLinesFrom(
                            Files.readAllLines(
                                Paths.get(RESOURCES + filename)
                        ))));
    }
}
