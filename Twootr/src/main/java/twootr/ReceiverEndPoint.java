package twootr;


/**
 * Defines the API used by clients to receive messages from the Twootr system.
 */
public interface ReceiverEndPoint
{
    /**
     * The implementation defines how to display received twoots.
     *
     * @param twoot The twoot being received.
     */
    void onReceiveTwoot(Twoot twoot);
}
