package src.main.java;

import java.util.List;
/**
 * A builder pattern for EnhancedRules provided to encourage easy definition of rules with domain
 * ubiquitous terminology.
 * <br> Example usage: <br>
 * <pre>{@code
 * public Rule rule = RuleBuilder
 *     .when(facts -> facts.getFacts("STATE") == "ERROR")
 *     .then(facts -> Logger.log("ERROR"))
 *     .named("NAME", "DESCRIPTION")
 *     .withPriority(0);
 * }</pre>
 */
public class EnhancedRuleBuilder
{
    private final List<Condition> conditions;
    private Action action;
    private String name;
    private String description;

    /**
     * Constructor for a EnhancedRuleBuilder that can create rules from a multiple. This
     * constructor is private in order to enforce a Fluent API.
     * 
     * @param conditions The list of conditions that will trigger an action.
     */
    private EnhancedRuleBuilder(List<Condition> conditions)
    {
        this.conditions = conditions;
    }

    /**
     * Returns an EnhancedRuleBuilder that will create rules that trigger a provided action
     * when at least one condition is set.
     * 
     * @param conditions The list of condition that will trigger an action.
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
     * Adds the name and description to the RuleBuilder configuration and returns /this/.
     * 
     * @param name The Rule's name.
     * @param description A description of the rule.
     */
    public EnhancedRuleBuilder named(String name, String description)
    {
        this.name = name;
        this.description = description;
        return this;
    }
    
    /**
     * Returns an EnhancedRule that executes the given action when the at least condition
     * is met on a set of Facts. Enhanced Rules are prioritized based on the given priority level.
     * 
     * @param priority The priority level of the Rule.
     * @return The Rule to be added to the rules engine.
     */
    public EnhancedRule withPriority(int priority) {
        return new EnhancedRule(name, description, this.conditions, this.action, priority);
    }
}
