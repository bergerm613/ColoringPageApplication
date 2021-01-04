package coloringpage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File input = new File("cookies.jpg");


        Converter converter = new Converter();
        BufferedImage lineDrawing;
        Frame frame = new Frame();
        frame.setVisible(true);

/*        try {
            lineDrawing = converter.toLineDrawing(input);

            File output = new File("line_drawing.jpg");
            ImageIO.write(lineDrawing, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    }
}
