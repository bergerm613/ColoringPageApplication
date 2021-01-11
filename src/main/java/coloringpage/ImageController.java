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

    public void setImages(File imageFile) { //if using local image
        setOriginalImage(imageFile);
        setFinalImage(getLineDrawing(imageFile));
    }

    public void setImages(URL url) {  //if using image from URL
        setOriginalImage(url);
        File file = URLtoFile(url);
        setFinalImage(getLineDrawing(file));
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
    private void setOriginalImage(File imageFile){
        try{
            Image image = ImageIO.read(imageFile);
            image = image.getScaledInstance(400,400, Image.SCALE_SMOOTH);
            originalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading Filepath.");
        }
    }

    private void setOriginalImage(URL url){
        try{
            Image image = ImageIO.read(url);
            image = image.getScaledInstance(400,400, Image.SCALE_SMOOTH);
            originalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading URL.");
        }
    }
}
