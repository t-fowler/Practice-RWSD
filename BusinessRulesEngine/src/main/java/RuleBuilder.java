package src.main.java;

/**
 * Write a description of class RuleBuilder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RuleBuilder
{
    private final Condition condition;
    
    private RuleBuilder(final Condition condition)
    {
        this.condition = condition;
    }
    
    public static RuleBuilder when(final Condition condition)
    {
        return new RuleBuilder(condition);
    }
    
    public Rule then(final Action action)
    {
        return new DefaultRule(condition, action);
    }
}
