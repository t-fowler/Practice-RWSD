package twootr;

/**
 * FollowStatus is returned from follow requests, indicating success or the type of failure that
 * occured. An exception could have been used, in which case the code architecture would be conveying
 * that such a request is not part of normal program execution. Enums allow for for more general
 * informational respones which could be normal execution or exceptional execution that needs to
 * be handled.
 */
public enum FollowStatus {
    SUCCESS,
    INVALID_USER,
    ALREADY_FOLLOWING
}
