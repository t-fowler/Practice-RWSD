package twootr;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

// Dependencies
import org.bouncycastle.crypto.generators.SCrypt;

import static java.nio.charset.StandardCharsets.UTF_16;

/**
 * Security class for storing Twootr passwords in an encrypted form. Uses
 * SCrypt implementation from the BouncyCastle library.
 */
class KeyGenerator {
    private static final int SCRYPT_COST = 16384;
    private static final int SCRYPT_BLOCK_SIZE = 8;
    private static final int SCRYPT_PARALLELISM = 1;
    private static final int KEY_LENGTH = 20;

    private static final int SALT_LENGTH = 16;

    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Encrypts/Decrypts a Twootr password using the SCrypt library with the above hash parameters.
     *
     * @param password The password to be hashed.
     * @param salt The salt stored with the user's password.
     * @return The hashed value.
     */
    static byte[] hash(final String password, final byte[] salt) {
        final byte[] passwordBytes = password.getBytes(UTF_16);
        return SCrypt.generate(
            passwordBytes,
            salt,
            SCRYPT_COST,
            SCRYPT_BLOCK_SIZE,
            SCRYPT_PARALLELISM,
            KEY_LENGTH);
    }

    /**
     * Provides a random salt to store with a password. Each password should be given it's own salt
     * which prevents attackers from gathering information from similar (commonly used) passwords in
     * the database.
     *
     * @return A random salt value.
     */
    static byte[] newSalt() {
        final byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
