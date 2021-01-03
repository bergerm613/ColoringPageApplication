package coloringpage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    File input = new File("/Users/esty/Downloads/taffy.jpg");

    Converter converter = new Converter();
    BufferedImage lineDrawing;

    {
        try {
            lineDrawing = converter.toLineDrawing(input);

            File output = new File("line_drawing.jpg");
            ImageIO.write(lineDrawing, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
