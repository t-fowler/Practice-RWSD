package src.main.java;


/**
 * Write a description of class NamedRuleBuilder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NamedRuleBuilder
{
    private final Condition condition;
    private Action action;

    /**
     * Constructor for objects of class NamedRuleBuilder
     */
    private NamedRuleBuilder(Condition condition)
    {
        this.condition = condition;
    }

    public static NamedRuleBuilder when(Condition condition) {
        return new NamedRuleBuilder(condition);
    }
    
    public NamedRuleBuilder then(Action action)
    {
        this.action = action;
        return this;
    }
    
    public NamedRule named(String name, String description)
    {
        return new NamedRule(name, description, this.condition, this.action);
    }
    
    
}
