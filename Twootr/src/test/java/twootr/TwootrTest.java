package twootr;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



/**
 * A test class for the Twootr app.
 */
public class TwootrTest
{
    private final ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);

    private Twootr twootr;
    private SenderEndPoint endPoint;
    
    /**
     * Runs before each test and sets up a Twootr intance.
     */
    @Before
    public void setUp()
    {
        twootr = new Twootr();

        assertEquals(RegistrationStatus.SUCCESS, twootr.onRegisterUser(TestData.USER_ID, TestData.PASSWORD));
        assertEquals(RegistrationStatus.SUCCESS, twootr.onRegisterUser(TestData.OTHER_USER_ID, TestData.PASSWORD));
    }
    
    /**
     * Tests that a user is able to authenticate to the system.
     */
    @Test
    public void shouldBeAbleToAuthenticateUser()
    {
        logon();
    }
    
    /**
     * Tests that users can not log in with wrong passwords.
     */
    @Test
    public void shouldNotAuthentidcateUserWithWrongPassword()
    {
        final Optional<SenderEndPoint> endPoint = twootr.onLogon(
            TestData.USER_ID, "bad password", receiverEndPoint);
            
        assertFalse(endPoint.isPresent());
    }

    /**
     * Tests that a valid user can be followed.
     */
    @Test
    public void shouldFollowValidUser()
    {
        logon();
        final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);
        assertEquals(FollowStatus.SUCCESS, followStatus);
    }

    /**
     * Tests that a followed user cannot be followed by the same person.
     */
    @Test
    public void shouldNotDuplicateFollowValidUser()
    {
        logon();

        endPoint.onFollow(TestData.OTHER_USER_ID);

        final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);
        assertEquals(FollowStatus.ALREADY_FOLLOWING, followStatus);
    }

    /**
     * Tests that users are not able to follow invalid userIds.
     */
    @Test
    public void shouldNotFollowInvalidUser()
    {
        logon();

        final FollowStatus followStatus = endPoint.onFollow(TestData.NOT_A_USER);
        assertEquals(FollowStatus.INVALID_USER, followStatus);
    }

    /**
     * Tests that a user who is logged on will receive Twoots from followed users.
     */
    @Test
    public void shouldReceiveTwootsFromFollowedUser()
    {
        final String id = "1";

        logon();

        endPoint.onFollow(TestData.OTHER_USER_ID);

        final SenderEndPoint otherEndPoint = otherLogon();
        otherEndPoint.onSendTwoot(id, TestData.TWOOT);

        //verify(twootRepository).add(id, TestData.OTHER_USER_ID, TestData.TWOOT);
        verify(receiverEndPoint).onReceiveTwoot(new Twoot(id, TestData.OTHER_USER_ID, TestData.TWOOT, TestData.POSITION_1));
    }

    /**
     * Tests that a user can see followed twoots that occured after logging off.
     */
    @Test
    public void shouldReceiveReplayOfTwootsAfterLogoff()
    {
        final String id = "1";

        userFollowsOtherUser();

        final SenderEndPoint otherEndPoint = otherLogon();
        otherEndPoint.onSendTwoot(id, TestData.TWOOT);

        logon();

        verify(receiverEndPoint).onReceiveTwoot(TestData.twootAt(id, TestData.POSITION_1));
    }
    
    /**
     * Sets the environments user-side end point to a valid test user.
     */
    private void logon()
    {
        this.endPoint = logon(TestData.USER_ID, receiverEndPoint);
    }
    
    /**
     * Sets the environment's user-side endpoint to the other test user.
     */
    private SenderEndPoint otherLogon()
    {
        return logon(TestData.OTHER_USER_ID, mock(ReceiverEndPoint.class));
    }

    /**
     * Attempts to log on with a test user. Fails if unable to authenticate, otherwise
     * returns the user end point.
     * 
     * @return The user end point object.
     */
    private SenderEndPoint logon(final String userId, final ReceiverEndPoint receiverEndPoint)
    {
        final Optional<SenderEndPoint> endPoint = twootr.onLogon(userId, TestData.PASSWORD, receiverEndPoint);
        assertTrue("Failed to logon", endPoint.isPresent());
        return endPoint.get();
    }

    /**
     * 
     */
    private void userFollowsOtherUser()
    {
        logon();

        endPoint.onFollow(TestData.OTHER_USER_ID);

        endPoint.onLogoff();
    }
}
