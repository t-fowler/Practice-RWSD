package src.main.java;

import java.util.List;
import java.util.ArrayList;

import static src.main.java.BusinessRulesEngine.MIN_PRIORITY_LEVEL;
/**
 * A more general rule which executes an action if a condition holds over a given set of facts and with a
 * set priority.
 */
public class EnhancedRule implements Rule
{
    private final List<Condition> conditions;
    private final Action action;
    private final int priority;
    private final String name;
    private final String description;
    
    /**
     * Constructor for an EnhancedRule that will execute the given action if
     * at least one given condition holds over a set of facts.
     * 
     * @param name The name of the rule.
     * @param description A description of the rule.
     * @param condition The condition.
     * @param action The action.
     * @param priority The priority level to perform the rule.
     */
    public EnhancedRule(final String name, final String description, final List<Condition> conditions, final Action action, final int priority) {
        this.name = name;
        this.description = description;
        this.conditions = new ArrayList<>(conditions);
        this.action = action;
        if (priority < 0 || priority > 10) {
            this.priority = 10;
        }
        else {
            this.priority = priority;
        }
    }
    
    /**
     * Performs the rule over the set of facts by executing action if at least one condition is held.
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
     * Compares priority levels with another rule.
     * 
     * @param The Rule to compare against.
     * @return Negative if this rule is higher priority, 0 if they are the same priority and positive if the other rule is higher priority.
     */
    public int compareTo(Rule otherRule)
    {
        return this.priorityLevel() - otherRule.priorityLevel();
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
    
    /**
     * Returns the Rule's priority value.
     * 
     * @return The priority level of the Rule.
     */
    public int priorityLevel()
    {
        return this.priority;
    }
}
