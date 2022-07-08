package src.main.java;


/**
 * A simple rule which executes an action if a condition holds over a given set of facts.
 * Also supports a name and description of the Rule.
 */
public class NamedRule extends DefaultRule
{
    private final String name;
    private final String description;

    /**
     * Constructor for a DefaultRule that will execute the given action if
     * the given condition holds over a set of facts.
     * 
     * @param name The Rule's name.
     * @param descripton A description of the Rule.
     * @param condition The condition.
     * @param action The action.
     */
    public NamedRule(String name, String description, Condition condition, Action action)
    {
        super(condition, action);
        this.name = name;
        this.description = description;
    }
    
    /**
     * Returns the Rule's name.
     * 
     * @return The Rule's name.
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Returns the Rule's description.
     * 
     * @return A descripton of the Rule.
     */
    public String getDescription()
    {
        return this.description;
    }
}
