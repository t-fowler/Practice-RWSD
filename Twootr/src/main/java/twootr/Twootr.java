package twootr;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Twootr is a simple implementation of a mini-blogging platform. Users can register to publish
 * short messages--called twoots--that are seen by followers of the user.
 */
public class Twootr
{
    private final Set<User> users;
    private final Set<Twoot> twoots;
    private Position currentPosition;
    //private final UserRepository userRepository;
    //private final TwootRepository twootRepository;

    /**
     * Constructor for the Twootr object.
     */
    public Twootr()
    {
        this.users = new HashSet<>();
        this.twoots = new HashSet<>();
        this.currentPosition = Position.INITIAL_POSITION;
    }

    /*
    public Twootr(UserRepository userRepository, TwootRepository twootRepository) {
        this.userRepository = userRepository;
        this.twootRepository = twootRepository;
    }
    */

    /**
     * Adds a new user to the system if the user is not already registered.
     *
     * @param userId The requested userID for the new user.
     * @param password The user's requested password. Saved in a hashed/salted form.
     * @return A RegistrationStatus indicating whether the user was successfully added
     * or the user is a duplicate.
     */
    public RegistrationStatus onRegisterUser(final String userId, final String password)
    {
        byte[] salt = KeyGenerator.newSalt();
        byte[] hashedPassword = KeyGenerator.hash(password, salt);
        User user = new User(userId, hashedPassword, salt, Position.INITIAL_POSITION);
        return users.add(user) ? RegistrationStatus.SUCCESS : RegistrationStatus.DUPLICATE;
    }

    /**
     * First authenticates the user by checking the provided password. Returns an optional
     * SenderEndPoint if the log on was successfully performed.
     *
     * @param userId The provided userId.
     * @param password The provided password.
     * @param receiverEndPoint The GUI adapter for receiving push notifications from the Twootr system.
     * @return An Optional SenderEndPoint object that the client can use 
     */
    Optional<SenderEndPoint> 
        onLogon(final String userId, final String password, final ReceiverEndPoint receiverEndPoint)
    {
        Objects.requireNonNull(userId, "userId");
        Objects.requireNonNull(password, "password");

        List<User> authenticatedUser = users
            .stream()
            .filter(user -> {return userId == user.getId();})
            .filter(userOfSameId ->
            {
                byte[] hashedPassword = KeyGenerator.hash(password, userOfSameId.getSalt());
                return Arrays.equals(hashedPassword, userOfSameId.getPassword());
            })
            .collect(Collectors.toList());


        if (authenticatedUser.size() <= 0)
            return Optional.empty();

        authenticatedUser.get(0).onLogon(receiverEndPoint);
            
        twoots.stream()
            .filter(twoot -> twoot.getPosition().getValue() > authenticatedUser.get(0).getLastSeenPosition().getValue())
            .forEach(twoot -> authenticatedUser.get(0).receiveTwoot(twoot));

        return Optional.of(new SenderEndPoint(authenticatedUser.get(0), this));
    }

    /**
     * Adds the user to the follow list of otherUser if they are not already there.
     *
     * @param userId The Id of the user doing the following.
     * @param otherUserId the ID of the user to be followed.
     * @return A FollowStatus indicating success or the type of failure.
     */
    FollowStatus onFollow(final String userId, final String otherUserId)
    {
        List<User> user = this.users.stream()
            .filter(follower -> follower.getId() == userId)  // Users must have unique ID's for this to work.
            .collect(Collectors.toList());

        List<User> followedUser = users.stream()
            .filter(userToFollow -> userToFollow.getId() == otherUserId)
            .collect(Collectors.toList());

        if (followedUser.size() <= 0)
        {
            return FollowStatus.INVALID_USER;
        }

        return followedUser.get(0).addFollower(user.get(0));
    }

    /**
     * Pushes twoots from followed accounts out to users whey they log on. Only Twoots
     * that appear later in the stream than the user's last seen twoot.
     *
     * @param id The twoot's unique id.
     * @param user The user that is publishing a twoot.
     * @param content The content of the Twoot.
     */
    void onSendTwoot(final String id, final User user, final String content)
    {
        this.currentPosition = new Position(currentPosition.getValue() + 1);
        final String userId = user.getId();
        final Twoot twoot = new Twoot(id, userId, content, currentPosition);

        twoots.add(twoot);
        user.followers()
            .filter(User::isLoggedOn)
            .forEach(follower -> {follower.receiveTwoot(twoot);});
    }
}
