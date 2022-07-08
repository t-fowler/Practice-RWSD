package src.main.java;

import java.util.Map;
import java.util.HashMap;
/**
 * A list of facts that can be stored and acted upon.
 */
public class Facts
{
    public final Map<String, String> facts;

    public Facts()
    {
        facts = new HashMap<>();
    }
    
    /**
     * Copy constructor for facts.
     * 
     * @param other The Facts object to copy.
     */
    public Facts(Facts other)
    {
        this();
        for (Map.Entry entry : other.facts.entrySet()) {
            this.facts.put(entry.getKey().toString(), entry.getValue().toString());
        }
    }
    
    /**
     * Returns the fact with the given name or null if there is no fact with that name.
     * 
     * @param The name of the fact to return.
     * @return The fact.
     */
    public String getFact(final String name)
    {
        return this.facts.get(name);
    }
    
    /**
     * Adds a new fact to the list with the given name and value.
     * 
     * @param name The name of the fact.
     * @param value The fact.
     */
    public void addFact(final String name, final String value)
    {
        this.facts.put(name, value);
    }
}
