package src.main.java;

import java.util.List;
import java.util.ArrayList;
/**
 * A more general rule rule which executes an action if a condition holds over a given set of facts.
 */
public class EnhancedRule implements Rule
{
    private final List<Condition> conditions;
    private final Action action;
    private final String name;
    private final String description;
    
    /**
     * Constructor for a DefaultRule that will execute the given action if
     * the given condition holds over a set of facts.
     * 
     * @param condition The condition.
     * @param action The action.
     */
    public EnhancedRule(final String name, final String description, final List<Condition> conditions, final Action action) {
        this.name = name;
        this.description = description;
        this.conditions = new ArrayList<>(conditions);
        this.action = action;
    }
    
    /**
     * Performs the rule over the set of facts by executing action if the condition is held.
     * 
     * @param facts The facts.
     */
    public void perform(Facts facts) 
    {
        for (Condition condition :  conditions) {
            if (condition.evaluate(facts)) {
                action.execute(facts);
                break;
            }
        }
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
