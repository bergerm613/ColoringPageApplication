package coloringpage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
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

        //     Resource:   http://www.jhlabs.com/ip/blurring.html
        float[] matrix = new float[400];
        for (int i = 0; i < 400; i++) {
            matrix[i] = 1.0f / 400.0f;
        }

        int blurRad = 300;
        float[] blurKernel = new float[blurRad*blurRad];
        for(int i =0; i<blurRad*blurRad; i++) {
            blurKernel[i] = 1.0f/256.0f;
        }


        BufferedImage blurryImg = processImage(image);

        return blurryImg;
    }
    public  BufferedImage processImage(BufferedImage image) {
        /*
        Resources:
        https://www.javatips.net/api/java.awt.image.convolveop
        https://stackoverflow.com/questions/29295929/java-blur-image
        * */
        int radius = 20;
        int size = radius * 2 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];

        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }
        BufferedImageOp blurFilter = new ConvolveOp(new Kernel(size, size, data), ConvolveOp.EDGE_ZERO_FILL, null);
        return blurFilter.filter(image, null);
    }
}
