package coloringpage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Converter {
    public BufferedImage toLineDrawing(File inputFile) throws IOException {
        BufferedImage image = ImageIO.read(inputFile);

        int width = image.getWidth();
        int height = image.getHeight();

        for(int i=0; i<height; i++) {

            for(int j=0; j<width; j++) {

                Color c = new Color(image.getRGB(j, i));
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() *0.114);

                Color newColor = new Color(red+green+blue,

                        red+green+blue,red+green+blue);

                image.setRGB(j,i, (255- newColor.getRGB()));
            }
        }

        // Blur
        // Un - invert it
        // edge finder

        return image;
    }
}
