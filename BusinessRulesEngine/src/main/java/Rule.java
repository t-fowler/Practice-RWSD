package src.main.java;

/**
 * An interface for Rules. Rules are performed on a set of facts.
 */
@FunctionalInterface
public interface Rule
{
    /**
     * Performs the rule for the provided set of facts.
     * 
     * @param facts The facts on which to perform the rule.
     */
    void perform(Facts facts);
}
