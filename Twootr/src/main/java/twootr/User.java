package twootr;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Stream;

import static twootr.FollowStatus.SUCCESS;
import static twootr.FollowStatus.ALREADY_FOLLOWING;

/**
 * A user object for the core to keep track of Twootr users, both logged on and off.
 * Users have a unique name/ID and their passwords are stored in salted hash form.Users
 * are registered with TwootrObject.onRegisterUser().
 */
public class User {
    private final String id;
    private final byte[] password;
    private final byte[] salt;
    private final Set<User> followers = new HashSet<>();
    private final Set<String> following = new HashSet<>();

    private Position lastSeenPosition;
    private ReceiverEndPoint receiverEndPoint;

    /**
     * Constructor for a User.
     *
     * @param id The user's id.
     * @param password The user's password.
     * @param salt The salt to store with the user's password.
     * @param lastSeenPosition The position of the user's last seen twoot.
     */
    public User(
        final String id,
        final byte[] password,
        final byte[] salt,
        final Position lastSeenPosition) {

        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(password, "password");

        this.id = id;
        this.password = password;
        this.salt = salt;
        this.lastSeenPosition = lastSeenPosition;
    }

    /**
     * Returns the user's encrypted password.
     *
     * @return The user's encrypted password.
     */
    public byte[] getPassword() {
        return password;
    }

    /**
     * Returns the salt used with the user's password.
     * 
     * @return The random salt used with the user's password.
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * Returns the user's unique ID.
     *
     * @return The user's unique ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Instructs the GUI to display a newly received twoot.
     *
     * @param The twoot to be received.
     * @return True if the twoot is successfully received, otherwise false.
     */
    boolean receiveTwoot(final Twoot twoot) {
        if (isLoggedOn()) {
            receiverEndPoint.onReceiveTwoot(twoot);
            lastSeenPosition = twoot.getPosition();
            return true;
        }

        return false;
    }

    /**
     * A predicate that returns whether the user is currently logged on to Twootr.
     *
     * @return True if the user is logged on, otherwise False.
     */
    boolean isLoggedOn() {
        return receiverEndPoint != null;
    }

    /**
     * Adds a user to the list of followers for this account.
     *
     * @param user The user to add as a follower.
     * @return FollowStatus indicating whether the follower was successfully added.
     */
    public FollowStatus addFollower(final User user) {
        if (followers.add(user)) {
            user.following.add(id);
            return SUCCESS;
        } else {
            return ALREADY_FOLLOWING;
        }
    }

    /**
     * Assigns the ReceiverEndPoint for the user.
     *
     * @param receiverEndPoint The client's endpoint for receiving messages from the system.
     */
    void onLogon(final ReceiverEndPoint receiverEndPoint) {
        this.receiverEndPoint = receiverEndPoint;
    }

    /**
     * Removes the client's receiverEndPoint.
     */
    void onLogoff() {
        receiverEndPoint = null;
    }

    /**
     * Returns the users followers as a Stream of Users.
     *
     * @return A stream of Users that are following this account.
     */
    Stream<User> followers() {
        return followers.stream();
    }

    /**
     * Returns the list of users that this account is following.
     *
     * @return The list of users that are this account is following.
     */
    Set<String> getFollowing() {
        return following;
    }

    /**
     * Returns the position of the last seen twoot by this user.
     *
     * @return The position of this user's last seen twoot.
     */
    public Position getLastSeenPosition() {
        return lastSeenPosition;
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            '}';
    }
}
