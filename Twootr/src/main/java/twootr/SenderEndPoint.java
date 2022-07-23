package twootr;

import java.util.Objects;
/**
 * Provides the API used by clients to send requests to the system. When a user logs on, the client
 * provides its receiver endpoint is returned with a SenderEndPoint on a successful logon.
 */
class SenderEndPoint
{
    private final User user;
    private final Twootr twootr;

    /**
     * Constructor for a SenderEndPoint.
     * @param user The user associated with the endpoint. Must not be null.
     * @param twootr The Twootr system. Must not be null.
     */
    SenderEndPoint(final User user, final Twootr twootr) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(twootr, "twootr");

        this.user = user;
        this.twootr = twootr;
    }

    /**
     * Requests the system to register user as following the user with the given ID.
     *
     * @param userIdToFollow The ID of the user to be followed. Must not be null.
     * @return The result of the of the follow request.
     */
    public FollowStatus onFollow(final String userIdToFollow) {
        Objects.requireNonNull(userIdToFollow, "userIdToFollow");

        return twootr.onFollow(user.getId(), userIdToFollow);
    }

    /**
     * Requests the system to publish a twoot from this user.
     *
     * @param id The twoot ID.
     * @param content The content of the twoot. Must not be null.
     */
    public void onSendTwoot(final String id, final String content) {
        Objects.requireNonNull(content, "content");

        twootr.onSendTwoot(id, user, content);
    }

    /**
     * Begins the user's log off procedure.
     */
    public void onLogoff() {
        user.onLogoff();
    }
}
