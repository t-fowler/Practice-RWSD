package src.main.java;

/**
 * An interface for evaluating whether a condition is held over a set of facts.
 * Kept separate from the Action interface in adherance of the Interface Segregation
 * Principal. Doing so improves cohesion and reduces couple because conditions can be
 * created without needing to implement and action and vice-versa.
 */
@FunctionalInterface
public interface Condition
{
    /**
     * Returns true/false for whether the condition holds agains the set of facts.
     * 
     * @param facts The facts on which to test the condition.
     * @return True or false.
     */
    boolean evaluate(Facts facts);
}
