package src.main.java;

import java.util.List;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

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
        List<BankTransaction> result = new ArrayList<>();
        final int size = bankTransactions.size();
        BankTransaction t;
        
        Collections.sort(bankTransactions);

        if (bankTransactions.size() < 10) {
            for (int i = 0; i < size; i++) {
                if ((t = bankTransactions.get(i)).getAmount() >= 0.0d) break;
                result.add(t);
            }
        } else {
            for (int i = 0; i <= 10; i++) {
                if ((t = bankTransactions.get(i)).getAmount() >= 0.0d) break;
                result.add(t);
            }
        }
        
        return result;
    }
    
    public String highestExpenseCategory()
    {
        Collections.sort(bankTransactions);
        
        if (bankTransactions.size() == 0 || bankTransactions.get(0).getAmount() >= 0.0d) {
            return null;
        }
        
        return bankTransactions.get(0).getPartner();
    }
}
