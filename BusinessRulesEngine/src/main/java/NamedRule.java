package src.main.java;


/**
 * Write a description of class NamedRule here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NamedRule extends DefaultRule
{
    private final String name;
    private final String description;

    /**
     * Constructor for objects of class NamedRule
     */
    public NamedRule(String name, String description, Condition condition, Action action)
    {
        super(condition, action);
        this.name = name;
        this.description = description;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getDescription()
    {
        return this.description;
    }
}
