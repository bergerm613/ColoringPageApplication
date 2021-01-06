package coloringpage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageController {

    private JLabel originalImageLabel;
    private JLabel finalImageLabel;

    public ImageController(JLabel originalImageLabel, JLabel finalImageLabel) {
        this.originalImageLabel = originalImageLabel;
        this.finalImageLabel = finalImageLabel;
    }

    public void setImages(String path) {
            setOriginalImage(path);

        Converter converter = new Converter();
        BufferedImage lineDrawing;

        try {
            lineDrawing = converter.toLineDrawing(new File(path));
            setFinalImage(lineDrawing);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setImages(URL url) {
        setOriginalImage(url);

        Converter converter = new Converter();
        BufferedImage lineDrawing;

/*        try {
  //          lineDrawing = converter.toLineDrawing(new File(path));
            setFinalImage(lineDrawing);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private void setFinalImage(BufferedImage image){
        try{
            //Image image = ImageIO.read(file);
            image = (BufferedImage) image.getScaledInstance(400,400, Image.SCALE_SMOOTH);
            finalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error reading Filepath");
        }
    }
    private void setOriginalImage(String string){
        try{
            Image image = ImageIO.read(new File(string));
            image = image.getScaledInstance(400,400, Image.SCALE_SMOOTH);
            originalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error reading Filepath");
        }
    }

    private void setOriginalImage(URL url){
        try{
            Image image = ImageIO.read(url);
            image = image.getScaledInstance(400,400, Image.SCALE_SMOOTH);
            originalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error reading URL");
        }
    }
}
