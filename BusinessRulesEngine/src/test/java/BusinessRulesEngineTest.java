package src.test.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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
     * Tests that EnhancedRules with names are created properly and that the 
     * BusinessRulesEngine accepts them properly.
     */
    @Test
    public void shouldAddTwoRulesWithNames() throws Exception
    {
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine(new Facts());
        final EnhancedRuleBuilder whenBankrupt = EnhancedRuleBuilder
            .when(Arrays.asList(facts -> Integer.parseInt(facts.getFact("BankAccount")) < 0));
        
        EnhancedRule malpractice = whenBankrupt
            .then(facts -> facts.addFact("CFO", "Fired"))
            .named("Malpractice", "An employee is being fired due to malpractice.");
        EnhancedRule sellOff = whenBankrupt
            .then(facts -> facts.addFact("Assets", "Sell"))
            .named("SellOff", "The company is forced to sell off assets to avoid going bankrupt.");
            
        assertEquals("Malpractice", malpractice.getName());
        assertEquals("An employee is being fired due to malpractice.", malpractice.getDescription());
        assertEquals("SellOff", sellOff.getName());
        assertEquals("The company is forced to sell off assets to avoid going bankrupt.", sellOff.getDescription());
            
        rulesEngine.addRule(malpractice);
        rulesEngine.addRule(sellOff);
            
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
     * Verifies that the EnhancedRule class is working executing an action
     * when a condition is positive.
     */
    @Test
    public void shouldExecuteEnhancedRuleOnce() throws Exception
    {
        final Facts factEnvironment = new Facts();
        factEnvironment.addFact("One", "1");
        factEnvironment.addFact("Two", "2");
        
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine(factEnvironment);
    
        final Action mockAction = mock(Action.class);
        final List<Condition> conditions = new ArrayList<>();
        conditions.add(facts -> facts.getFact("One") == "1");
        conditions.add(facts -> facts.getFact("Two") == "2");
        conditions.add(facts -> facts.getFact("Three") == "3");
        
        
        final Rule enhancedRule = EnhancedRuleBuilder
            .when(conditions)
            .then(mockAction)
            .named("Test", "A test rule.");
        
        rulesEngine.addRule(enhancedRule);
        rulesEngine.run();
        
        verify(mockAction).execute(factEnvironment);
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