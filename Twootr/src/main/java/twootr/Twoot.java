package twootr;

import java.util.Objects;

/**
 * Value object representing a Twoot. A twoot is a short message that is published to the system and
 * distributed to users who are following the twoot's creator.
 */
public final class Twoot {
    private final String id;
    private final String senderId;
    private final String content;
    private final Position position;

    /**
     * Constructor for a Twoot object.
     *
     * @param id The Twoot's unique ID.
     * @param senderID The user ID of the twoot's creator.
     * @param content The content of the Twoot.
     * @param position The position of the Twoot in the stream.
     */
    public Twoot(final String id, final String senderId, final String content, final Position position) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(senderId, "senderId");
        Objects.requireNonNull(content, "content");
        Objects.requireNonNull(position, "position");

        this.id = id;
        this.position = position;
        this.senderId = senderId;
        this.content = content;
    }

    /**
     *
     */
    //public boolean isAfter(final Position otherPosition) {
    //    return position.getValue() > otherPosition.getValue();
    //}

    /**
     * Returns the twoot's unique ID.
     *
     * @return The ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the user ID of the twoot's creator.
     *
     * @return The creator's user ID.
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Returns the content of the twoot.
     * 
     * @return The content of the twoot.
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the position of the twoot in the stream.
     *
     * @return The position of the twoot.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Determines if two twoot's are equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Twoot twoot = (Twoot) o;

        return id.equals(twoot.id);
    }

    /**
     * Returns a hash code of the Twoot's unique ID.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Returns a string representation of the twoot.
     *
     * @return The string representation of this object.
     */
    @Override
    public String toString() {
        return "Twoot{" +
            "id='" + id + '\'' +
            ", senderId='" + senderId + '\'' +
            ", content='" + content + '\'' +
            ", position=" + position +
            '}';
    }
}
