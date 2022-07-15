package src.main.java;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
/**
 * Write a description of class Twootr here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Twootr
{
    Optional<SenderEndPoint> onLogon(String userID, ReceiverEndPoint receiver) throws Exception
    {
        return Optional.of(new SenderEndPoint());
    }
}
