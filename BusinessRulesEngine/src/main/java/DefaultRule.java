package src.main.java;

import static src.main.java.BusinessRulesEngine.MIN_PRIORITY_LEVEL;
/**
 * A simple rule which executes an action if a condition holds over a given set of facts.
 */
public class DefaultRule implements Rule
{
    private final Condition condition;
    private final Action action;
    
    /**
     * Constructor for a DefaultRule that will execute the given action if
     * the given condition holds over a set of facts.
     * 
     * @param condition The condition.
     * @param action The action.
     */
    public DefaultRule(final Condition condition, final Action action) {
        this.condition = condition;
        this.action = action;
    }
    
    /**
     * Performs the rule over the set of facts by executing action if the condition is held.
     * 
     * @param facts The facts.
     */
    public void perform(Facts facts) 
    {
        if (condition.evaluate(facts))
            action.execute(facts);
    }
    
    /**
     * Returns the Rule's priority value.
     * 
     * @return The priority level of the Rule.
     */
    public int priorityLevel()
    {
        return MIN_PRIORITY_LEVEL;
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
}
