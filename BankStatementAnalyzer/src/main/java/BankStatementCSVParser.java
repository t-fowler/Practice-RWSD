package src.main.java;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Implements the BankStatementParser for CSV formatted bank statements. The CSV files are one line per transaction,
 * and the parts of each transaction are delimited by the ',' character.
 *
 * @author Tyler Fowler
 */
public class BankStatementCSVParser implements BankStatementParser
{
    private static final DateTimeFormatter DATE_PATTERN 
        = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public BankTransaction parseFrom(final String line)
    {
        final String[] columns = line.split(",");
        
        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String partner = columns[2];
        
        return new BankTransaction(date, amount, partner);
    }
    
    @Override
    public List<BankTransaction> parseLinesFrom(final List<String> lines)
    {
        List<BankTransaction> transactions = new ArrayList<>();
        for (String line: lines) {
            transactions.add(parseFrom(line));
        }
        
        return transactions;
    }
}
