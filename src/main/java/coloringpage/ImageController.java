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
    private final Converter converter;

    private BufferedImage lineDrawing;

    public ImageController(Converter converter, JLabel originalImageLabel, JLabel finalImageLabel) {
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
        Image image = ImageIO.read(imageFile);
        setImages(image, imageFile);
    }

    public void setImages(URL url) throws IOException {  //if using image from URL
        File file = URLtoFile(url);
        Image image = ImageIO.read(file);
        setImages(image, file);
    }

    public void setImages(Image image, File imageFile) { //made for testing purposes
        setOriginalImage(image);
        setFinalImage(getLineDrawing(imageFile));
    }

    private File URLtoFile(URL url) {
        File file = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(url);
            file = new File("urlImage.jpg");
            ImageIO.write(bufferedImage, "jpg", file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not read URL to file.");
        }
        return file;
    }

    private BufferedImage getLineDrawing(File file) {
        try {
            lineDrawing = converter.toLineDrawing(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void setOriginalImage(Image image){
        try{
            image = image.getScaledInstance(400,400, Image.SCALE_SMOOTH);
            originalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading Filepath.");
        }
    }
}
