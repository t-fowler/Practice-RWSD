package src.main.java;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.Month;
/**
 * Receives a list of BankTransactions and provides computation results
 * for queries defined in the class.
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
        
        System.out.println("The top ten expenditures are "
            + processor.topTenExpenditures());
        
        System.out.println("The highest expense category is "
            + processor.highestExpenseCategory());
    }
    
    public void analyze(final String filename, final BankStatementParser parser) throws java.io.IOException
    {   
        collectSummary(new BankStatementProcessor(
                        parser.parseLinesFrom(
                            Files.readAllLines(
                                Paths.get(RESOURCES + filename)
                        ))));
    }
}
