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
 * Write a description of class Twootr here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Twootr
{
    private final Set<User> users;
    private final Set<Twoot> twoots;
    private Position currentPosition;
    //private final UserRepository userRepository;
    //private final TwootRepository twootRepository;

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
     * 
     */
    public RegistrationStatus onRegisterUser(final String userId, final String password) {
        byte[] salt = KeyGenerator.newSalt();
        byte[] hashedPassword = KeyGenerator.hash(password, salt);
        User user = new User(userId, hashedPassword, salt, Position.INITIAL_POSITION);
        return users.add(user) ? RegistrationStatus.SUCCESS : RegistrationStatus.DUPLICATE;
    }

    /**
     * 
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
     * 
     */
    FollowStatus onFollow(final String userId, final String otherUserId)
    {
        List<User> user = users.stream()
            .filter(follower -> follower.getId() == userId)
            .collect(Collectors.toList());

        List<User> followedUser = users
            .stream()
            .filter(userToFollow -> userToFollow.getId() == otherUserId)
            .collect(Collectors.toList());

        if (followedUser.size() <= 0)
        {
            return FollowStatus.INVALID_USER;
        }

        return followedUser.get(0).addFollower(user.get(0));
    }

    /**
     * 
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
