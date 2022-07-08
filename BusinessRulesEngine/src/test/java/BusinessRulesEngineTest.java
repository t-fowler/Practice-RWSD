package src.test.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import src.main.java.*;

/**
 * A test class for the business rules engine. 
 */
public class BusinessRulesEngineTest
{
    /**
     * Tests that a rules engine is constructed with no Rules initially.
     */
    @Test
    public void shouldHaveNoRulesInitially() throws Exception
    {
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine(new Facts());
        
        assertEquals(0, rulesEngine.count());
    }
    
    /**
     * Tests that adding rules to the rules engine works.
     */
    @Test
    public void shouldAddTwoRules() throws Exception
    {
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine(new Facts());
        final RuleBuilder whenBankrupt = RuleBuilder
            .when(facts -> Integer.parseInt(facts.getFact("BankAccount")) < 0);
        
        rulesEngine.addRule(whenBankrupt
            .then(facts -> facts.addFact("CFO", "Fired")));
        rulesEngine.addRule(whenBankrupt
            .then(facts -> facts.addFact("Assets", "Sell")));
            
        assertEquals(2, rulesEngine.count());
    }
    
    /**
     * Creates a mock set of facts and rules and confirms that a rule is performed
     * correctly. 
     */
    @Test
    public void shouldExecuteOneRule() throws Exception
    {
        final Facts mockFacts = mock(Facts.class);
        final Rule mockRule = mock(Rule.class);
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine(mockFacts);
        
        rulesEngine.addRule(mockRule);
        rulesEngine.run();
        
        verify(mockRule).perform(mockFacts);
    }
    
    /**
     * Tests that the banckrupt example rule adds two facts to the environment of the
     * rules engine.
     */
    @Test
    public void shouldAddTwoFactsToEnvironment() {
        final Facts bankrupt = new Facts();
        bankrupt.addFact("BankAccount", "-1500000000");
        
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine(bankrupt);
        
        final RuleBuilder whenBankrupt = RuleBuilder
            .when(facts -> Integer.parseInt(facts.getFact("BankAccount")) < 0);
            
        rulesEngine.addRule(whenBankrupt
            .then(facts -> facts.addFact("CFO", "Fired")));
        rulesEngine.addRule(whenBankrupt
            .then(facts -> facts.addFact("Assets", "Sell")));
            
        rulesEngine.run();
            
        assertEquals("Fired", rulesEngine.getFacts().getFact("CFO"));
        assertEquals("Sell", rulesEngine.getFacts().getFact("Assets"));
    }
}