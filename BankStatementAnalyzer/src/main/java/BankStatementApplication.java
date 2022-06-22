package src.main.java;

import java.io.FileWriter;
import java.io.IOException;

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
        
        final SummaryStatistics results = bankStatementAnalyzer.analyze(args[0], bankStatementParser);
        
        System.out.println("The sum is: " + results.getSum());
        System.out.println("The max is: " + results.getMax());
        System.out.println("The min is: " + results.getMin());
        System.out.println("The avg is: " + results.getAverage());
        
        final BankStatementExporter htmlExporter  = new HtmlExporter();
        System.out.println("The html is: " + htmlExporter.export(results));
        
        try {
            final FileWriter htmlOutput = new FileWriter("BankStatementReport.html");
            htmlOutput.write(htmlExporter.export(results));
            htmlOutput.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
