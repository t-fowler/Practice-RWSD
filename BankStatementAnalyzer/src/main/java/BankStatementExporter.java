package src.main.java;


/**
 * Write a description of class Exporter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
@FunctionalInterface
public interface BankStatementExporter {
    String export(SummaryStatistics summaryStatistics);
}
