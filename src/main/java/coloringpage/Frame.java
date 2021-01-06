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
    private JButton goButton;
    private JButton browseButton;

    private JLabel imagePathLabel;
    private JTextField pathField;

    private JPanel middlePanel;
    private JLabel originalImageLabel;
    private JLabel finalImageLabel;

    private JPanel bottomPanel;
    private JButton saveButton;

    final JFileChooser fileChooser = new JFileChooser();


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
        imagePathLabel = new JLabel("Enter image url or Browse:");
        imagePathLabel.setOpaque(true);

        pathField = new JTextField(30);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
        browseButton = new JButton("Browse");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });


        goButton = new JButton("Go");
        topPanel.add(imagePathLabel);
        topPanel.add(pathField);
        topPanel.add(browseButton);
        topPanel.add(goButton);
    }

    private void setImagesPanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1,2));
        originalImageLabel = new JLabel();
        finalImageLabel = new JLabel();
        middlePanel.add(originalImageLabel);
        middlePanel.add(finalImageLabel);

    }

    private void setBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        saveButton = new JButton("Save As");
        bottomPanel.add(saveButton);
    }


    private void browseActionPerformed(ActionEvent evt) {

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            pathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
        pathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
    }
}
