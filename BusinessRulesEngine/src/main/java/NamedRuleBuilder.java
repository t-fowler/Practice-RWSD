package src.main.java;


/**
 * A builder pattern for NamedRules provided to encourage easy definition of rules with domain
 * ubiquitous terminology. A new class is defined so that the Fluent API can be extended.
 * <br> Example usage: <br>
 * <pre>{@code
 * public Rule rule = RuleBuilder
 *     .when(facts -> facts.getFacts("STATE") == "ERROR")
 *     .then(facts -> Logger.log("ERROR"))
 *     .named("NAME", "DESCRIPTION");
 * }</pre>
 */
public class NamedRuleBuilder
{
    private final Condition condition;
    private Action action;

    /**
     * Constructor for a NamedRuleBuilder that can create rules from a set condition. This
     * constructor is private in order to enforce a Fluent API.
     * 
     * @param condition The condition that must evaluate positive in order to trigger an action.
     */
    private NamedRuleBuilder(Condition condition)
    {
        this.condition = condition;
    }

    /**
     * Returns a RuleBuilder that will create rules that trigger a provided action
     * when the given condition is set.
     * 
     * @param condition The condition that must evaluate positive in order to trigger an action.
     */
    public static NamedRuleBuilder when(Condition condition) {
        return new NamedRuleBuilder(condition);
    }
    
    /**
     * Adds the action to the RuleBuilder configuration and returns /this/.
     * 
     * @param action The action that the Rule should execute on positive condition.
     */
    public NamedRuleBuilder then(Action action)
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
    public NamedRule named(String name, String description)
    {
        return new NamedRule(name, description, this.condition, this.action);
    }
    
    
}
