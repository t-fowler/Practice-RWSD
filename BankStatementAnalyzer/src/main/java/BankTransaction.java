package src.main.java;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Comparator;
/**
 * Write a description of class BankTransaction here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BankTransaction implements Comparable<BankTransaction>
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
    
    public String getDescription()
    {
        return "Date: " + transactionDate + 
                "\nAmount: " + transactionAmount +
                "\nDescription: " + transactionPartner;
    }
    
    @Override
    public String toString() {
        return "BankTransaction{" +
                "date=" + transactionDate +
                ", amount=" + transactionAmount +
                ", description='" + transactionPartner + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return Double.compare(that.transactionAmount, transactionAmount) == 0 &&
                transactionDate.equals(that.transactionDate) &&
                transactionPartner.equals(that.transactionPartner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, transactionAmount, transactionPartner);
    }
    
    public int compareTo(BankTransaction other)
    {
        return (int) (getAmount() - other.getAmount());
    }
}
