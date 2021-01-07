package coloringpage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Converter {

    private final int COLOR_MAX = 255;
    private final int COLOR_THRESHOLD = 240; //the lowest RGB number that will show in the final image

    public BufferedImage toLineDrawing(File inputFile) throws IOException {
        BufferedImage image = ImageIO.read(inputFile);

        BufferedImage grayImage = grayscaleImage(image);
        BufferedImage invertedAndBlurredImage = invertAndBlurImage(grayImage);

        return dodgeDivide(invertedAndBlurredImage, grayImage);
    }

    private BufferedImage grayscaleImage(BufferedImage image) {
        //https://www.tutorialspoint.com/java_dip/grayscale_conversion.htm

        int width = image.getWidth();
        int height = image.getHeight();

        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                Color c = new Color(image.getRGB(j, i));
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() * 0.114);

                int grayscaleColor = red + green + blue;
                Color newColor = new Color(grayscaleColor, grayscaleColor, grayscaleColor);

                image.setRGB(j,i, newColor.getRGB());
            }
        }
        return image;
    }

    private BufferedImage invertAndBlurImage(BufferedImage image) {
        BufferedImage newImage = cloneImage(image);
        invertImage(newImage);
        return blurImage(newImage);
    }

    private void invertImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                Color c = new Color(image.getRGB(j, i));
                int red = COLOR_MAX - c.getRed();
                int green = COLOR_MAX - c.getGreen();
                int blue = COLOR_MAX - c.getBlue();

                Color newColor = new Color(red, green, blue);
                image.setRGB(j, i, newColor.getRGB());
            }
        }
    }

    private BufferedImage blurImage(BufferedImage image) {
        /*
        Resources:
        https://www.javatips.net/api/java.awt.image.convolveop
        https://stackoverflow.com/questions/29295929/java-blur-image
        */

        int radius = 5;
        int size = radius * 5 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];

        Arrays.fill(data, weight);
        BufferedImageOp blurFilter = new ConvolveOp(new Kernel(size, size, data), ConvolveOp.EDGE_NO_OP, null);
        return blurFilter.filter(image, null);
    }

    /*
    * A blending mode, which divides the RGB values between two images
    * Conceptually described further here: https://photoshoptrainingchannel.com/blending-modes-explained/#divide
    */
    private BufferedImage dodgeDivide(BufferedImage front, BufferedImage back) {
        //https://www.freecodecamp.org/news/sketchify-turn-any-image-into-a-pencil-sketch-with-10-lines-of-code-cf67fa4f68ce/
        BufferedImage result = cloneImage(front);

        int width = front.getWidth();
        int height = front.getHeight();

        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                int frontValue = new Color(front.getRGB(j, i)).getRed(); //could be any of RGB, red is arbitrary
                int backValue = new Color(back.getRGB(j, i)).getRed();
                if(backValue == COLOR_MAX){
                    backValue--;
                }
                int newValue = (frontValue + 1) * COLOR_MAX / (COLOR_MAX - backValue);
                if (newValue > COLOR_THRESHOLD || backValue == COLOR_MAX) {
                    newValue = COLOR_MAX;
                } else {
                    newValue = 0;
                }

                Color newColor = new Color(newValue, newValue, newValue);
                result.setRGB(j, i, newColor.getRGB());
            }
        }

        return result;
    }

    private BufferedImage cloneImage(BufferedImage image) {
        //https://bytenota.com/java-cloning-a-bufferedimage-object/
        ColorModel colorModel = image.getColorModel();
        WritableRaster raster = image.copyData(null);
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}
