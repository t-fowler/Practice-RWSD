package src.main.java;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
/**
 * Write a description of class Validator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatementValidator
{
    // instance variables - replace the example below with your own
    private String description;
    private String amount;
    private String date;

    /**
     * Constructor for objects of class Validator
     */
    public StatementValidator(final String date, final String amount, final String description)
    {
        this.description = Objects.requireNonNull(description);
        this.amount = Objects.requireNonNull(amount);
        this.date = Objects.requireNonNull(date);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ParserNotification validate()
    {
        final ParserNotification notification = new ParserNotification();
        if (this.description.length() > 80) {
            notification.addError("The category description is too long");
        }
        
        final LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(this.date);
            if (parsedDate.isAfter(LocalDate.now())) {
                notification.addError("The date is in the future");
            }
        } catch (DateTimeParseException e)
        {
            notification.addError("The date format is incorrect");
        }
        
        double parsedAmount;
        try {
            parsedAmount = Double.parseDouble(this.amount);
        } catch (NumberFormatException e) {
            notification.addError("The amount format is incorrect");
        }
        
        
        
        return notification;
    }
}
