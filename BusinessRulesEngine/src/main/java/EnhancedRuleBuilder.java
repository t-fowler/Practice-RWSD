package src.main.java;

import java.util.List;
/**
 * Write a description of class EnhancedRuleBuilder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnhancedRuleBuilder
{
    private final List<Condition> conditions;
    private Action action;

    /**
     * Constructor for a NamedRuleBuilder that can create rules from a set condition. This
     * constructor is private in order to enforce a Fluent API.
     * 
     * @param condition The condition that must evaluate positive in order to trigger an action.
     */
    private EnhancedRuleBuilder(List<Condition> conditions)
    {
        this.conditions = conditions;
    }

    /**
     * Returns a RuleBuilder that will create rules that trigger a provided action
     * when the given condition is set.
     * 
     * @param condition The condition that must evaluate positive in order to trigger an action.
     */
    public static EnhancedRuleBuilder when(List<Condition> conditions) {
        return new EnhancedRuleBuilder(conditions);
    }
    
    /**
     * Adds the action to the RuleBuilder configuration and returns /this/.
     * 
     * @param action The action that the Rule should execute on positive condition.
     */
    public EnhancedRuleBuilder then(Action action)
    {
        this.action = action;
        return this;
    }
    
    /**
     * Returns a NamedRule that executes the given action when the provided condition
     * is met on a set of Facts.
     * 
     * @param name The name of the Rule.
     * @param description A description of the Rule.
     * @return The Rule to be added to the rules engine.
     */
    public EnhancedRule named(String name, String description)
    {
        return new EnhancedRule(name, description, this.conditions, this.action);
    }
}
