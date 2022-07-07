package src.main.java;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static src.main.java.Attributes.*;

/**
 * Importer for jpg images to the document management system.
 */
class ImageImporter implements Importer {
    /**
     * Creates a document with WIDTH, HEIGHT, and TYPE attributes from the given
     * jpg file.
     * 
     * @param file The jpg file to be imported.
     * 
     * @throws IOException An image read error.
     * 
     * @return A document created from the file.
     */
    @Override
    public Document importFile(final File file) throws IOException {
        final Map<String, String> attributes = new HashMap<>();
        attributes.put(PATH, file.getPath());

        final BufferedImage image = ImageIO.read(file);
        attributes.put(WIDTH, String.valueOf(image.getWidth()));
        attributes.put(HEIGHT, String.valueOf(image.getHeight()));
        attributes.put(TYPE, "IMAGE");

        return new Document(attributes);
    }
}