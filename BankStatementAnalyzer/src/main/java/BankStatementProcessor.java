import java.util.List;
import java.time.Month;
import java.util.ArrayList;

/**
 * Write a description of class BankStatementProcessor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BankStatementProcessor
{
    private final List<BankTransaction> bankTransactions;
    public BankStatementProcessor(List<BankTransaction> bankTransactions)
    {
        this.bankTransactions = bankTransactions;
    }
    
    public double calculateTotalProfit()
    {
        double profit = 0d;
        
        for (BankTransaction transaction : bankTransactions) {
            profit += transaction.getAmount();
        }
        
        return profit;
    }
    
    public double calculateTotalInMonth(final Month month)
    {
        double total = 0d;
        
        for (BankTransaction transaction : bankTransactions) {
            if (transaction.getDate().getMonth() == month) {
                total += transaction.getAmount();
            }
        }
        
        return total;
    }
    
    public double calculateTotalForCategory(final String category)
    {
        double total = 0d;
        
        for (BankTransaction transaction : bankTransactions) {
            if (transaction.getPartner() == category) {
                total += transaction.getAmount();
            }
        }
        
        return total;
    }
    
    public int countTransactionsInMonth(final Month month)
    {
        int n = 0;
        
        for (BankTransaction transaction : bankTransactions) {
            if (transaction.getDate().getMonth() == month) {
                n++;
            }
        }
        
        return n;
    }
    
    public List<BankTransaction> topTenExpenditures()
    {
        System.out.println("topTenExpenditures not yet implemented.");
        return new ArrayList<>();
    }
    
    public BankTransaction highestExpenseCategory()
    {
        System.out.println("highestExpenseCategory not yet implemented.");
        return bankTransactions.get(0);
    }
}
