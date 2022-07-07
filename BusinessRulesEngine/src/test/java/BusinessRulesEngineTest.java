package src.test.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import src.main.java.*;

/**
 * 
 */
public class BusinessRulesEngineTest
{
    /**
     * 
     */
    @Test
    public void shouldHaveNoRulesInitially() throws Exception
    {
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine();
        
        assertEquals(0, rulesEngine.count());
    }
    
    /**
     * 
     */
    @Test
    public void shouldAddTwoActions() throws Exception
    {
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine();
        
        rulesEngine.addAction(() -> {});
        rulesEngine.addAction(() -> {});
        assertEquals(2, rulesEngine.count());
    }
    
    /**
     * 
     */
    @Test
    public void shouldExecuteOneAction() throws Exception
    {
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine();
        final Action mockAction = mock(Action.class);
        
        rulesEngine.addAction(mockAction);
        rulesEngine.run();
        
        verify(mockAction).execute();
    }
    
    /**
     * 
     */
    @Test
    public void shouldExecuteActionWithFacts() {
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRulesEngine rulesEngine = new BusinessRulesEngine(mockFacts);
        
        rulesEngine.addAction(mockAction);
        rulesEngine.run();
        
        verify(mockAction).execute(mockFacts);
    }
}