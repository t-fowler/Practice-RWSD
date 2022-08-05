# Practice-RWSD
This repository contains code that I wrote to practice the concepts from <u> Real World Software Development </u> (Urma &amp; Warburton). Chunks of code are used from the book as permitted, but I have endeavoured to re-write portions and implement new functionality suggested by the book for learning purposes.

## Concepts Learned:
The concepts that <u> Real World Software Development </u> focuses on include:

* ### Java features
    * Packages
    * Exceptions
    * Lambda Expressions
    * Method references

* ### Software architecture and design
    * Coupling / Cohesion
    * Fluent API design
    * Test-driven development
    * Mocking
    * Event-driven architectures
    * Hexagonal architectures
    * Notification pattern
    * Builder pattern
    * Dependency injection
    * Streams


* ### SOLID design principles
    * #### Single responsibility principle
        Units of code should be focused on a single task.
    
        * Ensures that there is only ever one reason for code to be modified.
        * Discourages "god class" design where 
        * Promotes high cohesion and loose coupling by encouraging code to be organized by functionality.
        * High cohesion and low coupling makes reduces code duplication, increasing robustness, and making code more readable and maintainable.

    * #### Open / Closed principle
        Methods and classes should be open to extension, but closed to modification. 
        
        * Allows functionality to be extended without requiring changes to client code.
        * In Java, behaviour is abstracted out into interfaces so that new behaviour can be extended on by providing a new implementation to the interface.
            

    * #### Liskov substitution principle
        Subtypes should be interchangeable with their parent types.

        * The subtype can only assume as much as the parent type in its precondition.
        * The subtype must provide as much functionality as its parent type in its postcondition.
        * Invariants of the parent type must be maintained by the subtype.
        * External state must be modified by the subtype in the same way as the parent type.
        * The subtype's methods and the parent type's methods must have contravariant parameter types.
        * The subtype's methods and the parent type's methods must have covariant return types.

    * #### Interface segregation principle
        Interfaces should provide the minimum set of functions required to be useful.

        * Smaller interfaces means less coupling and higher cohesion because the separated interfaces can evolve separetly.
        * Larger interfaces provide more than users need, leading to unimplemented functions that can cause errors.
        * Increases readability due to potentialy introducing more domain terminology.

    * #### Dependency inversion principle
        High and low level modules should not depend on each other. Instead, both should depend upon abstractions.

        * 

* ### Testing