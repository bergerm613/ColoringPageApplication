package coloringpage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageController {

    private final JLabel originalImageLabel;
    private final JLabel finalImageLabel;
    private final ImageToOutlineConverter converter;

    private BufferedImage lineDrawing;

    public ImageController(ImageToOutlineConverter converter, JLabel originalImageLabel, JLabel finalImageLabel) {
        this.originalImageLabel = originalImageLabel;
        this.finalImageLabel = finalImageLabel;
        this.converter = converter;
    }

    public BufferedImage getFinalImage() {
        if (lineDrawing == null) {
            JOptionPane.showMessageDialog(null, "Line drawing image is null.");
        }
        return lineDrawing;
    }

    public void setImages(File imageFile) throws IOException { //if using local image
        BufferedImage image = ImageIO.read(imageFile);
        setImages(image);
    }

    public void setImages(URL url) throws IOException {  //if using image from URL\
        BufferedImage image = ImageIO.read(url);
        setImages(image);
    }

    public void setImages(BufferedImage image) { //made for testing purposes
        setOriginalImage(image);
        setFinalImage(getLineDrawing(image));
    }

    private BufferedImage getLineDrawing(BufferedImage image) {
        lineDrawing = converter.toLineDrawing(image);
        return lineDrawing;
    }

    private void setFinalImage(BufferedImage bufferedImage){
        try{
            Image image = bufferedImage.getScaledInstance(400,400, BufferedImage.SCALE_SMOOTH);
            finalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error setting final picture.");
        }
    }
    private void setOriginalImage(Image image) {
        try {
            image = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            originalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading Filepath.");
        }
    }
}
