package coloringpage;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

//https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html

public class Frame extends JFrame {

    private JPanel topPanel;
    private JTextField pathField;

    private JPanel middlePanel;
    private final JLabel originalImageLabel = new JLabel();
    private final JLabel finalImageLabel = new JLabel();

    private JPanel bottomPanel;

    private final  JFileChooser fileChooser = new JFileChooser();

    public Frame() {
        super();
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image to Coloring Page");
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
        JLabel imagePathLabel = new JLabel("Enter image url or Browse:");
        imagePathLabel.setOpaque(true);

        pathField = new JTextField(30);

        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });


        JButton goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    goActionPerformed(evt);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        bottomPanel.add(saveButton);
    }


    private void browseActionPerformed(ActionEvent evt) {
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpeg", "jpg", "png"));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            pathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void goActionPerformed(ActionEvent evt) throws MalformedURLException {
        ImageController controller = new ImageController(originalImageLabel, finalImageLabel);
        String s = pathField.getText().trim().toLowerCase();
        boolean isWeb = s.startsWith("http://") || s.startsWith("https://");
        if (isWeb) {
            URL url = new URL(pathField.getText());
            controller.setImages(url);
        } else {
            controller.setImages(pathField.getText());
        }

    }

    private void saveActionPerformed(ActionEvent evt) {
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
    }




}
