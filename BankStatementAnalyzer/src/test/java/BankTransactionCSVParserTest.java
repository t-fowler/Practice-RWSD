package src.test.java;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import src.main.java.*;

public class BankTransactionCSVParserTest {

    private static double DELTA = 1e-7;
    private BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        String line = "30-01-2017,-50,Tesco";

        BankTransaction result = statementParser.parseFrom(line);

        BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseCorrectLines() throws Exception
    {
        List<String> lines = List.of(
            "30-01-2017,-50,Tesco",
            "30-12-2017,-100,Steam",
            "30-01-2027,1435.79,Salary",
            "10-01-2007,-500.03,Rent",
            "16-01-1917,-16.73,Burrito"
        );
        List<BankTransaction> result = statementParser.parseLinesFrom(lines);
        
        List<BankTransaction> expected = List.of(
            new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco"),
            new BankTransaction(LocalDate.of(2017, Month.DECEMBER, 30), -100, "Steam"),
            new BankTransaction(LocalDate.of(2027, Month.JANUARY, 30), 1435.79, "Salary"),
            new BankTransaction(LocalDate.of(2007, Month.JANUARY, 10), -500.03, "Rent"),
            new BankTransaction(LocalDate.of(1917, Month.JANUARY, 16), -16.73, "Burrito")
        );
        
        if (result.size() != expected.size()) {
            Assert.fail("CSV parser: result does not have expected size.");
        }
        
        for (int i = 0; i < result.size(); i++) {
            BankTransaction e = expected.get(i), r = result.get(i);
            Assert.assertEquals(e.getDate(), r.getDate());
            Assert.assertEquals(e.getAmount(), r.getAmount(), 0.0d);
            Assert.assertEquals(e.getPartner(), r.getPartner());
        }
    }

    @Test
    public void shouldParseNoLines() throws Exception
    {
        List<String> lines = List.of();
        
        List<BankTransaction> result = statementParser.parseLinesFrom(lines);
        
        if (result.size() != 0) {
            Assert.fail("CSV parser: result does not have expected size of 0.");
        }
    }
}