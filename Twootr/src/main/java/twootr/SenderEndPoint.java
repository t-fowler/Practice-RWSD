package twootr;

import java.util.Objects;
/**
 * Write a description of class SenderEndPoint here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
class SenderEndPoint
{
    private final User user;
    private final Twootr twootr;

    /**
     * 
     */
    SenderEndPoint(final User user, final Twootr twootr) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(twootr, "twootr");

        this.user = user;
        this.twootr = twootr;
    }

    /**
     * 
     */
    public FollowStatus onFollow(final String userIdToFollow) {
        Objects.requireNonNull(userIdToFollow, "userIdToFollow");

        return twootr.onFollow(user.getId(), userIdToFollow);
    }

    /**
     * 
     */
    public void onSendTwoot(final String id, final String content) {
        Objects.requireNonNull(content, "content");

        twootr.onSendTwoot(id, user, content);
    }

    /**
     * 
     */
    public void onLogoff() {
        user.onLogoff();
    }
}
