package src.main.java;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
 * The BusinessRulesEngine is a system for co-ordinating business policy with execution from
 * software team. Users can add rules to the system which execute as actions under qualifying
 * conditions are met.
 */
public class BusinessRulesEngine
{
    public static int MIN_PRIORITY_LEVEL = 10;
    private List<Rule> rules;
    private Facts facts;
    /**
     * Constructor for the BusinessRulesEngine class. A base set of facts must be provided.
     * 
     * @param facts the base set of facts for the rules engine.
     */
    public BusinessRulesEngine(final Facts facts)
    {
        this.rules = new ArrayList();
        this.facts = facts;
    }

    /**
     * Adds a new rule to the system
     * 
     * @param rule The new rule.
     */
    public void addRule(final Rule rule)
    {
        this.rules.add(rule);
        Collections.sort(this.rules);
    }
    
    /**
     * Returns the number of rules that are currently being managed.
     * 
     * @return The number of rules.
     */
    public int count()
    {
        return this.rules.size();
    }
    
    /**
     * Performs each rule (action if condition is met) based on the given engines environment
     * of Facts.
     */
    public void run()
    {
        this.rules.forEach(rule -> rule.perform(facts));
    }
    
    /**
     * Returns a copy of the fact environment currently contained in the engine.
     * 
     * @return A copy of the engine'sfact environment.
     */
    public Facts getFacts()
    {
        return new Facts(this.facts);
    }
}
