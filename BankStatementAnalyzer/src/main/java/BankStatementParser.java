package src.main.java;

import java.util.List;
/**
 * Write a description of interface BankStatementParser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface BankStatementParser
{
    public BankTransaction parseFrom(String line);
    public List<BankTransaction> parseLinesFrom(List<String> lines);
}
