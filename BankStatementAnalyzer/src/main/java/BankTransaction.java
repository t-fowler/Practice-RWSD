package src.main.java;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Comparator;
/**
 * A record of the date, amount, and a description of  single transaction present in a bank statement.
 */
public class BankTransaction implements Comparable<BankTransaction>
{
    private final LocalDate transactionDate;
    private final double transactionAmount;
    private final String transactionDescription;
    
    /**
     * Constructs a Transaction from the given parts.
     * 
     * @param date The date of the transaction.
     * @param amount The total cost or income of a transaction.
     * @param Description A description of the transaction.
     */
    public BankTransaction(final LocalDate date, final double amount, final String description)
    {
        this.transactionDate = date;
        this.transactionAmount = amount;
        this.transactionDescription = description;
    }
    
    /**
     * @return The date of the transaction.
     */
    public LocalDate getDate()
    {
        return transactionDate;
    }
    
    /**
     * @return The cost/income of the transaction.
     */
    public double getAmount()
    {
        return transactionAmount;
    }
    
    /**
     * @return The description of the transaction.
     */
    public String getDescription()
    {
        return transactionDescription;
    }
    
    /**
     * @return A string representation of the transaction.
     */
    @Override
    public String toString() {
        return "BankTransaction{" +
                "date=" + transactionDate +
                ", amount=" + transactionAmount +
                ", description='" + transactionDescription + '\'' +
                '}';
    }

    /**
     * Compares all three parts of the transaction to determine if they are equal.
     * 
     * @param o The object to compare against.
     * 
     * @return True if the transactions are the same. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return Double.compare(that.transactionAmount, transactionAmount) == 0 &&
                transactionDate.equals(that.transactionDate) &&
                transactionDescription.equals(that.transactionDescription);
    }

    /**
     * A hash function for the transaction based on the date, amount and description.
     * 
     * @return The hash code for the transaction.
     */
    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, transactionAmount, transactionDescription);
    }
    
    /**
     * Orders transactions based on the amount of profit/cost.
     * 
     * @return Zero if the amount is the same. Less than zero if this amount is smaller than other amount. Greater than zero if this amount is greater than other amount.
     */
    public int compareTo(BankTransaction other)
    {
        return (int) (getAmount() - other.getAmount());
    }
}
