package src.main.java;

/**
 * An interface for the different types of actions that the rules engine can execute.
 * Kept separate from the Condition interface in adherance of the Interface Segregation
 * Principal. Doing so improves cohesion and reduces couple because conditions can be
 * created without needing to implement and action and vice-versa.
 */
@FunctionalInterface
public interface Action{
    /**
     * TODO: Document
     */
    void execute(Facts facts);
}
