package twootr;

/**
 * A value object representing the position of a Twoot in the stream.
 */
public class Position {
    /**
     * Position before any tweets have been seen
     */
    public static final Position INITIAL_POSITION = new Position(-1);

    private final int value;

    /**
     * Constructor for a Position object.
     *
     * @param value The position of the twoot in the stream.
     */
    public Position(final int value) {
        this.value = value;
    }

    /**
     * Returns the value representing the position of the twoot in the stream.
     *
     * @return The value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a string representation of the position object.
     *
     * @return The string representation of this object.
     */
    @Override
    public String toString() {
        return "Position{" +
            "value=" + value +
            '}';
    }

    /**
     * Predicate for whether two positions are equal. 
     *
     * @param o Another object to compare with.
     * @return True if the positions are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Position position = (Position) o;

        return value == position.value;
    }

    /**
     * The position value is sufficient for a hash of this simple object.
     */
    @Override
    public int hashCode() {
        return value;
    }

    /**
     * Returns a new Position object that follows this one.
     *
     * @return The next position.
     */
    public Position next() {
        return new Position(value + 1);
    }
}