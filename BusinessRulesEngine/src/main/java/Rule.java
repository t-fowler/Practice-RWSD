package src.main.java;

/**
 * An interface for Rules. Rules are performed on a set of facts.
 */
public interface Rule extends Comparable<Rule>
{
    /**
     * Performs the rule for the provided set of facts.
     * 
     * @param facts The facts on which to perform the rule.
     */
    void perform(Facts facts);
    
    /**
     * Returns the priority level for the Rule.
     * 
     * @return The priority level of the Rule.
     */
    int priorityLevel();
    
    int compareTo(Rule otherRule);
}
