package views;

import javax.swing.*;
import java.awt.*;

public class PopUpInfoView {

    private JFrame alertWindow;
    private JButton dispatchButton;
    private JPanel mainPanel;
    private JLabel displayedText;

    public PopUpInfoView(String textToDisplay, JFrame lockedWindow) {

        initiateWindow(textToDisplay);

        dispatchButton.addActionListener(e -> {
            alertWindow.dispose();
            lockedWindow.setEnabled(true);
        });

    }

    public PopUpInfoView(String textToDisplay) {

        initiateWindow(textToDisplay);

        dispatchButton.addActionListener(e -> {
            alertWindow.dispose();
        });

    }

    private void initiateWindow( String textToDisplay){

        alertWindow = new JFrame();
        Image icon = new ImageIcon("images\\LNIcon.png").getImage();
        alertWindow.setIconImage(icon);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));

        dispatchButton = new JButton("Ok");

        this.displayedText = new JLabel(textToDisplay);
        displayedText.setBounds(50,50,50,50);
        displayedText.setHorizontalAlignment(JLabel.CENTER);
        displayedText.setVerticalAlignment(JLabel.CENTER);
        displayedText.setFont(new Font("Verdana", Font.PLAIN, 14));
        mainPanel.add(displayedText);
        mainPanel.add(dispatchButton);

        alertWindow.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-150,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-40);
        alertWindow.getContentPane().add(mainPanel);
        alertWindow.setMinimumSize(new Dimension(300, 80));
        alertWindow.pack();
        alertWindow.setVisible(true);
    }

}
