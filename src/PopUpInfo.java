import javax.swing.*;
import java.awt.*;

public class PopUpInfo {

    private JFrame alertWindow;
    private JButton dispatchButton;
    private JPanel mainPanel;

    public PopUpInfo(String textToDisplay, JFrame lockedWindow) {

        initiateWindow(textToDisplay);

        dispatchButton.addActionListener(e -> {
            alertWindow.dispose();
            lockedWindow.setEnabled(true);
        });

    }

    public PopUpInfo(String textToDisplay) {

        initiateWindow(textToDisplay);

        dispatchButton.addActionListener(e -> {
            alertWindow.dispose();
        });

    }

    private void initiateWindow( String textToDisplay){

        alertWindow = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,1));

        dispatchButton = new JButton("Ok");

        mainPanel.add(new JLabel(textToDisplay));
        mainPanel.add(dispatchButton);

        alertWindow.getContentPane().add(mainPanel);

        alertWindow.setPreferredSize(new Dimension(300, 200));

        alertWindow.pack();
        alertWindow.setVisible(true);
    }

}
