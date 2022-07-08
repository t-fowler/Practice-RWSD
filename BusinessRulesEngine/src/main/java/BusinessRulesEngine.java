package src.main.java;

import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class BusinessRulesEngine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BusinessRulesEngine
{
    private List<Rule> rules;
    private Facts facts;
    /**
     * Constructor for objects of class BusinessRulesEngine
     */
    public BusinessRulesEngine(final Facts facts)
    {
        this.rules = new ArrayList();
        this.facts = facts;
    }

    /**
     * 
     */
    public void addRule(final Rule rule)
    {
        this.rules.add(rule);
    }
    
    /**
     * 
     */
    public int count()
    {
        return this.rules.size();
    }
    
    /**
     * 
     */
    public void run()
    {
        this.rules.forEach(rule -> rule.perform(facts));
    }
    
    public Facts getFacts()
    {
        return new Facts(this.facts);
    }
}
