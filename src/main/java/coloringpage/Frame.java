package coloringpage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Frame extends JFrame {

    private JPanel topPanel;
    private JTextField pathField;

    private JPanel middlePanel;
    private final JLabel originalImageLabel = new JLabel();
    private final JLabel finalImageLabel = new JLabel();

    private JPanel bottomPanel;

    private final ImageController controller = new ImageController(originalImageLabel, finalImageLabel);
    private final  JFileChooser fileChooser = new JFileChooser();

    public Frame() {
        super();
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coloring Page Maker");
        setLayout(new BorderLayout());

        setTopPanel();
        setImagesPanel();
        setBottomPanel();


        add(topPanel, BorderLayout.PAGE_START);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void setTopPanel() {
        topPanel = new JPanel();
        JLabel imagePathLabel = new JLabel("Enter image url or browse:");
        imagePathLabel.setOpaque(true);

        pathField = new JTextField(30);

        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(this::browseFiles);


        JButton goButton = new JButton("Convert");
        goButton.addActionListener(evt -> {
            try {
                convertImage(evt);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });

        topPanel.add(imagePathLabel);
        topPanel.add(pathField);
        topPanel.add(browseButton);
        topPanel.add(goButton);
    }

    private void setImagesPanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1,2));
        middlePanel.add(originalImageLabel);
        middlePanel.add(finalImageLabel);
    }

    private void setBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        JButton saveButton = new JButton("Save As");
        saveButton.addActionListener(this::saveLineImage);

        bottomPanel.add(saveButton);
    }


    private void browseFiles(ActionEvent evt) {
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpeg", "jpg", "png"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            pathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void convertImage(ActionEvent evt) throws MalformedURLException {
        String s = pathField.getText().trim().toLowerCase();
        boolean isWeb = s.startsWith("http://") || s.startsWith("https://");

        if (isWeb) {
            URL url = new URL(pathField.getText());
            controller.setImages(url);
        } else {
            File imageFile = new File(pathField.getText());
            controller.setImages(imageFile);
        }

    }

    private void saveLineImage(ActionEvent evt) {
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage bufferedImage = controller.getFinalImage();
                File outputFile = fileChooser.getSelectedFile();
                ImageIO.write(bufferedImage, "jpg", outputFile);
            } catch (Exception e) {
                System.out.println("error saving image");
            }
        }
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setVisible(true);
    }
}
