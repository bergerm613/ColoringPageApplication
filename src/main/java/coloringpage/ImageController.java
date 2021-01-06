package coloringpage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageController {

    private JLabel originalImageLabel;
    private JLabel finalImageLabel;
    private Boolean isURL;

    public ImageController(JLabel originalImageLabel, JLabel finalImageLabel) {
        this.originalImageLabel = originalImageLabel;
        this.finalImageLabel = finalImageLabel;
    }

    public void setImages(String path) {
            setOriginalImage(path);
    }

    public void setImages(URL url) {
        setOriginalImage(url);

    }

    private void setOriginalImage(String string){

    }

    private void setOriginalImage(URL url){
    //    URL url2 = https://assets.climatecentral.org/images/made/2020Snow-report-cover-image_1000_664_s_c1_c_c.jpg
        try{
            Image image = ImageIO.read(url);
            image = image.getScaledInstance(400,400, Image.SCALE_SMOOTH);
            originalImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error reading URL");
        }
    }
}
