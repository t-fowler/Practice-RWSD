package src.test.java;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import src.main.java.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Write a description of class TwootrTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TwootrTest
{
    private final ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);

    private Twootr twootr;
    private SenderEndPoint endPoint;
    
    @Before
    public void setUp()
    {
        //twootr = new Twootr(userRepository, twootRepository);

        //assertEquals(RegistrationStatus.SUCCESS, twootr.onRegisterUser(TestData.USER_ID, TestData.PASSWORD));
        //assertEquals(RegistrationStatus.SUCCESS, twootr.onRegisterUser(TestData.OTHER_USER_ID, TestData.PASSWORD));
    }
    
    @Test
    public void shouldBeAbleToAuthenticateUser()
    {
        logon();
    }
    
    @Test
    public void shouldNotAuthentidcateUserWithWrongPassword()
    {
        final Optional<SenderEndPoint> endPoint = twootr.onLogon(
            TestData.USER_ID, "bad password", receiverEndPoint);
            
        assertFalse(endPoint.isPresent());
    }
    
    private void logon()
    {
        this.endPoint = logon(TestData.USER_ID, receiverEndPoint);
    }
}
