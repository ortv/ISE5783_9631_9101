package unittests.unittests.renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Color;
import renderer.ImageWriter;

class ImageWriterTests {

	 /**
     * A test to test write to image function.
     */
    @Test
    void writeToImageTest() {
        ImageWriter imageWriter = new ImageWriter("test", 800, 500);
        for (int i = 0; i < 800; i++)
            for (int j = 0; j < 500; j++) {
                if ((i % 50 == 0) || (j % 50 == 0))
                    imageWriter.writePixel(i, j, new Color(0, 0, 0));
                else imageWriter.writePixel(i, j, new Color(204, 255, 153));
            }

        imageWriter.writeToImage();
    }
}
