
/**
 * This project is an implementation of the practice project from chapter 2 of
 * _Real World Software Development_ (Urma & Warburton). Some
 * code is copied, as permitted, from the book, but I have endeavoured
 * to re-write the majority of it for learning purposes as well as to implement
 * additional functionality suggested by the book for further practice.
 *
 * @author Tyler Fowler
 */
public class BankStatementApplication
{
    public static void main(final String... args) throws java.io.IOException
    {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        
        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
        // Read input.
        // Parse input.
        // Process input.
        // Summarize results.
    }
}
