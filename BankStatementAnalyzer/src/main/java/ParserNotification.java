package src.main.java;

import java.util.List;
import java.util.ArrayList;


/**
 * Write a description of class ParserNotification here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ParserNotification extends Exception
{
    // instance variables - replace the example below with your own
    private final List<String> errors = new ArrayList<>();

    public void addError(String message)
    {
        errors.add(message);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean hasError()
    {
        return !errors.isEmpty();
    }
    
    public String errorMessage()
    {
        return errors.toString();
    }
    
    public List<String> getErrors(){
        return errors;
    }
}
