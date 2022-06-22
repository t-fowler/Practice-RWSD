package src.main.java;

import java.util.List;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Processes a list of bank transactions by providing a set of queries.
 *
 * @author Tyler Fowler
 */
public class BankStatementProcessor
{
    private final List<BankTransaction> bankTransactions;
    
    /**
     * Constructor from a list of BankTransactions to be processed.
     * 
     * @param bankTransactions The transactions to be processed.
     */
    public BankStatementProcessor(List<BankTransaction> bankTransactions)
    {
        this.bankTransactions = bankTransactions;
        Collections.sort(bankTransactions);
    }
    
    /**
     * Sums the total amount of income or expenses from the entire bank statement.
     * 
     * @return The profit/loss for the statement.
     */
    public double calculateTotalProfit()
    {
        double profit = 0d;
        
        for (BankTransaction transaction : bankTransactions) {
            profit += transaction.getAmount();
        }
        
        return profit;
    }
    
    /**
     * Sums the total amount from transactions that occur during the given month.
     * 
     * @param month The query month.
     * @return the profit/loss for the statement in that month.
     */
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
    
    /**
     * Sums the total amount from transactions with the same description.
     * 
     * @param category The description to be queried.
     * @return the profit/loss for statement of all transactions with the given description.
     */
    public double calculateTotalForCategory(final String category)
    {
        double total = 0d;
        
        for (BankTransaction transaction : bankTransactions) {
            if (transaction.getDescription() == category) {
                total += transaction.getAmount();
            }
        }
        
        return total;
    }
    
    /**
     * Counts how many transactions occured in the given month.
     * 
     * @param month The month to be queried.
     * @return The number of transactions that occured in month.
     */
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
    
    /**
     * Returns the number of transactions in the statement.
     * 
     * @return The number of transactions in the statement.
     */
    public int numberOfTransactions()
    {
        return bankTransactions.size();
    }
    
    /**
     * Returns the transaction with the largest amount (may still be negative).
     * 
     * @return The transaction with of the largest amount.
     */
    public BankTransaction maxTransaction()
    {
        if (bankTransactions.size() == 0) {
            return null;
        }
        
        return bankTransactions.get(bankTransactions.size() - 1);
    }
    
    /**
     * Returns the transaction with the smallest amount (may still be positive).
     * 
     * @return The transaction with the smallest amount.
     */
    public BankTransaction minTransaction()
    {
        if (bankTransactions.size() == 0) {
            return null;
        }
        
        return bankTransactions.get(0);
    }
    
    /**
     * Finds the top ten expenditures in the bank statement.
     * 
     * @return A list of the top 10 transactions by amount debited.
     */
    public List<BankTransaction> topTenExpenditures()
    {
        List<BankTransaction> result = new ArrayList<>();

        for (int i = 0; i < bankTransactions.size(); i++) {
            if (i == 10)
                break;
            if (bankTransactions.get(i).getAmount() >= 0.0d) break;
            result.add(bankTransactions.get(i));
        }
        
        return result;
    }
    
    /**
     * Finds the category with the highest expenditure. If there are none, returns null;
     * 
     * @return The category with the largest debit transaction. Null if there are no transactions of expenditures.
     */
    public String highestExpenseCategory()
    {
        if (bankTransactions.size() == 0 || bankTransactions.get(0).getAmount() >= 0.0d) {
            return null;
        }
        
        return bankTransactions.get(0).getDescription();
    }
    
    /**
     * Finds all the transactions that pass the test provided by filter.
     * 
     * @param filter The condition to test transactions against.
     * @return A list of all transactions that pass the filter.
     */
    public List<BankTransaction> findTransactions(final BankTransactionFilter filter)
    {
        List<BankTransaction> result = new ArrayList<>();
        
        for (BankTransaction transaction : bankTransactions) {
            if (filter.test(transaction)) {
                result.add(transaction);
            }
        }
        
        return result;
    }
}
