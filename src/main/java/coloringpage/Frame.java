package coloringpage;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class Frame extends JFrame {

    private JPanel topPanel;
    private JButton goButton;

    private JLabel imagePathLabel;
    private JTextField pathField;

    private JPanel middlePanel;
    private JLabel originalImageLabel;
    private JLabel finalImageLabel;

    private JPanel bottomPanel;
    private JButton downloadButton;



    //input field    go button
    //view original image left    converted image right
    //download button

    public Frame() {
        super();
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Met Archives Search Tool");
        setLayout(new BorderLayout());

        setTopPanel();
        setImagesPanel();
        setBottomPanel();


        add(topPanel, BorderLayout.PAGE_START);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private void setTopPanel() {
        imagePathLabel = new JLabel("Enter image path:");
        imagePathLabel.setOpaque(true);

        pathField = new JTextField();
        pathField.setPreferredSize(new Dimension(160, 30));

        goButton = new JButton("Go");
        topPanel.add(downloadButton);

    }

    private void setImagesPanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1,2));
        originalImageLabel = new JLabel();
        finalImageLabel = new JLabel();
        originalImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        middlePanel.add(originalImageLabel);
        middlePanel.add(finalImageLabel);

    }

    private void setBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        downloadButton = new JButton("Download");
        bottomPanel.add(downloadButton);
    }

}
