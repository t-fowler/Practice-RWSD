package src.main.java;

/**
 * A builder pattern for Rules provided to encourage easy definition of rules with domain
 * ubiquitous terminology.
 * <br> Example usage: <br>
 * <pre>{@code
 * public Rule rule = RuleBuilder
 *     .when(facts -> facts.getFacts("STATE") == "ERROR")
 *     .then(facts -> Logger.log("ERROR"));
 * }</pre>
 */
public class RuleBuilder
{
    private final Condition condition;
    
    /**
     * Constructor for a RuleBuilder that can create rules from a set condition. This
     * constructor is private in order to enforce a Fluent API.
     * 
     * @param condition The condition that must evaluate positive in order to trigger an action.
     */
    private RuleBuilder(final Condition condition)
    {
        this.condition = condition;
    }
    
    /**
     * Returns a RuleBuilder that will create rules that trigger a provided action
     * when the given condition is set.
     * 
     * @param condition The condition that must evaluate positive in order to trigger an action.
     */
    public static RuleBuilder when(final Condition condition)
    {
        return new RuleBuilder(condition);
    }
    
    /**
     * Returns a DefaultRule that executes the given action when the provided condition
     * is met on a set of Facts.
     * 
     * @param action The action to execute.
     * @return The Rule to be added to the rules engine.
     */
    public Rule then(final Action action)
    {
        return new DefaultRule(condition, action);
    }
}
