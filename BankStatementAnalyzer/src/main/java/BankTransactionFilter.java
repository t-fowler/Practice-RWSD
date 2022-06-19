package src.main.java;


/**
 * An interface for classes that select transactions meeting a specific condition from the statement.
 *
 * @author Tyler Fowler
 */
@FunctionalInterface
public interface BankTransactionFilter
{
    boolean test(BankTransaction bankTransaction);
}
