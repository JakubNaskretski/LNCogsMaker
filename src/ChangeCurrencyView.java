import javax.swing.*;
import java.awt.*;

public class ChangeCurrencyView {


        private JFrame mainFrame;
        private JButton confirmButton, cancelButton;
        private JPanel mainPanel, inputPanel, buttonsPanel;
        private JTextField plnPrice;
        private GridBagConstraints c;
        private JComboBox currencyChooserField;
        private String[] currencyOptions = {"EUR", "USD"};


        public ChangeCurrencyView(JFrame lockedWindow) {

            initiateWindow();
            lockedWindow.setEnabled(false);

            cancelButton.addActionListener(e -> {
                mainFrame.dispose();
                lockedWindow.setEnabled(true);
            });

        }

        private void initiateWindow(){

            mainFrame = new JFrame();
            mainFrame.setTitle("Change currency ratio");
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            c = new GridBagConstraints();
            inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(1,4, 5, 5));

            inputPanel.add(new JLabel("Ratio:"));
            currencyChooserField = new JComboBox(currencyOptions);
            currencyChooserField.setBackground(Color.WHITE);
            inputPanel.add(currencyChooserField);
            inputPanel.add(new JLabel(" equals"));
            plnPrice = new JTextField();
            inputPanel.add(plnPrice);
            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(1,2,5,5));

            c.gridwidth = 6;
            c.gridy = 1;
            c.gridx = 0;
            mainPanel.add(inputPanel, c);

            confirmButton = new JButton("Confirm");
            buttonsPanel.add(confirmButton);
            cancelButton = new JButton("Cancel");
            buttonsPanel.add(cancelButton);

            c.gridwidth = 2;
            c.gridy = 2;
            c.gridx = 3;
            mainPanel.add(buttonsPanel, c);

            mainFrame.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-150,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-40);
            mainFrame.getContentPane().add(mainPanel);
//            mainFrame.setPreferredSize(new Dimension(300, 80));
            mainFrame.pack();
            mainFrame.setVisible(true);
        }


}
