package coloringpage;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.imageio.IIOException;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ImageControllerTest {

    @Test
    public void getFinalImage() throws IOException {
        JLabel originalImageLabel = mock(JLabel.class);
        JLabel finalImageLabel = mock(JLabel.class);
        ImageToOutlineConverter converter = mock(ImageToOutlineConverter.class);
        ImageController controller = new ImageController(converter, originalImageLabel, finalImageLabel);

        BufferedImage image = mock(BufferedImage.class);
        BufferedImage expectedImage = mock(BufferedImage.class);

        doReturn(expectedImage).when(converter).toLineDrawing(image);
        doReturn(image).when(expectedImage).getScaledInstance(400,400, Image.SCALE_SMOOTH);
        doReturn(image).when(image).getScaledInstance(400,400, Image.SCALE_SMOOTH);

        controller.setImages(image);

        //when
        BufferedImage actualImage = controller.getFinalImage();

        //then
        assertEquals(expectedImage, actualImage);
    }

    @Test
    public void getFinalImage_nullImage() {
        //given
        JLabel originalImageLabel = mock(JLabel.class);
        JLabel finalImageLabel = mock(JLabel.class);
        ImageToOutlineConverter converter = mock(ImageToOutlineConverter.class);
        ImageController controller = new ImageController(converter, originalImageLabel, finalImageLabel);

        //when
        controller.getFinalImage();

        //then
        verifyNoMoreInteractions(finalImageLabel);
    }


    @Test
    public void setImages() throws IOException {
        JLabel originalImageLabel = mock(JLabel.class);
        JLabel finalImageLabel = mock(JLabel.class);
        ImageToOutlineConverter converter = mock(ImageToOutlineConverter.class);
        ImageController controller = new ImageController(converter, originalImageLabel, finalImageLabel);

        BufferedImage image = mock(BufferedImage.class);
        BufferedImage expectedImage = mock(BufferedImage.class);

        doReturn(expectedImage).when(converter).toLineDrawing(image);
        doReturn(image).when(expectedImage).getScaledInstance(400,400, Image.SCALE_SMOOTH);
        doReturn(image).when(image).getScaledInstance(400,400, Image.SCALE_SMOOTH);

        ArgumentCaptor<ImageIcon> iconCaptor = ArgumentCaptor.forClass(ImageIcon.class);

        //when
        controller.setImages(image);

        //then
        verify(originalImageLabel).setIcon(iconCaptor.capture());
        ImageIcon iconCaptorValue = iconCaptor.getValue();
        assertEquals(ImageIcon.class, iconCaptorValue.getClass());

        verify(finalImageLabel).setIcon(iconCaptor.capture());
        iconCaptorValue = iconCaptor.getValue();
        assertEquals(ImageIcon.class, iconCaptorValue.getClass());
    }

    @Test (expected = IIOException.class)
    public void testSetImages_badLink() throws IOException {
        JLabel originalImageLabel = mock(JLabel.class);
        JLabel finalImageLabel = mock(JLabel.class);
        ImageToOutlineConverter converter = mock(ImageToOutlineConverter.class);
        ImageController controller = new ImageController(converter, originalImageLabel, finalImageLabel);
        URL badURL = new URL("http://notarealurlhimom.html");

        //when
        controller.setImages(badURL);
    }
}
