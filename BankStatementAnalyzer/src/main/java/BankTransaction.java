package src.main.java;

import java.time.LocalDate;
/**
 * Write a description of class BankTransaction here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BankTransaction
{
    private final LocalDate transactionDate;
    private final double transactionAmount;
    private final String transactionPartner;
    
    public BankTransaction(final LocalDate date, final double amount, final String partner)
    {
        this.transactionDate = date;
        this.transactionAmount = amount;
        this.transactionPartner = partner;
    }
    
    public LocalDate getDate()
    {
        return transactionDate;
    }
    
    public double getAmount()
    {
        return transactionAmount;
    }
    
    public String getPartner()
    {
        return transactionPartner;
    }
}
