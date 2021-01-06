package coloringpage;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html

public class Frame extends JFrame {

    private JPanel topPanel;
    private JTextField pathField;

    private JPanel middlePanel;
    private final JLabel originalImageLabel = new JLabel();
    private final JLabel finalImageLabel = new JLabel();

    private JPanel bottomPanel;

    private final  JFileChooser fileChooser = new JFileChooser();


    //input field    go button
    //view original image left    converted image right
    //download button

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
                goActionPerformed(evt);
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

    private void goActionPerformed(ActionEvent evt) {
        ImageController controller = new ImageController(originalImageLabel, finalImageLabel);
        controller.setImages(pathField.getText());
    }

    private void saveActionPerformed(ActionEvent evt) {
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
    }




}
