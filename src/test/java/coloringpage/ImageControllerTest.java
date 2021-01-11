package coloringpage;

import org.junit.Test;

import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ImageControllerTest {

    @Test
    public void getFinalImage() {
        //given
        JLabel originalImageLabel = mock(JLabel.class);
        JLabel finalImageLabel = mock(JLabel.class);
        Converter converter = mock(Converter.class);
        ImageController controller = new ImageController(converter, originalImageLabel, finalImageLabel);

        //when
        controller.getFinalImage();

        //then


    }

    @Test
    public void setImages_withFile() throws IOException {
        JLabel originalImageLabel = mock(JLabel.class);
        JLabel finalImageLabel = mock(JLabel.class);
        Converter converter = mock(Converter.class);
        ImageController controller = new ImageController(converter, originalImageLabel, finalImageLabel);

        File file = mock(File.class);

        ImageIcon icon1 = mock(ImageIcon.class);




        ImageIcon icon2 = mock(ImageIcon.class);
        BufferedImage image2 = mock(BufferedImage.class);
        doReturn(image2).when(converter).toLineDrawing(file);
        doReturn(icon2).when(new ImageIcon(image2));

        //when
        controller.setImages(file);

        //then
        verify(originalImageLabel).setIcon(icon1);
        verify(finalImageLabel).setIcon(icon2);
    }

    @Test
    public void testSetImages_withURL() {
        JLabel originalImageLabel = mock(JLabel.class);
        JLabel finalImageLabel = mock(JLabel.class);
        ImageController controller = new ImageController(originalImageLabel, finalImageLabel);
        URL url = mock(URL.class);

        //when
        controller.setImages(url);

        //then


    }

    @Test
    public void testSetImages_badLink() {
        //given


        //when


        //then


    }
}